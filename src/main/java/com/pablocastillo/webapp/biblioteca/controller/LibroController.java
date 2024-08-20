package com.pablocastillo.webapp.biblioteca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pablocastillo.webapp.biblioteca.model.Libro;
import com.pablocastillo.webapp.biblioteca.service.LibroService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@Controller
@RestController
@RequestMapping("")
@CrossOrigin(value = "http://127.0.0.1:5500")
public class LibroController {

    @Autowired
    LibroService libroService;

    @GetMapping("/libros")
    public ResponseEntity<List<Libro>> listarLibros() {
        try {
            return ResponseEntity.ok(libroService.listarLibros());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/libro")
        public ResponseEntity<Map<String,String>> agregarLibro(@RequestBody Libro libro) {
        Map<String, String> response = new HashMap<>();
        try {
            libroService.guardarLibro(libro);
            response.put("message",  "Libro agregado con éxito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response. put( "message",  "Error");
            response.put("err",  "Hubo un error al crear el libro.");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/libro")
    public ResponseEntity<Map<String, String>> editarLibro(@RequestParam Long id, @RequestBody Libro libroNuevo) {
        Map<String, String> response = new HashMap<>();
        try {
            Libro libroViejo = libroService.buscarLibroPorId(id);
            libroViejo.setAutor(libroNuevo.getAutor());
            libroViejo.setCategoria(libroNuevo.getCategoria());
            libroViejo.setCluster(libroNuevo.getCluster());
            libroViejo.setDisponibilidad(true);
            libroViejo.setEditorial(libroNuevo.getEditorial());
            libroViejo.setIsbn(libroNuevo.getIsbn());
            libroViejo.setNombre(libroNuevo.getNombre());
            libroViejo.setNumeroEstanteria(libroNuevo.getNumeroEstanteria());
            libroViejo.setSinopsis(libroNuevo.getSinopsis());
            libroService.guardarLibro(libroViejo);
            response.put("message", "Libro editado con éxito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al editar el libro");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/libro")
    public ResponseEntity<Map<String, String>> eliminarLibro(@RequestParam Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            Libro libro = libroService.buscarLibroPorId(id);
            libroService.eliminarLibro(libro);
            response.put("message", "El libro fue eliminado con éxito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al eliminar el libro ");
            return ResponseEntity.badRequest().body(response);
        }
    }
    

    


    
}
