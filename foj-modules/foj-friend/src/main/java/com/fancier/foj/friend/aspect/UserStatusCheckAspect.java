package com.fancier.foj.friend.aspect;


import com.fancier.foj.common.core.constant.enums.ResultCode;
import com.fancier.foj.common.core.constant.enums.UserStatus;
import com.fancier.foj.common.core.utils.UserHolder;
import com.fancier.foj.common.security.exception.BusinessException;
import com.fancier.foj.common.security.utils.ThrowUtils;
import com.fancier.foj.friend.domain.user.User;
import com.fancier.foj.friend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Component
@RequiredArgsConstructor
public class UserStatusCheckAspect {

    private final UserService userService;

    @Before(value = "@annotation(com.fancier.foj.friend.aspect.CheckUserStatus)")
    public void before(JoinPoint point){
        Long userId = UserHolder.get();
        User user = userService.getById(userId);

        ThrowUtils.throwIf(Objects.isNull(user),
                new BusinessException(ResultCode.FAILED_USER_NOT_EXISTS));

        ThrowUtils.throwIf(UserStatus.DISABLED.getValue().equals(user.getStatus()),
                new BusinessException(ResultCode.FAILED_USER_BANNED));
    }
}
