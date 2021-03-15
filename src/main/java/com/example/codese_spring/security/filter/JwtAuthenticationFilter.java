package com.example.codese_spring.security.filter;

import com.example.codese_spring.security.JwtTokenProvider;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  JwtTokenProvider jwtTokenProvider;
  UserDetailsService userDetailsService;
  public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider,
      UserDetailsService userDetailsService) {
    this.jwtTokenProvider = jwtTokenProvider;
    this.userDetailsService = userDetailsService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String authorizationString = request.getHeader("Authorization");

    if (authorizationString != null && jwtTokenProvider.validateToken(authorizationString)) {
      String username = jwtTokenProvider.getUsernameFromToken(authorizationString);
      UserDetails userDetails = userDetailsService.loadUserByUsername(username);
      Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null,
          userDetails.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);
    } else {

    }

    filterChain.doFilter(request, response);


  }
}
