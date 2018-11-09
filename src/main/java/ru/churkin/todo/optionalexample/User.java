package ru.churkin.todo.optionalexample;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Denis, 09.11.2018
 */
@Getter @Setter @NoArgsConstructor @ToString @AllArgsConstructor
public class User {

    private String firstName;
    private String lastName;
    private int age;

    public static void main(String[] args) {
        User user = new User();
        user.setFirstName("Vasya");
        System.out.println(user);
    }
}
