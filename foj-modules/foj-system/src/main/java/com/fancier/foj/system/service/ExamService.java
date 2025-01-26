package com.fancier.foj.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.fancier.foj.system.domain.exam.Exam;
import com.fancier.foj.system.domain.exam.dto.ExamQuestionDTO;
import com.fancier.foj.system.domain.exam.dto.ExamDTO;
import com.fancier.foj.system.domain.exam.dto.ExamQueryDTO;
import com.fancier.foj.system.domain.exam.vo.ExamVO;

import java.util.List;

/**
* @author Fancier
* @description 针对表【tb_exam(竞赛表)】的数据库操作Service
* @createDate 2025-01-25 07:13:02
*/
public interface ExamService extends IService<Exam> {
    List<ExamVO> getList(ExamQueryDTO examQueryDTO);

    Boolean addExamQuestion(ExamQuestionDTO dto);

    void validate(ExamDTO dto);
}
