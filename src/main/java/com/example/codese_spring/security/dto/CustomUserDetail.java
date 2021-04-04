package com.example.codese_spring.security.dto;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetail implements UserDetails {

  private String username;
  private String password;
  private Collection<GrantedAuthority> role = new ArrayList<>();

  public CustomUserDetail(UserDTO userDTO) {
    this.username = userDTO.getEmail();
    this.password = userDTO.getPassword();
    this.role.add(new SimpleGrantedAuthority(userDTO.getRoles()));
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return role;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
