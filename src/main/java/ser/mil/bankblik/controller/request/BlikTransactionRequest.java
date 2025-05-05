package ser.mil.bankblik.controller.request;

public record BlikTransactionRequest(double amount,String organizationName,String accountNumberDestination) {
}
