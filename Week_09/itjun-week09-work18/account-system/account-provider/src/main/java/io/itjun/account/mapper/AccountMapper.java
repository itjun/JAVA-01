package io.itjun.account.mapper;

import io.itjun.account.Account;
import org.apache.ibatis.annotations.*;

/**
 * @author: peixinyi
 * @version: V1.0.0.0
 * @date: 2021-03-16 14:44
 */
public interface AccountMapper {

    @Select("select id,user_id,cny_wallet,usd_wallet,create_time,update_time from account where user_id=#{userId};")
    @Results(id = "accountResults", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "cnyWallet", column = "cny_wallet"),
            @Result(property = "usdWallet", column = "usd_wallet"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
    })
    Account queryAccount(@Param("userId") Integer userId);

    @Update("update account set cny_wallet = cny_wallet+#{cnyWallet} where user_id=#{userId};")
    Boolean updateCNYWallet(@Param("cnyWallet") int cnyWallet, @Param("userId") int userId);

    @Update("update account set usd_wallet = usd_wallet+#{usdWallet} where user_id=#{userId};")
    Boolean updateUSDWallet(@Param("usdWallet") int usdWallet, @Param("userId") int userId);
}
