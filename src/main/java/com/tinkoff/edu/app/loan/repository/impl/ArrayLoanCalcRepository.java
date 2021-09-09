package com.tinkoff.edu.app.loan.repository.impl;

import com.tinkoff.edu.app.loan.models.LoanData;
import com.tinkoff.edu.app.loan.models.LoanServiceModel;
import com.tinkoff.edu.app.loan.repository.ILoanCalcRepository;
import com.tinkoff.edu.app.loan.types.LoanType;
import com.tinkoff.edu.app.loan.types.ResponseType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ArrayLoanCalcRepository implements ILoanCalcRepository {
    private final LoanServiceModel[] loanDataStorage;
    private int currentIndex = -1;
    private final int length;


    public ArrayLoanCalcRepository(int length) {
        this.loanDataStorage = new LoanServiceModel[length];
        this.length = length;
    }

    @Override
    public LoanServiceModel save(LoanData loanData, ResponseType responseType) {
        if (this.currentIndex + 1 >= this.length) {
            throw new ArrayIndexOutOfBoundsException();
        }

        LoanServiceModel loanServiceModel = new LoanServiceModel(
                UUID.randomUUID(),
                responseType,
                loanData.getFio(),
                loanData.getType()
        );

        this.loanDataStorage[++this.currentIndex] = loanServiceModel;

        return loanServiceModel;
    }

    public LoanServiceModel getById(UUID id) {
        if (currentIndex < 0) {
            return null;
        }

        for (int i = 0; i <= currentIndex; i++) {
            if (this.loanDataStorage[i].getId() == id) {
                return this.loanDataStorage[i];
            }
        }

        return null;
    }

    public LoanServiceModel updateType(UUID id, ResponseType type) {
        LoanServiceModel model = this.getById(id);

        if (model == null) {
            return null;
        }

        model.setType(type);

        return model;
    }

    @Override
    public List<LoanServiceModel> getAllByLoanType(LoanType loanType) {

        if (currentIndex < 0) {
            return new ArrayList<>();
        }

        List<LoanServiceModel> loanServiceModelList = new ArrayList<>();

        for (int i = 0; i <= currentIndex; i++) {
            if (this.loanDataStorage[i].getLoanType() == loanType) {
                loanServiceModelList.add(loanDataStorage[i]);
            }
        }

        return loanServiceModelList;
    }
}
