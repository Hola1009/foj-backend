package com.fancier.foj.system.domain.question.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author <a href="https://github.com/hola1009">fancier</a>
 **/
@Getter
@Setter
public class QuestionVO {

    private Long id;

    private String title;

    private Integer difficulty;

    private String creatorName;

    private LocalDateTime createTime;
}
