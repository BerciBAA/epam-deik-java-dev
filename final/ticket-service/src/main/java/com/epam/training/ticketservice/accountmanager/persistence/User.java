package com.epam.training.ticketservice.accountmanager.persistence;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
public class User {


    @GeneratedValue
    @Id
    private Integer id;
    @Column(unique = true)
    String userName;
    String userPassword;
    boolean isAdmin;

    public User(String userName, String userPassword, boolean isAdmin) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.isAdmin = isAdmin;
    }


}
