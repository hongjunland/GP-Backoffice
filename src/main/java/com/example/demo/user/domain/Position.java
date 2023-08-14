package com.example.demo.user.domain;

import lombok.Getter;

@Getter
public enum Position {
    ROOT(1, "관리자"),
    EXECUTIVE(2, "임원진"),
    TEAM_LEADER(3, "팀장"),
    EMPLOYEE(4, "사원");

    private final int depth;
    private final String description;
    Position(int depth, String description) {
        this.depth = depth;
        this.description = description;
    }
    public static Position fromLongValue(long value) {
        for (Position position : Position.values()) {
            if (position.getDepth() == value) {
                return position;
            }
        }
        throw new IllegalArgumentException("No Position enum with depth " + value);
    }
}
