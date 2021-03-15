package com.example.codese_spring.controller;

import com.example.codese_spring.dto.LoginRequestDTO;
import com.example.codese_spring.dto.LoginResponseDto;
import com.example.codese_spring.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  public LoginResponseDto login(@RequestBody LoginRequestDTO loginRequestDTO) {
    return authService.createToken(loginRequestDTO);
  }
}
