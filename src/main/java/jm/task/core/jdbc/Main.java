package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("John", "Doe", (byte) 20);
        userService.saveUser("Jack", "Black", (byte) 21);
        userService.saveUser("Tom", "Thum", (byte) 22);
        userService.saveUser("Cris", "Cross", (byte) 23);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
