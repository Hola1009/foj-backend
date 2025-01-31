package com.fancier.foj.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fancier.foj.system.domain.user.User;
import com.fancier.foj.system.domain.user.dto.UserQueryDTO;
import com.fancier.foj.system.domain.user.vo.UserVO;

import java.util.List;

/**
* @author Fancier
* @description 针对表【tb_user(普通用户表)】的数据库操作Service
* @createDate 2025-01-31 10:25:33
*/
public interface UserService extends IService<User> {
    List<UserVO> getList(UserQueryDTO dto);
}
