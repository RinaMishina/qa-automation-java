package com.tinkoff.edu;

import com.tinkoff.edu.app.loan.controller.LoanCalcController;
import com.tinkoff.edu.app.loan.models.LoanData;
import com.tinkoff.edu.app.loan.models.LoanResponse;
import com.tinkoff.edu.app.loan.repository.impl.LoanCalcRepository;
import com.tinkoff.edu.app.loan.service.impl.LoanCalcService;
import com.tinkoff.edu.app.loan.types.LoanType;
import com.tinkoff.edu.app.loan.types.ResponseType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    private LoanCalcRepository repository;
    private LoanCalcService service;
    private LoanCalcController controller;
    private LoanData request;

    @BeforeEach
    public void init() {
        repository = new LoanCalcRepository();
        service = new LoanCalcService(repository);
        controller = new LoanCalcController(service);

    }

    @Test
    @DisplayName ("1. requestId=1 при первом вызове")
    public void shouldGetId1WhenFirstCall() {
        request = new LoanData(LoanType.IP, 10, 1000);
        LoanResponse requestId = controller.createRequest(request);
        assertEquals(1, requestId.getId());
    }

    @Test
    @DisplayName ("2. requestId инкрементируется при повторных вызовах")
    public void shouldGetIncrementedIdWhenAnyCall() {
        request = new LoanData(LoanType.IP, 10, 1000);
        repository.setRequestId(10);
        LoanResponse requestId = controller.createRequest(request);
        assertEquals(11, requestId.getId());
    }

    @Test
    @DisplayName ("3. пустые поля в request")
    public void shouldGetErrorWhenApplyNullRequest() {
        request = new LoanData(LoanType.OOO, 10, 1000);
        LoanResponse requestId = controller.createRequest(null);
        assertEquals(-1, requestId.getId());
    }

    @Test
    @DisplayName ("4. отрицательная сумма в request")
    public void shouldGetErrorWhenApplyZeroOrNegativeAmountRequest() {
        request = new LoanData(LoanType.OOO, 10, -1);
        LoanResponse requestId = controller.createRequest(request);
        assertEquals(-1, requestId.getId());
    }

    @Test
    @DisplayName ("5. отрицательное количество месяцев в request")
    public void shouldGetErrorWhenApplyZeroOrNagativeMonthsRequest() {
        request = new LoanData(LoanType.OOO, -1, 1000);
        LoanResponse requestId = controller.createRequest(request);
        assertEquals(-1, requestId.getId());
    }

    @Test
    @DisplayName ("6. одобрить заявку для person с суммой меньше 10000 на срок меньше года")
    public void approvedWhenPersonWithAmountLess10000AndMonthsLess12() {
        request = new LoanData(LoanType.PERSON, 10, 9999);
        LoanResponse requestId = controller.createRequest(request);
        assertEquals(ResponseType.APPROVED, requestId.getType());
    }

    @Test
    @DisplayName ("7. одобрить заявку для person с суммой 10000 на срок год")
    public void approvedWhenPersonWithAmount10000AndMonths12() {
        request = new LoanData(LoanType.PERSON, 12, 10000);
        LoanResponse requestId = controller.createRequest(request);
        assertEquals(ResponseType.APPROVED, requestId.getType());
    }

    @Test
    @DisplayName ("8. отклонить заявку для person с суммой больше 10000 на срок больше года")
    public void declinedWhenPersonWithAmountMore10000AndMonthsMore12() {
        request = new LoanData(LoanType.PERSON, 13, 10001);
        LoanResponse requestId = controller.createRequest(request);
        assertEquals(ResponseType.DECLINED, requestId.getType());
    }

    @Test
    @DisplayName ("9. отклонить заявку для ООО с суммой 10000 на срок меньше года")
    public void declinedWhenOooWithAmount10000AndMonthsLess12() {
        request = new LoanData(LoanType.OOO, 9, 10000);
        LoanResponse requestId = controller.createRequest(request);
        assertEquals(ResponseType.DECLINED, requestId.getType());
    }

    @Test
    @DisplayName ("10. отклонить заявку для ООО с суммой 10000 на срок больше года")
    public void declinedWhenOooWithAmount10000AndMonthsMore12() {
        request = new LoanData(LoanType.OOO, 12, 10000);
        LoanResponse requestId = controller.createRequest(request);
        assertEquals(ResponseType.DECLINED, requestId.getType());
    }

    @Test
    @DisplayName ("11. отклонить заявку для ООО с суммой меньше 10000 на любой срок")
    public void declinedWhenOooWithAmountLess10000AndAnyMonths() {
        request = new LoanData(LoanType.OOO, 19, 9999);
        LoanResponse requestId = controller.createRequest(request);
        assertEquals(ResponseType.DECLINED, requestId.getType());
    }

    @Test
    @DisplayName ("12. одобрить заявку для ООО с суммой больше 10000 на срок меньше года")
    public void approvedWhenOooWithAmountMore10000AndMonthsLess12() {
        request = new LoanData(LoanType.OOO, 9, 10001);
        LoanResponse requestId = controller.createRequest(request);
        assertEquals(ResponseType.APPROVED, requestId.getType());
    }

    @Test
    @DisplayName ("13. отклонить заявку для ООО с суммой больше 10000 на год")
    public void declinedWhenOooWithAmountMore10000AndMonths12() {
        request = new LoanData(LoanType.OOO, 12, 10001);
        LoanResponse requestId = controller.createRequest(request);
        assertEquals(ResponseType.DECLINED, requestId.getType());
    }

    @Test
    @DisplayName ("14. отклонить заявку для ООО с суммой больше 10000 на срок больше года")
    public void declinedWhenOooWithAmountMore10000AndMonthsMore12() {
        request = new LoanData(LoanType.OOO, 13, 10001);
        LoanResponse requestId = controller.createRequest(request);
        assertEquals(ResponseType.DECLINED, requestId.getType());
    }

    @Test
    @DisplayName ("15. отклонить заявку для ИП с суммой больше 10000 на срок больше года")
    public void declinedWhenIpWithAmountMore10000AndMonthsMore12() {
        request = new LoanData(LoanType.IP, 13, 10001);
        LoanResponse requestId = controller.createRequest(request);
        assertEquals(ResponseType.DECLINED, requestId.getType());
    }

    @Test
    @DisplayName ("16. отклонить заявку для ИП с суммой меньше 10000 на срок меньше года")
    public void declinedWhenIpWithAmountLess10000AndMonthsLess12() {
        request = new LoanData(LoanType.IP, 1, 1);
        LoanResponse requestId = controller.createRequest(request);
        assertEquals(ResponseType.DECLINED, requestId.getType());
    }

}
