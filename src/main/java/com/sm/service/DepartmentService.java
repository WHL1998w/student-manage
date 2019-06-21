package com.sm.service;

import com.sm.entity.Department;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    /**
     * 查询院系
     * @return List<Department>
     */
    List<Department> selectAll();

    /**
     * 新增院系
     * @param department
     * @return int
     */
    int addDepartment(Department department);

    /**
     * 修改院系
     * @param department
     * @return
     */
    int updateDepartment(Department department);

    /**
     *
     * @return
     */
    List<Map> selectDepartmentInfo();

    /**
     *
     * @return
     */
    List<Department> selectDepartmentById(int id);
}
