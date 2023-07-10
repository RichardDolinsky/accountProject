package sk.example.accountant.core;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.servlet.http.HttpServletRequest;

@RestController
	public class UiController {

//	    @GetMapping("/")
//	    public ModelAndView redirectToPage(HttpServletRequest request) {
//	        ModelAndView modelAndView = new ModelAndView("redirect:/page.html");
//	        return modelAndView;
//	    }
	    
    @GetMapping("/")
    public RedirectView redirectToPage(HttpServletRequest request) {
        String redirectUrl = "https://" + request.getServerName() + "/page.html";
        return new RedirectView(redirectUrl);
    }
	}

