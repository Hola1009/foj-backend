package com.fancier.foj.friend.controller;

import com.fancier.foj.common.core.constant.HttpConstants;
import com.fancier.foj.common.core.controller.BaseController;
import com.fancier.foj.common.core.domain.vo.Result;
import com.fancier.foj.friend.domain.user.dto.UserDTO;
import com.fancier.foj.friend.domain.user.dto.UserUpdateDTO;
import com.fancier.foj.friend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author <a href="https://github.com/hola1009">fancier</a>
 **/
@RestController
@RequiredArgsConstructor
@Tag(name = "用户管理层")
@RequestMapping("/user")
public class UserController extends BaseController {

    private final UserService userService;

    @PostMapping("/sendCode")
    @Operation(summary = "发送验证码")
    Result sendCode(UserDTO dto) {
        return toResult(userService.sendCode(dto));
    }

    @PostMapping("/code/login")
    @Operation(summary = "验证码登录")
    Result codeLogin(UserDTO dto) {
        return Result.success(userService.codeLogin(dto.getPhone(), dto.getCode()));
    }

    @DeleteMapping("/logout")
    @Operation(summary = "用户退出登录")
    public Result logout(@RequestHeader(HttpConstants.AUTHENTICATION) String token) {
        return toResult(userService.logout(token));
    }

    @GetMapping("/info")
    @Operation(summary = "获取用户信息")
    public Result info(@RequestHeader(HttpConstants.AUTHENTICATION) String token) {
        return Result.success(userService.info(token));
    }

    @GetMapping("/detail")
    @Operation(summary = "获取用户详情")
    public Result detail() {
        return Result.success(userService.detail());
    }

    @PostMapping("/edit")
    @Operation(summary = "更新用户信息")
    public Result edit(@RequestBody UserUpdateDTO dto) {
        return toResult(userService.edit(dto));
    }
}
