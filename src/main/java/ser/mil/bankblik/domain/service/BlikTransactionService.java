package ser.mil.bankblik.domain.service;

import org.springframework.stereotype.Component;
import ser.mil.bankblik.domain.model.*;
import ser.mil.bankblik.domain.repository.BlikRepository;

import java.util.UUID;

@Component
public class BlikTransactionService {
    private final BlikRepository blikRepository;

    public BlikTransactionService(BlikRepository blikRepository) {
        this.blikRepository = blikRepository;
    }

    public BlikTransactionResponse createTransaction(double amount, String organizationName, String accountNumberDestination) {
        Organization organization = blikRepository.findByName(organizationName).orElseThrow();
        Account accountSource = organization.getAccount();
        Account accountDestination = blikRepository.findByAccountNumber(accountNumberDestination).orElseThrow();

        BlikTransaction blikTransaction = new BlikTransaction(
                UUID.randomUUID().toString(),
                amount,
                TransactionStatus.PENDING,
                organization,
                accountSource,
                accountDestination);
        blikRepository.save(blikTransaction);

        String transactionId = blikTransaction.getId();
        String blikCodePath = "/blik/code/" + transactionId;

        return new BlikTransactionResponse(transactionId, blikCodePath);
    }

    public void performTransaction(BlikTransaction transaction) {
        double amount = transaction.getAmount();
        Account sourceAccount = transaction.getSourceAccount();
        Account destinationAccount = transaction.getDestinationAccount();

        if (sourceAccount.getBalance() < amount) throw new RuntimeException("Insufficient funds");

        sourceAccount.setBalance(sourceAccount.getBalance() - amount);
        destinationAccount.setBalance(destinationAccount.getBalance() + amount);
    }


}

