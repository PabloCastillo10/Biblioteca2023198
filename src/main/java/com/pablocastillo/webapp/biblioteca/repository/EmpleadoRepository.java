package com.pablocastillo.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pablocastillo.webapp.biblioteca.model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

}
