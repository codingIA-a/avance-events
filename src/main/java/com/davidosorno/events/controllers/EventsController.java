package com.davidosorno.events.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.davidosorno.events.models.User;
import com.davidosorno.events.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/events")
public class EventsController {
  
  private final UserService userService;

  public EventsController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("")
  public String events(Model model, HttpSession session) {
    Long userId = (Long) session.getAttribute("userId");
    if(userId == null){
      return "redirect:/";
    }

    User user = userService.findById(userId);
    model.addAttribute("user", user);

    return "events.jsp";
  }

}
