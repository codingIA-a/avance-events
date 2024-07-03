package com.davidosorno.events.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.davidosorno.events.models.Event;
import com.davidosorno.events.models.User;
import com.davidosorno.events.services.EventService;
import com.davidosorno.events.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/events")
public class EventsController {
  @Autowired
  private  UserService userService;
  @Autowired
  private  EventService eventService;
  

  //arreglo con todos los estados
  private final String[] states = { "AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "IA", "ID",
	        "IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MI", "MN", "MO", "MS", "MT", "NC", "ND", "NE", "NH", "NJ",
	        "NM", "NV", "NY", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VA", "VT", "WA", "WI", "WV",
	        "WY" };

  @GetMapping("")
  public String events(Model model, HttpSession session, @ModelAttribute("event") Event event) {
    Long userId = (Long) session.getAttribute("userId");
    if(userId == null){
      return "redirect:/";
    }

    User user = userService.findById(userId);
    model.addAttribute("user", user);

    return "events.jsp";
  }


   @PostMapping(value="/create")
    public String createEvent(@Valid @ModelAttribute("event") Event event, BindingResult result, Model model, HttpSession session) {
    	if(result.hasErrors()) {
        	Long userId = (Long) session.getAttribute("userId");
        	User u = eventService.findUserById(userId);
        	model.addAttribute("user", u);
        	String state = u.getState();
        	List<Event> eventsIn = eventService.eventsInState(state);
        	model.addAttribute("eventsIn", eventsIn);
        	List<Event> eventsOut = eventService.eventsOutOfState(state);
        	model.addAttribute("eventsOut", eventsOut);
        	model.addAttribute("states", states); 
    		return "events.jsp";
    	}else {
    		eventService.createEvent(event);
    		return "redirect:/"+event.getId();
    	}
    }
}
