package com.sm.service.impl;

import com.sm.dao.CClassDAO;
import com.sm.dao.StudnetDAO;
import com.sm.entity.CClass;
import com.sm.factory.DAOFactory;
import com.sm.service.CClassService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CClassServiceImpl implements CClassService {
    private StudnetDAO studnetDAO = DAOFactory.getStudentDAOInstance();
     private CClassDAO cClassDAO = DAOFactory.getCClassDAOInstance();
    @Override
    public List<CClass> selectByDepartmentId(int departmentId) {
        List<CClass> cClassList = null;
        try {
            cClassList = cClassDAO.selectByDepartmentId(departmentId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cClassList;
    }

    @Override
    public int deleteClassById(int id) {
        int n = 0;
        try {
            n = cClassDAO.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public int addCClass(CClass cClass) {
        int n = 0;
        try {
            n = cClassDAO.insertClass(cClass);
        } catch (SQLException e) {
            System.err.print("新增院系信息出现异常");
        }
        return n;
    }

    @Override
    public List<CClass> selectAllClass() {
        List<CClass> cClassList = null;
        try {
            cClassList = cClassDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cClassList;
    }

    @Override
    public List<Map> selectClassInfo() {
        List<CClass> cClassList = null;
        try {
            cClassList = cClassDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Map> mapList = new ArrayList<>();
        for (CClass cClasss:cClassList) {
            Map<String,Object> map = new HashMap<>();
            map.put("cClass",cClasss);
            try {
                map.put("studentCount",studnetDAO.countByClassId(cClasss.getId()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            mapList.add(map);
        }
        return mapList;
    }
}
