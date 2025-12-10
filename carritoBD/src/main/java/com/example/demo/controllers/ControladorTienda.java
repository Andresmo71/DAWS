package com.example.demo.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.services.ServicioAlmacen;

@Controller
public class ControladorTienda {
	ServicioAlmacen servicioAlmacen;
	public ControladorTienda(ServicioAlmacen servicioAlmacen){
		this.servicioAlmacen = servicioAlmacen;
		
	}
	@GetMapping("/")
	public String muestraFormularioTienda(Model modelo) {
		modelo.addAttribute("finalCompra", false);
		modelo.addAttribute("almacen", servicioAlmacen.getAll());
		
		return "carrito";
	}
	
	@PostMapping("/")
	public String procesaFormularioTienda(Model modelo,@RequestParam Map<String,String>camposForm
			,@RequestParam(name="comprar") String btnComprar) {
		//camposForm.remove("comprar");
		servicioAlmacen.restaStock(camposForm);
		return "carrito";
	}
}
