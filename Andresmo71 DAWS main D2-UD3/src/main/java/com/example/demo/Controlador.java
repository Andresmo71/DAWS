package com.example.demo;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
public class Controlador {
	private  final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	private final Map<String, String> usuarios = new HashMap<>();
	String aux="";
	public Controlador(){
		usuarios.put("Andres", passwordEncoder.encode("contraseña"));
	    usuarios.put("Maria", passwordEncoder.encode("12345"));
	    usuarios.put("Carlos", passwordEncoder.encode("54321"));
	}

	
	
	@GetMapping("/")
	public String showForm(Model modelo,HttpSession sesion,HttpServletRequest request) {
		if(sesion.getAttribute("usuario")==null) {
			return "login";
		}else {
			
			return "form";
		}
		
	}
	
	@GetMapping("/login")
	public String showFormLogIn(Model modelo,HttpSession sesion,HttpServletRequest request) {
		if(sesion.getAttribute("usuario")==null) {
			return "login";
		}else {
			modelo.addAttribute("usuario", aux);
			return "form";
		}
		
	}
	
	@GetMapping("/form")
	public String showForm1(Model modelo,HttpSession sesion,HttpServletRequest request) {
		if(sesion.getAttribute("usuario")==null) {
			return "login";
		}else {
			return "form";
		}
		
	}
	
	@GetMapping("/form2")
	public String showForm2(Model modelo,HttpSession sesion,HttpServletRequest request) {
		if(sesion.getAttribute("usuario")==null) {
			return "login";
		}else {
			return "form2";
		}
		
	}
	@GetMapping("/form3")
	public String showForm3(Model modelo,HttpSession sesion,HttpServletRequest request) {
		if(sesion.getAttribute("usuario")==null) {
			return "login";
		}else {
			return "form3";
		}
		
	}
	
	@GetMapping("/results")
	public String showFormResults(Model modelo,HttpSession sesion,HttpServletRequest request) {
		if(!request.isRequestedSessionIdFromCookie()) {
			return "login";
		}else {
			return "results";
		}
		
	}
	
	@PostMapping("/login")
	public String logIn(@RequestParam(name="usuario")String usuario,@RequestParam(name="contraseña")String contraseña,
			HttpSession sesion,Model modelo) {
		
		String usuarioGuardado=usuarios.get(usuario);
		if(usuarioGuardado==null || !passwordEncoder.matches(contraseña, usuarioGuardado)) {
			
			modelo.addAttribute("error", "Error , usuario o contraseña incorrectos");
			return "login";
		}
		aux=usuario;
		sesion.setAttribute("usuario", usuario);
		sesion.setAttribute("contraseña", contraseña);
		modelo.addAttribute("contraseña", contraseña);
		modelo.addAttribute("usuario", usuario);
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
