package rs.tim14.xml.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.tim14.xml.dto.request.LoginRequest;
import rs.tim14.xml.dto.response.LoginResponse;
import rs.tim14.xml.model.User;
import rs.tim14.xml.service.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping(produces = {"application/xml"})
    public ResponseEntity<User> register(@RequestBody User user) throws Exception {
        return new ResponseEntity<>(this.userService.register(user), HttpStatus.CREATED);
    }

    @PostMapping(path = "/login",produces = {"application/xml"})
    public ResponseEntity<LoginResponse> register(@RequestBody LoginRequest loginRequest) throws Exception {
        return new ResponseEntity<>(this.userService.login(loginRequest), HttpStatus.ACCEPTED);
    }


}
