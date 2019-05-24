package com.sm.service.impl;

import com.sm.dao.CClassDAO;
import com.sm.entity.CClass;
import com.sm.factory.DAOFactory;
import com.sm.service.CClassService;

import java.sql.SQLException;
import java.util.List;

public class CClassServiceImpl implements CClassService {
    private CClassDAO cClassDAO = DAOFactory.getCClassDAOInstance();
    @Override
    public List<CClass> selectByDepartmentId(int departmentId) {
        List<CClass> cClassList = null;
        try {
            cClassList = cClassDAO.selectByDepartmentId(departmentId);
        } catch (SQLException e) {
            System.out.println("班级查询异常");
        }
        return cClassList;
    }

    @Override
    public int addCClass(CClass cClass) {
        int n = 0;
        try {
            n = cClassDAO.insertCClass(cClass);
        } catch (SQLException e) {
            System.out.println("新增班级信息出现错误");
        }
        return n;
    }

    @Override
    public void deletCClass(int id) {
        try {
            cClassDAO.deleteById(id);
        } catch (SQLException e) {
            System.out.println("删除班级出现错误");
        }
    }


}
