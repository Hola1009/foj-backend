package com.fancier.foj.system.mapper;

import com.fancier.foj.system.domain.user.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fancier.foj.system.domain.user.dto.UserQueryDTO;
import com.fancier.foj.system.domain.user.vo.UserVO;

import java.util.List;

/**
* @author Fancier
* @description 针对表【tb_user(普通用户表)】的数据库操作Mapper
* @createDate 2025-01-31 10:25:33
* @Entity generator.domain.User
*/
public interface UserMapper extends BaseMapper<User> {

    List<UserVO> getUserList(UserQueryDTO dto);
}




