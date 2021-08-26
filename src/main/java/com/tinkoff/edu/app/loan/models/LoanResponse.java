package com.tinkoff.edu.app.loan.models;

import com.tinkoff.edu.app.loan.models.LoanData;
import com.tinkoff.edu.app.loan.types.ResponseType;

public class LoanResponse {
    private final int id;
    private final ResponseType type;
    private final LoanData request;

    public LoanResponse(int id, ResponseType type, LoanData request) {
        this.id = id;
        this.type = type;
        this.request = request;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return "{id: "
                + this.id + ", type: " + this.type + ", " + this.request.toString() + "}";
    }
}
