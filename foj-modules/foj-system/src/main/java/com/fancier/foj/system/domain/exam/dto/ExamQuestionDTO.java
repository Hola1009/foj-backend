package com.fancier.foj.system.domain.exam.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author <a href="https://github.com/hola1009">fancier</a>
 **/
@Getter
@Setter
public class ExamQuestionDTO {
    private Long examId;
    private List<Long> questionIds;
}
