package org.lessons.java.spring_la_mia_pizzeria_crud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
  
  @Bean
  @SuppressWarnings("removal")
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    http.authorizeHttpRequests(auth -> auth
      .requestMatchers("/pizza/create", "/pizza/edit/**", "/pizza/delete/**").hasAuthority("ADMIN")
      .requestMatchers(HttpMethod.POST, "/pizza/**").hasAuthority("ADMIN")
      .requestMatchers("/discount", "/discount/**").hasAuthority("ADMIN")
      .requestMatchers("/pizza", "/pizza/**").hasAnyAuthority("USER", "ADMIN")
      .requestMatchers("/**").permitAll()
    )
    .formLogin(withDefaults -> {})
    .logout(withDefaults -> {})
    .exceptionHandling(withDefaults -> {});

    return http.build();
  }
  // // VERSIONE VECCHIA:
  // SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
  //   http.authorizeHttpRequests().requestMatchers("/pizza/create", "/pizza/edit/**", "/pizza/delete/**").hasAuthority("ADMIN")
  //       .requestMatchers(HttpMethod.POST, "/pizza/**").hasAuthority("ADMIN")
  //       .requestMatchers("/discount", "/discount/**").hasAuthority("ADMIN")
  //       .requestMatchers("/pizza", "/pizza/**").hasAnyAuthority("USER", "ADMIN")
  //       .requestMatchers("/**").permitAll()
  //       .and().formLogin()
  //       .and().logout()
  //       .and().exceptionHandling();

  //   return http.build();
  // }

  @SuppressWarnings("deprecation")
  DaoAuthenticationProvider authenticationProvider(){
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

    authProvider.setUserDetailsService(userDetailsService());

    authProvider.setPasswordEncoder(passwordEncoder());

    return authProvider;
  }

  @Bean
  DatabaseUserDetailsService userDetailsService() {
    return new DatabaseUserDetailsService();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
}
