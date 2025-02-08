package com.fancier.foj.friend.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fancier.foj.friend.domain.exam.vo.ExamVO;
import com.fancier.foj.friend.domain.user.UserExam;

import java.util.List;


/**
* @author Fancier
* @description 针对表【tb_user_exam】的数据库操作Mapper
* @createDate 2025-02-03 11:06:12
* @Entity generator.domain.UserExam
*/
public interface UserExamMapper extends BaseMapper<UserExam> {

    List<ExamVO> selectUserExamList();
}




