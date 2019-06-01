package com.sm.service;

import com.sm.entity.Rewards;

import java.util.List;

public interface RewardsService {

    /**
     * 查询所有学生的奖惩情况
     * @return
     */
    List<Rewards> selectAll();

    /**
     * 根据关键字查询
     * @param keywords
     * @return
     */
    List<Rewards> selectByKeywords(String keywords);

}
