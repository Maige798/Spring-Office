package com.springoffice.book.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springoffice.book.entity.Conversation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConversationMapper extends BaseMapper<Conversation> {
}
