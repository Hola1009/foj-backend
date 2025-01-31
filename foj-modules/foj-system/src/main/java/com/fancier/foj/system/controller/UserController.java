package com.fancier.foj.system.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.fancier.foj.common.core.constant.enums.ResultCode;
import com.fancier.foj.common.core.constant.enums.UserStatus;
import com.fancier.foj.common.core.controller.BaseController;
import com.fancier.foj.common.core.domain.vo.PageResult;
import com.fancier.foj.common.core.domain.vo.Result;
import com.fancier.foj.common.security.exception.BusinessException;
import com.fancier.foj.common.security.utils.ThrowUtils;
import com.fancier.foj.system.domain.user.User;
import com.fancier.foj.system.domain.user.dto.UserQueryDTO;
import com.fancier.foj.system.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author <a href="https://github.com/hola1009">fancier</a>
 **/
@RestController
@RequiredArgsConstructor
@Tag(name = "普通用户管理")
@RequestMapping("/user")
public class UserController extends BaseController {
    private final UserService userService;

    @GetMapping("/list")
    @Operation(summary = "获取用户列表")
    public PageResult list(UserQueryDTO dto) {
        return toPageResult(userService.getList(dto));
    }

    @PutMapping("/updateStatus")
    @Operation(summary = "更新用户状态")
    public Result updateStatus(@NonNull Long userId, @NonNull Integer status) {
        ThrowUtils.throwIf(Objects.isNull(userService.getById(userId)),
                new BusinessException(ResultCode.FAILED_USER_NOT_EXISTS));

        ThrowUtils.throwIf(Arrays.stream(UserStatus.values())
                        .noneMatch(e -> status.equals(e.getValue())),
                new BusinessException(ResultCode.FAILED_STATUS_NOT_EXISTS));

        boolean update = userService.update(new LambdaUpdateWrapper<User>()
                .set(User::getStatus, status)
                .eq(User::getId, userId));

        return toResult(update);
    }
}
