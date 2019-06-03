package com.sm.service.impl;

import com.sm.entity.Rewards;
import com.sm.factory.ServiceFacotry;
import com.sm.service.RewardsService;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class RewardsServiceImplTest {
    private RewardsService rewardsService = ServiceFacotry.getRewardsServiceInstance();

    @Test
    public void selectAll() {
        List<Rewards> rewardsList = rewardsService.selectAll();
        rewardsList.forEach(rewards -> System.out.println(rewards));
    }

    @Test
    public void selectByKeywords() {
        List<Rewards> rewardsList = rewardsService.selectByKeywords("机");
        rewardsList.forEach(rewards -> System.out.println(rewards));
    }

    @Test
    public void updateRewards() {
        Rewards rewards = new Rewards();
        rewards.setAward("校级舞蹈大赛一等奖");
        rewards.setPunishment("无");
        rewards.setStudentId("1802343301");
        int n = 0;
        n = rewardsService.updateRewards(rewards);
        assertEquals(1,n);
    }
}