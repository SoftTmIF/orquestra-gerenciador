package br.edu.iftm.orquestra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeControlador {

	@RequestMapping("/home")
	public String home() {
		return "home";
	}

	@RequestMapping("/")
	public String begin() {
		return "redirect:/home";
	}

}
