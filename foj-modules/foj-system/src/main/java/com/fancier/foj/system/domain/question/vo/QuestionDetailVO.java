package com.fancier.foj.system.domain.question.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author <a href="https://github.com/hola1009">fancier</a>
 **/
@Getter
@Setter
public class QuestionDetailVO {

    private String title;

    private Integer difficulty;

    private Integer timeLimit;

    private Integer spaceLimit;

    private String content;

    private String questionCase;

    private String defaultCode;

    private String mainFunc;
}
