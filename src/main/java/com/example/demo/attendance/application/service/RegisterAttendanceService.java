package com.example.demo.attendance.application.service;

import com.example.demo.attendance.application.port.in.command.RegisterAttendanceCommand;
import com.example.demo.attendance.application.port.in.usecase.RegisterAttendanceUseCase;
import com.example.demo.attendance.application.port.out.LoadFixedStartTimePort;
import com.example.demo.attendance.application.port.out.SaveAttendancePort;
import com.example.demo.attendance.domain.Attendance;
import com.example.demo.attendance.domain.FixedStartTime;
import com.example.demo.common.annotaion.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.Optional;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class RegisterAttendanceService implements RegisterAttendanceUseCase {

    private final SaveAttendancePort saveAttendancePort;
    private final LoadFixedStartTimePort loadFixedStartTimePort;

    @Override
    public void registerAttendance(RegisterAttendanceCommand command) {
        FixedStartTime fixedStartTime = loadFixedStartTimePort.loadFixedStartTime(command.getUserId());

        String attendanceStatus = Optional.ofNullable(fixedStartTime)
                .map(fixedTime -> fixedTime.getFixedStartTime().plusMinutes(1))
                .map(allowedTime -> allowedTime.isAfter(command.getStartTime()) ? "정상 출근" : "지각")
                .orElse(null);

        Attendance attendance = Attendance.builder()
                .userId(command.getUserId())
                .department(command.getDepartment())
                .name(command.getName())
                .workDate(command.getWorkDate())
                .dayType(command.getDayType())
                .startTime(command.getStartTime())
                .endTime(command.getEndTime())
                .attendanceStatus(attendanceStatus)
                .fixedStartTime(fixedStartTime.getFixedStartTime())
                .build();

        saveAttendancePort.saveAttendance(attendance);
    }

}
