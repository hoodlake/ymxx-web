package com.ymxx.jweb.action.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/component")
public class ComponentShow { 

	@RequestMapping(method=RequestMethod.GET,value="cal")
	public String toCalendarPage(){
		
		return "component/calendar"; 
	}
}
