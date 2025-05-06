package ser.mil.bankblik.domain.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ser.mil.bankblik.domain.model.Account;
import ser.mil.bankblik.domain.model.BlikCode;
import ser.mil.bankblik.domain.model.BlikTransaction;
import ser.mil.bankblik.domain.model.TransactionStatus;
import ser.mil.bankblik.domain.repository.BlikRepository;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BlikCodeServiceTest {

    @Mock
    private BlikRepository blikRepository;

    @Mock
    private BlikTransactionService transactionService;

    @InjectMocks
    private BlikCodeService blikCodeService;

    @Test
    void shouldSaveBlikCode() {
        //Given
        String accountNumber = "1234567890";
        Account account = new Account("1", accountNumber, 1000.0);
        when(blikRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(account));

        //When
        int code = blikCodeService.saveBlikCode(accountNumber);

        //Then
        verify(blikRepository, times(1)).save(account);
        verify(blikRepository, times(1)).save(any(BlikCode.class));
        assertTrue(code >= 100000 && code <= 999999);
    }

    @Test
    void shouldHandleBlikCode() {
        //Given
        String transactionId = "tx123";
        int code = 123456;

        BlikTransaction transaction = new BlikTransaction(transactionId, 100.0, TransactionStatus.PENDING, null, new Account("1", "source", 500.0), new Account("2", "dest", 100.0));
        BlikCode blikCode = new BlikCode(UUID.randomUUID().toString(), code, new Account("3", "user", 1000.0));

        when(blikRepository.getBlikTransactionById(transactionId)).thenReturn(transaction);
        when(blikRepository.findByCode(code)).thenReturn(Optional.of(blikCode));

        //When
        blikCodeService.handleBlikCode(transactionId, code);

        //Then
        assertEquals(TransactionStatus.AWAITING_CONFIRMATION, transaction.getStatus());
        assertEquals(blikCode, transaction.getBlikCode());
        verify(blikRepository, times(1)).save(transaction);
    }

    @Test
    void shouldAcceptBlikCode() {
        //Given
        String transactionId = "tx123";
        BlikTransaction transaction = new BlikTransaction(transactionId, 100.0, TransactionStatus.AWAITING_CONFIRMATION, null, new Account("1", "source", 1000.0), new Account("2", "dest", 500.0));

        when(blikRepository.getBlikTransactionById(transactionId)).thenReturn(transaction);

        //When
        blikCodeService.acceptingBlikCode(transactionId);

        //Then
        verify(transactionService, times(1)).performTransaction(transaction);
        verify(blikRepository, times(1)).save(transaction);
        assertEquals(TransactionStatus.SUCCESS, transaction.getStatus());
    }

    @Test
    void shouldThrowWhenHandlingCodeWithWrongStatus() {
        //Given
        String transactionId = "tx123";
        BlikTransaction transaction = new BlikTransaction(transactionId, 100.0, TransactionStatus.SUCCESS, null, null, null);
        when(blikRepository.getBlikTransactionById(transactionId)).thenReturn(transaction);

        //When //Then
        assertThrows(RuntimeException.class, () -> blikCodeService.handleBlikCode(transactionId, 123456));
    }

    @Test
    void shouldThrowWhenAcceptingCodeWithWrongStatus() {
        //Given
        String transactionId = "tx123";
        BlikTransaction transaction = new BlikTransaction(transactionId, 100.0, TransactionStatus.PENDING, null, null, null);
        when(blikRepository.getBlikTransactionById(transactionId)).thenReturn(transaction);

        //When //Then
        assertThrows(RuntimeException.class, () -> blikCodeService.acceptingBlikCode(transactionId));
    }
}
