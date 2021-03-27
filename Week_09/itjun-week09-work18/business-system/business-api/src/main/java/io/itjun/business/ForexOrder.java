package io.itjun.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForexOrder {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 寄售用户id
     */
    private Integer consignmentUserId;

    /**
     * 寄售用户id
     */
    private Integer buyUserId;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 订单类型
     */
    private Integer orderType;

    /**
     * 货币金额
     */
    private Integer currency;

    /**
     * 货币类型
     */
    private Integer currencyType;
    /**
     * 目标货币
     */
    private Integer targetType;

    /**
     * 冻结状态
     */
    private Integer status;

    /**
     * 创建时间时间戳
     */
    private Integer createTime;

    /**
     * 修改时间时间戳
     */
    private Integer updateTime;
}

