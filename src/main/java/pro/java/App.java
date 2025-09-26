package pro.java;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import pro.java.dto.User;
import pro.java.service.impl.UserService;

import java.util.Collection;

/**
 * Home task-4
 *
 */
@ComponentScan
public class App {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        UserService userService = context.getBean(UserService.class);

        User use1 = userService.create(1L, "Ivam");
        User use2 = userService.create(2L, "Anna");
        User use3 = userService.create(3L, "Oleg");

        Collection<User> all = userService.findAll();
        System.out.println(all);

        userService.upadate(1L, "Ivan");

        all = userService.findAll();
        System.out.println(all);

        userService.delete(2L);

        all = userService.findAll();
        System.out.println(all);


    }
}
