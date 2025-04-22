package ser.mil.bankblik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ser.mil.bankblik.controller.request.UserRequest;
import ser.mil.bankblik.domain.service.UserService;

@RestController
@RequestMapping("/User")
public class BlikController {
    private final UserService userService;

    @Autowired
    public BlikController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/create")
    public void requestUser(@RequestBody UserRequest userRequest) {
        userService.saveUser(
                userRequest.name(),
                userRequest.email(),
                userRequest.phone(),
                userRequest.balance());
    }
    @GetMapping("/allUsers")
    public void getUsers(){
        userService.getUsers();
    }

}
