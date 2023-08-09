package com.example.demo.attendance.adapter.in.web;

import com.example.demo.attendance.adapter.in.request.CheckLatenessRequest;
import com.example.demo.attendance.application.port.in.command.CheckLatenessCommand;
import com.example.demo.attendance.application.port.in.usecase.CheckLatenessUseCase;
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
public class CheckLatenessController {

    private final CheckLatenessUseCase checkLatenessUseCase;

    @PostMapping
    public SuccessApiResponse checkLateness(
            @RequestBody CheckLatenessRequest checkLatenessRequest
    ) {
        CheckLatenessCommand command = CheckLatenessCommand.builder()
                .fixedStartTime(checkLatenessRequest.getFixedStartTime())
                .userId(checkLatenessRequest.getUserId())
                .effectiveFromDate(checkLatenessRequest.getEffectiveFromDate())
                .build();

        checkLatenessUseCase.CheckLateness(command);

        return SuccessApiResponse.of(new String("성공"));
    }

}
