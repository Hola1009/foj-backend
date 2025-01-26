package com.fancier.foj.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fancier.foj.common.core.constant.enums.ResultCode;
import com.fancier.foj.common.security.exception.BusinessException;
import com.fancier.foj.common.security.utils.ThrowUtils;
import com.fancier.foj.system.domain.exam.Exam;
import com.fancier.foj.system.domain.exam.ExamQuestion;
import com.fancier.foj.system.domain.exam.dto.ExamQuestionDTO;
import com.fancier.foj.system.domain.exam.dto.ExamDTO;
import com.fancier.foj.system.domain.exam.dto.ExamQueryDTO;
import com.fancier.foj.system.domain.exam.vo.ExamVO;
import com.fancier.foj.system.mapper.ExamMapper;
import com.fancier.foj.system.mapper.ExamQuestionMapper;
import com.fancier.foj.system.mapper.QuestionMapper;
import com.fancier.foj.system.service.ExamService;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
* @author Fancier
* @description 针对表【tb_exam(竞赛表)】的数据库操作Service实现
* @createDate 2025-01-25 07:13:02
*/
@Service
@RequiredArgsConstructor
public class ExamServiceImpl extends ServiceImpl<ExamMapper, Exam>
    implements ExamService {

    private final ExamMapper examMapper;

    private final ExamQuestionMapper examQuestionMapper;

    private final QuestionMapper questionMapper;
    @Override
    public List<ExamVO> getList(ExamQueryDTO examQueryDTO) {
        PageHelper.startPage(examQueryDTO.getPageNum(), examQueryDTO.getPageSize());

        return examMapper.getExamList(examQueryDTO);
    }

    @Override
    public Boolean addExamQuestion(ExamQuestionDTO dto) {
        Long examId = dto.getExamId();
        List<Long> questionIds = dto.getQuestionIds();

        List<ExamQuestion> exitedIds = questionMapper.selectBatchIds(questionIds).stream()
                .map(question -> ExamQuestion.builder()
                        .examId(examId)
                        .questionId(question.getId())
                        .questionOrder(questionIds.indexOf(question.getId()))
                        .build()
                ).toList(); 

        return examQuestionMapper.addBatch(exitedIds);
    }

    @Override
    public void validate(ExamDTO dto) {
        // 校验时间是否合法
        if (Objects.nonNull(dto.getStartTime())) {
            ThrowUtils.throwIf(dto.getStartTime().isBefore(LocalDateTime.now()),
                    new BusinessException(ResultCode.EXAM_START_TIME_BEFORE_CURRENT_TIME));
        }
        if (Objects.nonNull(dto.getStartTime()) && Objects.nonNull(dto.getEndTime())) {
            ThrowUtils.throwIf(dto.getStartTime().isAfter(dto.getEndTime()),
                    new BusinessException(ResultCode.EXAM_START_TIME_AFTER_END_TIME));
        }

        // 校验标题是否合法
        LambdaQueryWrapper<Exam> wrapper = new LambdaQueryWrapper<Exam>()
                .eq(Exam::getTitle, dto.getTitle().trim());
        // 校验题目是否存在
        Exam one = getOne(wrapper);
        // 放置更新的时候输入相同的标题引发错误
        if (Objects.nonNull(one) && one.getId().equals(dto.getId())) {
            return;
        }
        ThrowUtils.throwIf(Objects.nonNull(one), ResultCode.FAILED_ALREADY_EXISTS);
    }
}




