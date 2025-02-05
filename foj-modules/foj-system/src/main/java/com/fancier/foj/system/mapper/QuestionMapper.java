package com.fancier.foj.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fancier.foj.system.domain.question.Question;
import com.fancier.foj.system.domain.question.vo.QuestionVO;

import java.util.List;

/**
* @author Fancier
* @description 针对表【tb_question(题目表)】的数据库操作Mapper
* @createDate 2025-01-21 15:57:50
*/
public interface QuestionMapper extends BaseMapper<Question> {
    List<QuestionVO> selectQuestionList(String title, Integer difficulty);
}




