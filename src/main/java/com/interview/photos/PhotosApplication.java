package com.interview.photos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PhotosApplication {

   public static void main(String[] args) {
      SpringApplication.run(PhotosApplication.class, args);
   }

}
