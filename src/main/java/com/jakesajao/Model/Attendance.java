package com.jakesajao.Model;

import javax.persistence.*;
import java.time.LocalDate;

@Table
@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String present;
    private LocalDate createdDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "attendance_id")
    private Member members;

    public Attendance(String present, LocalDate createdDate) {
        this.present = present;
        this.createdDate = createdDate;
    }

    public Attendance(Member member, String present, LocalDate createdDate) {
        //this.id = id;
        this.members = member;
        this.present = present;
        this.createdDate = createdDate;
    }
    public Attendance() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return members;
    }
    @Override
    public String toString() {
        return "Attendance{" +
                "id=" + id +
                ", members=" + members +
                ", present='" + present + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }

    public void setMember(Member member) {
        this.members = member;
    }

    public String getPresent() {
        return present;
    }

    public void setPresent(String present) {
        this.present = present;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }


}
