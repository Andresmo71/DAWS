package com.example.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.entities.Profesor;

public interface RepositorioProfesor extends CrudRepository<Profesor, Long> {

}
