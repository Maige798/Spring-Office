package com.springoffice.message.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springoffice.message.entity.Message;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {
}
