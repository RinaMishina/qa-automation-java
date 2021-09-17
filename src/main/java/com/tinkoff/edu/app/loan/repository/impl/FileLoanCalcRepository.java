package com.tinkoff.edu.app.loan.repository.impl;


import com.tinkoff.edu.app.loan.models.LoanData;
import com.tinkoff.edu.app.loan.models.LoanServiceModel;
import com.tinkoff.edu.app.loan.repository.ILoanCalcRepository;
import com.tinkoff.edu.app.loan.types.LoanType;
import com.tinkoff.edu.app.loan.types.ResponseType;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class FileLoanCalcRepository implements ILoanCalcRepository {
    @Override
    public LoanServiceModel save(LoanData loanData, ResponseType responseType) throws IOException {
        LoanServiceModel loanServiceModel = new LoanServiceModel(
                UUID.randomUUID(),
                responseType,
                loanData.getFio(),
                loanData.getType()
        );

        Path testFilePath = Path.of("testfile.txt");

        String loanRequestString = loanServiceModel.getId().toString() + '|' + loanServiceModel.getType() + '|' + loanServiceModel.getFio()
                + '|' + loanServiceModel.getLoanType();
        Files.write(testFilePath, List.of(loanRequestString), StandardOpenOption.APPEND, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        return loanServiceModel;
    }


    @Override
    public LoanServiceModel getById(UUID id) {
        List<LoanServiceModel> loanDataStorage = this.loadDataFromFile();

        if (loanDataStorage == null || loanDataStorage.isEmpty()) {
            return null;
        }

        for (LoanServiceModel model : loanDataStorage) {
            if (model.getId().equals(id)) {
                return model;
            }
        }

        return null;
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

    @Override
    public List<LoanServiceModel> getAllByLoanType(LoanType loanType) {
        List<LoanServiceModel> loanDataStorage = this.loadDataFromFile();

        if (loanDataStorage == null || loanDataStorage.isEmpty()) {
            return new ArrayList<>();
        }

        List<LoanServiceModel> list = loanDataStorage.stream()
                .filter(model -> model.getLoanType() == loanType)
                .collect(Collectors.toList());

        return list;
    }

    private List<LoanServiceModel> loadDataFromFile() {
        Path testFilePath = Path.of("testfile.txt");
        Stream<String> lines;
        List<LoanServiceModel> result = new ArrayList<>();

        try {
            lines = Files.lines(testFilePath);
            List<String> rawDataList = lines.collect(Collectors.toList());
            lines.close();
            rawDataList.forEach((item) -> {
                String[] data = item.split("\\|");
                LoanServiceModel loanServiceModel = new LoanServiceModel(
                        UUID.fromString(data[0]),
                        ResponseType.valueOf(data[1]),
                        data[2],
                        LoanType.valueOf(data[3])
                );

                result.add(loanServiceModel);
            });

        } catch (IOException e) {
            e.printStackTrace();

            return null;
        }

        return result;
    }

    public void clearFile() {
        PrintWriter writer;
        try {
            writer = new PrintWriter("testfile.txt");
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
