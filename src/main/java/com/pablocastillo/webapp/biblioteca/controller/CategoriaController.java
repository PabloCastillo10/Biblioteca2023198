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

import com.pablocastillo.webapp.biblioteca.model.Categoria;
import com.pablocastillo.webapp.biblioteca.service.CategoriaService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;








@Controller
@RestController
@RequestMapping("")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/categorias")
    public List<Categoria> listaCategorias() {
        return categoriaService.listaCategorias();
    }

    @GetMapping("/categoria")
    public ResponseEntity<Categoria> buscarCategoriaPorId(@RequestParam Long id) {
        try {
            Categoria categoria = categoriaService.busCategoriaPorId(id);
            return ResponseEntity.ok(categoria);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
        
    }
    

    @PostMapping("/categoria")
    public ResponseEntity<Map<String, String>> agregarCategoria(@RequestBody Categoria categoria){
        Map<String, String> response = new HashMap<>();
         try { 
            if(!categoriaService.verificarCategoriaDuplicada(categoria)) {
                categoriaService.guardarCategoria(categoria);
                response.put("message", "Categoria agregada con exito");
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "error");
                response.put("err", "Se esta duplicando la categoria");
                return ResponseEntity.badRequest().body(response);
            }
         } catch (Exception e) { 
            response.put("message", "Error");
            response.put("err", "Hubo un error al crear la categoria");
            return ResponseEntity.badRequest().body(response);
         }

    }

    @PutMapping("/categoria")
    public ResponseEntity<Map<String, String>> editarCategoria(@RequestParam Long id, @RequestBody Categoria categoriaNueva){
        Map<String, String> response = new HashMap<>();
        try {
            Categoria categoriaVieja =  categoriaService.busCategoriaPorId(id);
            categoriaVieja.setNombreCategoria(categoriaNueva.getNombreCategoria());

            if(!categoriaService.verificarCategoriaDuplicada(categoriaVieja)) {
                categoriaService.guardarCategoria(categoriaVieja);
                response.put("message", "La categoria se ha modificado con exito");
                return ResponseEntity.ok(response);
            }else{
                response.put("message", "error");
                response.put("err", "Se esta duplicando la categoria");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al editar la categoria");
            return ResponseEntity.badRequest().body(response);

        }

    }
    
    @DeleteMapping("/categoria")
    public ResponseEntity<Map<String, String>> eliminarCategoria(@RequestParam Long id){
        Map<String, String> response = new HashMap<>();
        try {
            Categoria categoria = categoriaService.busCategoriaPorId(id);
            categoriaService.eliminarCategoria(categoria);
            response.put("message", "La categoria fue eliminada con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al eliminar la categoria");
            return ResponseEntity.badRequest().body(response);
        }

    }
}
