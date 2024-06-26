package com.springoffice.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springoffice.user.entity.Login;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper extends BaseMapper<Login> {
}
