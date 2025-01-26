package com.fancier.foj.system.controller;

import cn.hutool.core.bean.BeanUtil;
import com.fancier.foj.common.core.constant.enums.ResultCode;
import com.fancier.foj.common.core.controller.BaseController;
import com.fancier.foj.common.core.domain.vo.PageResult;
import com.fancier.foj.common.core.domain.vo.Result;
import com.fancier.foj.common.security.exception.BusinessException;
import com.fancier.foj.common.security.utils.ThrowUtils;
import com.fancier.foj.system.domain.exam.Exam;
import com.fancier.foj.system.domain.exam.dto.ExamDTO;
import com.fancier.foj.system.domain.exam.dto.ExamQueryDTO;
import com.fancier.foj.system.domain.exam.dto.ExamQuestionDTO;
import com.fancier.foj.system.domain.exam.vo.ExamDetailVO;
import com.fancier.foj.system.domain.question.vo.QuestionVO;
import com.fancier.foj.system.service.ExamQuestionService;
import com.fancier.foj.system.service.ExamService;
import com.fancier.foj.system.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author <a href="https://github.com/hola1009">fancier</a>
 *
 **/
@RestController
@RequiredArgsConstructor
@Tag(name = "竞赛管理接口")
@RequestMapping("/exam")
public class ExamController extends BaseController {
    private final ExamService examService;
    private final ExamQuestionService examQuestionService;
    private final QuestionService questionService;

    @GetMapping("/list")
    @Operation(summary = "竞赛分页查询", description = "竞赛分页查询")
    public PageResult list(ExamQueryDTO examQueryDTO) {
        return toPageResult(examService.getList(examQueryDTO));
    }
    @PostMapping("/add")
    @Operation(summary = "添加竞赛", description = "添加竞赛")
    public Result add(@RequestBody ExamDTO dto) {
        // 参数校验
        examService.validate(dto);

        // 属性拷贝
        Exam exam = new Exam();
        BeanUtil.copyProperties(dto, exam);
        examService.save(exam);
        return Result.success(exam.getId());
    }

    @PostMapping("/question/add")
    @Operation(summary = "添加题目到竞赛")
    public Result addQuestion(@RequestBody ExamQuestionDTO dto) {
        Exam byId = examService.getById(dto.getExamId());
        // 竞赛不存在
        ThrowUtils.throwIf(Objects.isNull(byId),
                new BusinessException(ResultCode.FAILED_NOT_EXISTS));
        return toResult(examService.addExamQuestion(dto));
    }

    @GetMapping("/detail/{examId}")
    @Operation(summary = "获取竞赛详情")
    public Result detail(@PathVariable String examId) {
        Exam byId = examService.getById(examId);
        ThrowUtils.throwIf(Objects.isNull(byId), new BusinessException(ResultCode.FAILED_NOT_EXISTS));

        ExamDetailVO examDetailVO = new ExamDetailVO();

        BeanUtil.copyProperties(byId, examDetailVO);

        List<Long> questionIds = examQuestionService.getByExamId(byId.getId());
        examDetailVO.setExamQuestionList(questionService.listByIds(questionIds).stream()
                .map(question -> {
                    QuestionVO questionVO = new QuestionVO();
                    BeanUtil.copyProperties(question, questionVO);
                    return questionVO;
                }).toList());

        return Result.success(examDetailVO);
    }

    @DeleteMapping("/{examId}")
    @Operation(summary = "删除竞赛")
    public Result delete(@PathVariable String examId) {
        Exam byId = examService.getById(examId);
        ThrowUtils.throwIf(Objects.isNull(byId), new BusinessException(ResultCode.FAILED_NOT_EXISTS));

        LocalDateTime now = LocalDateTime.now();
        ThrowUtils.throwIf(byId.getStartTime().isBefore(now)
                        && byId.getEndTime().isAfter(now),
                new BusinessException(ResultCode.FAILED_EXAM_IN_PROGRESS));

        return toResult(examService.removeById(examId));
    }
}
