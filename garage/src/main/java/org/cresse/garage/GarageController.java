package org.cresse.garage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class GarageController {
	
	private static final Log log = LogFactory.getLog(GarageController.class);
	
	@Autowired
	private GarageService garageService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {		
		return "home";
	}
	
	@RequestMapping(value = "/status", method = RequestMethod.GET)
	@ResponseBody
	public Garage status() {		
		return garageService.getGarage();
	}
	
	@RequestMapping(value = "/toggle", method = RequestMethod.GET)
	public String toggleGet() {		
		return "toggle";
	}
	
	@RequestMapping(value = "/toggle", method = RequestMethod.POST)
	public void togglePost() {
		log.info("Toggling door.");
		garageService.toggleGarageDoor();
	}
	
}
