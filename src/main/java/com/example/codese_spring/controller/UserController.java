package com.example.codese_spring.controller;

import com.example.codese_spring.helper.ResponseBuilder.ResponseForm;
import com.example.codese_spring.security.dto.UserRegister;
import com.example.codese_spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {
  @Autowired
  UserService userService;
  @PostMapping(value = "/register")
  public ResponseEntity<ResponseForm<Boolean>> addUser(@RequestBody UserRegister userRegister){
    return ResponseEntity.ok(ResponseForm.buildCustomResponse(userService.addUser(userRegister),1,"ok"));
  }

}
