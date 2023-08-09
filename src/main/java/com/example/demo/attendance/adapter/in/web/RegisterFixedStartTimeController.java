package com.example.demo.attendance.adapter.in.web;

import com.example.demo.attendance.adapter.in.request.RegisterFixedStartTimeRequest;
import com.example.demo.attendance.application.port.in.command.RegisterFixedStartTimeCommand;
import com.example.demo.attendance.application.port.in.usecase.RegisterFixedStartTimeUseCase;
import com.example.demo.common.SuccessApiResponse;
import com.example.demo.common.annotaion.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lateness")
public class RegisterFixedStartTimeController {

    private final RegisterFixedStartTimeUseCase registerFixedStartTimeUseCase;

    @PostMapping
    public SuccessApiResponse<String> RegisterFixedStartTime(
            @RequestBody RegisterFixedStartTimeRequest request
    ) {
        RegisterFixedStartTimeCommand command = RegisterFixedStartTimeCommand.builder()
                .fixedStartTime(request.getFixedStartTime())
                .userId(request.getUserId())
                .build();

        registerFixedStartTimeUseCase.RegisterFixedStartTime(command);

        return SuccessApiResponse.of(new String("성공"));
    }

}
