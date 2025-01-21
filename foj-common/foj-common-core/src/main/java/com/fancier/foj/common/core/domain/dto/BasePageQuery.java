package com.fancier.foj.common.core.domain.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author <a href="https://github.com/hola1009">fancier</a>
 **/
@Getter
@Setter
public class BasePageQuery {
    private Integer pageNum;
    private Integer pageSize;
}
