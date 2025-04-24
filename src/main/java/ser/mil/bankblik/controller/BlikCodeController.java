package ser.mil.bankblik.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ser.mil.bankblik.controller.request.BlickCodeRequest;
import ser.mil.bankblik.domain.model.BlikCode;
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
                blickCodeRequest.amount(),
                blickCodeRequest.accountName());
    }

    @PostMapping("/usingCode")
    public void usingCode(int code, String accountNameUsingCode) {
        blikCodeService.usingCode(code, accountNameUsingCode);
    }
}
