package com.tinkoff.edu.app.loan.models;

import com.tinkoff.edu.app.loan.types.ResponseType;

import java.util.UUID;

public class LoanResponse {
    private final UUID id;
    private final ResponseType type;

    public LoanResponse(UUID id, ResponseType type) {
        this.id = id;
        this.type = type;
    }

    public UUID getId() {
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
