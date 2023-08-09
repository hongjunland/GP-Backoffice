package com.example.demo.attendance.application.service;

import com.example.demo.attendance.application.port.in.command.CheckLatenessCommand;
import com.example.demo.attendance.application.port.in.usecase.CheckLatenessUseCase;
import com.example.demo.attendance.application.port.out.SaveFixedStartTimePort;
import com.example.demo.attendance.domain.FixedStartTime;
import com.example.demo.common.annotaion.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class CheckLatenessService implements CheckLatenessUseCase {

    private final SaveFixedStartTimePort saveFixedStartTimePort;

    @Override
    public void CheckLateness(CheckLatenessCommand command) {
        FixedStartTime fixedStartTime = FixedStartTime.builder()
                .fixedStartTime(command.getFixedStartTime())
                .userId(command.getUserId())
                .effectiveFromDate(command.getEffectiveFromDate())
                .build();

        saveFixedStartTimePort.saveFixedStartTime(fixedStartTime);
    }

}
