package com.fancier.foj.system.controller;

import com.fancier.foj.common.core.controller.BaseController;
import com.fancier.foj.common.core.domain.vo.PageResult;
import com.fancier.foj.system.domain.question.dto.QuestionQueryDTO;
import com.fancier.foj.system.domain.question.vo.QuestionVO;
import com.fancier.foj.system.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    @Operation(summary = "登录", description = "根据提供信息登录")
    public PageResult list(@RequestBody QuestionQueryDTO questionQueryDTO) {
        List<QuestionVO> list = questionService.getList(questionQueryDTO);
        return toPageResult(list);
    }


}
