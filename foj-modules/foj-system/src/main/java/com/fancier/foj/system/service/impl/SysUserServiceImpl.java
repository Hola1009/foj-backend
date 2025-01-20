package com.fancier.foj.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fancier.foj.common.core.constant.enums.ResultCode;
import com.fancier.foj.common.core.constant.enums.UserIdentity;
import com.fancier.foj.common.core.domain.dto.LoginUser;
import com.fancier.foj.common.core.domain.vo.LoginUserVO;
import com.fancier.foj.common.core.domain.vo.Result;
import com.fancier.foj.common.security.exception.BusinessException;
import com.fancier.foj.common.security.service.TokenService;
import com.fancier.foj.common.security.utils.ThrowUtils;
import com.fancier.foj.system.domain.sysUser.SysUser;
import com.fancier.foj.system.domain.sysUser.dto.SysUserDTO;
import com.fancier.foj.system.mapper.SysUserMapper;
import com.fancier.foj.system.service.SysUserService;
import com.fancier.foj.system.utils.BCryptUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
* @author Fancier
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2025-01-05 21:50:22
*/
@Service
@RefreshScope
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService {

    private final SysUserMapper sysUserMapper;

    private final TokenService tokenService;

    @Override
    public Result login(SysUserDTO userLogin) {
        String account = userLogin.getAccount();
        String password = userLogin.getPassword();

        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<SysUser>()
                .select(SysUser::getPassword, SysUser::getId, SysUser::getUsername)
                .eq(StringUtils.isNotBlank(account), SysUser::getAccount, account);

        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);

        // 未查询到用户
        ThrowUtils.throwIf(Objects.isNull(sysUser), new BusinessException(ResultCode.FAILED_LOGIN));

        // 密码校验未通过
        ThrowUtils.throwIf(!BCryptUtils.matchesPassword(password, sysUser.getPassword()),
                new BusinessException(ResultCode.FAILED_LOGIN));

        String token = tokenService.createToken(sysUser.getId(), sysUser.getUsername(), UserIdentity.ADMIN.getValue());

        return Result.success(token);
    }

    @Override
    public Result getUserinfo(String token) {
        LoginUser userinfo = tokenService.getUserinfo(token);
        LoginUserVO loginUserVO = new LoginUserVO();
        BeanUtil.copyProperties(userinfo, loginUserVO);
        return Result.success(loginUserVO);
    }

}




