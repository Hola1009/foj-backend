package com.fancier.foj.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fancier.foj.system.domain.exam.ExamQuestion;
import com.fancier.foj.system.mapper.ExamQuestionMapper;
import com.fancier.foj.system.service.ExamQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Fancier
* @description 针对表【tb_exam_question(竞赛题目表)】的数据库操作Service实现
* @createDate 2025-01-25 07:17:07
*/
@Service
@RequiredArgsConstructor
public class ExamQuestionServiceImpl extends ServiceImpl<ExamQuestionMapper, ExamQuestion>
    implements ExamQuestionService {

    @Override
    public List<Long> getByExamId(Long id) {
        List<ExamQuestion> examQuestions = list(new LambdaQueryWrapper<ExamQuestion>()
                .eq(ExamQuestion::getExamId, id));

        return examQuestions.stream()
                .map(ExamQuestion::getQuestionId).toList();
    }
}




