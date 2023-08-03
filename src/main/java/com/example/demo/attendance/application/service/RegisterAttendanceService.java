package com.example.demo.attendance.application.service;

import com.example.demo.attendance.application.port.in.command.RegisterAttendanceCommand;
import com.example.demo.attendance.application.port.in.usecase.RegisterAttendanceUseCase;
import com.example.demo.attendance.application.port.out.SaveAttendancePort;
import com.example.demo.attendance.domain.Attendance;
import com.example.demo.common.annotaion.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class RegisterAttendanceService implements RegisterAttendanceUseCase {

    private final SaveAttendancePort saveAttendancePort;

    @Override
    public void registerAttendance(RegisterAttendanceCommand command) {
        Attendance attendance = Attendance.builder()
                .userId(command.getUserId())
                .department(command.getDepartment())
                .name(command.getName())
                .workDate(command.getWorkDate())
                .dayType(command.getDayType())
                .startTime(command.getStartTime())
                .endTime(command.getEndTime())
                .build();

        saveAttendancePort.saveAttendance(attendance);
    }

}
