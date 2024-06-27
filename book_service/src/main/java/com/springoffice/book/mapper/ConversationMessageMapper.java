package com.springoffice.book.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springoffice.book.entity.ConversationMessage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConversationMessageMapper extends BaseMapper<ConversationMessage> {
}
