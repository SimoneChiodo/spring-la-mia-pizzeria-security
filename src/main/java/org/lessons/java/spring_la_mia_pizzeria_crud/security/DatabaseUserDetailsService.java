package org.lessons.java.spring_la_mia_pizzeria_crud.security;

import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.User;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailsService implements UserDetailsService{

  @Autowired
  UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> userAttempt = userRepository.findByUsername(username);
    if (userAttempt.isEmpty()) {
      throw new UsernameNotFoundException("There is no user with this username: " + username);
    }

    return new DatabaseUserDetails(userAttempt.get());
  }
  
}
