package io.itjun.business;

import org.dromara.hmily.annotation.Hmily;
import io.itjun.account.Account;
import io.itjun.business.status.ForexOrderStatus;

import java.util.List;

/**
 * @author: peixinyi
 * @version: V1.0.0.0
 * @date: 2021-03-18 10:08
 */
public interface ForexOrderService {
    /**
     * 创建订单
     *
     * @param forexOrder
     * @return
     */
    Boolean createOrder(ForexOrder forexOrder);

    /**
     * 检查金额是否足够
     *
     * @param account
     * @param forexOrder
     * @return
     */
    Boolean checkMoney(Account account, int type, int money);

    /**
     * 减去金额
     *
     * @param account
     * @param forexOrder
     * @return
     */
    Boolean minusMoney(int userId, int type, int money, int businessType);

    /**
     * 创建订单和冻结资金
     *
     * @param account
     * @param forexOrder
     * @return
     */
    Boolean createOrderAndFreezeMoney(Account account, ForexOrder forexOrder);

    /**
     * @param id
     * @param buyUserId
     * @return
     */
    @Hmily
    Boolean buyOrder(ForexOrder forexOrder);

    /**
     * 修改订单状态
     *
     * @param id
     * @param forexOrderStatus
     * @return
     */
    Boolean updateOrderStatus(int id, ForexOrderStatus forexOrderStatus);

    @Hmily
    Boolean transfer(ForexOrder forexOrder);

    @Hmily
    Boolean confirmBuyOrder(ForexOrder forexOrder);

    @Hmily
    Boolean cancelBuyOrder(ForexOrder forexOrder);

    /**
     * @param id
     * @return
     */
    ForexOrder getForexOrder(int id);

    /**
     * 获取订单列表
     *
     * @return
     */
    List<ForexOrder> getForexOrderList();

}
