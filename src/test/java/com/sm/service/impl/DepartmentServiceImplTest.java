package com.sm.service.impl;

import com.sm.entity.Department;
import com.sm.factory.ServiceFacotry;
import com.sm.service.DepartmentService;
import org.junit.Test;
import java.util.List;
import java.util.Map;


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

    @Test
    public void selectDepartmentInfo() {
        List<Map> mapList = departmentService.selectDepartmentInfo();
        mapList.forEach(map -> {
            System.out.println(map.get("department") + "," + map.get("classCount") + "个班，" + map.get("studentCount") + "个学生");

        });
    }

    /**
     * 修改院系
     */
    @Test
    public void updateDepartment() {

    }

    @Test
    public void selectDepartmentById() {
    }
}