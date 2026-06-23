package com.northernArc.springDao.config;

import com.northernArc.springDao.dao.*;
import com.northernArc.springDao.ui.FlightConsoleController;
import com.northernArc.springDao.ui.TodoConsoleController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import java.util.Scanner;

@Configuration
@ComponentScan("com.northernArc.springDao")
public class MySpringConfig {


//    @Bean
//    public TodoDao todoDaoCollection() {
//        return new TodoDaoImplCollection();
//    }
//
//    @Bean
//    public TodoDaoJdbc todoDaoJdbc() {
//        return new TodoDaoImplJdbc();
//    }

    @Bean
    public Scanner scanner(){
        return new Scanner(System.in);
    }

//    @Bean
//    public FlightDaoImpl flightDaoImpl(){
//        return new FlightDaoImpl();
//    }


//    @Bean("consoleControllerCollection")
//    public TodoConsoleController consoleController(Scanner scanner, TodoDao todoDao){
//        return new TodoConsoleController(scanner,todoDao);
//    }
//
//    @Bean("consoleControllerJdbc")
//    public TodoConsoleController consoleControllerJdbc(Scanner scanner, TodoDaoJdbc todoDao){
//        return new TodoConsoleController(scanner, todoDao);
//    }

//    @Bean
//    public FlightConsoleController flightConsoleController(Scanner scanner,  FlightDaoImpl flightDaoImpl){
//        return new FlightConsoleController(scanner, flightDaoImpl);
//    }
}
