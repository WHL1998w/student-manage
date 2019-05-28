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
     * 删除院系
     * @param id
     */

    void deleteDepartment(int id);

    /**
     * 新增院系
     * @param department
     * @return int
     */
    int addDepartment(Department department);

    /**
     *
     * @return
     */
    List<Map> selectDepartmentInfo();
}
