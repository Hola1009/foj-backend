package com.fancier.foj.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.fancier.foj.common.core.domain.vo.Result;
import com.fancier.foj.system.domain.admin.Admin;
import com.fancier.foj.system.domain.admin.dto.AdminDTO;

/**
* @author Fancier
* @description 针对表【sys_user(用户表)】的数据库操作Service
* @createDate 2025-01-05 21:50:22
*/
public interface AdminService extends IService<Admin> {
    Result login(AdminDTO userLogin);

    /**
     * 获取用户信息
     */
    Result getUserinfo(String token);

    /**
     * 退出登录
     */
    Boolean logout(String token);
}
