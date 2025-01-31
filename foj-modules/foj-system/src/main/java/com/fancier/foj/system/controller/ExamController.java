package com.fancier.foj.system.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.fancier.foj.common.core.constant.enums.ExamStatus;
import com.fancier.foj.common.core.constant.enums.ResultCode;
import com.fancier.foj.common.core.controller.BaseController;
import com.fancier.foj.common.core.domain.vo.PageResult;
import com.fancier.foj.common.core.domain.vo.Result;
import com.fancier.foj.common.security.exception.BusinessException;
import com.fancier.foj.common.security.utils.ThrowUtils;
import com.fancier.foj.system.domain.exam.Exam;
import com.fancier.foj.system.domain.exam.ExamQuestion;
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
import lombok.NonNull;
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
        if (CollUtil.isNotEmpty(questionIds)) {
            examDetailVO.setExamQuestionList(questionService.listByIds(questionIds).stream()
                    .map(question -> {
                        QuestionVO questionVO = new QuestionVO();
                        BeanUtil.copyProperties(question, questionVO);
                        return questionVO;
                    }).toList());
        }
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

    @PutMapping("/edit")
    @Operation(summary = "修改竞赛信息")
    public Result edit(@RequestBody ExamDTO dto) {
        // 校验
        examService.validate(dto);

        // 属性拷贝
        Exam exam = new Exam();
        BeanUtil.copyProperties(dto, exam);

        return toResult(examService.updateById(exam));
    }

    @PutMapping("/publish")
    @Operation(summary = "竞赛发布")
    public Result publish(@RequestBody Long id) {

        boolean flag = examService.update(new LambdaUpdateWrapper<Exam>()
                .eq(Exam::getId, id)
                .set(Exam::getStatus, ExamStatus.PUBLISHED.getValue()));

        return toResult(flag);
    }

    @PutMapping("/unpublish")
    @Operation(summary = "竞赛发布")
    public Result unPublish(@RequestBody Long id) {

        boolean flag = examService.update(new LambdaUpdateWrapper<Exam>()
                .eq(Exam::getId, id)
                .set(Exam::getStatus, ExamStatus.UNPUBLISHED.getValue()));

        return toResult(flag);
    }

    @PutMapping("/question/del")
    @Operation(summary = "删除竞赛下题目")
    public Result delExamQuestion(@NonNull Long examId, Long questionId) {
        return toResult(examQuestionService.remove(new LambdaQueryWrapper<ExamQuestion>()
                .eq(ExamQuestion::getExamId, examId)
                .eq(ExamQuestion::getQuestionId, questionId)));
    }
}
