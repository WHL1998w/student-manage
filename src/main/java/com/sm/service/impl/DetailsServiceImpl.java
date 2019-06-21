package com.sm.service.impl;

import com.sm.dao.DetailsDAO;
import com.sm.entity.Department;
import com.sm.entity.Details;
import com.sm.factory.DAOFactory;
import com.sm.service.DetailsService;

import java.sql.SQLException;
import java.util.List;

public class DetailsServiceImpl implements DetailsService {
    private DetailsDAO detailsDAO = DAOFactory.getDetailsDAOInstance();
    @Override
    public List<Details> selectDepartmentById(int departmentId) {
        List<Details> detailsList = null;
        try {
            detailsList = detailsDAO.selectDepartmentById(departmentId);
        } catch (SQLException e) {
            System.err.print("查询院系详细信息出现异常");
        }
        return detailsList;
    }

    @Override
    public List<Details> selectAll() {
        List<Details> detailsList = null;
        try {
            detailsList = detailsDAO.selectAll();
        } catch (SQLException e) {
            System.err.print("查询院系详细信息出现异常");
        }
        return detailsList;
    }
}
