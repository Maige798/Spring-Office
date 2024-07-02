package com.springoffice.fish.controller;

import com.springoffice.fish.entity.Fish;
import com.springoffice.fish.service.FishService;
import com.springoffice.global.util.DataResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/fish")
public class FishController {
    @Resource
    private FishService fishService;

    @PostMapping("/init/{id}")
    public DataResult<Fish> initFish(@PathVariable("id") Integer userId) {
        return fishService.initFish(userId);
    }

    @PostMapping("/knock/{id}")
    public DataResult<Fish> knock(@PathVariable("id") Integer userId) {
        return fishService.knock(userId);
    }

    @GetMapping("/department/list")
    public DataResult<List<Fish>> getDepartmentFishRankingList(@RequestParam(name = "dept_id") Integer deptId) {
        return fishService.getDepartmentFishRankingList(deptId);
    }
}
