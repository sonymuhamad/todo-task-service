package com.sony.taskservice.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TodoStatus {
    SCHEDULED("Scheduled"),
    IN_PROGRESS("In Progress"),
    DONE("Done"),
    CANCELED("Canceled");

    private final String label;

    TodoStatus(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }
}
