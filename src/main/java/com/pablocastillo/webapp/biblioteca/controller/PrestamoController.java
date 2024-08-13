package com.pablocastillo.webapp.biblioteca.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pablocastillo.webapp.biblioteca.model.Prestamo;
import com.pablocastillo.webapp.biblioteca.service.PrestamoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RestController
@RequestMapping("")
public class PrestamoController {

    @Autowired
    PrestamoService prestamoService;

    @GetMapping("/prestamos")
    public ResponseEntity<?> listarPrestamos() {
        Map<String,String> response = new HashMap<>();
    try {
            return ResponseEntity.ok(prestamoService.listarPrestamos());
        } catch (Exception e ) {
            response.put("message", "Error");
            response.put("err", "No se encontro una lista Prestamos");
            return ResponseEntity.badRequest().body(response);
        }
    }

        @GetMapping("/prestamo")
    public ResponseEntity<Prestamo> buscarPrestamoPorId(@RequestParam Long id){
        try {
            Prestamo prestamo = prestamoService.buscarPrestamoPorId(id);
            return ResponseEntity.ok(prestamo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/prestamo")
    public ResponseEntity<Map<String,String>> agregarPrestamo(@RequestBody Prestamo prestamo) {
    Map<String, String> response = new HashMap<>();
    try {
        prestamoService.guardarPrestamo(prestamo);
        response.put("message", "Prestamo agregado con éxito");
        return ResponseEntity.ok(response);
    } catch (Exception e) {
        response.put("message", "Error");
        response.put("err", "Hubo un error al agregar el prestamo");
        return ResponseEntity.badRequest().body(response);
    }
    }

    @PutMapping("/prestamo")
    public ResponseEntity<Map<String, String>> editarPrestamo(@RequestParam Long id, @RequestBody Prestamo prestamoNuevo){
        Map<String, String> response = new HashMap<>();
        try {
            Prestamo prestamo = prestamoService.buscarPrestamoPorId(id);
            prestamo.setFechaDePrestamo(prestamoNuevo.getFechaDePrestamo());
            prestamo.setFechaDeDevolucion(prestamoNuevo.getFechaDeDevolucion());
            prestamo.setVigencia(prestamoNuevo.getVigencia());
            prestamo.setEmpleado(prestamoNuevo.getEmpleado());
            prestamo.setCliente(prestamoNuevo.getCliente());
            prestamoService.guardarPrestamo(prestamo);
            response.put("message", "!Prestamo modificado con éxito¡");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al modificar el prestamo");
            return ResponseEntity.badRequest().body(response);
        }
    }

        @DeleteMapping("/prestamo")
    public ResponseEntity<Map<String, String>> eliminarPrestamo(@RequestParam Long id){
        Map<String, String> response = new HashMap<>();
        try {
            Prestamo prestamo = prestamoService.buscarPrestamoPorId(id);
            prestamoService.eliminarPrestamo(prestamo);
            response.put("message", "!Prestamo eliminado con éxito¡");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al eliminar el prestamo");
            return ResponseEntity.badRequest().body(response);
        }
    }
    


    
}
