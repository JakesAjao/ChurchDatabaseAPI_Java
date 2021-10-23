package com.jakesajao.Model;

import com.jakesajao.dto.MemberAttend;

public class Percentage {
    private int PERCENTAGE;
    private boolean Week1;
    private boolean Week2;
    private boolean Week3;
    private boolean Week4;
    private MemberAttend memberAttend;

    public int getPERCENTAGE() {
        return PERCENTAGE;
    }

    public void setPERCENTAGE(int PERCENTAGE) {
        this.PERCENTAGE = PERCENTAGE;
    }

    public MemberAttend setMemberAttend() {
        return memberAttend;
    }

    public void getMemberAttend(MemberAttend memberAttend) {
        memberAttend = memberAttend;
    }
    public boolean isWeek1() {
        return Week1;
    }

    public void setWeek1(boolean week1) {
        Week1 = week1;
    }

    public boolean isWeek2() {
        return Week2;
    }

    public void setWeek2(boolean week2) {
        Week2 = week2;
    }

    public boolean isWeek3() {
        return Week3;
    }

    public void setWeek3(boolean week3) {
        Week3 = week3;
    }

    public boolean isWeek4() {
        return Week4;
    }

    public void setWeek4(boolean week4) {
        Week4 = week4;
    }

}
