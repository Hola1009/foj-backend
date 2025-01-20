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
import com.fancier.foj.system.domain.admin.Admin;
import com.fancier.foj.system.domain.admin.dto.AdminDTO;
import com.fancier.foj.system.mapper.AdminMapper;
import com.fancier.foj.system.service.AdminService;
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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService {

    private final AdminMapper adminMapper;

    private final TokenService tokenService;

    @Override
    public Result login(AdminDTO userLogin) {
        String account = userLogin.getAccount();
        String password = userLogin.getPassword();

        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<Admin>()
                .select(Admin::getPassword, Admin::getId, Admin::getUsername)
                .eq(StringUtils.isNotBlank(account), Admin::getAccount, account);

        Admin admin = adminMapper.selectOne(queryWrapper);

        // 未查询到用户
        ThrowUtils.throwIf(Objects.isNull(admin), new BusinessException(ResultCode.FAILED_LOGIN));

        // 密码校验未通过
        ThrowUtils.throwIf(!BCryptUtils.matchesPassword(password, admin.getPassword()),
                new BusinessException(ResultCode.FAILED_LOGIN));

        String token = tokenService.createToken(admin.getId(), admin.getUsername(), UserIdentity.ADMIN.getValue());

        return Result.success(token);
    }

    @Override
    public Result getUserinfo(String token) {
        LoginUser userinfo = tokenService.getUserinfo(token);
        LoginUserVO loginUserVO = new LoginUserVO();
        BeanUtil.copyProperties(userinfo, loginUserVO);
        return Result.success(loginUserVO);
    }

    @Override
    public Boolean logout(String token) {
        return tokenService.removeLoginUser(token);
    }

}




