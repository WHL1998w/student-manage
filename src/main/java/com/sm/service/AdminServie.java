package com.sm.service;

import com.sm.utils.ResultEntity;
/**
 * 管理员业务逻辑接口
 */
public interface AdminServie {
    /**
     * 登录界面
     * @param
     * @param account
     * @param password
     * @return
     */
    ResultEntity adminLogin(String account, String password);

}
