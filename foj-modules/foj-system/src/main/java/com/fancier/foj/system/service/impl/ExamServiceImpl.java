package com.fancier.foj.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fancier.foj.system.domain.exam.Exam;
import com.fancier.foj.system.domain.exam.dto.ExamQueryDTO;
import com.fancier.foj.system.domain.exam.vo.ExamVO;
import com.fancier.foj.system.mapper.ExamMapper;
import com.fancier.foj.system.service.ExamService;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Fancier
* @description 针对表【tb_exam(竞赛表)】的数据库操作Service实现
* @createDate 2025-01-25 07:13:02
*/
@Service
@RequiredArgsConstructor
public class ExamServiceImpl extends ServiceImpl<ExamMapper, Exam>
    implements ExamService {

    private final ExamMapper examMapper;
    @Override
    public List<ExamVO> getList(ExamQueryDTO examQueryDTO) {
        PageHelper.startPage(examQueryDTO.getPageNum(), examQueryDTO.getPageSize());

        return examMapper.getExamList(examQueryDTO);
    }
}




