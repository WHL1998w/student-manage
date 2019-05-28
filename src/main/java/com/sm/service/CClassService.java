package com.sm.service;

import com.sm.entity.CClass;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface CClassService {
    List<CClass> selectByDepartmentId(int departmentId);

    int deleteClassById(int id);

    int addCClass(CClass cClass);

    List<CClass> selectAllClass();

    List<Map> selectClassInfo();
}