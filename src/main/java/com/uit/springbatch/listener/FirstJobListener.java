package com.uit.springbatch.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class FirstJobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Run before job is executed "+jobExecution.getJobInstance().getJobName());
        System.out.println("Job param"+jobExecution.getJobParameters().getParameters());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("Run after job is executed "+jobExecution.getJobInstance().getJobName());
        System.out.println("Job param"+jobExecution.getJobParameters().getParameters());
    }
}
