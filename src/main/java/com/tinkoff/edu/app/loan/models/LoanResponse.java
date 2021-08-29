package com.tinkoff.edu.app.loan.models;

import com.tinkoff.edu.app.loan.models.LoanData;
import com.tinkoff.edu.app.loan.types.ResponseType;

public class LoanResponse {
    private final int id;
    private final ResponseType type;

    public LoanResponse(int id, ResponseType type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public ResponseType getType() {
        return type;
    }

    public String toString() {
        return "{id: "
                + this.id + ", type: " + this.type + "}";
    }
}
