package com.jakesajao.dto;

import java.time.LocalDate;
import java.util.List;

public class MemberAttend {
    private String firstName;
    private String lastName;
    private String gender;
    private String present;
    private boolean present1;
    private LocalDate createdDate;
    private Long id;
    private String title;
    private String mobilephone1;
    private boolean week1;
    private boolean week2;
    private boolean week3;
    private boolean week4;
    private int percentage;

    public MemberAttend(){
    }
    public MemberAttend(Long id,String title,String firstName, String lastName, boolean _present1,
                        boolean week1,boolean week2,boolean week3,boolean week4,int percentage,
                        String gender, LocalDate createdDate) {
        this.firstName = firstName;
        this.id = id;
        this.lastName = lastName;
        this.gender = gender;
        this.createdDate = createdDate;
        this.present1 = _present1;
        this.week4 = week4;
        this.week1 = week1;
        this.week2 = week2;
        this.week3 = week3;
        this.percentage = percentage;
        this.mobilephone1 = mobilephone1;
        this.title = title;
    }
    public MemberAttend(Long id,String title,String firstName, String lastName, String present, String gender, LocalDate createdDate,
                        String mobilephone1) {
        this.firstName = firstName;
        this.title = title;
        this.id = id;
        this.lastName = lastName;
        this.gender = gender;
        this.createdDate = createdDate;
        this.present = present;
        this.mobilephone1 = mobilephone1;
    }
    public MemberAttend(Long id,String title,String firstName, String lastName, String present, String gender, LocalDate createdDate) {
        this.firstName = firstName;
        this.title = title;
        this.id = id;
        this.lastName = lastName;
        this.gender = gender;
        this.createdDate = createdDate;
        this.present = present;
    }

    public MemberAttend(Long id,String firstName, String lastName) {
        this.firstName = firstName;
        this.id = id;
        this.lastName = lastName;
    }
    public String getMobilephone1() {
        return mobilephone1;
    }

    public void setMobilephone1(String mobilephone1) {
        this.mobilephone1 = mobilephone1;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean getPresent1() {
        return present1;
    }

    public void setPresent1(boolean present1) {
        this.present1 = present1;
    }

    public String getPresent() {
        return present;
    }

    public void setPresent(String present) {
        this.present = present;
    }

//    public String getStatus() {
//        return present;
//    }
//
//    public void setStatus(String status) {
//        this.present = present;
//    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isWeek1() {
        return week1;
    }

    public void setWeek1(boolean week1) {
        this.week1 = week1;
    }

    public boolean isWeek2() {
        return week2;
    }

    public void setWeek2(boolean week2) {
        this.week2 = week2;
    }

    public boolean isWeek3() {
        return week3;
    }

    public void setWeek3(boolean week3) {
        this.week3 = week3;
    }

    public boolean isWeek4() {
        return week4;
    }

    public void setWeek4(boolean week4) {
        this.week4 = week4;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "MemberAttend{" +
                "id='" + id + '\'' +
                ",title='" + title + '\'' +
                ",firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", createdDate=" + createdDate +
                ", present='" + present + '\'' +
                ", present1=" + present1 +
                ", week1=" + week1 +
                ", week2=" + week2 +
                ", week3=" + week3 +
                ", week4=" + week4 +
                ", percentage=" + percentage +
                '}';
    }


}
