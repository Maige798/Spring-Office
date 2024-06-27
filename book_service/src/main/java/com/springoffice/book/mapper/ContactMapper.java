package com.springoffice.book.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springoffice.book.entity.Contact;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContactMapper extends BaseMapper<Contact> {
}
