package com.tinkoff.edu;

import com.tinkoff.edu.app.loan.controller.LoanCalcController;
import com.tinkoff.edu.app.loan.models.LoanData;
import com.tinkoff.edu.app.loan.models.LoanResponse;
import com.tinkoff.edu.app.loan.repository.impl.MapLoanCalcRepository;
import com.tinkoff.edu.app.loan.service.impl.LoanCalcService;
import com.tinkoff.edu.app.loan.types.LoanType;
import com.tinkoff.edu.app.loan.types.ResponseType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class MapAppTest {
    private MapLoanCalcRepository mapRepository;
    private LoanCalcService service;
    private LoanCalcController controller;
    private LoanData request;
    private String fio;

    @BeforeEach
    public void init() {
        mapRepository = new MapLoanCalcRepository();
        service = new LoanCalcService(mapRepository);
        controller = new LoanCalcController(service);
        this.fio = "Иванов Иван Иваныч";

    }

    @Test
    @DisplayName ("requestId not null при любом запросе")
    public void shouldGetId1WhenFirstCall() {
        request = new LoanData(LoanType.PERSON, 10, 1000, fio);
        LoanResponse requestId = controller.createRequest(request);
        assertNotNull(requestId.getId());
    }

    @Test
    @DisplayName ("пустые поля в request")
    public void shouldGetErrorWhenApplyNullRequest() {
        request = new LoanData(LoanType.OOO, 10, 1000, fio);
        LoanResponse requestId = controller.createRequest(null);
        assertNull(requestId.getId());
    }

    @Test
    @DisplayName ("отрицательная сумма в request")
    public void shouldGetErrorWhenApplyZeroOrNegativeAmountRequest() {
        request = new LoanData(LoanType.OOO, 10, -1, fio);
        LoanResponse requestId = controller.createRequest(request);
        assertNull(requestId.getId());
    }

    @Test
    @DisplayName ("отрицательное количество месяцев в request")
    public void shouldGetErrorWhenApplyZeroOrNagativeMonthsRequest() {
        request = new LoanData(LoanType.OOO, -1, 1000, fio);
        LoanResponse requestId = controller.createRequest(request);
        assertNull(requestId.getId());
    }

    @Test
    @DisplayName ("одобрить заявку для person с суммой меньше 10000 на срок меньше года")
    public void approvedWhenPersonWithAmountLess10000AndMonthsLess12() {
        request = new LoanData(LoanType.PERSON, 10, 9999, fio);
        LoanResponse requestId = controller.createRequest(request);
        assertEquals(ResponseType.APPROVED, requestId.getType());
    }

    @Test
    @DisplayName ("одобрить заявку для person с суммой 10000 на срок год")
    public void approvedWhenPersonWithAmount10000AndMonths12() {
        request = new LoanData(LoanType.PERSON, 12, 10000, fio);
        LoanResponse requestId = controller.createRequest(request);
        assertEquals(ResponseType.APPROVED, requestId.getType());
    }

    @Test
    @DisplayName ("отклонить заявку для person с суммой больше 10000 на срок больше года")
    public void declinedWhenPersonWithAmountMore10000AndMonthsMore12() {
        request = new LoanData(LoanType.PERSON, 13, 10001, fio);
        LoanResponse requestId = controller.createRequest(request);
        assertEquals(ResponseType.DECLINED, requestId.getType());
    }

    @Test
    @DisplayName ("отклонить заявку для ООО с суммой 10000 на срок меньше года")
    public void declinedWhenOooWithAmount10000AndMonthsLess12() {
        request = new LoanData(LoanType.OOO, 9, 10000, fio);
        LoanResponse requestId = controller.createRequest(request);
        assertEquals(ResponseType.DECLINED, requestId.getType());
    }

    @Test
    @DisplayName ("отклонить заявку для ООО с суммой 10000 на срок больше года")
    public void declinedWhenOooWithAmount10000AndMonthsMore12() {
        request = new LoanData(LoanType.OOO, 12, 10000, fio);
        LoanResponse requestId = controller.createRequest(request);
        assertEquals(ResponseType.DECLINED, requestId.getType());
    }

    @Test
    @DisplayName ("отклонить заявку для ООО с суммой меньше 10000 на любой срок")
    public void declinedWhenOooWithAmountLess10000AndAnyMonths() {
        request = new LoanData(LoanType.OOO, 19, 9999, fio);
        LoanResponse requestId = controller.createRequest(request);
        assertEquals(ResponseType.DECLINED, requestId.getType());
    }

    @Test
    @DisplayName ("одобрить заявку для ООО с суммой больше 10000 на срок меньше года")
    public void approvedWhenOooWithAmountMore10000AndMonthsLess12() {
        request = new LoanData(LoanType.OOO, 9, 10001, fio);
        LoanResponse requestId = controller.createRequest(request);
        assertEquals(ResponseType.APPROVED, requestId.getType());
    }

    @Test
    @DisplayName ("отклонить заявку для ООО с суммой больше 10000 на год")
    public void declinedWhenOooWithAmountMore10000AndMonths12() {
        request = new LoanData(LoanType.OOO, 12, 10001, fio);
        LoanResponse requestId = controller.createRequest(request);
        assertEquals(ResponseType.DECLINED, requestId.getType());
    }

    @Test
    @DisplayName ("отклонить заявку для ООО с суммой больше 10000 на срок больше года")
    public void declinedWhenOooWithAmountMore10000AndMonthsMore12() {
        request = new LoanData(LoanType.OOO, 13, 10001, fio);
        LoanResponse requestId = controller.createRequest(request);
        assertEquals(ResponseType.DECLINED, requestId.getType());
    }

    @Test
    @DisplayName ("отклонить заявку для ИП с суммой больше 10000 на срок больше года")
    public void declinedWhenIpWithAmountMore10000AndMonthsMore12() {
        request = new LoanData(LoanType.IP, 13, 10001, fio);
        LoanResponse requestId = controller.createRequest(request);
        assertEquals(ResponseType.DECLINED, requestId.getType());
    }

    @Test
    @DisplayName ("отклонить заявку для ИП с суммой меньше 10000 на срок меньше года")
    public void declinedWhenIpWithAmountLess10000AndMonthsLess12() {
        request = new LoanData(LoanType.IP, 1, 1, fio);
        LoanResponse requestId = controller.createRequest(request);
        assertEquals(ResponseType.DECLINED, requestId.getType());
    }

    @Test
    @DisplayName ("должен вернуться статус заявки по id, если заявка сохранена в репозитории")
    public void shouldGetResponseTypeByIdIfDataExists() {
        request = new LoanData(LoanType.IP, 1, 1, fio);
        LoanResponse response = controller.createRequest(request);
        LoanResponse responseById = controller.getById(response.getId());
        assertEquals(response.getType(), responseById.getType());
        assertEquals(response.getId(), responseById.getId());
    }

    @Test
    @DisplayName ("должен вернуться статус approved, при апдейте статуса заявки для IP")
    public void shouldGetResponseTypeAfterUpdateLoanData() {
        request = new LoanData(LoanType.IP, 1, 1, fio);
        LoanResponse response = controller.createRequest(request);
        LoanResponse responseAfterUpdate = controller.updateById(response.getId(), ResponseType.APPROVED);
        assertEquals(ResponseType.APPROVED, responseAfterUpdate.getType());
    }

    @Test
    @DisplayName ("должен вернуться статус заявки declined и id null, если репозиторий пустой")
    public void shouldGetResponseTypeWithIdNullIfRepositoryIsEmpty() {
        LoanResponse responseById = controller.getById(UUID.randomUUID());
        assertNull(responseById.getId());
        assertEquals(ResponseType.DECLINED, responseById.getType());
    }

    @Test
    @DisplayName ("должен вернуться статус заявки declined и id null, если заявки не существует в репозитории")
    public void shouldGetResponseTypeWithIdNullIfDataNotExists() {
        request = new LoanData(LoanType.IP, 1, 1, fio);
        LoanResponse response = controller.createRequest(request);
        System.out.println(response);
        LoanResponse responseById = controller.getById(UUID.randomUUID());
        assertNull(responseById.getId());
        assertEquals(ResponseType.DECLINED, responseById.getType());
    }

    @Test
    @DisplayName ("должен вернуться статус заявки declined и id null при update, если заявки не существует в репозитории")
    public void shouldGetResponseTypeWhenUpdateWithIdNullIfDataNotExists() {
        LoanResponse responseByIdForUpdate = controller.updateById(UUID.randomUUID(), ResponseType.APPROVED);
        assertNull(responseByIdForUpdate.getId());
        assertEquals(ResponseType.DECLINED, responseByIdForUpdate.getType());
    }

    @Test
    @DisplayName ("должен вернуться статус заявки declined и id null, если fio пусто")
    public void shouldGetResponseTypeWithIdNullIfFioIsEmpty() {
        request = new LoanData(LoanType.PERSON, 2, 100, null);
        LoanResponse response = controller.createRequest(request);
        assertNull(response.getId());
        assertEquals(ResponseType.DECLINED, response.getType());
    }

    @Test
    @DisplayName ("должен вернуться статус заявки declined и id null, если fio короткое")
    public void shouldGetResponseTypeWithIdNullIfFioIsTooShort() {
        request = new LoanData(LoanType.PERSON, 2, 100, "тест");
        LoanResponse response = controller.createRequest(request);
        assertNull(response.getId());
        assertEquals(ResponseType.DECLINED, response.getType());
    }

    @Test
    @DisplayName ("должен вернуться статус заявки declined и id null, если fio длинное")
    public void shouldGetResponseTypeWithIdNullIfFioIsTooLong() {
        request = new LoanData(LoanType.PERSON, 2, 100, "аотулоы ваолмты амаама амамуаццуацу уцацуцуауац цацуацуаам вдмтыдвл мдлывтмдлфвтмдлыв амлоытвмдыотвмол");
        LoanResponse response = controller.createRequest(request);
        assertNull(response.getId());
        assertEquals(ResponseType.DECLINED, response.getType());
    }

    @Test
    @DisplayName ("должен вернуться статус заявки declined и id null, если fio с запрещенными символами")
    public void shouldGetResponseTypeWithIdNullIfFioIsContainsProhibitedSymbols() {
        request = new LoanData(LoanType.PERSON, 2, 100, "123_123_123_123");
        LoanResponse response = controller.createRequest(request);
        assertNull(response.getId());
        assertEquals(ResponseType.DECLINED, response.getType());
    }

    @Test
    @DisplayName ("должен вернуться статус заявки declined и id null, если месяцев больше 100")
    public void shouldGetResponseTypeWithIdNullIfMonthsMoreThan100() {
        request = new LoanData(LoanType.PERSON, 101, 100, fio);
        LoanResponse response = controller.createRequest(request);
        assertNull(response.getId());
        assertEquals(ResponseType.DECLINED, response.getType());
    }

    @Test
    @DisplayName ("должен вернуться статус заявки declined и id null, если сумма больше 1_000_000")
    public void shouldGetResponseTypeWithIdNullIfMAnountMoreThan1000000() {
        request = new LoanData(LoanType.PERSON, 2, 1000001, fio);
        LoanResponse response = controller.createRequest(request);
        assertNull(response.getId());
        assertEquals(ResponseType.DECLINED, response.getType());
    }

    @Test
    @DisplayName ("должны вернуться все ооо из репо")
    public void shouldGetAllByLoanType() {
        request = new LoanData(LoanType.OOO, 9, 10001, fio);
        LoanResponse response1 = controller.createRequest(request);
        request = new LoanData(LoanType.PERSON, 2, 1000001, fio);
        LoanResponse response2 = controller.createRequest(request);
        request = new LoanData(LoanType.OOO, 12, 10001, fio);
        LoanResponse response3 = controller.createRequest(request);

        List<LoanResponse> testResponseList = new ArrayList<>();
        testResponseList.add(response1);
        testResponseList.add(response3);
        System.out.println(testResponseList);
        System.out.println(controller.getAllByLoanType(LoanType.OOO));

        assertThat(controller.getAllByLoanType(LoanType.OOO), containsInAnyOrder(
                hasProperty("id", is(response1.getId())),
                hasProperty("id", is(response3.getId()))
        ));
    }

    @Test
    @DisplayName ("не должно вернуться все ip, где нет ip")
    public void shouldGetEmptyAllByLoanTypeWhenLoanTypeNotExists() {
        request = new LoanData(LoanType.OOO, 9, 10001, fio);
        LoanResponse response1 = controller.createRequest(request);
        request = new LoanData(LoanType.PERSON, 12, 10000, fio);
        LoanResponse response2 = controller.createRequest(request);
        request = new LoanData(LoanType.OOO, 12, 10001, fio);
        LoanResponse response3 = controller.createRequest(request);

        assertThat(controller.getAllByLoanType(LoanType.IP).isEmpty(), is(true));
    }

    @Test
    @DisplayName ("не должно вернуться ничего для getAllByLoanType, когда репо пустой")
    public void shouldGeEmptyAllByLoanTypeWhenRepoIsEmpty() {
        assertThat(controller.getAllByLoanType(LoanType.IP).isEmpty(), is(true));
    }

}
