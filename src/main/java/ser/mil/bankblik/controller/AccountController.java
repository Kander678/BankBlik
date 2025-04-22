package ser.mil.bankblik.controller;

import org.hibernate.annotations.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ser.mil.bankblik.controller.request.AccountRequest;
import ser.mil.bankblik.domain.model.Account;
import ser.mil.bankblik.domain.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping("/create")
    public void requestAccount(@RequestBody AccountRequest accountRequest) {
        accountService.saveAccount(accountRequest.accountNumber(), accountRequest.balance());
    }
    @GetMapping("/getAllAccounts")
    public List<Account> getAllAccount(){
        return accountService.getAccounts();
    }


}
