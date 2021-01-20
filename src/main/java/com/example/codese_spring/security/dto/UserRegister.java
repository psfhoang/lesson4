package com.example.codese_spring.security.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRegister {
  private String email;
  private String display;
  private String password;

}
