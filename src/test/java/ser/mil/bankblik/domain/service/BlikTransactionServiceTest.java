package ser.mil.bankblik.domain.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ser.mil.bankblik.domain.model.*;
import ser.mil.bankblik.domain.repository.BlikRepository;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BlikTransactionServiceTest {

    @Mock
    private BlikRepository blikRepository;

    @InjectMocks
    private BlikTransactionService blikTransactionService;

    @Test
    void shouldCreateTransaction() {
        // Given
        String organizationName = "MyOrg";
        String accountNumberDest = "DEST123";
        double amount = 200.0;

        Account sourceAccount = new Account("1","SRC123", 1000.0);
        Account destinationAccount = new Account("2",accountNumberDest, 500.0);
        Organization organization = new Organization(UUID.randomUUID().toString(), organizationName, sourceAccount);

        when(blikRepository.findByName(organizationName)).thenReturn(Optional.of(organization));
        when(blikRepository.findByAccountNumber(accountNumberDest)).thenReturn(Optional.of(destinationAccount));

        // When
        BlikTransactionResponse response = blikTransactionService.createTransaction(amount, organizationName, accountNumberDest);

        // Then
        verify(blikRepository, times(1)).save(any(BlikTransaction.class));
        assertNotNull(response.transactionId());
        assertEquals("/blik/code/" + response.transactionId(), response.blikCodePath());
    }

    @Test
    void shouldPerformTransaction() {
        // Given
        double amount = 100.0;
        Account source = new Account("1","SRC123", 500.0);
        Account destination = new Account("2","DEST123", 300.0);
        BlikTransaction transaction = new BlikTransaction("TX123", amount, TransactionStatus.AWAITING_CONFIRMATION, null, source, destination);

        // When
        blikTransactionService.performTransaction(transaction);

        // Then
        assertEquals(400.0, source.getBalance());
        assertEquals(400.0, destination.getBalance());
    }

    @Test
    void shouldThrowWhenInsufficientFunds() {
        // Given
        double amount = 600.0;
        Account source = new Account("1","SRC123", 500.0);
        Account destination = new Account("2","DEST123", 300.0);
        BlikTransaction transaction = new BlikTransaction("TX124", amount, TransactionStatus.AWAITING_CONFIRMATION, null, source, destination);

        // Then
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> blikTransactionService.performTransaction(transaction));

        assertEquals("Insufficient funds", exception.getMessage());
        assertEquals(500.0, source.getBalance());
        assertEquals(300.0, destination.getBalance());
    }
}
