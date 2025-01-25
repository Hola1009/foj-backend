package com.fancier.foj.system.controller;

import com.fancier.foj.common.core.controller.BaseController;
import com.fancier.foj.common.core.domain.vo.PageResult;
import com.fancier.foj.system.domain.exam.dto.ExamQueryDTO;
import com.fancier.foj.system.service.ExamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="https://github.com/hola1009">fancier</a>
 **/
@RestController
@RequiredArgsConstructor
@Tag(name = "竞赛管理接口")
@RequestMapping("/exam")
public class ExamController extends BaseController {
    private final ExamService examService;

    @GetMapping("/list")
    @Operation(summary = "竞赛分页查询", description = "竞赛分页查询")
    public PageResult list(ExamQueryDTO examQueryDTO) {
        return toPageResult(examService.getList(examQueryDTO));
    }
}
