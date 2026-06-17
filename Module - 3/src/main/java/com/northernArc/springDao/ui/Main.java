package com.northernArc.springDao.ui;

import com.northernArc.springDao.config.MySpringConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context= new AnnotationConfigApplicationContext(MySpringConfig.class);

        // collections
//        TodoConsoleController todoConsoleControllerCollection = context.getBean("consoleControllerCollection", TodoConsoleController.class);
//        todoConsoleControllerCollection.printWelcomeMessage();
//        todoConsoleControllerCollection.showMenu();
//

        // jdbc
//        TodoConsoleController todoConsoleControllerJdbc = context.getBean("consoleControllerJdbc", TodoConsoleController.class);
//        todoConsoleControllerJdbc.printWelcomeMessage();
//        todoConsoleControllerJdbc.showMenu();


        FlightConsoleController controller = context.getBean(FlightConsoleController.class);
        controller.showWelcomeMessage();
        controller.showMenu();

    }
}
