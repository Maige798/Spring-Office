package com.springoffice.fish.service;

import com.springoffice.fish.entity.Fish;
import com.springoffice.global.util.DataResult;

import java.util.List;

public interface FishService {
    DataResult<Fish> initFish(Integer userId);

    DataResult<Fish> knock(Integer userId);

    DataResult<List<Fish>> getDepartmentFishRankingList(Integer deptId);
}
