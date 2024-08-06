package com.pablocastillo.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pablocastillo.webapp.biblioteca.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
