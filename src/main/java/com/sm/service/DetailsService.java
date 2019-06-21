package com.sm.service;

import com.sm.entity.Details;

import java.util.List;

public interface DetailsService {
    /**
     * 根据院系id查找详情
     * @param departmentId
     * @return int
     */
    List<Details> selectDepartmentById(int departmentId);

    /**
     * 查询所有详情
     * @return
     */
    List<Details> selectAll();
}
