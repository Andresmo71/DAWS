package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class ControladorCarrito {
	private static RepositorioStockEnTxt repositorio = new RepositorioStockEnTxt();
	static final String[] PRODUCTOS = { "Plátano", "Uvas", "Peras", "Manzanas", "Brocoli" };
	static final double[] PRECIOS  = {4.3, 5.3, 3.25, 2.1, 1.5 };
   
	
	/*
	 ¿Nombres de inputs a array? -> Params en mapa
	 ¿ Mapa productos y precios ?
	 
	 productosYprecios
	total
	productos 
	 
	 */
	
	@GetMapping("/")
	public String muestraCarrito(Model modelo, HttpSession sesión) {
		modelo.addAttribute("productos",PRODUCTOS);
		modelo.addAttribute("precios",PRECIOS);
		double total = 0;
		// Pasa a la plantilla el carrito que haya en la sesión:
		int[] cantidadesSesión = (int[]) sesión.getAttribute("cantidades");
		if (cantidadesSesión!=null) {
			// Genera un array de String cada uno con producto : cantidad
			// para pasarlo a la plantilla para mostrar el acumulado:
			String[] productosYcantidades = new String[PRODUCTOS.length];
			total = 0;
			for (int i=0; i<PRODUCTOS.length; i++) {
				productosYcantidades[i] = PRODUCTOS[i] + " : " + cantidadesSesión[i];
				total +=  cantidadesSesión[i]*PRECIOS[i];
			}
			modelo.addAttribute("productosYcantidades",productosYcantidades);
		}
		modelo.addAttribute("total",total);
		modelo.addAttribute("productosYstock",repositorio.getAll());
		return "carrito";
	}
	
	@PostMapping("/")
	public String procesaCarrito(Model modelo, @RequestParam(name="cantidades") int[] cantidades,
			@RequestParam(name="comprar",required=false) String comprar,
			HttpSession sesión) {
		modelo.addAttribute("productos",PRODUCTOS);
		modelo.addAttribute("precios",PRECIOS);
		modelo.addAttribute("productosYstock",repositorio.getAll());
		double total = 0;
		
		int[] cantidadesSesión = (int[]) sesión.getAttribute("cantidades");
		// Consolido lo que hay en la sesión (carrito en variable cantidadesSesión)
		// con lo que llega del post del formulario previo:
		if (cantidadesSesión != null) {
			for (int i=0;i<PRODUCTOS.length;i++) {
				if(cantidades[i]<0) {
					modelo.addAttribute("errores", "No puedes enviar cantidades negativas bobo");
				}else {
					Integer stockEnDisco = repositorio.getOne(PRODUCTOS[i]);
				cantidadesSesión[i] += cantidades[i];
				if (stockEnDisco==null) stockEnDisco = 0;
				if (cantidadesSesión[i]>stockEnDisco) {
					modelo.addAttribute("errores", "No puedes comprar tanto porque no hay stock , lo sentimos te jodes");
					cantidadesSesión[i] -= cantidades[i];
				}
				}
				
				//System.out.println("Sumo " + cantidadesSesión[i] + "+" + cantidades[i] + " " + PRODUCTOS[i]);
			}
		}
		else {
			
			cantidadesSesión = cantidades;
		}
		sesión.setAttribute("cantidades", cantidadesSesión);
		
		// Genera un array de String cada uno con producto : cantidad
		// para pasarlo a la plantilla para mostrar el acumulado:
		String[] productosYcantidades = new String[PRODUCTOS.length];
		for (int i=0; i<PRODUCTOS.length; i++) {
			//productosYcantidades[i] = PRODUCTOS[i] + " : " + cantidadesSesión[i];
			productosYcantidades[i] = String.format("Del producto %s llevas %d unidades que multiplicadas por el precio %.2f dan %.2f", PRODUCTOS[i],
					cantidadesSesión[i],PRECIOS[i],cantidadesSesión[i]*PRECIOS[i] );
			
			total +=  cantidadesSesión[i]*PRECIOS[i];
		}
		modelo.addAttribute("productosYcantidades",productosYcantidades);
		
		if (comprar != null ) {
			for (int i=0; i<cantidadesSesión.length;i++) {
				Integer stockEnDisco = repositorio.getOne(PRODUCTOS[i]);
				if (stockEnDisco==null) stockEnDisco = 0;
				repositorio.modify(PRODUCTOS[i], stockEnDisco - cantidadesSesión[i] );
				cantidadesSesión[i] = 0;
			}
			sesión.setAttribute("cantidades", cantidadesSesión);
			modelo.addAttribute("finalCompra",true);
			sesión.removeAttribute("cantidades");
		}
		
		//modelo.addAttribute("total",String.format("%.2f",total));
		
		total = Math.round(total*100)/100.0;
		
		modelo.addAttribute("total",total);
		return "carrito";
	}
	
	@GetMapping("/administrador")
	public String administracion(Model modelo) {
		modelo.addAttribute("productos",PRODUCTOS);
		modelo.addAttribute("precios",PRECIOS);
		modelo.addAttribute("productosYstock",repositorio.getAll());
		return "administrador";
	}

	
	@PostMapping("/administrador")
	public String servirAdministracion(Model modelo,@RequestParam(name="cantidades")int[]cantidades) {
		modelo.addAttribute("productos",PRODUCTOS);
		modelo.addAttribute("precios",PRECIOS);
		modelo.addAttribute("productosYstock",repositorio.getAll());
		for(int i=0;i<PRODUCTOS.length;i++) {
			Integer stockActual=repositorio.getOne(PRODUCTOS[i]);
			
			if((stockActual+cantidades[i])<0) {
				modelo.addAttribute("errores", "No puedes poner el stock por debajo de 0 espabilao");
			}else {
				repositorio.modify(PRODUCTOS[i] , stockActual + cantidades[i]);
			}
				
		}
		return "administrador";
	}
}
