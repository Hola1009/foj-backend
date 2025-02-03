package com.fancier.foj.job.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fancier.foj.job.domain.message.MessageText;
import com.fancier.foj.job.mapper.MessageTextMapper;
import com.fancier.foj.job.service.MessageTextService;
import org.springframework.stereotype.Service;

/**
* @author Fanfan
* @description 针对表【tb_message_text】的数据库操作Service实现
* @createDate 2025-02-03 14:58:13
*/
@Service
public class MessageTextServiceImpl extends ServiceImpl<MessageTextMapper, MessageText>
    implements MessageTextService {

}




