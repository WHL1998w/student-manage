package com.sm.service.impl;

import com.sm.dao.ProfessionDAO;
import com.sm.entity.Details;
import com.sm.entity.Profession;
import com.sm.factory.DAOFactory;
import com.sm.service.ProfessionService;

import java.sql.SQLException;
import java.util.List;

public class ProfessionServiceImpl implements ProfessionService {
    private ProfessionDAO professionDAO = DAOFactory.getProfessionDAOInstance();
    @Override
    public List<Profession> selectDepartmentId(int departmentId) {
        List<Profession> detailsList = null;
        try {
            detailsList = professionDAO.selectDepartmentId(departmentId);
        } catch (SQLException e) {
            System.err.print("查询专业详细信息出现异常");
        }
        return detailsList;
    }

    @Override
    public int insetProfessionById(Profession profession) {
        int n = 0;
        try {
            n = professionDAO.insetProfessionById(profession);
        } catch (SQLException e) {
            System.out.println("新增专业错误");
        }
        return n;
    }

    @Override
    public List<Profession> selectAll() {
        List<Profession> detailsList = null;
        try {
            detailsList = professionDAO.selectAll();
        } catch (SQLException e) {
            System.err.print("查询专业详细信息出现异常");
        }
        return detailsList;
    }

    @Override
    public int deletById(int id) {
        int n = 0;
        try {
            n = professionDAO.deletById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public int updateProfessionById(Profession profession) {
        int n = 0;
        try {
            n = professionDAO.updateProfessionById(profession);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
}
