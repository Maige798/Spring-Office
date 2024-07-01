package com.springoffice.check.controller;

import com.springoffice.check.entity.Checker;
import com.springoffice.check.service.CheckService;
import com.springoffice.global.util.DataResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/check")
public class CheckController {
    @Resource
    private CheckService checkService;

    @PostMapping("/create")
    public DataResult<Checker> createCheck(@RequestBody Checker checker) {
        return checkService.createCheck(checker);
    }

    @GetMapping("/query")
    public DataResult<Checker> getCheckById(@RequestParam(name = "id") Integer id) {
        return checkService.getCheckById(id);
    }

    @GetMapping("/list/user")
    public DataResult<List<Checker>> getCheckByUser(@RequestParam(name = "id") Integer userId) {
        return checkService.getCheckListByUser(userId);
    }

    @GetMapping("/list/unchecked")
    public DataResult<List<Checker>> getUncheckedByUser(@RequestParam(name = "id") Integer userId) {
        return checkService.getUncheckedListByUser(userId);
    }

    @GetMapping("/list/department")
    public DataResult<List<Checker>> getCheckByDepartment(@RequestParam(name = "id") Integer deptId) {
        return checkService.getCheckListByDepartment(deptId);
    }
}
