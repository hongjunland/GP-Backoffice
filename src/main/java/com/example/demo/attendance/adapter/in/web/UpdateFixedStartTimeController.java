package com.example.demo.attendance.adapter.in.web;

import com.example.demo.attendance.adapter.in.request.UpdateFixedStartTimeRequest;
import com.example.demo.attendance.application.port.in.command.UpdateFixedStartTimeCommand;
import com.example.demo.attendance.application.port.in.usecase.UpdateFixedStartTimeUseCase;
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
@RequestMapping("/api/v1/fixed-start-time")
public class UpdateFixedStartTimeController {

    private final UpdateFixedStartTimeUseCase updateFixedStartTimeUseCase;

    @PostMapping("/update")
    public SuccessApiResponse<String> updateFixedStartTime(
            @RequestBody UpdateFixedStartTimeRequest request
    ) {
        UpdateFixedStartTimeCommand command = UpdateFixedStartTimeCommand.builder()
                .fixedStartTime(request.getFixedStartTime())
                .updateDate(request.getUpdateDate())
                .userId(request.getUserId())
                .build();

        updateFixedStartTimeUseCase.UpdateFixedStartTime(command);

        return SuccessApiResponse.of();

    }

}
