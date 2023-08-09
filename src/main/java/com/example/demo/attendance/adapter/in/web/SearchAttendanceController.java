package com.example.demo.attendance.adapter.in.web;

import com.example.demo.attendance.adapter.in.request.SearchAttendanceRequest;
import com.example.demo.attendance.adapter.in.response.AttendanceResponseMapper;
import com.example.demo.attendance.application.port.in.query.SearchAttendanceQuery;
import com.example.demo.attendance.application.port.in.query.SearchAttendanceQueryParameters;
import com.example.demo.attendance.domain.Attendance;
import com.example.demo.common.SuccessApiResponse;
import com.example.demo.common.annotaion.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attendances")
public class SearchAttendanceController {

    private final SearchAttendanceQuery searchAttendanceQuery;

    @GetMapping("/search")
    public SuccessApiResponse searchAttendance(
            @ModelAttribute SearchAttendanceRequest searchAttendanceRequest
    ) {
        SearchAttendanceQueryParameters parameters = SearchAttendanceQueryParameters.builder()
                .startDate(searchAttendanceRequest.getStartDate())
                .endDate(searchAttendanceRequest.getEndDate())
                .department(searchAttendanceRequest.getDepartment())
                .name(searchAttendanceRequest.getName())
                .build();

        List<Attendance> attendances = searchAttendanceQuery.searchAttendance(parameters);

        return SuccessApiResponse.of(AttendanceResponseMapper.mapToSearchAttendanceResponses(attendances));
    }

}
