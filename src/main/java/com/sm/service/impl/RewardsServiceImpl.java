package com.sm.service.impl;

import com.sm.dao.RewardsDAO;
import com.sm.entity.Rewards;
import com.sm.factory.DAOFactory;
import com.sm.service.RewardsService;

import java.sql.SQLException;
import java.util.List;

public class RewardsServiceImpl implements RewardsService {
    private RewardsDAO rewardsDAO = DAOFactory.getRewardsDAOInstance();

    /**
     * 查询所有学生的奖惩情况
     * @return
     */
    @Override
    public List<Rewards> selectAll() {
        List<Rewards> rewardsList = null;
        try {
            rewardsList = rewardsDAO.selectAll();
        } catch (SQLException e) {
            System.out.println("查询学生奖惩情况错误");
        }
        return rewardsList;
    }

    /**
     * 根据关键字查询所有学生的奖惩情况
     * @param keywords
     * @return
     */
    @Override
    public List<Rewards> selectByKeywords(String keywords) {
        List<Rewards> rewardsList = null;
        try {
            rewardsList = rewardsDAO.selectByKeywords(keywords);
        } catch (SQLException e) {
            System.out.println("根据关键字查询学生奖惩情况错误");
        }
        return rewardsList;
    }

    /**
     * 修改学生奖惩情况错误
     * @param rewards
     * @return int
     */
    @Override
    public int updateRewards(Rewards rewards) {
        int n = 0;
        try {
            n = rewardsDAO.updateRewards(rewards);
        } catch (SQLException e) {
            System.out.println("修改学生奖惩情况错误");
        }
        return n;
    }
}
