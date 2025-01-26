package com.fancier.foj.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fancier.foj.common.core.constant.enums.ResultCode;
import com.fancier.foj.common.security.utils.ThrowUtils;
import com.fancier.foj.system.domain.question.Question;
import com.fancier.foj.system.domain.question.dto.QuestionDTO;
import com.fancier.foj.system.domain.question.dto.QuestionQueryDTO;
import com.fancier.foj.system.domain.question.vo.QuestionVO;
import com.fancier.foj.system.mapper.QuestionMapper;
import com.fancier.foj.system.service.QuestionService;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    @Override
    public Boolean edit(QuestionDTO questionDTO) {
        Question question = getById(questionDTO.getId());
        ThrowUtils.throwIf(Objects.isNull(question), ResultCode.FAILED_NOT_EXISTS);

        // 如果标题不为空，并且与数据库中的标题不一致，则校验题目是否存在
        if (StrUtil.isNotEmpty(questionDTO.getTitle())
                && !question.getTitle().equals(questionDTO.getTitle())) {
            validate(questionDTO);
        }

        BeanUtil.copyProperties(questionDTO, question);

        return updateById(question);
    }

    /**
     * 根据标题校验题目是否存在
     */
    @Override
    public void validate(QuestionDTO questionDTO) {
        LambdaQueryWrapper<Question> wrapper = new LambdaQueryWrapper<Question>()
                .eq(Question::getTitle, questionDTO.getTitle().trim());
        // 校验题目是否存在
        Question one = getOne(wrapper);

        if (Objects.nonNull(one) && one.getId().equals(questionDTO.getId())) {
            return;
        }
        ThrowUtils.throwIf(Objects.nonNull(one), ResultCode.FAILED_ALREADY_EXISTS);
    }
}




