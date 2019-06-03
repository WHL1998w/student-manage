package com.sm.dao.impl;

import com.sm.dao.RewardsDAO;
import com.sm.entity.Rewards;
import com.sm.factory.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class RewardsDAOImplTest {
    private RewardsDAO rewardsDAO = DAOFactory.getRewardsDAOInstance();

    @Test
    public void selectAll() {
        List<Rewards> rewardsList = null;
        try {
            rewardsList = rewardsDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        rewardsList.forEach(rewards -> System.out.println(rewards));
    }

    @Test
    public void selectByKeywords() {
        List<Rewards> rewardsList = null;
        try {
            rewardsList = rewardsDAO.selectByKeywords("机");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        rewardsList.forEach(rewards -> System.out.println(rewards));
    }

    @Test
    public void updateRewards() {
        Rewards rewards = new Rewards();
        rewards.setAward("校级舞蹈大赛一等奖");
        rewards.setPunishment("无");
        rewards.setStudentId("1802343301");
        int n = 0;
        try {
            n = rewardsDAO.updateRewards(rewards);
        } catch (SQLException e) {
            System.out.println("修改奖惩情况失败");
        }
        assertEquals(1,n);
    }
}