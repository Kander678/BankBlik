package ser.mil.bankblik.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import ser.mil.bankblik.domain.model.*;
import ser.mil.bankblik.domain.repository.BlikRepository;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class BlikCodeControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private BlikRepository blikRepository;

    private Account account;
    private Organization organization;

    @BeforeEach
    void setUp() {
        blikRepository.clearDatabase();

        account = new Account("2", "1234567890", 1000.0);
        organization = new Organization(UUID.randomUUID().toString(), "TestOrg", account);
        blikRepository.save(account);
        blikRepository.save(organization);
    }

    @Test
    void shouldHandleBlikCodeAndSetAwaitingConfirmation() {
        // Given
        BlikCode code = new BlikCode(UUID.randomUUID().toString(), 123456, account);
        blikRepository.save(code);

        Account destination = new Account("1","0987654321", 500.0);
        blikRepository.save(destination);

        BlikTransaction transaction = new BlikTransaction("tx123", 100.0, TransactionStatus.PENDING, organization, account, destination);
        blikRepository.save(transaction);

        // When
        webTestClient.post()
                .uri("/blik/code/tx123?blickCode=123456")
                .exchange()
                .expectStatus().isOk();

        // Then
        BlikTransaction updated = blikRepository.getBlikTransactionById("tx123");
        assertEquals(TransactionStatus.AWAITING_CONFIRMATION, updated.getStatus());
        assertEquals(123456, updated.getBlikCode().getCode());
    }

    @Test
    void shouldConfirmBlikTransactionAndTransferFunds() {
        // Given
        Account destination = new Account("1","0987654321", 500.0);
        blikRepository.save(destination);

        BlikTransaction transaction = new BlikTransaction("tx999", 200.0, TransactionStatus.AWAITING_CONFIRMATION, organization, account, destination);
        blikRepository.save(transaction);

        // When
        webTestClient.post()
                .uri("/blik/confirm/tx999")
                .exchange()
                .expectStatus().isOk();

        // Then
        Account account1=blikRepository.findByAccountNumber(account.getAccountNumber()).orElseThrow();
        Account account2=blikRepository.findByAccountNumber(destination.getAccountNumber()).orElseThrow();

        BlikTransaction confirmed = blikRepository.getBlikTransactionById("tx999");
        assertEquals(TransactionStatus.SUCCESS, confirmed.getStatus());
        assertEquals(800.0, account1.getBalance());
        assertEquals(700.0, account2.getBalance());
    }
}