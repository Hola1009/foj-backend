package com.fancier.foj.friend.domain.exam.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author <a href="https://github.com/hola1009">fancier</a>
 **/
@Getter
@Setter
public class ExamVO {
    private Long examId;

    private String title;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private boolean enter = false; //true: 已经报名  false 未报名
}
