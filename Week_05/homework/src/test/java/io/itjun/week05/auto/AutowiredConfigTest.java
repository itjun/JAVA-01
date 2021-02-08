package io.itjun.week05.auto;

import io.itjun.week05.auto.AutowiredConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AutowiredConfig.class})
public class AutowiredConfigTest {

    @Autowired
    AutowiredConfig config;

    @Test
    public void test() {
        config.hello();
    }

}
