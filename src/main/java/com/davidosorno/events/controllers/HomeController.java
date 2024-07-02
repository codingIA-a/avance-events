package com.davidosorno.events.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.davidosorno.events.models.User;

@Controller
public class HomeController {
  
  @GetMapping("")
  public String home(@ModelAttribute("user") User user) {
    return "index.jsp";
  }

}
