package com.sm.dao;

import com.sm.entity.Rewards;

import java.sql.SQLException;
import java.util.List;

public interface RewardsDAO {
    /**
     * 查看所有奖惩情况
     * @return List<RewardsDAO>
     * @throws SQLException
     */
    List<Rewards> selectAll() throws SQLException;

    /**
     * 根据关键字查询学生奖惩情况
     * @param keywords
     * @return List<Rewards>
     * @throws SQLException
     */
    List<Rewards> selectByKeywords(String keywords) throws SQLException;

    /**
     * 修改学生奖惩情况
     * @param rewards
     * @return int
     * @throws SQLException
     */
    int updateRewards(Rewards rewards) throws SQLException;
}
