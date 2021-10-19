package com.jakesajao.dto;

import java.util.ArrayList;
import java.util.List;

public class MemberAttendDto {
    List<MemberAttend> attends =new ArrayList<>();

    public List<MemberAttend> getAttends() {
        return attends;
    }

    public void setAttends(List<MemberAttend> attends) {
        this.attends = attends;
    }

    @Override
    public String toString() {
        return "MemberAttendDto{" +
                "attends=" + attends +
                '}';
    }

    public void addMemberAttend(MemberAttend memberAttend) {
        this.attends.add(memberAttend);
    }

}
