package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserServiceImpl;



public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Neil","Alishev", (byte) 25);
        userService.saveUser("Zaur","Tregulov", (byte) 35);
        userService.saveUser("Alexey","Vladykin", (byte) 45);
        userService.saveUser("Claude","Shannon", (byte) 109);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
