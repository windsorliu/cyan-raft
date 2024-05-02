package com.windsor.cyanraft.controller;

import com.windsor.cyanraft.dto.UserLoginRequest;
import com.windsor.cyanraft.dto.UserRegisterRequest;
import com.windsor.cyanraft.model.User;
import com.windsor.cyanraft.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired private UserService userService;

  @PostMapping("/register")
  public ResponseEntity<User> register(
      @RequestBody @Valid UserRegisterRequest userRegisterRequest) {

    Integer userId = userService.register(userRegisterRequest);

    User user = userService.getUserById(userId);

    return ResponseEntity.status(HttpStatus.CREATED).body(user);
  }

  @PostMapping("/login")
  public ResponseEntity<User> login(@RequestBody @Valid UserLoginRequest userLoginRequest) {

    User user = userService.login(userLoginRequest);

    return ResponseEntity.status(HttpStatus.OK).body(user);
  }
}
