package com.sm.factory;

import com.sm.service.AdminServie;
import com.sm.service.CClassService;
import com.sm.service.DepartmentService;
import com.sm.service.StudentService;
import com.sm.service.impl.AdminServiceImpl;
import com.sm.service.impl.CClassServiceImpl;
import com.sm.service.impl.DepartmentServiceImpl;
import com.sm.service.impl.StudentServiceImpl;

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
}
