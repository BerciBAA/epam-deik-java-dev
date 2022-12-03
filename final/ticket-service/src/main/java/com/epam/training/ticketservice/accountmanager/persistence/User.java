package com.epam.training.ticketservice.accountmanager.persistence;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
public class User {


    @Column(unique = true)
    String userName;
    String userPassword;
    boolean isAdmin;
    @GeneratedValue
    @Id
    private Integer id;

    public User(String userName, String userPassword, boolean isAdmin) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.isAdmin = isAdmin;
    }


}
