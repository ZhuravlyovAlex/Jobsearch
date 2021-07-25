package ru.innopolis.jobsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLOutput;

@SpringBootApplication
public class JobsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobsearchApplication.class, args);
        System.out.println("hello world!!!");
    }

}
