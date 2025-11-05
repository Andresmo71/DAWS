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
import jakarta.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;




@Controller
public class WebController {
	
	
	@GetMapping("/")
	public String showForm(Model modelo) {
		
		return "form";
	}
	
	@PostMapping("/form")
	
	public String formulario(@RequestParam(name="nombre") String nombre,
			HttpServletResponse response, Model modelo) {
		if (!compNom(nombre)) {
			modelo.addAttribute("error", "Error en la sintaxis de datos Nombre: Len entre 2 y 30, no vacio ");
			return "form";
		} 
		Cookie cookieNombre=new Cookie("nombre", nombre);
		cookieNombre.setMaxAge(3600);
		response.addCookie(cookieNombre);
		modelo.addAttribute("nombre", nombre);
		return "form2";
	}
	
	@PostMapping("/form2")
    public String mostrarFormulario2(@RequestParam(name="genero", required=false) String genero,
    		HttpServletResponse response, Model modelo) {
		if (genero == null || genero.trim().isEmpty()) {
			modelo.addAttribute("error", "Error, elige una opcion");
			return "form2";
		}
		
		Cookie cookieGenero=new Cookie("genero", genero);
		cookieGenero.setMaxAge(3600);
		response.addCookie(cookieGenero);
		modelo.addAttribute("genero", genero);
		
			return "form3";
		
    }

	@PostMapping("/form3")
	public String formulario2(@RequestParam(name="habitos", required=false) List<String> habitos,
			HttpServletResponse response, HttpServletRequest request, Model modelo) {

	    if (habitos == null || habitos.isEmpty()) {
	        habitos = List.of("Ninguna seleccionada");
	    }

	    Cookie cookieHabitos=new Cookie("habitos", String.join("-", habitos));
	    cookieHabitos.setMaxAge(3600);
	    response.addCookie(cookieHabitos);

        modelo.addAttribute("nombre", getCookieValue(request, "nombre"));
        modelo.addAttribute("genero", getCookieValue(request, "genero"));
        modelo.addAttribute("habitos", String.join(", ", habitos));
	    
	    return "results";
	}

    
    private String getCookieValue( HttpServletRequest request, String name) {
        if (request.getCookies() != null) {
            for (Cookie c : request.getCookies()) {
                if (c.getName().equals(name)) {
                    return c.getValue();
                }
            }
        }
        return "";
    }
	
	
	private boolean compNom(String nom) {
		String numeros="0123456789";
		for (int i=0;i<numeros.length();i++) {
			if(nom.isEmpty()||nom.length()>30||nom.length()<2) {
				return false;
			}
		}
		return true;
	}
	
	
}
