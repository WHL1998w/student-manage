package com.sm.dao;

import com.sm.entity.Details;

import java.sql.SQLException;
import java.util.List;

public interface DetailsDAO {
    /**
     * 根据院系id查找院系详情
     * @param departmentId
     * @return
     * @throws SQLException
     */
    List<Details> selectDepartmentById(int departmentId) throws SQLException;

    /**
     * 查询所有详情
     * @param
     * @return
     * @throws SQLException
     */
    List<Details> selectAll() throws SQLException;
}
