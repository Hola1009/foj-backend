package com.fancier.foj.system.controller;

import cn.hutool.core.bean.BeanUtil;
import com.fancier.foj.common.core.constant.enums.ResultCode;
import com.fancier.foj.common.core.controller.BaseController;
import com.fancier.foj.common.core.domain.vo.PageResult;
import com.fancier.foj.common.core.domain.vo.Result;
import com.fancier.foj.common.security.utils.ThrowUtils;
import com.fancier.foj.system.domain.question.Question;
import com.fancier.foj.system.domain.question.dto.QuestionDTO;
import com.fancier.foj.system.domain.question.dto.QuestionQueryDTO;
import com.fancier.foj.system.domain.question.vo.QuestionDetailVO;
import com.fancier.foj.system.domain.question.vo.QuestionVO;
import com.fancier.foj.system.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author <a href="https://github.com/hola1009">fancier</a>
 **/
@RestController
@RequiredArgsConstructor
@Tag(name = "题库管理接口")
@RequestMapping("/question")
public class QuestionController extends BaseController {

    private final QuestionService questionService;

    @GetMapping("/list")
    @Operation(summary = "题目分页查询", description = "题目分页查询")
    public PageResult list(QuestionQueryDTO questionQueryDTO) {
        List<QuestionVO> list = questionService.getList(questionQueryDTO);
        return toPageResult(list);
    }


    @PostMapping("/add")
    @Operation(summary = "添加题目")
    public Result add(@RequestBody @Validated QuestionDTO questionDTO) {
        // 校验该标题是否存在
        questionService.validate(questionDTO);

        Question question = new Question();

        BeanUtil.copyProperties(questionDTO, question);

        return toResult(questionService.save(question));
    }
    @PutMapping("/edit")
    @Operation(summary = "修改题目")
    public Result edit(@RequestBody QuestionDTO questionDTO) {

        Boolean edited = questionService.edit(questionDTO);

        return toResult(edited);
    }
    @GetMapping("/detail")
    public Result getDetail(Long questionId) {

        Question question = questionService.getById(questionId);
        ThrowUtils.throwIf(Objects.isNull(question), ResultCode.FAILED_NOT_EXISTS);

        QuestionDetailVO questionDetailVO = new QuestionDetailVO();
        BeanUtil.copyProperties(question, questionDetailVO);

        return Result.success(question);
    }

    @DeleteMapping("/{questionId}")
    @Operation(summary = "删除题目", description = "通过题目id删除题目")
    public Result delete(@PathVariable Long questionId) {
        return toResult(questionService.removeById(questionId));
    }
}
