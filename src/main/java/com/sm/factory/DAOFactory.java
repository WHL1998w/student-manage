package com.sm.factory;

import com.sm.dao.*;
import com.sm.dao.impl.*;

public class DAOFactory {
    public static AdminDAO getAdminDAOInstance() {
        return new AdminDAOImpl();
    }
    public static DepartmentDAO getDepartmentDAOInstance(){
        return new DepartmentDAOImpl();
    }
    public static CClassDAO getCClassDAOInstance(){
        return new CClassDAOImpl();
    }
    public static StudnetDAO getStudentDAOInstance(){
        return new StudentDAOImpl();
    }
    public static RewardsDAO getRewardsDAOInstance(){
        return new RewardsDAOImpl();
    }
    public static TeacherLoginDAO getTeacherLoginDAOInstance(){return new TeacherLoginDAOImpl();}
    public static StudentLoginDAO getStudnetLoginDAOInstance(){
        return new StudentLoginDAOImpl();
    }
    public static ProfessionDAO getProfessionDAOInstance(){
        return new ProfessionDAOImpl();
    }
    public static DetailsDAO getDetailsDAOInstance(){
        return new DetailsDAOImpl();
    }
    public static CourseDAO getCourseDAOInstance(){
        return new CourseDAOImpl();
    }
    public static SchoolDAO getSchoolDAOInstanche(){
        return new SchoolDAOImpl();
    }
    public static DepartmentnNewsDAO getDepartmentNewsDAOInstance(){
        return new DepartmentNewsDAOImpl();
    }
    public static TeacherDAO getTeacherDAOInstance(){
        return new TeacherDAOImpl();
    }
}
