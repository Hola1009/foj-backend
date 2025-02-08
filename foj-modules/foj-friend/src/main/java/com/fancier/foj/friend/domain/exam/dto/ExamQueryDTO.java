package com.fancier.foj.friend.domain.exam.dto;

import com.fancier.foj.common.core.domain.dto.BasePageQuery;
import lombok.Getter;
import lombok.Setter;

/**
 * @author <a href="https://github.com/hola1009">fancier</a>
 **/
@Getter
@Setter
public class ExamQueryDTO extends BasePageQuery {

    private String title;

    private String startTime;

    private String endTime;

    private Integer type; //0 未完善  1 历史竞赛
}