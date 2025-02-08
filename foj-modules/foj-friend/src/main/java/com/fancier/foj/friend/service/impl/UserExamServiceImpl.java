package com.fancier.foj.friend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fancier.foj.common.core.constant.enums.ResultCode;
import com.fancier.foj.common.core.domain.vo.PageResult;
import com.fancier.foj.common.core.utils.UserHolder;
import com.fancier.foj.common.security.exception.BusinessException;
import com.fancier.foj.common.security.service.TokenService;
import com.fancier.foj.common.security.utils.ThrowUtils;
import com.fancier.foj.friend.domain.exam.Exam;
import com.fancier.foj.friend.domain.exam.dto.ExamDTO;
import com.fancier.foj.friend.domain.exam.dto.ExamQueryDTO;
import com.fancier.foj.friend.domain.exam.vo.ExamVO;
import com.fancier.foj.friend.domain.user.UserExam;
import com.fancier.foj.friend.mapper.ExamMapper;
import com.fancier.foj.friend.mapper.UserExamMapper;
import com.fancier.foj.friend.service.UserExamService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
* @author Fancier
* @description 针对表【tb_user_exam】的数据库操作Service实现
* @createDate 2025-02-03 11:06:12
*/
@Service
@RequiredArgsConstructor
public class UserExamServiceImpl extends ServiceImpl<UserExamMapper, UserExam>
    implements UserExamService {

    private final TokenService tokenService;

    private final UserExamMapper userExamMapper;

    private final ExamMapper examMapper;
    @Override
    public Boolean enter(String token, ExamDTO examDTO) {
        Long examId = examDTO.getExamId();
        Exam exam = examMapper.selectById(examId);

        ThrowUtils.throwIf(Objects.isNull(exam),
                new BusinessException(ResultCode.FAILED_NOT_EXISTS));

        ThrowUtils.throwIf(exam.getStartTime().isBefore(LocalDateTime.now()),
                new BusinessException(ResultCode.EXAM_STARTED));

        Long userId = UserHolder.get();

        UserExam userExam = userExamMapper.selectOne(new LambdaQueryWrapper<UserExam>()
                .eq(UserExam::getExamId, examId)
                .eq(UserExam::getUserId, userId));
        ThrowUtils.throwIf(Objects.isNull(userExam),
                new BusinessException(ResultCode.USER_EXAM_HAS_ENTER));

        userExam = new UserExam();
        userExam.setExamId(examId);
        userExam.setUserId(userId);

        return userExamMapper.insert(userExam) > 0;
    }

    @Override
    public PageResult list(ExamQueryDTO examQueryDTO) {
        Long userId = UserHolder.get();
        List<ExamVO> examVOList;

        PageHelper.startPage(examQueryDTO.getPageNum(), examQueryDTO.getPageSize());
        examVOList = userExamMapper.selectUserExamList();

        return PageResult.success(examVOList, new PageInfo<>(examVOList).getTotal());
    }
}




