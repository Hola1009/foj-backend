package com.fancier.foj.friend.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.fancier.foj.common.core.domain.vo.LoginUserVO;
import com.fancier.foj.friend.domain.user.User;
import com.fancier.foj.friend.domain.user.dto.UserDTO;
import com.fancier.foj.friend.domain.user.dto.UserUpdateDTO;
import com.fancier.foj.friend.domain.user.vo.UserVO;

/**
* @author Fancier
* @description 针对表【tb_user(普通用户表)】的数据库操作Service
* @createDate 2025-02-03 15:31:33
*/
public interface UserService extends IService<User> {
    boolean sendCode(UserDTO dto);

    String codeLogin(String phone, String code);

    boolean logout(String token);

    LoginUserVO info(String token);

    UserVO detail();

    Boolean edit(UserUpdateDTO dto);
}
