package com.epam.training.ticketservice.accountmanager.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class UserDto {
    String userName;

    boolean isAdmin;

    public static UserDto createUser(String userName, boolean isAdmin) {
        return UserDto.builder().userName(userName).isAdmin(isAdmin).build();
    }

}
