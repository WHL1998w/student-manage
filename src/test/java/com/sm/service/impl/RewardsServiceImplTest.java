package com.sm.service.impl;

import com.sm.entity.Rewards;
import com.sm.factory.ServiceFacotry;
import com.sm.service.RewardsService;
import org.junit.Test;

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
}