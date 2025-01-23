package com.fancier.foj.system.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fancier.foj.common.core.constant.enums.ResultCode;
import com.fancier.foj.common.core.controller.BaseController;
import com.fancier.foj.common.core.domain.vo.PageResult;
import com.fancier.foj.common.core.domain.vo.Result;
import com.fancier.foj.common.security.utils.ThrowUtils;
import com.fancier.foj.system.domain.question.Question;
import com.fancier.foj.system.domain.question.dto.QuestionDTO;
import com.fancier.foj.system.domain.question.dto.QuestionQueryDTO;
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
    public PageResult list(@RequestBody QuestionQueryDTO questionQueryDTO) {
        List<QuestionVO> list = questionService.getList(questionQueryDTO);
        return toPageResult(list);
    }


    @PostMapping("/add")
    @Operation(summary = "添加题目")
    public Result add(@RequestBody @Validated QuestionDTO questionDTO) {

        // 校验题目是否存在
        LambdaQueryWrapper<Question> wrapper = new LambdaQueryWrapper<Question>()
                .eq(Question::getTitle, questionDTO.getTitle());
        Question one = questionService.getOne(wrapper);
        ThrowUtils.throwIf(Objects.nonNull(one), ResultCode.FAILED_ALREADY_EXISTS);

        Question question = new Question();

        BeanUtil.copyProperties(questionDTO, question);
        return toResult(questionService.save(question));
    }
}
