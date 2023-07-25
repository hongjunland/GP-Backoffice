package com.example.demo.domain.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DayType {
    WEEKDAY("평일"),
    WEEKEND("주말");

    private final String description;
}
