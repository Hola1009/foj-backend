package com.fancier.foj.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.fancier.foj.common.core.domain.vo.Result;
import com.fancier.foj.system.domain.sysUser.SysUser;
import com.fancier.foj.system.domain.sysUser.dto.SysUserDTO;

/**
* @author Fancier
* @description 针对表【sys_user(用户表)】的数据库操作Service
* @createDate 2025-01-05 21:50:22
*/
public interface SysUserService extends IService<SysUser> {
    Result login(SysUserDTO userLogin);

}
