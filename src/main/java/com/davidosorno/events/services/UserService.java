package com.davidosorno.events.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.davidosorno.events.models.User;
import com.davidosorno.events.repositories.UserRepository;

@Service
public class UserService {
  
  private final UserRepository repository;

  public UserService(UserRepository repository) {
    this.repository = repository;
  }

  public List<User> findAll() {
    return repository.findAll();
  }

  public boolean save(User user){
    String hashedPassword = getHashPassword(user.getPassword());
    user.setPassword(hashedPassword);
    return repository.save(user) != null;
  }

  private String getHashPassword(String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt());
  }

  public boolean isInvalidPassword(User user) {
    // return !user.getPassword().equals(user.getPasswordConfirmation());
    return user.getPassword().equals(user.getPasswordConfirmation()) == false;
  }

  public User findByEmail(String email) {
    Optional<User> user = repository.findByEmail(email);
    return user.isPresent() ? user.get() : null;
  }

  public User findById(Long userId) {
    Optional<User> user = repository.findById(userId);
    return user.isPresent() ? user.get() : null;
  }

  public boolean emailExists(String email) {
    return repository.existsByEmail(email);
  }

  public User signIn(String email, String password) {
    User user = findByEmail(email);
    if(user != null) {
      if(BCrypt.checkpw(password, user.getPassword())){
        return user;
      }
    }

    return null;
  }

}
