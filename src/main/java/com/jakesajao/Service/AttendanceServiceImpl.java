package com.jakesajao.Service;

import com.jakesajao.Model.Attendance;
import com.jakesajao.Model.Member;
import com.jakesajao.Repository.AttendanceRepository;
import com.jakesajao.Repository.MemberRepository;
import com.jakesajao.dto.AttendanceCreationDto;
import com.jakesajao.dto.MemberCreationDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class AttendanceServiceImpl implements AttendanceService{
    @Autowired
    private AttendanceRepository attendanceRepository;

    @Override
    public Attendance Save(AttendanceCreationDto attendanceCreationDto) {
        Attendance attendance = new Attendance(attendanceCreationDto.getMember(), attendanceCreationDto.getStatus()
               ,LocalDate.now());

        System.out.println("Save attendance as : " + attendance);
        return attendanceRepository.save(attendance);
    }


}
