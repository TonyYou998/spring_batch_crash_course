package com.uit.springbatch.config;

import com.uit.springbatch.listener.FirstJobListener;
import com.uit.springbatch.listener.FirstStepListener;
import com.uit.springbatch.processor.FirstItemProcessor;
import com.uit.springbatch.reader.FirstItemReader;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.uit.springbatch.service.SecondTasklet;
import com.uit.springbatch.writer.FirstItemWriter;

@Configuration
public class SampleJob {
    @Autowired
    private FirstStepListener firstStepListener;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private SecondTasklet secondTasklet;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private FirstJobListener firstJobListener;

    @Autowired
    private FirstItemReader firstItemReader;

    @Autowired
    private FirstItemProcessor firstItemProcessor;

    @Autowired
    private FirstItemWriter firstItemWriter;
    @Bean
    public Job firstJob(){
        System.out.println("run job");
            return jobBuilderFactory.get("First Job")
                    .incrementer(new RunIdIncrementer())
                    .start(firstStep())
                    .next(secondStep())
                    .listener(firstJobListener)
                    .build();

    }


    private Step firstStep(){
        return stepBuilderFactory.get("First Step")
                .tasklet(firstTask())
                .listener(firstStepListener)
                .build();
    }
    private Tasklet firstTask(){
//        anonymouse class
        return  new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("This is first tasklet step");
                return RepeatStatus.FINISHED;
            }
        };
    }
    private Step secondStep(){
        return stepBuilderFactory.get("Second Step")
                .tasklet(secondTasklet)
                .build();
    }
//     private Tasklet secondTask(){
// //        anonymouse class
//         return  new Tasklet() {
//             @Override
//             public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                 System.out.println("This is second tasklet step");
//                 return RepeatStatus.FINISHED;
//             }
//         };
//     }
   @Bean
    public Job secondJob(){
        return  jobBuilderFactory.get("Second Job")
                .incrementer(new RunIdIncrementer())
                .start(firstChunkStep())
                .next(secondStep())
                .build();

    }
    private Step firstChunkStep(){
        return stepBuilderFactory.get("Chuck step")
        .<Integer,Integer>chunk(3)
        .reader(firstItemReader)
//        .processor(firstItemProcessor)
        .writer(firstItemWriter)
        .build();

    }

}
