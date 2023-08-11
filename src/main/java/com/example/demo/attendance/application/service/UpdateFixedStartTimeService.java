package com.example.demo.attendance.application.service;

import com.example.demo.attendance.application.port.in.command.UpdateFixedStartTimeCommand;
import com.example.demo.attendance.application.port.in.usecase.UpdateFixedStartTimeUseCase;
import com.example.demo.attendance.application.port.out.LoadFixedStartTimePort;
import com.example.demo.attendance.application.port.out.SaveFixedStartTimePort;
import com.example.demo.attendance.application.port.out.UpdateAttendanceStatusPort;
import com.example.demo.attendance.domain.FixedStartTime;
import com.example.demo.common.annotaion.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class UpdateFixedStartTimeService implements UpdateFixedStartTimeUseCase {

    private final SaveFixedStartTimePort saveFixedStartTimePort;
    private final LoadFixedStartTimePort loadFixedStartTimePort;
    private final UpdateAttendanceStatusPort updateAttendanceStatusPort;

    @Override
    public void UpdateFixedStartTime(UpdateFixedStartTimeCommand command) {
        FixedStartTime fixedStartTime = Optional.ofNullable(loadFixedStartTimePort.loadFixedStartTime(command.getUserId()))
                .map(existingFixedStartTime -> existingFixedStartTime.updateFixedStartTime(command.getFixedStartTime(), command.getUpdateDate()))
                .orElseThrow(); // todo : 에러처리하기 -> 이미 생성된 이후라 무조건 있어야함.

        saveFixedStartTimePort.saveFixedStartTime(fixedStartTime);
        updateAttendanceStatusPort.updateAttendanceStatus(fixedStartTime);
    }
}
