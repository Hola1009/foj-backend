package com.fancier.foj.system.domain.question.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author <a href="https://github.com/hola1009">fancier</a>
 **/
@Getter
@Setter
public class QuestionDTO {
    @NotNull(message = "题目标题不能为空")
    private String title;

    @NotNull(message = "题目难度不能为空")
    private Integer difficulty;

    @NotNull(message = "题目时间限制不能为空")
    private Integer timeLimit;

    @NotNull(message = "题目空间限制不能为空")
    private Integer spaceLimit;

    @NotNull(message = "题目内容不能为空")
    private String content;

    @NotNull(message = "题目样例不能为空")
    private String questionCase;

    @NotNull(message = "题目默认代码不能为空")
    private String defaultCode;

    @NotNull(message = "题目主函数不能为空")
    private String mainFunc;
}
