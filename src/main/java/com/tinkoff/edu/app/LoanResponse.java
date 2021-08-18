package com.tinkoff.edu.app;

public class LoanResponse {

    private final int id;
    private final ResponseType type;
    private final LoanRequest request;

    public LoanResponse(int id, ResponseType type, LoanRequest request) {
        this.id = id;
        this.type = type;
        this.request = request;
    }

    public String toString() {
        return "{id: "
                + this.id + ", type: " + this.type + " ,request: " + this.request.toString() + "}";
    }
}
