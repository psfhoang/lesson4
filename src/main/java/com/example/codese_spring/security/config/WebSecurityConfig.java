package com.example.codese_spring.security.config;

import com.example.codese_spring.repository.UserRepository;
import com.example.codese_spring.security.JwtTokenProvider;
import com.example.codese_spring.security.filter.JwtAuthenticationFilter;
import com.example.codese_spring.security.filter.JwtUsernamePasswordAuthenticationFilter;
import com.example.codese_spring.security.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Bean
  public UserDetailsService userDetailsService(){
    return new CustomUserDetailService();
  }
  @Bean
  public PasswordEncoder passwordEncoder()
  {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserRepository userRepository(){
    return new UserRepository();
  }
  @Bean(BeanIds.AUTHENTICATION_MANAGER)
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
  @Bean
  public JwtTokenProvider jwtTokenProvider(){
    return new JwtTokenProvider();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService());
  }


  @Override
  protected AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .addFilter(new BasicAuthenticationFilter(authenticationManager()))
        .addFilterBefore(new JwtUsernamePasswordAuthenticationFilter(authenticationManager(),jwtTokenProvider()),BasicAuthenticationFilter.class)
        .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider(),userDetailsService()),JwtUsernamePasswordAuthenticationFilter.class)
        .authorizeRequests()
        .antMatchers("/user/register").permitAll()
        .antMatchers("/receipt/all").hasAnyAuthority("STAFF","ADMIN")
        .antMatchers("/receipt/create").hasAnyAuthority("STAFF","ADMIN","USER")
        .anyRequest()
        .authenticated()
    ;
  }
}
