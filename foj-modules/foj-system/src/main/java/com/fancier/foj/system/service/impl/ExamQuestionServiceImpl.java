package com.fancier.foj.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fancier.foj.system.domain.exam.ExamQuestion;
import com.fancier.foj.system.mapper.ExamQuestionMapper;
import com.fancier.foj.system.service.ExamQuestionService;
import org.springframework.stereotype.Service;

/**
* @author Fancier
* @description 针对表【tb_exam_question(竞赛题目表)】的数据库操作Service实现
* @createDate 2025-01-25 07:17:07
*/
@Service
public class ExamQuestionServiceImpl extends ServiceImpl<ExamQuestionMapper, ExamQuestion>
    implements ExamQuestionService {

}




