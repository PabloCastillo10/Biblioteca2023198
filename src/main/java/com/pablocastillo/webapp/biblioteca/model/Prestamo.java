package com.pablocastillo.webapp.biblioteca.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Entity
@Data
@Table(name = "Prestamos")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date fechaDePrestamo;
    private Date fechaDeDevolucion;
    private Boolean vigencia;

    @ManyToOne
    private Empleado empleado;

    @ManyToOne
    private Cliente cliente;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "prestamos_libros",
            joinColumns = @JoinColumn(name = "prestamo_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "libros_id", referencedColumnName = "id"))
            private List<Libro> libros;

   
}