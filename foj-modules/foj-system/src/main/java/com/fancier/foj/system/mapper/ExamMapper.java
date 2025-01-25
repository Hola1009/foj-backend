package com.fancier.foj.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fancier.foj.system.domain.exam.Exam;
import com.fancier.foj.system.domain.exam.dto.ExamQueryDTO;
import com.fancier.foj.system.domain.exam.vo.ExamVO;

import java.util.List;

/**
* @author Fanfan
* @description 针对表【tb_exam(竞赛表)】的数据库操作Mapper
* @createDate 2025-01-25 07:13:02
* @Entity generator.domain.Exam
*/
public interface ExamMapper extends BaseMapper<Exam> {

    List<ExamVO> getExamList(ExamQueryDTO queryDTO);
}




