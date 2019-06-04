package com.sm.dao.impl;

import com.sm.dao.RewardsDAO;
import com.sm.entity.Rewards;
import com.sm.entity.Student;
import com.sm.factory.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Date;
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
        rewards.setAwardPunishment("晚归");
        rewards.setKind("惩");
        rewards.setId(1);
        int n = 0;
        try {
            n = rewardsDAO.updateRewards(rewards);
        } catch (SQLException e) {
            System.out.println("修改奖惩情况失败");
        }
        assertEquals(1,n);
    }

    @Test
    public void insert() {
        Rewards rewards = new Rewards();
        rewards.setId(32);
        rewards.setStudentId("18023433301");
        rewards.setStudentName("白建坤");
        rewards.setAwardPunishment("晚归");
        rewards.setKind("惩");
        rewards.settDate(new Date());
        int n = 0;
        try {
            n = rewardsDAO.insert(rewards);
        } catch (SQLException e) {
            System.out.println("新增失败");
        }
        assertEquals(1,n);

    }

}