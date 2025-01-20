package com.fancier.foj.system.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fancier.foj.common.core.constant.HttpConstants;
import com.fancier.foj.common.core.controller.BaseController;
import com.fancier.foj.common.core.domain.vo.Result;
import com.fancier.foj.common.core.constant.enums.ResultCode;
import com.fancier.foj.common.security.exception.BusinessException;
import com.fancier.foj.common.security.utils.ThrowUtils;
import com.fancier.foj.system.domain.admin.Admin;
import com.fancier.foj.system.domain.admin.dto.AdminDTO;
import com.fancier.foj.system.domain.admin.vo.AdminVO;
import com.fancier.foj.system.service.AdminService;
import com.fancier.foj.system.utils.BCryptUtils;
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
@RequestMapping("/admin")
public class AdminController extends BaseController {

    private final AdminService adminService;

    @PostMapping("/login")
    @Operation(summary = "登录", description = "根据提供信息登录")
    @ApiResponse(responseCode = "1000", description = "操作成功")
    @ApiResponse(responseCode = "2000", description = "业务繁忙请稍后重试")
    @ApiResponse(responseCode = "3103", description = "用户名或密码错误")
    public Result login(@RequestBody AdminDTO userLogin) {
        return adminService.login(userLogin);
    }

    @PostMapping("/add")
    @Operation(summary = "添加管理员", description = "根据提供信息新增")
    @ApiResponse(responseCode = "1000", description = "操作成功")
    @ApiResponse(responseCode = "2000", description = "业务繁忙请稍后重试")
    @ApiResponse(responseCode = "3103", description = "用户名或密码错误")
    public Result add(@RequestBody AdminDTO userRegister) {
        // 校验账户是否存在
        Admin one = adminService.getOne(new LambdaQueryWrapper<Admin>()
                .eq(Admin::getAccount, userRegister.getAccount()));

        ThrowUtils.throwIf(Objects.nonNull(one),
                new BusinessException(ResultCode.FILED_USER_EXISTS));

        // 属性拷贝
        Admin admin = new Admin();
        BeanUtils.copyProperties(userRegister, admin);

        // 进行密码加密
        String password = userRegister.getPassword();
        admin.setPassword(BCryptUtils.encryptPassword(password));

        // 设置创建人
        admin.setCreateBy(1L);
        admin.setUpdateBy(1L);
        // 插入数据库
        boolean isSaved = adminService.save(admin);

        return toResult(isSaved);
    }

    @PutMapping("/update")
    @Operation(summary = "修改管理员信息", description = "根据提供信息修改")
    @ApiResponse(responseCode = "1000", description = "操作成功")
    @ApiResponse(responseCode = "2000", description = "业务繁忙请稍后重试")
    @ApiResponse(responseCode = "3103", description = "用户名或密码错误")
    @ApiResponse(responseCode = "3101", description = "⽤⼾不存在")
    public Result update(@RequestBody AdminDTO userRegister) {

        Admin admin = new Admin();
        BeanUtils.copyProperties(userRegister, admin);

        boolean b = adminService.updateById(admin);

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
        adminService.removeById(userId);
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
        Admin admin = adminService.getOne(new LambdaQueryWrapper<Admin>()
                .eq(Objects.nonNull(userId), Admin::getId, userId));

        // 脱敏
        AdminVO adminVO = new AdminVO();
        BeanUtils.copyProperties(admin, adminVO);

        return Result.success(adminVO);
    }

    /**
     * 获取当前用户信息
     */
    @GetMapping("/info")
    public Result getUserinfo(@RequestHeader(HttpConstants.AUTHENTICATION) String token) {
        token = getToken(token);
        return adminService.getUserinfo(token);
    }

    @DeleteMapping("/logout")
    public Result logout(@RequestHeader(HttpConstants.AUTHENTICATION) String token) {
        token = getToken(token);
        return toResult(adminService.logout(token));
    }

    /**
     * 去除 token 前缀
     */
    private String getToken(String token) {
        if (StrUtil.isNotEmpty(token) && token.startsWith(HttpConstants.PREFIX)) {
            token = token.replaceFirst(HttpConstants.PREFIX, StrUtil.EMPTY);
        }
        return token;
    }
}
