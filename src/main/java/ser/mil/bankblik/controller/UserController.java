package ser.mil.bankblik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ser.mil.bankblik.controller.request.UserRequest;
import ser.mil.bankblik.domain.model.User;
import ser.mil.bankblik.domain.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
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
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping("/pairing")
    public void addAccountToUser(String emailUser,String accountNumber){
        userService.pairAccountWithUser(emailUser, accountNumber);
    }

    @GetMapping("/findUser")
    public User findUser(@RequestBody String emailUser){
        return userService.findUserByEmail(emailUser).orElseThrow();
    }
}
