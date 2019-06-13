package com.sm.service;

import com.sm.utils.ResultEntity;

public interface StudentLoginService {
    /**
     *
     * @param account
     * @param password
     * @return
     */
    ResultEntity studentLogin(String account, String password);
}
