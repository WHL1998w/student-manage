package com.sm.service;

import com.sm.entity.CClass;

import java.util.List;

public interface CClassService {
    /**
     * 根据院系id查询班级
     * @param departmentId
     * @return
     */
    List<CClass> selectByDepartmentId(int departmentId);

    /**
     * 新增班级
     * @param cClass
     * @return int
     */
    int addCClass(CClass cClass);

    /**
     * 根据id删除班级
     * @param id
     */
    void deletCClass(int id);
}
