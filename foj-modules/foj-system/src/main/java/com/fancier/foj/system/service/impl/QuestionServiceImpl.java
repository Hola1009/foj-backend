package com.fancier.foj.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fancier.foj.system.domain.question.Question;
import com.fancier.foj.system.domain.question.dto.QuestionQueryDTO;
import com.fancier.foj.system.domain.question.vo.QuestionVO;
import com.fancier.foj.system.mapper.QuestionMapper;
import com.fancier.foj.system.service.QuestionService;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Fanfan
* @description 针对表【tb_question(题目表)】的数据库操作Service实现
* @createDate 2025-01-21 15:57:50
*/
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService {

    private final QuestionMapper questionMapper;

    /**
     * 对问题进行分页查询
     *
     */
    @Override
    public List<QuestionVO> getList(QuestionQueryDTO questionQueryDTO) {
        // 为 pagehelper 提供页码和每页记录数
        PageHelper.startPage(questionQueryDTO.getPageNum(), questionQueryDTO.getPageSize());

        return questionMapper.selectQuestionList(questionQueryDTO.getTitle(),
                questionQueryDTO.getDifficulty());
    }
}




