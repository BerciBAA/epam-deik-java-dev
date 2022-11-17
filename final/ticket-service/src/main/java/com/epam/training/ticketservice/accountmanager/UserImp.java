package com.epam.training.ticketservice.accountmanager;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.nio.file.FileAlreadyExistsException;
import java.util.Objects;

@Builder
@Value
public class User {

    String userName;
    String userPassword;


    public static User createUsers(String userName, String userPassword){
        return User.builder()
                .userName(userName)
                .userPassword(userPassword)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName) && Objects.equals(userPassword, user.userPassword);
    }


}
