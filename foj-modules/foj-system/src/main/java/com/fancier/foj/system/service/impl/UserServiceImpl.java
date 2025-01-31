package com.fancier.foj.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fancier.foj.system.domain.user.User;
import com.fancier.foj.system.domain.user.dto.UserQueryDTO;
import com.fancier.foj.system.domain.user.vo.UserVO;
import com.fancier.foj.system.mapper.UserMapper;
import com.fancier.foj.system.service.UserService;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Fancier
* @description 针对表【tb_user(普通用户表)】的数据库操作Service实现
* @createDate 2025-01-31 10:25:33
*/
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    private final UserMapper userMapper;

    @Override
    public List<UserVO> getList(UserQueryDTO dto) {
        // 为 pagehelper 提供页码和每页记录数
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());

        return userMapper.getUserList(dto);
    }
}




