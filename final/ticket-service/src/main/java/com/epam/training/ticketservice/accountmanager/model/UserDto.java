package com.epam.training.ticketservice.accountmanager;


import com.epam.training.ticketservice.bookingmanager.Book;
import lombok.*;

@Builder
@AllArgsConstructor
@Data
public class UserDto {
    String userName;
    String userPassword;
    boolean isAdmin;
    Book book;

    public static UserDto createUser(String userName, String userPassword, boolean isAdmin) {
        return UserDto.builder()
                .userName(userName)
                .userPassword(userPassword)
                .isAdmin(isAdmin)
                .build();
    }

}
