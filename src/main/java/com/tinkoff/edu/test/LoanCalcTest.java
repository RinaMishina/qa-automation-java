package com.tinkoff.edu.test;

import com.tinkoff.edu.app.loan.controller.LoanCalcController;
import com.tinkoff.edu.app.loan.models.LoanData;
import com.tinkoff.edu.app.loan.models.LoanResponse;
import com.tinkoff.edu.app.loan.repository.impl.LoanCalcRepository;
import com.tinkoff.edu.app.loan.service.impl.IpNotFriendlyService;
import com.tinkoff.edu.app.loan.service.impl.LoanCalcService;
import com.tinkoff.edu.app.loan.types.LoanType;

public class LoanCalcTest {
    public static void main(String... args) {
        LoanCalcRepository repository = new LoanCalcRepository();
        LoanCalcService service = new IpNotFriendlyService(repository);
        LoanCalcController controller = new LoanCalcController(service);
        LoanData request = new LoanData(LoanType.IP, 10, 1000);
        LoanResponse response = controller.createRequest(request);

        System.out.println(response);
    }
}
