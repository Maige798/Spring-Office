package com.springoffice.fish.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springoffice.fish.entity.Fish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FishMapper extends BaseMapper<Fish> {
}
