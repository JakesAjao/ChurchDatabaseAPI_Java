package com.jakesajao.Model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

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
    @Transient
    @Column(name = "pwd")
    private String password;//
    private String firstName;
    private String lastName;
    private String mobilephone;
    private String email;
    private String role;
    private LocalDate CreatedDate;
    private String birthdate;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;
    //String firstName, String lastName, String mobilephone, String email, String role, LocalDate createdDate, String birthdate, String gender, String message
    public User_(Long id, String firstName, String lastName, String mobilephone, String email, String role, LocalDate createdDate, String birthdate, String gender, String message) {
        this.Id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobilephone = mobilephone;
        this.email = email;
        this.role = role;
        CreatedDate = createdDate;
        this.birthdate = birthdate;
        this.gender = gender;
        Message = message;
    }

    public User_(String firstName, String lastName, String mobilephone, String email, String role, LocalDate createdDate, String birthdate, String gender, String message) {
        //this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobilephone = mobilephone;
        this.email = email;
        this.role = role;
        CreatedDate = createdDate;
        this.birthdate = birthdate;
        this.gender = gender;
        Message = message;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private String gender;
    @Transient
    private String Message;

    public User_() {
    }
    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        role = role;
    }

    public LocalDate getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        CreatedDate = createdDate;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }
    public String getGender() {
        return gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    @Override
    public String toString() {
        return "User_{" +
                "Id=" + Id +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobilephone='" + mobilephone + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", CreatedDate=" + CreatedDate +
                ", birthdate=" + birthdate +
                ", gender='" + gender + '\'' +
                ", Message='" + Message + '\'' +
                '}';
    }


}
