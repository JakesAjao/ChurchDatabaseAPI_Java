package com.jakesajao.Controller;

import com.jakesajao.Model.Attendance;
import com.jakesajao.Repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

public class AttendanceController {
    @Autowired
    AttendanceRepository attendanceRepository;

    @GetMapping("/attendance")
    public String getAttendance(Model model){
        List<Attendance> attendanceList = attendanceRepository.findAll();

        model.addAttribute("attendanceList",attendanceList);
        return "/attendance";
    }

    public String postAttendance(@ModelAttribute("attendance")Attendance attendance){
        System.out.println("Attendance has been posted");
        System.out.println(attendance);
        return "/attendance";
    }
}
