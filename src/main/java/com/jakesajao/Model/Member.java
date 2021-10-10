package com.jakesajao.Model;

import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Table
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String mobilephone1;
    private String mobilephone2;
    private String gender;
    private String title;
    private LocalDate createdDate;

    private String state;

    private String town;

    private String address;

    private String role;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "members_roles",
            joinColumns = @JoinColumn(
                    name = "member_id", referencedColumnName = "Id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;
    public Member(){

    }

    public Member(Long id, String firstName, String lastName, String mobilephone1, String mobilephone2,String gender,String state,String town,Role role,
                  String address,String title) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobilephone1 = mobilephone1;
        this.mobilephone2 = mobilephone2;
        this.roles = (Collection<Role>) role;
        this.gender = gender;
        this.state = state;
        this.address = address;
        this.title = title;
        this.town = town;

    }
    public Member(String firstName, String lastName, String mobilephone1, String mobilephone2,String gender,String state,
                  String town,String address, String title,String role,
                  LocalDate createdDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobilephone1 = mobilephone1;
        this.mobilephone2 = mobilephone2;
        this.gender = gender;
        this.state = state;
        this.role = role;
        this.town = town;
        this.address = address;
        this.title = title;
        this.createdDate = createdDate;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
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
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobilephone1() {
        return mobilephone1;
    }

    public void setMobilephone1(String mobilephone) {
        this.mobilephone1 = mobilephone;
    }
    public String getMobilephone2() {
        return mobilephone2;
    }

    public void setMobilephone2(String mobilephone) {
        this.mobilephone2 = mobilephone;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        state = state;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobilephone='" + mobilephone1 + '\'' +
                ", mobilephone2='" + mobilephone2 + '\'' +
                ", gender='" + gender + '\'' +
                ", state='" + state + '\'' +
                ", town='" + town + '\'' +
                ", title='" + title + '\'' +
                ", createDate='" + createdDate + '\'' +
                '}';
    }

}
