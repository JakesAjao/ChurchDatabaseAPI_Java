package com.jakesajao.dto;

import com.jakesajao.constraints.FieldMatch;

import javax.validation.constraints.*;

@FieldMatch.List({
        @FieldMatch(first = "mobilephone1", second = "mobilephone1", message = "The mobilephone1 fields must match."),
        @FieldMatch(first = "firstName", second = "firstName", message = "The firstName fields must match.")
})
public class MemberCreationDto {

    private Long id;

    @Override
    public String toString() {
        return "MemberCreationDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobilephone1='" + mobilephone1 + '\'' +
                ", address='" + address + '\'' +
                ", State='" + State + '\'' +
                ", mobilephone2='" + mobilephone2 + '\'' +
                ", role='" + role + '\'' +
                ", status='" + status + '\'' +
                ", gender='" + gender + '\'' +
                ", title='" + title + '\'' +
                ", town='" + town + '\'' +
                ", terms=" + terms +
                '}';
    }

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    @NotNull
    @Size(max = 11, min = 11, message = "Mobile number should be of 11 digits")
    @Pattern(regexp = "(^$|[0-9]{11})", message = "Mobile number is invalid!!")
    private String mobilephone1;
    @NotEmpty
    private String address;

    @NotEmpty
    private String State;

    @Size(max = 11, min = 11, message = "Mobile number should be of 11 digits")
    @Pattern(regexp = "(^$|[0-9]{11})", message = "Mobile number is invalid!!")
    private String mobilephone2;
    private String role;
    private String gender;

    private String title;

    private String town;
    private String status;
    public String getMobilephone1() {
        return mobilephone1;
    }

    public void setMobilephone1(String mobilephone) {
        this.mobilephone1 = mobilephone;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @AssertTrue
    private Boolean terms;
    public String getMobilephone2() {
        return mobilephone2;
    }

    public void setMobilephone2(String mobilephone2) {
        this.mobilephone2 = mobilephone2;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public Boolean getTerms() {
        return terms;
    }

    public void setTerms(Boolean terms) {
        this.terms = terms;
    }
    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
