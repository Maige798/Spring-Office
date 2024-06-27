package com.springoffice.check.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springoffice.check.entity.Checker;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CheckMapper extends BaseMapper<Checker> {
}
