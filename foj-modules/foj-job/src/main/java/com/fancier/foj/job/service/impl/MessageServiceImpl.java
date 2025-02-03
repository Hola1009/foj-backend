package com.fancier.foj.job.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fancier.foj.job.domain.message.Message;
import com.fancier.foj.job.mapper.MessageMapper;
import com.fancier.foj.job.service.MessageService;
import org.springframework.stereotype.Service;

/**
* @author Fancier
* @description 针对表【tb_message】的数据库操作Service实现
* @createDate 2025-02-03 14:53:08
*/
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
    implements MessageService {

}




