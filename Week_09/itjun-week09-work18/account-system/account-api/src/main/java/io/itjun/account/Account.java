package io.itjun.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 账户表
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 人民币钱包
     */
    private Integer cnyWallet;

    /**
     * 美元钱包
     */
    private Integer usdWallet;

    /**
     * 创建时间时间戳
     */
    private Long createTime;

    /**
     * 修改时间时间戳
     */
    private Long updateTime;

    public Integer getWallet(int type) {
        switch (type) {
            case 0:
                return cnyWallet;
            case 1:
                return usdWallet;
            default:
                return 0;
        }
    }
}