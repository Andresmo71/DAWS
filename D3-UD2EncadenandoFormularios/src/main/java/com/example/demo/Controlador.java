package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Controlador {

    @GetMapping("/paso1")
    public String paso1Form(@RequestParam(value="nombre", required=false) String nombre,
                            @RequestParam(value="datos", required=false) List<String> datos,
                            Model model) {
        if(nombre != null) model.addAttribute("nombre", nombre);
        if(datos != null) model.addAttribute("datos", datos);
        return "paso1";
    }

    @PostMapping("/paso1")
    public String paso1Submit(@RequestParam("nombre") String nombre, Model model) {
        if(nombre == null || nombre.trim().isEmpty()) {
            model.addAttribute("error", "El nombre no puede estar vacío");
            model.addAttribute("nombre", nombre);
            return "paso1";
        }
        if(nombre.length() > 30) {
            model.addAttribute("error", "El nombre no puede superar 30 caracteres");
            model.addAttribute("nombre", nombre);
            return "paso1";
        }

        List<String> datos = new ArrayList<>();
        datos.add("Nombre: " + nombre);
        model.addAttribute("datos", datos);
        model.addAttribute("nombre", nombre);
        return "paso2";
    }

    @GetMapping("/paso2")
    public String paso2Form(@RequestParam("nombre") String nombre,
                            @RequestParam(value="genero", required=false) String genero,
                            @RequestParam(value="pais", required=false) String pais,
                            @RequestParam("datos") List<String> datos,
                            Model model) {
        model.addAttribute("nombre", nombre);
        model.addAttribute("genero", genero);
        model.addAttribute("pais", pais);
        model.addAttribute("datos", datos);
        return "paso2";
    }

    @PostMapping("/paso2")
    public String paso2Submit(@RequestParam("genero") String genero,
                              @RequestParam("pais") String pais,
                              @RequestParam("datos") List<String> datos,
                              @RequestParam("nombre") String nombre,
                              Model model) {

        datos.add("Género: " + genero);
        datos.add("País favorito: " + pais);

        model.addAttribute("nombre", nombre);
        model.addAttribute("genero", genero);
        model.addAttribute("pais", pais);
        model.addAttribute("datos", datos);
        return "paso3";
    }

    @GetMapping("/paso3")
    public String paso3Form(@RequestParam("nombre") String nombre,
                            @RequestParam("genero") String genero,
                            @RequestParam("pais") String pais,
                            @RequestParam("datos") List<String> datos,
                            Model model) {
        model.addAttribute("nombre", nombre);
        model.addAttribute("genero", genero);
        model.addAttribute("pais", pais);
        model.addAttribute("datos", datos);
        return "paso3";
    }
 
    @PostMapping("/paso3")
    public String paso3Submit(@RequestParam(value="platos", required=false) String[] platos,
                              @RequestParam("datos") List<String> datos,
                              @RequestParam("nombre") String nombre,
                              @RequestParam("genero") String genero,
                              @RequestParam("pais") String pais,
                              Model model) {
        if(platos != null && platos.length > 0) {
            datos.add("Platos favoritos: " + String.join(", ", platos));
        } else {
            datos.add("Platos favoritos: Ninguno");
        }

        model.addAttribute("datos", datos);
        return "resultado";
    }
}
