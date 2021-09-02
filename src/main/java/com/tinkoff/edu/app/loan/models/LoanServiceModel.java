package com.tinkoff.edu.app.loan.models;

import com.tinkoff.edu.app.loan.types.ResponseType;

import java.util.UUID;

public class LoanServiceModel {
    private final UUID id;
    private ResponseType type;
    private final String fio;

    public LoanServiceModel(UUID id, ResponseType type, String fio) {
        this.id = id;
        this.type = type;
        this.fio = fio;
    }

    public UUID getId() {
        return id;
    }

    public ResponseType getType() {
        return type;
    }

    public String getFio() {
        return fio;
    }

    public void setType(ResponseType type) {
        this.type = type;
    }
}
