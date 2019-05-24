package com.sm.dao;

import com.sm.entity.CClass;

import java.sql.SQLException;
import java.util.List;

public interface CClassDAO {
    /**
     * 根据院系id查询班级
     * @param departmentId
     * @return List<CClass>
     * @throws SQLException
     */
    List<CClass> selectByDepartmentId(int departmentId) throws SQLException;

    /**
     * 根据班级id删除
     * @param id
     * @return int
     * @throws SQLException
     */
    int deleteById(int id)throws SQLException;

    /**
     * 新增班级
     * @param cClass
     * @return int
     * @throws SQLException
     */
    int insertCClass(CClass cClass) throws SQLException;
}
