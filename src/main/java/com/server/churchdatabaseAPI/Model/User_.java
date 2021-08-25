package com.server.churchdatabaseAPI.Model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table
public class User_ {
    @Id
    @SequenceGenerator(
            name="user_sequence",
            sequenceName="user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy= GenerationType.SEQUENCE,
            generator = "user_sequence")
    private Long Id;
    @Column(name = "user_name")
    private String Username;
    @Column(name = "pass_word")
    private String Password;
    private String FirstName;
    private String Surname;
    private String Email;
    private String Role;
    private LocalDate CreatedDate;
    private String Status;
    private String Message;
    public User_() {
    }

    public User_(String username, String password, String firstName, String surname, String email, String role, LocalDate createdDate, String status) {
        Username = username;
        Password = password;
        FirstName = firstName;
        Surname = surname;
        Email = email;
        Role = role;
        CreatedDate = createdDate;
        Status = status;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public LocalDate getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        CreatedDate = createdDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", Username='" + Username + '\'' +
                ", Password='" + Password + '\'' +
                ", FirstName='" + FirstName + '\'' +
                ", Surname='" + Surname + '\'' +
                ", Email='" + Email + '\'' +
                ", Role='" + Role + '\'' +
                ", CreatedDate=" + CreatedDate +
                ", Status='" + Status + '\'' +
                ", Message='" + Message + '\'' +
                '}';
    }


}
