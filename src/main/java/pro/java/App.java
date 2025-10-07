package pro.java;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pro.java.dto.User;
import pro.java.service.impl.UserService;

import java.util.Collection;

/**
 * Home task-5
 *
 */
@SpringBootApplication
@RequiredArgsConstructor
public class App implements CommandLineRunner {
    private final UserService userService;

    public static void main(String[] args) {

        SpringApplication.run(App.class);

    }

    @Override
    public void run(String... args) throws Exception {
        Collection<User> all = userService.findAll();
        System.out.println(all);

        User ivan = userService.update(1L, "Ivan");
        System.out.println(ivan);

        User anna = userService.findById(2L);
        System.out.println(anna);

        all = userService.findAll();
        System.out.println(all);

        userService.delete(3L);

        all = userService.findAll();
        System.out.println(all);
    }
}
