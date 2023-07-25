package com.example.demo.adapter.in.web;

import com.example.demo.adapter.in.request.RegisterAttendanceRequest;
import com.example.demo.application.port.in.command.RegisterAttendanceCommand;
import com.example.demo.application.port.in.usecase.RegisterAttendanceUseCase;
import com.example.demo.global.utils.ReturnObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attendance")
public class AttendanceRegisterController {

    private final RegisterAttendanceUseCase registerAttendanceUseCase;

    @PostMapping("/register")
    public ResponseEntity<ReturnObject> registerAttendance(
            @RequestBody List<RegisterAttendanceRequest> registerAttendanceRequests
    ) {

        for (RegisterAttendanceRequest request : registerAttendanceRequests) {

            RegisterAttendanceCommand command = RegisterAttendanceCommand.builder()
                    .userId(request.getUserId())
                    .department(request.getDepartment())
                    .name(request.getName())
                    .workDate(request.getWorkDate())
                    .dayType(request.getDayType())
                    .startTime(request.getStartTime())
                    .endTime(request.getEndTime())
                    .build();

            registerAttendanceUseCase.registerAttendance(command);
        }

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data("DB에 적재되었습니다.")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }
}
