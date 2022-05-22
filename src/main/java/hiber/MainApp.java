package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("user1", "lastname1", "user1@mail.com");
        User user2 = new User("user2", "lastname2", "user2@mail.com");
        User user3 = new User("user3", "lastname3", "user3@mail.com");
        User user4 = new User("user4", "lastname4", "user4@mail.com");

        Car car1 = new Car("Tesla model S", 1020);
        Car car2 = new Car("Tesla model X", 1030);
        Car car3 = new Car("Tesla model Y", 1040);
        Car car4 = new Car("Tesla model 3", 1050);

        userService.add(user1.setCar(car1).setUser(user1));
        userService.add(user2.setCar(car2).setUser(user2));
        userService.add(user3.setCar(car3).setUser(user3));
        userService.add(user4.setCar(car4).setUser(user4));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        for (User user : userService.listUsers()) {
            System.out.println(user + " " + user.getCar());
        }

        System.out.println("Show user info car model: ");

        System.out.println(userService.getUserCar("Tesla model X", 1030));

        context.close();
    }
}
