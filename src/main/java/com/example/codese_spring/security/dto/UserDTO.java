package com.example.codese_spring.security.dto;

import java.util.Collection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@NoArgsConstructor
public class UserDTO {

  private String email;

  private String password;

  private String roles;

  private String ID;

  private String display;


}
