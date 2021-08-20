package com.tinkoff.edu.test;

import com.tinkoff.edu.app.*;

public class LoanCalcTest {
    public static void main(String... args) {
        LoanCalcRepository repository = new LoanCalcRepository();
        LoanCalcService service = new IpNotFriendlyService(repository);
        LoanCalcController controller = new LoanCalcController(service);
        LoanRequest request = new LoanRequest(LoanType.IP, 10,1000);
        LoanResponse response = controller.createRequest(request);

        System.out.println(response);
    }
}
