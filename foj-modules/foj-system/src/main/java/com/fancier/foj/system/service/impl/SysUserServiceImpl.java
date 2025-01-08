package com.fancier.foj.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fancier.foj.common.core.domain.Result;
import com.fancier.foj.common.core.enums.ResultCode;
import com.fancier.foj.common.core.exception.BusinessException;
import com.fancier.foj.common.core.utils.ThrowUtils;
import com.fancier.foj.system.domain.sysUser.SysUser;
import com.fancier.foj.system.domain.sysUser.SysUserDTO;
import com.fancier.foj.system.mapper.SysUserMapper;
import com.fancier.foj.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
* @author Fancier
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2025-01-05 21:50:22
*/
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService {
    private final SysUserMapper sysUserMapper;
    @Override
    public Result login(SysUserDTO userLogin) {
        String account = userLogin.getAccount();
        String password = userLogin.getPassword();

        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<SysUser>()
                .select(SysUser::getAccount, SysUser::getPassword)
                .eq(StringUtils.isNotBlank(account), SysUser::getAccount, account)
                .eq(StringUtils.isNotBlank(password), SysUser::getPassword, password);

        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);

        ThrowUtils.throwIf(Objects.isNull(sysUser), new BusinessException(ResultCode.FAILED_LOGIN));

        return Result.success();
    }

}




