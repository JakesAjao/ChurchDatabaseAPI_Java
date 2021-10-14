package com.jakesajao.Model;

import javax.persistence.*;
import java.time.LocalDate;

@Table
@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", insertable = false, updatable = false)
    private Member members;

    private String status;
    private LocalDate createdDate;

    public Attendance(String status, LocalDate createdDate) {
        this.status = status;
        this.createdDate = createdDate;
    }

    public Attendance(Member member, String status, LocalDate createdDate) {
        //this.id = id;
        this.members = member;
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
        return members;
    }
    @Override
    public String toString() {
        return "Attendance{" +
                "id=" + id +
                ", members=" + members +
                ", status='" + status + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }

    public void setMember(Member member) {
        this.members = member;
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
