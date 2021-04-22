package com.example.springbook.service;

import com.example.springbook.dao.user.UserDao;
import com.example.springbook.domain.user.Level;
import com.example.springbook.domain.user.User;

import static com.example.springbook.service.UserService.MIN_LOG_COUNT_FOR_SILVER;
import static com.example.springbook.service.UserService.MIN_RECOMMEND_FOR_GOLD;

public class UserLevelUpgrade implements UserLevelUpgradePolicy{
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void upgradeLevel(User user) {
        user.upgradeLevel();
        userDao.update(user);
    }

    public boolean canUpgradeLevels(User user) {
        Level currentLevel = user.getLevel();
        switch (currentLevel) {
            case BASIC:
                return (user.getLogin() >= MIN_LOG_COUNT_FOR_SILVER);
            case SILVER:
                return (user.getRecommend() >= MIN_RECOMMEND_FOR_GOLD);
            case GOLD:
                return false;
            default:
                throw new IllegalArgumentException("Unknown Level:" + currentLevel);
        }
    }
}
