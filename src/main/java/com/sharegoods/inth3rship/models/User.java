package com.sharegoods.inth3rship.models;

import com.sharegoods.inth3rship.helpers.hash.HashPassword;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Column(unique=true)
    private String email;

    @NotNull
    private String password;

    public User() {  // Default constructor for JPA
    }

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = HashPassword.getPasswordHash(password.getBytes(), "SHA-512");
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
