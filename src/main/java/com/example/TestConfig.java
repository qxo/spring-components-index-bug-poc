package com.example;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.sub.TestCmp;

@Configuration("testConfiguration")
public class TestConfig {

    @Bean
    @ConditionalOnMissingBean(name = "testCmp")
    public TestCmp getTestCmp() {
        return new TestCmp();
    }
}
