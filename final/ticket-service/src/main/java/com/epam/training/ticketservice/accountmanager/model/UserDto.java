package com.epam.training.ticketservice.accountmanager.model;


import com.epam.training.ticketservice.bookingmanager.Book;
import lombok.*;

@Builder
@AllArgsConstructor
@Data
public class UserDto {
    String userName;

    boolean isAdmin;

    public static UserDto createUser(String userName, boolean isAdmin) {
        return UserDto.builder()
                .userName(userName)
                .isAdmin(isAdmin)
                .build();
    }

}
