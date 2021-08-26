package com.tinkoff.edu;

import com.tinkoff.edu.app.loan.controller.LoanCalcController;
import com.tinkoff.edu.app.loan.models.LoanData;
import com.tinkoff.edu.app.loan.models.LoanResponse;
import com.tinkoff.edu.app.loan.repository.ILoanCalcRepository;
import com.tinkoff.edu.app.loan.repository.impl.LoanCalcRepository;
import com.tinkoff.edu.app.loan.service.impl.LoanCalcService;
import com.tinkoff.edu.app.loan.types.LoanType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    private ILoanCalcRepository repository;
    private LoanCalcService service;
    private LoanCalcController controller;
    private LoanData request;

    @BeforeEach
    public void init() {
        repository = new LoanCalcRepository();
        service = new LoanCalcService(repository);
        controller = new LoanCalcController(service);
        request = new LoanData(LoanType.IP, 10, 1000);
    }

    @Test
    public void shouldGetId1WhenFirstCall() {
        LoanResponse requestId = controller.createRequest(request);
        assertEquals(1, requestId.getId());
    }

    @Test
    public void shouldGetIncrementedIdWhenAnyCall() {
        int testRequestId = LoanCalcRepository.getRequestId();
        LoanCalcRepository.setRequestId(10);
        LoanResponse requestId = controller.createRequest(request);
        assertEquals(11, requestId.getId());
        LoanCalcRepository.setRequestId(testRequestId);
    }




}
