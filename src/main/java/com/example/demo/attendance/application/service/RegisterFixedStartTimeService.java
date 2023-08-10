package com.example.demo.attendance.application.service;

import com.example.demo.attendance.application.port.in.command.RegisterFixedStartTimeCommand;
import com.example.demo.attendance.application.port.in.usecase.RegisterFixedStartTimeUseCase;
import com.example.demo.attendance.application.port.out.SaveFixedStartTimePort;
import com.example.demo.attendance.application.port.out.UpdateAttendanceStatusPort;
import com.example.demo.attendance.domain.FixedStartTime;
import com.example.demo.common.annotaion.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class RegisterFixedStartTimeService implements RegisterFixedStartTimeUseCase {

    private final SaveFixedStartTimePort saveFixedStartTimePort;
    private final UpdateAttendanceStatusPort updateAttendanceStatusPort;

    @Override
    public void RegisterFixedStartTime(RegisterFixedStartTimeCommand command) {
        FixedStartTime fixedStartTime = FixedStartTime.builder()
                .fixedStartTime(command.getFixedStartTime())
                .userId(command.getUserId())
                .build();

        saveFixedStartTimePort.saveFixedStartTime(fixedStartTime);
        updateAttendanceStatusPort.updateAttendanceStatus(fixedStartTime);
    }

}
