package io.itjun.week07;

import java.sql.Timestamp;

public interface GenerateOrderSql extends GenerateSql {

    String getId();

    /**
     * 获取消费者Id
     *
     * @return
     */
    Integer getConsumerId();

    /**
     * 获取国家
     *
     * @return
     */
    String getCountries();

    /**
     * 获取省份
     *
     * @return
     */
    String getProvinces();

    /**
     * 获取城市
     *
     * @return
     */
    String getCity();

    /**
     * 获取区域
     *
     * @return
     */
    String getAres();

    /**
     * 获取详细信息
     *
     * @return
     */
    String getDetail();

    /**
     * 获取订单价格
     *
     * @return
     */
    Integer getOrderAmount();

    /**
     * 获取物流价格
     *
     * @return
     */
    Integer getLogisticsAmount();

    /**
     * 获取物流状态
     *
     * @return
     */
    Integer getLogisticsStatus();

    /**
     * 获取订单状态
     *
     * @return
     */
    Integer getOrderStatus();

    /**
     * 获取修改时间
     *
     * @return
     */
    Timestamp getUpdateTime();

    /**
     * 获取创建时间
     *
     * @return
     */
    Timestamp getCreateTime();

    /**
     * 获取备注
     *
     * @return
     */
    String getNote();
}
