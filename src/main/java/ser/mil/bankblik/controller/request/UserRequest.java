package ser.mil.bankblik.controller.request;

public record UserRequest(String name, String email, String phone, Double balance) {
}
