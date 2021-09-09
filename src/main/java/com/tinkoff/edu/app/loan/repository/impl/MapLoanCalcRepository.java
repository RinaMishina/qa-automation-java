package com.tinkoff.edu.app.loan.repository.impl;

import com.tinkoff.edu.app.loan.models.LoanData;
import com.tinkoff.edu.app.loan.models.LoanServiceModel;
import com.tinkoff.edu.app.loan.repository.ILoanCalcRepository;
import com.tinkoff.edu.app.loan.types.LoanType;
import com.tinkoff.edu.app.loan.types.ResponseType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class MapLoanCalcRepository implements ILoanCalcRepository {
    private HashMap<UUID, LoanServiceModel> mapStorage;

    public MapLoanCalcRepository() {
        this.mapStorage = new HashMap<>();
    }


    @Override
    public LoanServiceModel save(LoanData loanData, ResponseType responseType) {
        LoanServiceModel loanServiceModel = new LoanServiceModel(
                UUID.randomUUID(),
                responseType,
                loanData.getFio(),
                loanData.getType()
        );
        mapStorage.put(loanServiceModel.getId(), loanServiceModel);

        return loanServiceModel;
    }

    @Override
    public LoanServiceModel getById(UUID id) {
        if (mapStorage.isEmpty()) {
            return null;
        }
        return mapStorage.get(id);
    }

    @Override
    public LoanServiceModel updateType(UUID id, ResponseType type) {
        LoanServiceModel model = this.getById(id);

        if (model == null) {
            return null;
        }

        model.setType(type);

        return model;

    }

    public List<LoanServiceModel> getAllByLoanType(LoanType loanType) {
        if (mapStorage.isEmpty()) {
            return new ArrayList<>();
        }

        List<LoanServiceModel> list = mapStorage.values()
                .stream()
                .filter(model -> model.getLoanType() == loanType)
                .collect(Collectors.toList());

        return list;
    }
}
