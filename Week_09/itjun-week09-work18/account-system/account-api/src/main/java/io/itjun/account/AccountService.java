package io.itjun.account;

/**
 * @author peixinyi
 */
public interface AccountService {
    /**
     * 获取钱包信息
     *
     * @param userId
     * @return
     */
    Account getAccount(int userId);

    /**
     * 修改人民币钱包
     *
     * @param cnyWallet
     * @return
     */
    Boolean updateCNYWallet(int userId, int cnyWallet);

    /**
     * 修改美元钱包
     *
     * @param userId
     * @param cnyWallet
     * @return
     */
    Boolean updateUSDWallet(int userId, int cnyWallet);

}
