import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import io.itjun.account.AccountApplication;
import io.itjun.account.FreezeMoney;
import io.itjun.account.FreezeMoneyService;
import io.itjun.account.mapper.FreezeMoneyMapper;

/**
 * @author: peixinyi
 * @version: V1.0.0.0
 * @date: 2021-03-17 16:43
 */
@SpringBootTest(classes = AccountApplication.class)
public class FreezeMoneyTest {
    @Autowired
    FreezeMoneyMapper freezeMoneyMapper;

    @Autowired
    FreezeMoneyService freezeMoneyService;

    private static int id;

    static {
        id = Integer.valueOf(String.valueOf(System.currentTimeMillis()).substring(5));
    }

    @Test
    public void addFreezeMoneyTest() {
        FreezeMoney freezeMoney = new FreezeMoney();
        freezeMoney.setUserId(0);
        freezeMoney.setOrderId(id);
        freezeMoney.setCurrency(0);
        freezeMoney.setCurrencyType(0);
        freezeMoney.setStatus(0);
        freezeMoney.setCreateTime(0);
        freezeMoney.setUpdateTime(0);
        Assert.assertTrue(freezeMoneyService.addFreezeMoney(freezeMoney.getUserId(), freezeMoney));

        freezeMoney.setUserId(1);
        Assert.assertTrue(freezeMoneyService.addFreezeMoney(freezeMoney.getUserId(), freezeMoney));
    }

    @Test
    public void getFreezeMoneyTest() {
        FreezeMoney f0 = freezeMoneyService.getFreezeMoney(0, id);
        FreezeMoney f1 = freezeMoneyService.getFreezeMoney(1, id);
        Assert.assertNotNull(f0);
        Assert.assertNotNull(f1);
        System.out.println(f0.toString());
        System.out.println(f1.toString());
    }

    @Test
    public void updateFreezeMoneyStatus() {
        Assert.assertTrue(freezeMoneyService.updateFreezeMoneyStatus(0, id, -99));
    }
}
