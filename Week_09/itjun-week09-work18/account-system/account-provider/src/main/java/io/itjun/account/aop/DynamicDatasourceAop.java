package io.itjun.account.aop;

import io.itjun.account.config.DataSourceTypeManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author: peixinyi
 * @version: V1.0.0.0
 * @date: 2021-03-16 15:47
 */
@Aspect
@Component
public class DynamicDatasourceAop {

    private Logger logger = LoggerFactory.getLogger(DynamicDatasourceAop.class);

    @Pointcut("@annotation(io.itjun.account.aop.ChangeDB)")
    public void changeDB() {
    }

    @Before("changeDB()")
    public void beforeChangeDB(JoinPoint point) {
        Integer userID = (Integer) point.getArgs()[0];
        String db = "db" + (userID % 2);
        logger.info("do - beforeChangeDB->" + db);
        DataSourceTypeManager.setDataSourceType(db);
    }

    @After("changeDB()")
    public void afterChangeDB() {
        logger.info("do - afterChangeDB");
        DataSourceTypeManager.resetDataSourceType();
    }
}
