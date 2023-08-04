package com.example.demo.attendance.adapter.in.web;

import com.example.demo.attendance.adapter.in.request.SearchAttendanceRequest;
import com.example.demo.attendance.adapter.out.response.AttendanceResponseMapper;
import com.example.demo.attendance.application.port.in.query.SearchAttendanceQuery;
import com.example.demo.attendance.application.port.in.query.SearchAttendanceCriteria;
import com.example.demo.attendance.domain.Attendance;
import com.example.demo.common.SuccessApiResponse;
import com.example.demo.common.annotaion.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attendances")
public class SearchAttendanceController {

    private final SearchAttendanceQuery searchAttendanceQuery;

    @PostMapping("/search")
    public SuccessApiResponse searchAttendance(
            @RequestBody SearchAttendanceRequest searchAttendanceRequest
    ) {
        SearchAttendanceCriteria criteria = SearchAttendanceCriteria.builder()
                .startDate(searchAttendanceRequest.getStartDate())
                .endDate(searchAttendanceRequest.getEndDate())
                .build();

        List<Attendance> attendances = searchAttendanceQuery.searchAttendance(criteria);

        return SuccessApiResponse.of(AttendanceResponseMapper.mapToSearchAttendanceResponses(attendances));
    }

}
