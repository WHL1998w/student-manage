package com.sm.dao;

import com.sm.entity.Department;

import java.sql.SQLException;
import java.util.List;

public interface DepartmentDAO {
    /**
     * 查询所有院系
     * @return List<Department>
     * @throws SQLException
     */
    List<Department> getAll() throws SQLException;

    /**
     *新增院系
     * @param department
     * @return int
     * @throws SQLException
     */
    int insertDepartment (Department department) throws SQLException;


    /**
     * 修改院系
     * @param department
     * @return int
     * @throws SQLException
     */
    int updateDepartmentById(Department department) throws SQLException;

    /**
     * 根据院系id查找院系
     * @param id
     * @return int
     * @throws SQLException
     */
    List<Department> selectDepartmentById(int id) throws SQLException;
}
