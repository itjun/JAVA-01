package io.itjun.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 冻结资金表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FreezeMoney implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 货币金额
     */
    private Integer currency;

    /**
     * 货币类型
     */
    private Integer currencyType;

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