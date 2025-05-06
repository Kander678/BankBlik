package ser.mil.bankblik.domain.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import ser.mil.bankblik.domain.model.Account;
import ser.mil.bankblik.domain.model.BlikCode;
import ser.mil.bankblik.domain.model.BlikTransaction;
import ser.mil.bankblik.domain.model.TransactionStatus;
import ser.mil.bankblik.domain.repository.BlikRepository;

import java.util.UUID;

@Component
public class BlikCodeService {
    private final BlikRepository blikRepository;
    private final BlikTransactionService transactionService;

    public BlikCodeService(BlikRepository blikRepository, BlikTransactionService transactionService) {
        this.blikRepository = blikRepository;
        this.transactionService = transactionService;
    }

    public int saveBlikCode(String accountName) {
        Account account = blikRepository.findByAccountNumber(accountName).orElseThrow();
        BlikCode blikCode = new BlikCode(UUID.randomUUID().toString(), generateBlikCode(), account);
        blikRepository.save(account);
        blikRepository.save(blikCode);
        return blikCode.getCode();
    }

    public void handleBlikCode(String transactionId, int blikCode) {
        BlikTransaction transaction = blikRepository.getBlikTransactionById(transactionId);
        if (transaction.getStatus() != TransactionStatus.PENDING) {
            throw new RuntimeException();
        }
        BlikCode blikCode1 = blikRepository.findByCode(blikCode).orElseThrow();
        transaction.setBlikCode(blikCode1);
        transaction.setStatus(TransactionStatus.AWAITING_CONFIRMATION);
        blikRepository.save(transaction);
    }


    public void acceptingBlikCode(String transactionId) {
        BlikTransaction transaction = blikRepository.getBlikTransactionById(transactionId);
        if (transaction.getStatus() != TransactionStatus.AWAITING_CONFIRMATION) {
            throw new RuntimeException();
        }

        transactionService.performTransaction(transaction);
        transaction.setStatus(TransactionStatus.SUCCESS);
        blikRepository.save(transaction);
    }

    public int generateBlikCode() {
        int randomCode = (int) (Math.random() * 900000) + 100000;
        return randomCode;
    }

}
