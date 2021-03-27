import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import io.itjun.account.Account;
import io.itjun.account.AccountApplication;
import io.itjun.account.AccountService;
import io.itjun.account.mapper.AccountMapper;

/**
 * @author: peixinyi
 * @version: V1.0.0.0
 * @date: 2021-03-16 14:59
 */
@SpringBootTest(classes = AccountApplication.class)
public class AccountTest {

    @Autowired
    AccountMapper accountMapper;

    @Autowired
    AccountService accountService;

    @Test
    public void changeDB0Test() {
        Assert.assertTrue(accountService.updateCNYWallet(10000, 1));
        Assert.assertTrue(accountService.updateUSDWallet(10000, 1));
        Assert.assertTrue(accountService.updateCNYWallet(10000, -1));
        Assert.assertTrue(accountService.updateUSDWallet(10000, -1));
    }

    @Test
    public void changeDB1Test() {
        Assert.assertTrue(accountService.updateCNYWallet(10001, 1));
        Assert.assertTrue(accountService.updateUSDWallet(10001, 1));
        Assert.assertTrue(accountService.updateCNYWallet(10001, -1));
        Assert.assertTrue(accountService.updateUSDWallet(10001, -1));
    }

    @Test
    public void getAccount() {
        Account account10000 = accountService.getAccount(10000);
        System.out.println(account10000.toString());
        Assert.assertNotNull(account10000);
        Account account10001 = accountService.getAccount(10001);
        Assert.assertNotNull(account10001);
        System.out.println(account10001.toString());
    }

}
