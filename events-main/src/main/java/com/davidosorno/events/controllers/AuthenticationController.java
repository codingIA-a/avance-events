package com.davidosorno.events.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.davidosorno.events.models.User;
import com.davidosorno.events.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthenticationController {
  
  private final UserService userService;

  public AuthenticationController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/login")
  public String login(
    @RequestParam("email") String email,
    @RequestParam("password") String password,
    HttpSession session,
    RedirectAttributes redirectAttributes
  ) {
    User user = userService.signIn(email, password);
    if(user == null) {
      redirectAttributes.addFlashAttribute("userError", "Usuario o contraseña invalidos");
      return "redirect:/";
    }

    session.setAttribute("userId", user.getId());
    return "redirect:/events";
  }

  @GetMapping("/logout")
  public String logout(HttpSession session){
    session.invalidate();
    return "redirect:/";
  }
}
