package com.davidosorno.events.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.davidosorno.events.models.User;
import com.davidosorno.events.services.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
  
  
  private final UserService service;

  public UserController(UserService service) {
    this.service = service;
  }

  @PostMapping("/new")
  public String save(
    @Valid @ModelAttribute("user") User user,
    BindingResult result
  ) {

    if(service.isInvalidPassword(user)){
      FieldError error = new FieldError("passwordConfirmation", "passwordConfirmation", "Las contraseñas no coinciden.");
      result.addError(error);
    }

    if(service.emailExists(user.getEmail())){
      FieldError error = new FieldError("email", "email", "Este email ya se encuentra registrado.");
      result.addError(error);
    }

    if(result.hasErrors()){
      return "index.jsp";
    }

    service.save(user);
    return "redirect:/events";
  }

}



/* 

  /users --- findAll
  /users/new --- GET -> muestra formulario para registro
  /users/new --- POST -> recibe un formulario para guardar
  /users/{id}
  /users/{id}/edit --- GET
  /users/{id}/edit --- POST
  /users/{id}/delete --- GET || POST

*/