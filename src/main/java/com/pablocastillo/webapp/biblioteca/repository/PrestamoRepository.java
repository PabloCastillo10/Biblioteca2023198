package com.pablocastillo.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pablocastillo.webapp.biblioteca.model.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

}
