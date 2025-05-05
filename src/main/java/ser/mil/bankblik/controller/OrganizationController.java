package ser.mil.bankblik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ser.mil.bankblik.controller.request.OrganizationRequest;
import ser.mil.bankblik.domain.service.OrganizationService;

@RestController
@RequestMapping("/organization")
public class OrganizationController {
    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping("/create")
    public void createOrganization(OrganizationRequest organizationRequest) {
        organizationService.createOrganization(
                organizationRequest.organizationName(),
                organizationRequest.accountName());
    }
}
