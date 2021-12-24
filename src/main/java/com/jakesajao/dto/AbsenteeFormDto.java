package com.jakesajao.dto;

public class AbsenteeFormDto {
    private String week;
    private String category;
    public AbsenteeFormDto() {
    }

    public AbsenteeFormDto(String week, String category) {
        this.week = week;
        this.category = category;
    }
    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "AbsenteeFormDto{" +
                "week='" + week + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

}
