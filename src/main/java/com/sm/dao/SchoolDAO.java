package com.sm.dao;

import com.sm.entity.SchoolNews;

import java.sql.SQLException;
import java.util.List;

public interface SchoolDAO {
    /**
     * 查询所有
     * @return
     * @throws SQLException
     */
    List<SchoolNews> selsctAll() throws SQLException;
}
