package com.springoffice.fish.service.impl;

import com.springoffice.fish.client.DepartmentClient;
import com.springoffice.fish.client.UserClient;
import com.springoffice.fish.entity.Fish;
import com.springoffice.fish.entity.User;
import com.springoffice.fish.mapper.FishMapper;
import com.springoffice.fish.service.FishService;
import com.springoffice.global.util.DataResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("fish-service")
public class FishServiceImpl implements FishService {
    @Resource
    private FishMapper fishMapper;
    @Resource
    private UserClient userClient;
    @Resource
    private DepartmentClient departmentClient;

    @Override
    public DataResult<Fish> initFish(Integer userId) {
        DataResult<Fish> fishResult = getFishByUserId(userId);
        if (fishResult.success()) {
            return DataResult.ok("Fish记录初始化成功", fishResult.unwrap());
        }
        return createFish(userId);
    }

    @Override
    public DataResult<Fish> knock(Integer userId) {
        DataResult<Fish> fishResult = getFishByUserId(userId);
        if (!fishResult.success()) {
            return DataResult.error("User ID:" + userId + "不存在Fish记录");
        }
        Fish fish = fishResult.unwrap();
        fish.setNumber(fish.getNumber() + 1);
        int resultValue = fishMapper.updateById(fish);
        if (resultValue <= 0) {
            return DataResult.error("Fish记录跟新失败", fish);
        }
        return DataResult.ok("Fish记录更新成功", fish);
    }

    @Override
    public DataResult<List<Fish>> getDepartmentFishRankingList(Integer deptId) {
        DataResult<List<User>> userListResult = departmentClient.getDepartmentMembers(deptId);
        if (!userListResult.success()) {
            return DataResult.error("Department成员查询失败，ID:" + deptId);
        }
        List<Fish> fishList = new ArrayList<>();
        for (User user : userListResult.unwrap()) {
            DataResult<Fish> fishResult = getFishByUserId(user.getId());
            if (!fishResult.success()) continue;
            fishList.add(fishResult.unwrap());
        }
        fishList.sort((a, b) -> b.getNumber().compareTo(a.getNumber()));
        fishList.forEach(this::loadUserName);
        return DataResult.ok("Fish list查询成功", fishList);
    }

    private DataResult<Fish> getFishByUserId(Integer userId) {
        Fish fish = fishMapper.selectById(userId);
        if (fish == null) {
            return DataResult.error("User ID:" + userId + "不存在Fish记录");
        }
        return DataResult.ok("Fish查询成功", fish);
    }

    private DataResult<Fish> createFish(Integer userId) {
        Fish fish = new Fish();
        fish.setUserId(userId);
        int resultValue = fishMapper.insert(fish);
        if (resultValue <= 0) {
            return DataResult.error("Fish记录创建失败", fish);
        }
        return DataResult.ok("Fish记录创建成功", fish);
    }

    private void loadUserName(Fish fish) {
        User user = userClient.getUserById(fish.getUserId()).unwrap();
        if (user == null) {
            System.err.println("User ID:" + fish.getUserId() + "不存在");
            return;
        }
        fish.setUserName(user.getName());
    }
}
