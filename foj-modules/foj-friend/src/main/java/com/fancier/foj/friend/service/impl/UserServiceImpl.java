package com.fancier.foj.friend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fancier.foj.common.core.constant.CacheConstants;
import com.fancier.foj.common.core.constant.HttpConstants;
import com.fancier.foj.common.core.constant.enums.ResultCode;
import com.fancier.foj.common.core.constant.enums.UserIdentity;
import com.fancier.foj.common.core.domain.dto.LoginUser;
import com.fancier.foj.common.core.domain.vo.LoginUserVO;
import com.fancier.foj.common.core.utils.UserHolder;
import com.fancier.foj.common.redis.service.RedisService;
import com.fancier.foj.common.security.exception.BusinessException;
import com.fancier.foj.common.security.service.TokenService;
import com.fancier.foj.common.security.utils.ThrowUtils;
import com.fancier.foj.friend.domain.user.User;
import com.fancier.foj.friend.domain.user.dto.UserDTO;
import com.fancier.foj.friend.domain.user.dto.UserUpdateDTO;
import com.fancier.foj.friend.domain.user.vo.UserVO;
import com.fancier.foj.friend.mapper.UserMapper;
import com.fancier.foj.friend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
* @author Fancier
* @description 针对表【tb_user(普通用户表)】的数据库操作Service实现
* @createDate 2025-02-03 15:31:33
*/
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    private final RedisService redisService;

    private final TokenService tokenService;

    private final UserMapper userMapper;

    @Value("${sms.send-limit:3}")
    private Integer sendLimit;

    /**
     * 验证码有效期
     * 单位: 分钟
     */
    @Value("${sms.code-expiration:5}")
    private Long phoneCodeExpiration;


    @Override
    public boolean sendCode(UserDTO dto) {
        // 校验手机号
        ThrowUtils.throwIf(Validator.isMobile(dto.getPhone()),
                new BusinessException(ResultCode.FAILED_USER_PHONE));

        String phoneCodeKey = CacheConstants.PHONE_CODE_KEY + dto.getPhone();
        // 判断是否操作有效 避免频繁操作
        Long expire = redisService.getExpire(phoneCodeKey, TimeUnit.SECONDS);
        ThrowUtils.throwIf(Objects.nonNull(expire) && (phoneCodeExpiration * 60 - expire) < 60,
                new BusinessException(ResultCode.FAILED_FREQUENT));

        // 获取当天请求验证码的次数
        String codeTimeKey = CacheConstants.CODE_TIME_KEY + dto.getPhone();

        // 判断是否已经超过发送次数限制
        Long sendTimes = redisService.getCacheObject(codeTimeKey, Long.class);
        ThrowUtils.throwIf(Objects.nonNull(sendTimes) && sendTimes >= sendLimit,
                new BusinessException(ResultCode.FAILED_TIME_LIMIT));

        // 生成验证码
//        String code = RandomUtil.randomNumbers(6);
        String code = "123456";
        // 存储验证码到 redis
        redisService.setCacheObject(phoneCodeKey, code, phoneCodeExpiration, TimeUnit.MINUTES);

        // TODO: 发送验证码

        // 记录发送次数
        redisService.increment(codeTimeKey);

        if (Objects.isNull(sendTimes)) { // 当天第一次获取验证码
            //计算验证码的有效期
            long seconds = ChronoUnit.SECONDS.between(LocalDateTime.now(),
                    LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0));
            redisService.expire(codeTimeKey, seconds, TimeUnit.SECONDS);
        }

        return true;
    }

    @Override
    public String codeLogin(String phone, String code) {
        checkCode(phone, code);

        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                        .select(User::getId, User::getUsername)
                .eq(User::getPhone, phone));

        // 如果是新用户则进行注册
        if (Objects.isNull(user)) {
            user = new User();
            user.setPhone(phone);
            userMapper.insert(user);
        }

        return tokenService.createToken(user.getId(), user.getUsername(),
                UserIdentity.ORDINARY.getValue());
    }

    @Override
    public boolean logout(String token) {
        if (StrUtil.isNotEmpty(token) && token.startsWith(HttpConstants.PREFIX)) {
            token = token.substring(HttpConstants.PREFIX.length());
        }
        return tokenService.removeLoginUser(token);
    }

    @Override
    public LoginUserVO info(String token) {
        if (StrUtil.isNotEmpty(token) && token.startsWith(HttpConstants.PREFIX)) {
            token = token.substring(HttpConstants.PREFIX.length());
        }
        LoginUser userinfo = tokenService.getUserinfo(token);
        ThrowUtils.throwIf(Objects.isNull(userinfo), new BusinessException(ResultCode.FAILED_USER_NOT_EXISTS));

        LoginUserVO loginUserVO = new LoginUserVO();

        BeanUtil.copyProperties(userinfo, loginUserVO);

        return loginUserVO;
    }

    @Override
    public UserVO detail() {
        Long userId = UserHolder.get();
        ThrowUtils.throwIf(Objects.isNull(userId), new BusinessException(ResultCode.FAILED_USER_NOT_EXISTS));

        User user = userMapper.selectById(userId);
        ThrowUtils.throwIf(Objects.isNull(user), new BusinessException(ResultCode.FAILED_USER_NOT_EXISTS));

        UserVO userVO = new UserVO();
        BeanUtil.copyProperties(user, userVO);

        return userVO;
    }

    @Override
    public Boolean edit(UserUpdateDTO dto) {
        Long userId = UserHolder.get();
        ThrowUtils.throwIf(Objects.isNull(userId), new BusinessException(ResultCode.FAILED_USER_NOT_EXISTS));

        User user = new User();
        BeanUtil.copyProperties(dto, user);
        user.setId(userId);

        tokenService.refreshLoginUser(dto.getUsername(), userId.toString());

        return userMapper.updateById(user) > 0;
    }

    private void checkCode(String phone, String code) {
        String phoneCodeKey = CacheConstants.CODE_TIME_KEY + phone;
        String cacheCode = redisService.getCacheObject(phoneCodeKey, String.class);

        ThrowUtils.throwIf(StrUtil.isEmpty(cacheCode) || !cacheCode.equals(code),
                new BusinessException(ResultCode.FAILED_INVALID_CODE));

        //验证码比对成功
        redisService.deleteObject(phoneCodeKey);
    }
}




