package com.fancier.foj.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fancier.foj.system.domain.question.Question;
import com.fancier.foj.system.mapper.QuestionMapper;
import com.fancier.foj.system.service.QuestionService;
import org.springframework.stereotype.Service;

/**
* @author Fanfan
* @description 针对表【tb_question(题目表)】的数据库操作Service实现
* @createDate 2025-01-21 15:57:50
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService {

}




