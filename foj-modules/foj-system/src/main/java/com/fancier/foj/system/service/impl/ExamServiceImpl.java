package com.fancier.foj.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fancier.foj.system.domain.exam.Exam;
import com.fancier.foj.system.mapper.ExamMapper;
import com.fancier.foj.system.service.ExamService;
import org.springframework.stereotype.Service;

/**
* @author Fancier
* @description 针对表【tb_exam(竞赛表)】的数据库操作Service实现
* @createDate 2025-01-25 07:13:02
*/
@Service
public class ExamServiceImpl extends ServiceImpl<ExamMapper, Exam>
    implements ExamService {

}




