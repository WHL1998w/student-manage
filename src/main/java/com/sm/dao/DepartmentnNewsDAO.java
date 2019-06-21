package com.sm.dao;

import com.sm.entity.DepartmentNews;

import java.sql.SQLException;
import java.util.List;

public interface DepartmentnNewsDAO {

    List<DepartmentNews> selectAll() throws SQLException;
}
