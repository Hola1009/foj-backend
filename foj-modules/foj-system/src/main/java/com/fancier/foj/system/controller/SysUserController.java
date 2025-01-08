package com.fancier.foj.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fancier.foj.common.core.domain.Result;
import com.fancier.foj.common.core.enums.ResultCode;
import com.fancier.foj.common.core.exception.BusinessException;
import com.fancier.foj.common.core.utils.ThrowUtils;
import com.fancier.foj.system.domain.sysUser.SysUser;
import com.fancier.foj.system.domain.sysUser.SysUserDTO;
import com.fancier.foj.system.domain.sysUser.SysUserVO;
import com.fancier.foj.system.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@Tag(name = "管理员接口")
@RequestMapping("/sys/user")
public class SysUserController {

    private final SysUserService sysUserService;

    @PostMapping("/login")
    @Operation(summary = "登录", description = "根据提供信息登录")
    @ApiResponse(responseCode = "1000", description = "操作成功")
    @ApiResponse(responseCode = "2000", description = "业务繁忙请稍后重试")
    @ApiResponse(responseCode = "3103", description = "用户名或密码错误")
    public Result login(@RequestBody SysUserDTO userLogin) {
        return sysUserService.login(userLogin);
    }

    @PostMapping("/add")
    @Operation(summary = "添加管理员", description = "根据提供信息新增")
    @ApiResponse(responseCode = "1000", description = "操作成功")
    @ApiResponse(responseCode = "2000", description = "业务繁忙请稍后重试")
    @ApiResponse(responseCode = "3103", description = "用户名或密码错误")
    public Result add(@RequestBody SysUserDTO userRegister) {

        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userRegister, sysUser);

        sysUserService.save(sysUser);

        return Result.success();
    }

    @PutMapping("/update")
    @Operation(summary = "修改管理员信息", description = "根据提供信息修改")
    @ApiResponse(responseCode = "1000", description = "操作成功")
    @ApiResponse(responseCode = "2000", description = "业务繁忙请稍后重试")
    @ApiResponse(responseCode = "3103", description = "用户名或密码错误")
    @ApiResponse(responseCode = "3101", description = "⽤⼾不存在")
    public Result update(@RequestBody SysUserDTO userRegister) {

        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userRegister, sysUser);

        boolean b = sysUserService.updateById(sysUser);

        ThrowUtils.throwIf(b, new BusinessException(ResultCode.FAILED_USER_NOT_EXISTS));

        return Result.success();
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "删除⽤⼾", description = "通过⽤⼾id删除⽤⼾")
    @Parameters(value = {
        @Parameter(name = "userId", in = ParameterIn.PATH, description = "⽤⼾ID")
    })
    @ApiResponse(responseCode = "1000", description = "成功删除⽤⼾")
    @ApiResponse(responseCode = "2000", description = "服务繁忙请稍后重试")
    public Result delete(@PathVariable Long userId) {
        sysUserService.removeById(userId);
        return Result.success();
    }


    @GetMapping("/detail")
    @Parameters(value = {
            @Parameter(name = "userId", in = ParameterIn.QUERY, description = "⽤⼾ID"),
            @Parameter(name = "sex", in = ParameterIn.QUERY, description = "⽤⼾性别")
    })
    @Operation(summary = "⽤⼾详情", description = "根据查询条件查询⽤⼾详情")
    @ApiResponse(responseCode = "1000", description = "成功获取⽤⼾信息")
    @ApiResponse(responseCode = "2000", description = "服务繁忙请稍后重试")
    @ApiResponse(responseCode = "3101", description = "⽤⼾不存在")
    public Result detail(Long userId, @RequestParam(required = false) String ignoreSex){
        // 参数校验
        // 根据查询字段查询
        SysUser sysUser = sysUserService.getOne(new LambdaQueryWrapper<SysUser>()
                .eq(Objects.nonNull(userId), SysUser::getId, userId));

        // 脱敏
        SysUserVO sysUserVO = new SysUserVO();
        BeanUtils.copyProperties(sysUser, sysUserVO);

        return Result.success(sysUserVO);
    }
}
