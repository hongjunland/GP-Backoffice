package com.example.demo.attendance.adapter.in.web;

import com.example.demo.attendance.adapter.in.request.RegisterAttendanceRequest;
import com.example.demo.attendance.application.port.in.command.RegisterAttendanceCommand;
import com.example.demo.attendance.application.port.in.usecase.RegisterAttendanceUseCase;
import com.example.demo.common.SuccessApiResponse;
import com.example.demo.common.annotaion.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attendances")
public class RegisterAttendanceController {

    private final RegisterAttendanceUseCase registerAttendanceUseCase;

    @PostMapping
    public SuccessApiResponse<String> registerAttendance(
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

        return SuccessApiResponse.of(new String("성공"));
    }
}
