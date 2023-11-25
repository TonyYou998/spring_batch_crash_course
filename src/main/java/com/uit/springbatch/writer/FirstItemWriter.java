package com.uit.springbatch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class FirstItemWriter implements ItemWriter<Integer> {

    @Override
    public void write(List<? extends Integer> items) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("Inside item writer");
        items.stream().forEach(System.out::println);
    }
    
}
