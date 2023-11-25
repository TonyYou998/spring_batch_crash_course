package com.uit.springbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableBatchProcessing
@EnableAsync
@EnableScheduling
@ComponentScan({"com.uit.springbatch.controller","com.uit.springbatch.writer","com.uit.springbatch.processor","com.uit.springbatch.config","com.uit.springbatch.service","com.uit.springbatch.listener","com.uit.springbatch.reader"})
public class SpringBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchApplication.class, args);
    }

}
