package com.zykj.forum.config;

import com.zykj.forum.image.ImageManager;
import com.zykj.forum.image.ImageMonitor;
import com.zykj.forum.image.UuidNameGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImageConfiguration {


    @Bean
    public ImageManager imageManager(){
        ImageManager imageManager = new ImageManager();
        imageManager.setNameGenerator(new UuidNameGenerator());
        imageManager.setImageMonitor(new ImageMonitor());
        imageManager.setPath("D:/");
        return imageManager;
    }
}
