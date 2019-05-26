package com.sm.service;

import com.sm.entity.CClass;

import java.sql.SQLException;
import java.util.List;

public interface CClassService {
    List<CClass> selectByDepartmentId(int departmentId);

    int deleteClassById(int id);

    int addCClass(CClass cClass);

    List<CClass> selectAllClass();
}