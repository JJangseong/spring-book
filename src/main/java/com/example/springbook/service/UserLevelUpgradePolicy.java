package com.example.springbook.service;

import com.example.springbook.domain.user.User;

public interface UserLevelUpgradePolicy {
    boolean canUpgradeLevels(User user);

    void upgradeLevel(User user);
}
