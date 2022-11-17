package com.epam.training.ticketservice.accountmanager;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class AdminUserServiceImp implements AdminUserService {

    private List<Admin> adminsList;

    public void initUser() {
        adminsList = new LinkedList<>(List.of(Admin.createUser("admin", "admin")));
    }

    @Override
    public List<Admin> getUserList() {
        return adminsList;
    }

    @Override
    public Optional<Admin> getUserByName(String userName) {
        return adminsList.stream().filter(admin -> admin.getUserName().equals(userName)).findFirst();
    }

    @Override
    public void createUser(String userName, String userPassword) {
        Admin admin = Admin.createUser(userName, userPassword);
        adminsList.add(admin);
    }

    @Override
    public Optional<Admin> getUser(String userName, String userPassword) {
        return adminsList.stream().filter(admin -> admin.getUserName().equals(userName)
                && admin.getUserPassword().equals((userPassword))).findFirst();
    }
}
