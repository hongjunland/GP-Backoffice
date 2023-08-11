package com.example.demo.attendance.application.service;

import com.example.demo.attendance.application.port.in.command.SubmissionLateReasonCommand;
import com.example.demo.attendance.application.port.in.usecase.SubmissionLateReasonUseCase;
import com.example.demo.attendance.application.port.out.LoadAttendancePort;
import com.example.demo.attendance.application.port.out.SaveAttendancePort;
import com.example.demo.attendance.domain.Attendance;
import com.example.demo.common.annotaion.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;
import java.time.LocalTime;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class SubmissionLateReasonService implements SubmissionLateReasonUseCase {

    private final LoadAttendancePort loadAttendancePort;
    private final SaveAttendancePort saveAttendancePort;

    @Override
    public void submissionLateReason(SubmissionLateReasonCommand command) {
        Attendance attendance = loadAttendancePort.loadAttendanceByAttendanceId(command.getAttendanceId());
        attendance.updateLateReason(command.getLateReason());

        if ("F-day".equals(command.getLateReason())) {
            attendance.updateAttendanceStatus(attendance.getStartTime().isAfter(LocalTime.of(9, 30)) ? "지각" : "정상 출근");
        }
        saveAttendancePort.saveAttendance(attendance);
    }
}
