package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class ControladorGrasa {
	
	@GetMapping("/")
	public String mostrarFormulario(Persona persona) {
		return "formulario";
		
	}
	
	@PostMapping("/")
	public String procesarFormulario(@Valid Persona persona,
			BindingResult bindingResult,Model model) {
		
		if(bindingResult.hasErrors()) {
			return "formulario";
		}
		
		double porcentajeGrasa=persona.calcularPorcentajeGrasa();
		
		model.addAttribute("persona", persona);
		model.addAttribute("porcentajeGrasa", porcentajeGrasa);
		return "resultado";
	}
	
}
