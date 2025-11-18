package com.example.demo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Persona {

	
	@NotNull
	@Size(min=2,max=30)
	private String nombre;
	
	@NotNull
	@Min(1)
	@Max(120)
	private Integer edad;
	
	@NotNull
	@Min(0)
	@Max(2)
	private Double altura;
	
	@NotNull
	@Min(1)
	@Max(250)
	private Double peso;
	
	
	@NotNull
	@Min(1)
	@Max(2)
	private Integer sexo;


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Integer getEdad() {
		return edad;
	}


	public void setEdad(Integer edad) {
		this.edad = edad;
	}


	public Double getAltura() {
		return altura;
	}


	public void setAltura(Double altura) {
		this.altura = altura;
	}


	public Double getPeso() {
		return peso;
	}


	public void setPeso(Double peso) {
		this.peso = peso;
	}


	public Integer getSexo() {
		return sexo;
	}


	public void setSexo(Integer sexo) {
		this.sexo = sexo;
	}
	
	
	public double calcularPorcentajeGrasa() {
		double imc=peso/(altura*altura);
		return 1.2*imc+0.23*edad-10.8*sexo+5.4;
	}
	
	
}
