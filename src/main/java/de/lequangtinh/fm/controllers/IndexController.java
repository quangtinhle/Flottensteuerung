package de.lequangtinh.fm.controllers;

import de.lequangtinh.fm.repositories.BenutzerRepository;
import de.lequangtinh.fm.repositories.FahrzeugRepository;
import de.lequangtinh.fm.repositories.HerstellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	@Autowired
	private BenutzerRepository benutzerRepository;
	
	@Autowired
	private HerstellerRepository herstellerRepository;
	
	@Autowired
	private FahrzeugRepository fahrzeugRepository;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/viewBenutzer") 
	public String viewBenutzer() {
		return "viewBenutzer";
	}


	@GetMapping("/viewAntrag") 
	public ModelAndView viewAntrag() {
		ModelAndView mav = new ModelAndView("viewAntrag");
		mav.addObject("alleFahrzeuge", fahrzeugRepository.findAll());
		mav.addObject("alleBenutzer", benutzerRepository.findAll());		
		return mav;
	}
	
	@GetMapping("/viewHersteller")
	public String viewHersteller() {
		return "viewHersteller";
	}

	@GetMapping("/viewFahrzeug")
	public ModelAndView viewFahrzeug() {
		ModelAndView mav = new ModelAndView("viewFahrzeug");
		mav.addObject("alleHersteller", herstellerRepository.findAll());
		mav.addObject("alleBenutzer", benutzerRepository.findAll());		
		return mav;
	}
}
