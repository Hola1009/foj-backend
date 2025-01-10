package com.fancier.foj.system.controller;

import com.fancier.foj.common.core.domain.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "测试")
@RequestMapping("test")
public class TestController {

    @GetMapping("/api-fox")
    public Result testApiFox() {
        return Result.success();
    }
}
