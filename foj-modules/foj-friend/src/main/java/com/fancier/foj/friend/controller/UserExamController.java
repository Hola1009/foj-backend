package com.fancier.foj.friend.controller;

import com.fancier.foj.common.core.constant.HttpConstants;
import com.fancier.foj.common.core.controller.BaseController;
import com.fancier.foj.common.core.domain.vo.PageResult;
import com.fancier.foj.common.core.domain.vo.Result;
import com.fancier.foj.friend.aspect.CheckUserStatus;
import com.fancier.foj.friend.domain.exam.dto.ExamDTO;
import com.fancier.foj.friend.domain.exam.dto.ExamQueryDTO;
import com.fancier.foj.friend.service.UserExamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author <a href="https://github.com/hola1009">fancier</a>
 **/
@RestController
@RequiredArgsConstructor
@Tag(name = "用户-竞赛管理")
@RequestMapping("/user/exam")
public class UserExamController extends BaseController {
    private final UserExamService userExamService;

    @CheckUserStatus
    @PostMapping("/enter")
    @Operation(summary = "报名参赛")
    public Result enter(@RequestHeader(HttpConstants.AUTHENTICATION) String token, @RequestBody ExamDTO examDTO) {
        return toResult(userExamService.enter(token, examDTO));
    }

    public PageResult list(ExamQueryDTO examQueryDTO) {
        return userExamService.list(examQueryDTO);
    }
}
