package com.example.codese_spring.service;

import com.example.codese_spring.dto.LoginRequestDTO;
import com.example.codese_spring.dto.LoginResponseDto;
import com.example.codese_spring.repository.TokenRepository;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

  private final UserService userService;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;

  public LoginResponseDto createToken(LoginRequestDTO loginRequestDTO) {
    if (userService.isExist(loginRequestDTO.getUsername(),
        loginRequestDTO.getPassword())) {
      String uuid = UUID.randomUUID().toString();
      tokenRepository.addToken(loginRequestDTO.getUsername(), uuid);
      return LoginResponseDto.builder().key(uuid).build();
    } else {
      return LoginResponseDto.builder().key("tai khoan mat khau khong hop le!").build();
    }
  }
}
