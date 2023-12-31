package com.uit.springbatch.controller;

import com.uit.springbatch.service.JobService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;



@RestController
@RequestMapping("/api/job")
public class JobController {
    @Autowired
    private JobOperator jobOperator;
    @Autowired
    private JobService jobService;
    @GetMapping("/start/{jobName}")
    public String startJob(@PathVariable String jobName) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

        jobService.startJob(jobName);
        return  "Job Started";

    }
    @GetMapping("/stop/{executionId}")
    public String stopJob(@PathVariable("executionId") String executionId){
        try{
               jobOperator.stop(Long.valueOf(executionId));
        return "Stopped";
        }
        catch(Exception ex){
             return ex.getMessage();
        }
     
    }
}
