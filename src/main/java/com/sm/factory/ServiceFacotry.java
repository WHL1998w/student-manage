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
}
