package com.fancier.foj.friend.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.fancier.foj.common.core.domain.vo.PageResult;
import com.fancier.foj.friend.domain.exam.dto.ExamDTO;
import com.fancier.foj.friend.domain.exam.dto.ExamQueryDTO;
import com.fancier.foj.friend.domain.user.UserExam;


/**
* @author Fancier
* @description 针对表【tb_user_exam】的数据库操作Service
* @createDate 2025-02-03 11:06:12
*/
public interface UserExamService extends IService<UserExam> {

    Boolean enter(String token, ExamDTO examDTO);

    PageResult list(ExamQueryDTO examQueryDTO);
}
