package ser.mil.bankblik.domain.service;

import org.springframework.stereotype.Component;
import ser.mil.bankblik.domain.model.Account;
import ser.mil.bankblik.domain.model.BlikCode;
import ser.mil.bankblik.domain.repository.BlikRepository;

import java.util.UUID;
@Component
public class BlikCodeService {
    private final BlikRepository blikRepository;

    public BlikCodeService(BlikRepository blikRepository) {
        this.blikRepository = blikRepository;
    }

    public int saveBlikCode(double amount, String accountName) {
        Account account = blikRepository.findByAccountNumber(accountName).orElseThrow();
        BlikCode blikCode = new BlikCode(UUID.randomUUID().toString(),generateBlikCode(),amount,account);
        account.setBalance(account.getBalance() - amount);
        blikRepository.save(account);
        blikRepository.save(blikCode);
        return blikCode.getCode();
    }

    public void usingCode(int code, String accountNameUsingCode){
        Account account = blikRepository.findByAccountNumber(accountNameUsingCode).orElseThrow();
        BlikCode blikCode =blikRepository.findByCode(code).orElseThrow();

        account.setBalance(account.getBalance()+blikCode.getAmount());
        blikRepository.save(account);
        blikRepository.deleteByCode(blikCode.getCode());
    }


    public int generateBlikCode() {
        int randomCode = (int)(Math.random() * 900000) + 100000;
        return randomCode;
    }

}
