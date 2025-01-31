package com.fancier.foj.system.domain.user.dto;

import com.fancier.foj.common.core.domain.dto.BasePageQuery;
import lombok.Getter;
import lombok.Setter;

/**
 * @author <a href="https://github.com/hola1009">fancier</a>
 **/
@Getter
@Setter
public class UserQueryDTO extends BasePageQuery {
    private String username;
    private Long userId;
}
