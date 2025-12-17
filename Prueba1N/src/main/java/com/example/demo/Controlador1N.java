package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import com.example.entities.Email;
import com.example.entities.Profesor;
import com.example.repositories.RepositorioEmail;
import com.example.repositories.RepositorioProfesor;


@Controller
public class Controlador1N {
	
	RepositorioProfesor repositorioProfesor;
	RepositorioEmail repositorioEmail;

	
	
	public Controlador1N(RepositorioProfesor repositorioProfesor, RepositorioEmail repositorioEmail) {
		this.repositorioProfesor = repositorioProfesor;
		this.repositorioEmail = repositorioEmail;
	}



	@GetMapping("/crear")
	public String crearObjetos(Model modelo) {
		Profesor p1=new Profesor("Javier Puche");
		Profesor p2=new Profesor("Javier Puche");
		
		Email e1=new Email("javier@gmail.com");
		Email e2=new Email("jandres@gmail.com");
		
		p1.addEmail(e1);
		p1.addEmail(e2);
		
		//repositorioEmail.save(e1);
		//repositorioEmail.save(e2);
		repositorioProfesor.save(p1);
		repositorioProfesor.save(p2);

		modelo.addAttribute(null, e2);
		return "plantilla";
	}
	
}
