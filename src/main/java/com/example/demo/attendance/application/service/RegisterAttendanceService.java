package com.example.demo.attendance.application.service;

import com.example.demo.attendance.application.port.in.command.RegisterAttendanceCommand;
import com.example.demo.attendance.application.port.in.usecase.RegisterAttendanceUseCase;
import com.example.demo.attendance.application.port.out.LoadAttendancePort;
import com.example.demo.attendance.application.port.out.LoadFixedStartTimePort;
import com.example.demo.attendance.application.port.out.SaveAttendancePort;
import com.example.demo.attendance.domain.Attendance;
import com.example.demo.attendance.domain.FixedStartTime;
import com.example.demo.common.annotaion.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.Optional;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class RegisterAttendanceService implements RegisterAttendanceUseCase {

    private final SaveAttendancePort saveAttendancePort;
    private final LoadAttendancePort loadAttendancePort;
    private final LoadFixedStartTimePort loadFixedStartTimePort;

    @Override
    public void registerAttendance(RegisterAttendanceCommand command) {
        Attendance existAttendance = loadAttendancePort.loadAttendanceByUserIdAndWorkDate(command.getUserId(), command.getWorkDate());
        FixedStartTime fixedStartTime = loadFixedStartTimePort.loadFixedStartTime(command.getUserId());

        String attendanceStatus = Optional.ofNullable(fixedStartTime)
                .map(FixedStartTime::getFixedStartTime)
                .map(allowedTime -> !command.getStartTime().isAfter(allowedTime) ? "정상 출근" : "지각")
                .orElse(null);

        if (existAttendance == null) {
            saveNewAttendance(command, attendanceStatus, fixedStartTime);
        } else if (command.getEndTime() != null) {
            updateExistAttendanceEndTime(existAttendance, command.getEndTime());
        }
    }

    // 기존에 없는 경우엔 저장 시켜주기.
    private void saveNewAttendance(RegisterAttendanceCommand command, String attendanceStatus, FixedStartTime fixedStartTime) {
        Attendance attendance = Attendance.builder()
                .userId(command.getUserId())
                .department(command.getDepartment())
                .name(command.getName())
                .workDate(command.getWorkDate())
                .dayType(command.getDayType())
                .startTime(command.getStartTime())
                .endTime(command.getEndTime())
                .attendanceStatus(attendanceStatus)
                .fixedStartTime(attendanceStatus != null ? fixedStartTime.getFixedStartTime() : null)
                .build();
        saveAttendancePort.saveAttendance(attendance);
    }

    // 기존에 있는 경우 && 퇴근시간이 기입 되어있는 경우
    private void updateExistAttendanceEndTime(Attendance existAttendance, LocalTime endTime) {
        existAttendance.updateEndTime(endTime);
        saveAttendancePort.saveAttendance(existAttendance);
    }
}
