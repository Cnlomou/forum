package com.zykj.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ForumPostsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ForumPostsApplication.class,args);
    }
}
