package com.jakesajao.dto;

public class ChartFormDto {
    private String month;
    private String year;

    public ChartFormDto(String month, String year) {
        this.month = month;
        this.year = year;
    }
    public ChartFormDto() {
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "ChartFormDto{" +
                "month='" + month + '\'' +
                ", year='" + year + '\'' +
                '}';
    }

}
