package com.sm.dao;

import com.sm.entity.Profession;

import java.sql.SQLException;
import java.util.List;

public interface ProfessionDAO {

    List<Profession> selectAll() throws SQLException;
    /**
     * 根据院系id查询专业
     * @param departmentId
     * @return
     * @throws SQLException
     */
    List<Profession> selectDepartmentId(int departmentId) throws SQLException;

    /**
     * 新增专业
     * @param profession
     * @return
     * @throws SQLException
     */
    int insetProfessionById(Profession profession) throws SQLException;

    /**
     * 修改专业
     * @param profession
     * @return
     * @throws SQLException
     */
    int updateProfessionById(Profession profession) throws SQLException;

    /**
     * 删除专业
     * @param id
     * @return
     * @throws SQLException
     */
    int deletById(int id) throws SQLException;
}
