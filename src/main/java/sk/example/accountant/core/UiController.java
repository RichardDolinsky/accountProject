package sk.example.accountant.core;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
	public class UiController {

	    @GetMapping("/")
	    public ModelAndView redirectToPage() {
	        ModelAndView modelAndView = new ModelAndView("redirect:/page.html");
	        return modelAndView;
	    }
	    
	}

