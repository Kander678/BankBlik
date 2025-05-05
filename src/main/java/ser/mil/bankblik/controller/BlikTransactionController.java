package ser.mil.bankblik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ser.mil.bankblik.controller.request.BlikTransactionRequest;
import ser.mil.bankblik.domain.model.BlikTransactionResponse;
import ser.mil.bankblik.domain.service.BlikTransactionService;
@RestController
@RequestMapping("/transaction")
public class BlikTransactionController {
    private final BlikTransactionService blikTransactionService;
    @Autowired
    public BlikTransactionController(BlikTransactionService blikTransactionService) {
        this.blikTransactionService = blikTransactionService;
    }
    @PostMapping("/create")
    public BlikTransactionResponse createBlikTransaction(@RequestBody BlikTransactionRequest blikTransactionRequest) {
        return blikTransactionService.createTransaction(
                blikTransactionRequest.amount(),
                blikTransactionRequest.organizationName(),
                blikTransactionRequest.accountNumberDestination());
    }
}
