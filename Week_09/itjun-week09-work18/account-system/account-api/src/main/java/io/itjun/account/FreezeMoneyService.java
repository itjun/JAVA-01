package io.itjun.account;

/**
 * @author: peixinyi
 * @version: V1.0.0.0
 * @date: 2021-03-17 09:56
 */
public interface FreezeMoneyService {
    /**
     * 添加冻结订单
     *
     * @param freezeMoney
     * @return
     */
    Boolean addFreezeMoney(int userID, FreezeMoney freezeMoney);

    /**
     * 获取冻结订单信息
     *
     * @param userID
     * @param orderID
     * @return
     */
    FreezeMoney getFreezeMoney(int userID, int orderID);

    /**
     * 修改冻结订单状态
     *
     * @param userID
     * @param orderID
     * @param status
     * @return
     */
    Boolean updateFreezeMoneyStatus(int userID, int orderID, int status);

    /**
     * 获取冻结信息
     *
     * @param userID
     * @param orderID
     * @param status
     * @return
     */
    FreezeMoney getFreeMoneyStats(int userID, int orderID, int status);

}
