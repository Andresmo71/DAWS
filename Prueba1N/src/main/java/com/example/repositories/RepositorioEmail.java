package com.example.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.entities.Email;

public interface RepositorioEmail extends CrudRepository<Email, Long> {

}
