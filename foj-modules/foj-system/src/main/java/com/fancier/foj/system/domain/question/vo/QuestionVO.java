package com.fancier.foj.system.domain.question.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author <a href="https://github.com/hola1009">fancier</a>
 **/
@Getter
@Setter
public class QuestionVO {
    // 解决精度丢失问题
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String title;

    private Integer difficulty;

    private String creatorName;

    private LocalDateTime createTime;
}
