package com.jakesajao.Model;

import javax.persistence.*;
import java.time.LocalDate;

@Table
@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name="member_id", nullable=false)
    private Member member;

    private String status;
    private LocalDate createdDate;


    public Attendance(String status, LocalDate createdDate) {
        this.status = status;
        this.createdDate = createdDate;
    }

    public Attendance(Member member, String status, LocalDate createdDate) {
        //this.id = id;
        this.member = member;
        this.status = status;
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
        return member;
    }
    @Override
    public String toString() {
        return "Attendance{" +
                "id=" + id +
                ", member=" + member +
                ", status='" + status + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }


}
