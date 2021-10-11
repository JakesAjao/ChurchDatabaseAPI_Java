package com.jakesajao.Service;

import com.jakesajao.Model.Attendance;
import com.jakesajao.dto.AttendanceCreationDto;

public interface AttendanceService {
    Attendance Save(AttendanceCreationDto attendanceCreationDto);
}
