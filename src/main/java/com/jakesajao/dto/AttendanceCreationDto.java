package com.jakesajao.dto;

import com.jakesajao.Model.Member;
import com.jakesajao.constraints.FieldMatch;

import java.time.LocalDate;

@FieldMatch.List({
        @FieldMatch(first = "mobilephone1", second = "mobilephone1", message = "The mobilephone1 fields must match."),

})
public class AttendanceCreationDto {
    public AttendanceCreationDto() {
    }

    private LocalDate createdDate;
    private String Status;
    private Member member;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
