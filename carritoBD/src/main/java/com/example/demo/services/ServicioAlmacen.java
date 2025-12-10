package com.example.demo.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.entities.ProductoAlmacen;
import com.example.demo.repositories.RepositorioProductoAlmacen;

@Service
public class ServicioAlmacen {

	private RepositorioProductoAlmacen repositorioProductoAlmacen;
	
	public ServicioAlmacen(RepositorioProductoAlmacen repositorioProductoAlmacen) {
		
		this.repositorioProductoAlmacen=repositorioProductoAlmacen;
		if(repositorioProductoAlmacen.count()==0) {
			repositorioProductoAlmacen.save(new ProductoAlmacen("Platanos",3.5,100));
			repositorioProductoAlmacen.save(new ProductoAlmacen("Peras",2.5,100));
			repositorioProductoAlmacen.save(new ProductoAlmacen("Brocoli",1.5,100));
			repositorioProductoAlmacen.save(new ProductoAlmacen("Batata",4.5,100));

		}
	}
	
	public List<ProductoAlmacen> getAll(){
		return (List<ProductoAlmacen>) repositorioProductoAlmacen.findAll();
	} 
	
	public void restaStock(Map<String,String>productos) {
		for(String porducto:productos.keySet()) {
			ProductoAlmacen productoAlmacen=repositorioProductoAlmacen.findById(porducto).orElse(null);
			if(productoAlmacen!=null) {
				productoAlmacen.setStock(productoAlmacen.getStock()-Integer.parseInt(productos.get(porducto)) );
			repositorioProductoAlmacen.save(productoAlmacen);
			}
			else {
				 System.err.println("Error producto no encontrado en BBDD");
			}
			
		}
	}
}
