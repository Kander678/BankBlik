package ser.mil.bankblik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ser.mil.bankblik.controller.request.BlickCodeRequest;
import ser.mil.bankblik.domain.service.BlikCodeService;

@RestController
@RequestMapping("/blik")
public class BlikCodeController {
    private final BlikCodeService blikCodeService;

    @Autowired
    public BlikCodeController(BlikCodeService blikCodeService) {
        this.blikCodeService = blikCodeService;
    }

    @PostMapping("/create")
    public int sendCode(@RequestBody BlickCodeRequest blickCodeRequest) {
        return blikCodeService.saveBlikCode(
                blickCodeRequest.accountName());
    }

    @PostMapping("/code/{transactionId}")
    public void handlingCode(@PathVariable("transactionId") String transactionId, int blickCode) {
        blikCodeService.handleBlikCode(transactionId, blickCode);
    }
    @PostMapping("/confirm/{transactionId}")
    public void acceptingTransaction(@PathVariable("transactionId") String transactionId) {
        blikCodeService.acceptingBlikCode(transactionId);
    }
}
