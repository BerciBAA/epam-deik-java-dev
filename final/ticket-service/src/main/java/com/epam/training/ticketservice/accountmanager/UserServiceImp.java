package com.epam.training.ticketservice.accountmanager;

import com.epam.training.ticketservice.accountmanager.model.UserDto;
import com.epam.training.ticketservice.accountmanager.persistence.User;
import com.epam.training.ticketservice.accountmanager.persistence.UserRepository;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class  UserServiceImp implements UserService {

    UserRepository userRepository;

    @Override
    public Optional<UserDto> getUserByName(String userName) {
        Optional<User> dbUser = userRepository.findByUserName(userName);
        if(dbUser.isEmpty()){
            return Optional.empty();
        }
        UserDto user  = new UserDto(dbUser.get().getUserName(),dbUser.get().isAdmin());
        return Optional.of(user);
    }

    @Override
    public void createUser(String userName, String userPassword, boolean isAdmin) {
        User user = new User(userName,userPassword,isAdmin);
        userRepository.save(user);
    }

    @Override
    public Optional<UserDto> getUser(String userName, String userPassword) {
        Optional<User> dbUser = userRepository.findByUserNameAndUserPassword(userName,userPassword);
        if(dbUser.isEmpty()){
            return Optional.empty();
        }
        UserDto user  = new UserDto(dbUser.get().getUserName(),dbUser.get().isAdmin());
        return Optional.of(user);
    }
}
