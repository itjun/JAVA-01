package io.itjun.account.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.itjun.account.FreezeMoney;
import io.itjun.account.FreezeMoneyService;
import io.itjun.account.aop.ChangeDB;
import io.itjun.account.mapper.FreezeMoneyMapper;

/**
 * @author: peixinyi
 * @version: V1.0.0.0
 * @date: 2021-03-17 16:01
 */
@DubboService
@Service
public class FreezeMoneyServiceImpl implements FreezeMoneyService {

    @Autowired
    FreezeMoneyMapper freezeMoneyMapper;

    @Override
    @ChangeDB
    public Boolean addFreezeMoney(int userID, FreezeMoney freezeMoney) {
        return freezeMoneyMapper.addFreezeMoney(freezeMoney);
    }

    @Override
    @ChangeDB
    public FreezeMoney getFreezeMoney(int userID, int orderID) {
        return freezeMoneyMapper.getFreezeMoney(userID, orderID);
    }

    @Override
    @ChangeDB
    public Boolean updateFreezeMoneyStatus(int userID, int orderID, int status) {
        return freezeMoneyMapper.updateFreezeMoneyStatus(userID, orderID, status);
    }

    @Override
    @ChangeDB
    public FreezeMoney getFreeMoneyStats(int userID, int orderID, int status) {
        return freezeMoneyMapper.getFreezeMoneyStatus(userID, orderID, status);
    }
}
