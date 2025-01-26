package com.fancier.foj.system.domain.exam.vo;

import com.fancier.foj.system.domain.question.vo.QuestionVO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author <a href="https://github.com/hola1009">fancier</a>
 **/
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExamDetailVO {
    /**
     * 标题
     */
    private String title;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 题目列表
     */
    private List<QuestionVO> examQuestionList;
}
