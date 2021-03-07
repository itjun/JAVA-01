package io.itjun.week07.work14.aspect;

import io.itjun.week07.work14.entity.Order;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Random;

@Aspect
@Component
public class SlaveAspect {

    @Autowired
    List<DataSource> slaveDatasourceList;

    @Pointcut("@annotation(io.itjun.week07.work14.annotaion.Slave)")
    public void slave() {
    }

    @Around("slave()")
    public Order setDataSource(ProceedingJoinPoint point) throws Throwable {
        Object[] arg = point.getArgs();
        DataSource dataSource = slaveDatasourceList.get(new Random().nextInt(slaveDatasourceList.size()));
        arg[0] = dataSource;
        return (Order) point.proceed(arg);
    }

}
