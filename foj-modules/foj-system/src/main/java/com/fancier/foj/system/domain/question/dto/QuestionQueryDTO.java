package com.fancier.foj.system.domain.question.dto;

import com.fancier.foj.common.core.domain.dto.BasePageQuery;
import lombok.Getter;
import lombok.Setter;

/**
 * @author <a href="https://github.com/hola1009">fancier</a>
 **/
@Getter
@Setter
public class QuestionQueryDTO extends BasePageQuery {
    private String title;
    private Integer difficulty;
}
