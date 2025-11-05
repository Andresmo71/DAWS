package com.example.demo;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;




@Controller
public class WebController2 {
	
	@GetMapping("/")
	public String showForm(Model modelo) {
		
		return "form";
	}
	
	@PostMapping("/form")
	
	public String formulario(@RequestParam(name="nombre") String nombre,
			HttpSession sesion, Model modelo) {
		if (!compNom(nombre)) {
			modelo.addAttribute("error", "Error en la sintaxis de datos Nombre: Len entre 2 y 30, no vacio ");
			return "form";
		} 
		sesion.setAttribute("nombre", nombre);
		modelo.addAttribute("nombre", nombre);
		return "form2";
	}
	
	@PostMapping("/form2")
    public String mostrarFormulario2(@RequestParam(name="genero", required=false) String genero,
    		HttpSession sesion, Model modelo) {
		if (genero == null || genero.trim().isEmpty()) {
			modelo.addAttribute("error", "Error, elige una opcion");
			return "form2";
		}
		
		sesion.setAttribute("genero", genero);
		modelo.addAttribute("genero", genero);
		
			return "form3";
		
    }

	@PostMapping("/form3")
	public String formulario2(@RequestParam(name="habitos", required=false) List<String> habitos,
			HttpSession sesion, Model modelo) {

	    if (habitos == null || habitos.isEmpty()) {
	        habitos = List.of("Ninguna seleccionada");
	    }

	    sesion.setAttribute("habitos", String.join(", ", habitos));
	    

        modelo.addAttribute("nombre", sesion.getAttribute("nombre"));
        modelo.addAttribute("genero", sesion.getAttribute("genero"));
        modelo.addAttribute("habitos", sesion.getAttribute("habitos"));
	    
	    return "results";
	}

    

	
	
	private boolean compNom(String nom) {
	    if (nom == null || nom.isEmpty() || nom.length() < 2 || nom.length() > 30) {
	        return false;
	    }
	    return true;
	}


	
	
}
