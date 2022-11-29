package com.epam.training.ticketservice.accountmanager;

import com.epam.training.ticketservice.accountmanager.model.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<UserDto> getUserByName(String userName);

    Optional<UserDto> getUser(String userName, String userPassword);

    void createUser(String userName, String userPassword, boolean isAdmin);
}
