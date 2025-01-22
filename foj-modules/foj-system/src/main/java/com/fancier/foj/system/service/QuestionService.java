package com.fancier.foj.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.fancier.foj.system.domain.question.Question;
import com.fancier.foj.system.domain.question.dto.QuestionQueryDTO;
import com.fancier.foj.system.domain.question.vo.QuestionVO;

import java.util.List;

/**
* @author Fanfan
* @description 针对表【tb_question(题目表)】的数据库操作Service
* @createDate 2025-01-21 15:57:50
*/
public interface QuestionService extends IService<Question> {
    List<QuestionVO> getList(QuestionQueryDTO questionQueryDTO);
}
