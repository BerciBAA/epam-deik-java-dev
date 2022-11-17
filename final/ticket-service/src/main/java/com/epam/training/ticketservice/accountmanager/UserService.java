package com.epam.training.ticketservice.accountmanager;

import java.util.List;
import java.util.Optional;

public interface AdminUserService {

    List<Admin> getUserList();

    Optional<Admin> getUserByName(String userName);

    Optional<Admin> getUser(String userName, String userPassword);

    void createUser(String userName, String userPassword);
}
