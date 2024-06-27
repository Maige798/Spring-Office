package com.springoffice.check.service;

import com.springoffice.check.entity.Checker;
import com.springoffice.global.util.DataResult;

import java.util.List;

public interface CheckService {
    DataResult<Checker> createCheck(Checker checker);

    DataResult<Checker> getCheckById(Integer id);

    DataResult<List<Checker>> getCheckListByUser(Integer userId);

    DataResult<List<Checker>> getCheckListByDepartment(Integer deptId);
}
