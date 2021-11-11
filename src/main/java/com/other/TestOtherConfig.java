package com.other;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("otherConfiguration")
public class TestOtherConfig {

    @Bean
    @ConditionalOnMissingBean(name = "testOther")
    public TestOther getTestOther() {
        return new TestOther();
    }
}
