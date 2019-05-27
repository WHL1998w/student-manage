package com.sm.service.impl;

import com.sm.entity.Department;
import com.sm.factory.ServiceFacotry;
import com.sm.service.DepartmentService;
import org.junit.Test;
import java.util.List;


public class DepartmentServiceImplTest {
    private DepartmentService departmentService = ServiceFacotry.getDempartmentServiceInstance();

    /**
     * 查询所有院系
     */
    @Test
    public void selectAll() {
        List<Department> departmentList = departmentService.selectAll();
        departmentList.forEach(department -> System.out.println(department));
    }

    /**
     * 新增院系
     */

    @Test
    public void addDepartment() {

    }
    /**
     * 删除院系
     */

    @Test
    public void deleteDepartment() {
        departmentService.deleteDepartment(8);
    }
}