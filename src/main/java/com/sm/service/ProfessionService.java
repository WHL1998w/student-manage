package com.sm.service;

import com.sm.entity.Profession;

import java.sql.SQLException;
import java.util.List;

public interface ProfessionService {
    /**
     * 根据院系id查询专业
     * @param departmentId
     * @return
     * @throws SQLException
     */
    List<Profession> selectDepartmentId(int departmentId);

    int insetProfessionById(Profession profession);

    List<Profession> selectAll();

    int deletById(int id);

    int updateProfessionById(Profession profession);
}
