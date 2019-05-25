package com.sm.dao;

import com.sm.entity.StudentVO;

import java.sql.SQLException;
import java.util.List;

public interface StudnetDAO {

    /**
     * 查询所有学生
     * @return List<StudentVO>
     * @throws SQLException
     */
    List<StudentVO> selectAll() throws SQLException;
}
