package com.pablocastillo.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pablocastillo.webapp.biblioteca.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {

}
