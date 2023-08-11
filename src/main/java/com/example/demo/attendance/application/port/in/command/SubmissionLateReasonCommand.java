package com.example.demo.attendance.application.port.in.command;

import com.example.demo.attendance.domain.Attendance;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SubmissionLateReasonCommand {

    private Attendance.AttendanceId attendanceId;
    private String lateReason;

}
