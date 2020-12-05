package com.gis.snake;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@ComponentScan(basePackages = {"com.gis.snake.crawler"})
@SpringBootApplication(scanBasePackages = {"com.gis.snake"})
@MapperScan("com.gis.snake.mapper")
public class App {

    public static void  main(String[] args){
        SpringApplication.run(App.class,args);
    }

}
