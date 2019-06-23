package com.sm.factory;

import com.sm.service.*;
import com.sm.service.impl.*;

public class ServiceFacotry {
    public static AdminServie getAdminServiceInstance(){
        return new AdminServiceImpl();
    }
    public static DepartmentService getDempartmentServiceInstance(){
        return new DepartmentServiceImpl();
    }
    public static CClassService getCClassServiceInstance(){
        return new CClassServiceImpl();
    }
    public static StudentService getStudnetServiceInstance(){
        return new StudentServiceImpl();
    }
    public static RewardsService getRewardsServiceInstance(){
        return new RewardsServiceImpl();
    }
    public static TeacherLoginService getTeacherServiceInstance(){
        return new TearcherLoginServiceImpl();
    }

    public static StudentLoginService getStudentLoginServiceInstance(){
        return new StudentloginServiceImpl();
    }
    public static DetailsService getDetailsServiceInstance(){
        return new DetailsServiceImpl();
    }
    public static ProfessionService getProfessionService(){ return new ProfessionServiceImpl();}
    public static CourseService getCourseServiceInstance(){
        return new CourseServiceImpl();
    }
    public static SchoolNewsService getSchoolNewsServiceInstance(){
        return new SchoolNewsServiceImpl();
    }
    public static DepartmentNewsService getDepartmentNewsServiceInstance(){
        return new DepartmentNewsServiceImpl();
    }
    public static TeacherService getTeachersServiceInstance(){
        return new TeacherServieImpl();
    }
}
