package io.itjun.business.service;

import io.itjun.business.ForexOrder;
import io.itjun.business.ForexOrderService;
import io.itjun.business.mapper.ForexOrderMapper;
import io.itjun.business.status.ForexOrderStatus;
import org.apache.dubbo.config.annotation.DubboReference;
import org.dromara.hmily.annotation.HmilyTCC;
import org.dromara.hmily.common.exception.HmilyRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.itjun.account.Account;
import io.itjun.account.AccountService;
import io.itjun.account.FreezeMoney;
import io.itjun.account.FreezeMoneyService;
import io.itjun.business.value.ParitiesMap;

import java.util.List;
import java.util.Random;

/**
 * @author: peixinyi
 * @version: V1.0.0.0
 * @date: 2021-03-18 10:19
 */
@Service
public class ForexOrderServiceImpl implements ForexOrderService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ForexOrderMapper forexOrderMapper;

    @DubboReference
    AccountService accountService;

    @DubboReference
    FreezeMoneyService freezeMoneyService;

    @Override
    public Boolean createOrder(ForexOrder forexOrder) {
        forexOrder.setOrderId(new Random().nextInt(9999999));
        Account account = accountService.getAccount(forexOrder.getConsignmentUserId());
        if (account == null) {
            logger.info("没有当前用户");
            return false;
        }
        //判断金额是否足够
        if (!checkMoney(account, forexOrder.getCurrencyType(), forexOrder.getCurrency())) {
            logger.info("当前用户寄售的商品余额不足");
            return false;
        }
        //冻结所寄售的货币和创建寄售订单
        return createOrderAndFreezeMoney(account, forexOrder);
    }

    @Override
    public Boolean checkMoney(Account account, int type, int money) {
        switch (type) {
            case 0: {
                if (account.getCnyWallet() >= money) {
                    return true;
                }
                break;
            }
            case 1: {
                if (account.getUsdWallet() >= money) {
                    return true;
                }
                break;
            }
            default: {
                return false;
            }
        }
        return false;
    }

    @Override
    public Boolean minusMoney(int userId, int type, int money, int businessType) {

        money = (businessType == (-1)) ? -money : money;

        switch (type) {
            case 0: {
                return accountService.updateCNYWallet(userId, money);
            }
            case 1: {
                return accountService.updateUSDWallet(userId, money);
            }
            default: {
                return false;
            }
        }
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmCreateOrderAndFreezeMoney", cancelMethod = "cancelCreateOrderAndFreezeMoney")
    public Boolean createOrderAndFreezeMoney(Account account, ForexOrder forexOrder) {
        FreezeMoney freezeMoney = new FreezeMoney();
        freezeMoney.setUserId(account.getUserId());
        freezeMoney.setOrderId(forexOrder.getOrderId());
        freezeMoney.setCurrency(forexOrder.getCurrency());
        freezeMoney.setCurrencyType(forexOrder.getCurrencyType());
        return minusMoney(account.getUserId(), forexOrder.getCurrencyType(), forexOrder.getCurrency(), -1) && forexOrderMapper.insertSelective(forexOrder) && freezeMoneyService.addFreezeMoney(account.getUserId(), freezeMoney);
    }

    public void confirmCreateOrderAndFreezeMoney(Account account, ForexOrder forexOrder) {

    }

    public void cancelCreateOrderAndFreezeMoney(Account account, ForexOrder forexOrder) {
        int cancelMoney = 0;
        switch (forexOrder.getCurrencyType()) {
            case 0:
                cancelMoney = account.getCnyWallet();
                break;
            case 1:
                cancelMoney = account.getUsdWallet();
                break;
            default:
                break;
        }
        if (cancelMoney == (accountService.getAccount(account.getUserId()).getWallet(forexOrder.getCurrencyType()))) {
            accountService.updateUSDWallet(account.getUserId(), -forexOrder.getCurrency());
        }
        if (forexOrderMapper.selectByPrimaryKey(forexOrder.getId()) != null) {
            forexOrderMapper.deleteByPrimaryKey(forexOrder.getId());
        }
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmBuyOrder", cancelMethod = "cancelBuyOrder")
    public Boolean buyOrder(ForexOrder forexOrder) {

        int buyUser = forexOrder.getBuyUserId();
        int createTime = forexOrder.getCreateTime();
        //获取订单详细信息
        forexOrder = forexOrderMapper.selectByPrimaryKey(forexOrder.getId());
        if (forexOrder == null) {
            logger.info("没有该订单 -> forexOrder.getId()");
            return false;
        }
        forexOrder.setBuyUserId(buyUser);
        forexOrder.setCreateTime(createTime);

        //处理幂等
        if (forexOrderMapper.selectByPrimaryKey(forexOrder.getId()).getBuyUserId() != 0) {
            logger.info("订单已经存在请勿操作 -> id:{} - orderId:{}", forexOrder.getId(), forexOrder.getOrderId());
            return false;
        }

        Account account = accountService.getAccount(forexOrder.getBuyUserId());
        if (account == null) {
            logger.info("没有当前用户");
            return false;
        }
        //汇率
        Double parities = ParitiesMap.getParities(forexOrder.getCurrencyType(), forexOrder.getTargetType());
        if (parities == null) {
            logger.info("没有该汇率");
            return false;
        }
        int targetMoney = (int) (parities * forexOrder.getCurrency());
        // 判断金额是否足够
        if (!checkMoney(account, forexOrder.getTargetType(), targetMoney)) {
            logger.info("当前用户寄售的商品余额不足");
            return false;
        }
        // 提交订单
        forexOrderMapper.updateByPrimaryKey(forexOrder);

        // 冻结购买者货币
        // 扣除金额
        minusMoney(account.getUserId(), forexOrder.getTargetType(), targetMoney, -1);
        FreezeMoney freezeMoney = new FreezeMoney();
        freezeMoney.setUserId(forexOrder.getBuyUserId());
        freezeMoney.setOrderId(forexOrder.getOrderId());
        freezeMoney.setCurrency(targetMoney);
        freezeMoney.setCurrencyType(forexOrder.getTargetType());
        freezeMoneyService.addFreezeMoney(forexOrder.getBuyUserId(), freezeMoney);
        // 修改订单状态
        forexOrder.setStatus(1);
        forexOrderMapper.updateByPrimaryKey(forexOrder);
        return transfer(forexOrder);
    }

    @Override
    public Boolean transfer(ForexOrder forexOrder) {
        //获取冻结信息
        FreezeMoney buyFreezeMoney = freezeMoneyService.getFreezeMoney(forexOrder.getBuyUserId(), forexOrder.getOrderId());
        FreezeMoney sellFreezeMoney = freezeMoneyService.getFreezeMoney(forexOrder.getConsignmentUserId(), forexOrder.getOrderId());

        //处理买家账户
        minusMoney(forexOrder.getBuyUserId(), sellFreezeMoney.getCurrencyType(), sellFreezeMoney.getCurrency(), 1);
        freezeMoneyService.updateFreezeMoneyStatus(forexOrder.getBuyUserId(), forexOrder.getOrderId(), 99);

        //人为制造异常
        if (forexOrder.getCreateTime() == 8888) {
            throw new HmilyRuntimeException("人为制造异常");
        }

        //处理卖家账户
        minusMoney(forexOrder.getConsignmentUserId(), buyFreezeMoney.getCurrencyType(), buyFreezeMoney.getCurrency(), 1);
        freezeMoneyService.updateFreezeMoneyStatus(forexOrder.getConsignmentUserId(), forexOrder.getOrderId(), 99);
        return true;
    }

    @Override
    public Boolean confirmBuyOrder(ForexOrder forexOrder) {
        System.out.println("1111");
        logger.info("开始转账->{},{}", forexOrder.getBuyUserId(), forexOrder.getConsignmentUserId());
        return true;
    }

    @Override
    public Boolean cancelBuyOrder(ForexOrder forexOrder) {
        System.out.println("2222");
        logger.info("触发回滚操作->cancelBuyOrder");

        forexOrder = forexOrderMapper.selectByPrimaryKey(forexOrder.getId());
        forexOrder.setStatus(-99);
        forexOrderMapper.updateByPrimaryKeySelective(forexOrder);
        if (forexOrder == null) {
            logger.info("没有该订单 -> forexOrder.getId()");
            return false;
        }

        FreezeMoney buyFreezeMoney = freezeMoneyService.getFreeMoneyStats(forexOrder.getBuyUserId(), forexOrder.getOrderId(), 99);
        //判断买家用户是否操作
        if (buyFreezeMoney != null) {
            minusMoney(forexOrder.getBuyUserId(), forexOrder.getCurrencyType(), forexOrder.getCurrency(), -1);
            freezeMoneyService.updateFreezeMoneyStatus(forexOrder.getBuyUserId(), forexOrder.getOrderId(), 0);
        } else {
            //因为buy都没有成功那么 sell也不会进行操作
            return true;
        }
        FreezeMoney sellFreezeMoney = freezeMoneyService.getFreeMoneyStats(forexOrder.getConsignmentUserId(), forexOrder.getOrderId(), 99);
        //判断买家用户是否操作
        if (sellFreezeMoney != null) {
            minusMoney(forexOrder.getConsignmentUserId(), forexOrder.getTargetType(), buyFreezeMoney.getCurrency(), -1);
            freezeMoneyService.updateFreezeMoneyStatus(forexOrder.getConsignmentUserId(), forexOrder.getOrderId(), 0);
        }
        return true;
    }

    @Override
    public Boolean updateOrderStatus(int id, ForexOrderStatus forexOrderStatus) {
        ForexOrder forexOrder = new ForexOrder();
        forexOrder.setId(id);
        forexOrder.setStatus(forexOrderStatus.getStatus());
        return forexOrderMapper.updateByPrimaryKey(forexOrder);
    }

    @Override
    public ForexOrder getForexOrder(int id) {
        return forexOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ForexOrder> getForexOrderList() {
        return forexOrderMapper.selectOrder();
    }
}

