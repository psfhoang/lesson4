package com.example.codese_spring.security.filter;

import com.example.codese_spring.security.JwtTokenProvider;
import com.example.codese_spring.security.dto.LoginReq;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

public class JwtUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private final String URL = "/create-token";
  AuthenticationManager authenticationManager;
  JwtTokenProvider jwtTokenProvider;

  public JwtUsernamePasswordAuthenticationFilter(
      AuthenticationManager authenticationManager,
      JwtTokenProvider jwtTokenProvider) {
    this.authenticationManager = authenticationManager;
    this.jwtTokenProvider = jwtTokenProvider;
    this.setURL();
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException {

    try {
      LoginReq loginReq = new ObjectMapper().readValue(request.getInputStream(), LoginReq.class);
      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              loginReq.getUsername(),
              loginReq.getPassword()
          )
      );
      return authentication;

    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain, Authentication authResult) throws IOException, ServletException {
    response.addHeader("Authorization", jwtTokenProvider.generateToken(authResult.getName()));
    response.getWriter().print(
        jwtTokenProvider.generateToken(authResult.getName()));
  }

  public void setURL() {
    this.setFilterProcessesUrl(URL);
  }

  @Override
  public void setFilterProcessesUrl(String filterProcessesUrl) {
    super.setFilterProcessesUrl(filterProcessesUrl);
  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request,
      HttpServletResponse response, AuthenticationException failed)
      throws IOException, ServletException {
    response.getWriter().println("OOPS");
  }


}

