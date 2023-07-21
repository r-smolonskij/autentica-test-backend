package com.example.autenticatestbackend.inquiry;

public enum Status {
    REQUESTED("requested"),
    CONFIRMED("confirmed"),
    REJECTED("rejected");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
