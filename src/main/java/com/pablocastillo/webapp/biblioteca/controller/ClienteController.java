package com.pablocastillo.webapp.biblioteca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pablocastillo.webapp.biblioteca.model.Cliente;
import com.pablocastillo.webapp.biblioteca.service.ClienteService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;





@Controller
@RestController
@RequestMapping("")
@CrossOrigin(value = "http://127.0.0.1:5500")
public class ClienteController {

        @Autowired
        ClienteService clienteService;

        @GetMapping("/clientes")
        public List<Cliente> listarClientes(){
            return clienteService.listaClientes();
        }

        @GetMapping("/cliente")
        public ResponseEntity<Cliente> buscarClientePorId(@RequestParam Long dpi) {
           try {
            Cliente cliente = clienteService.buscarClientePorId(dpi);
            return ResponseEntity.ok(cliente);
           } catch (Exception e) {
                return ResponseEntity.badRequest().body(null);
           }
        }

        @PostMapping("/cliente")
    public ResponseEntity<Map<String, String>> agregarCliente(@RequestBody Cliente cliente){
        Map<String, String> response = new HashMap<>();
         try { //BIEN
            clienteService.guardarCliente(cliente);
            response.put("message", "Cliente agregado con éxito");
            return ResponseEntity.ok(response);
         } catch (Exception e) { //MAL
            response.put("message", "Error");
            response.put("err", "Hubo un error al agregar un cliente :( )");
            return ResponseEntity.badRequest().body(response);
         }
    }

    @PutMapping("/cliente")
    public ResponseEntity<Map<String, String>> editarCliente(@RequestParam Long dpi, @RequestBody Cliente clienteNuevo) {
      Map<String, String> response = new HashMap<>();
        try {
            Cliente clienteViejo = clienteService.buscarClientePorId(dpi);
            clienteViejo.setNombre(clienteNuevo.getNombre());
            clienteViejo.setApellido(clienteNuevo.getApellido());
            clienteViejo.setTelefono(clienteNuevo.getTelefono());
            clienteService.guardarCliente(clienteViejo);
            response.put("message", "El cliente fue editado con exito :) ");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al editar el cliente");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/cliente")
    public ResponseEntity<Map<String, String>> eliminarCliente(@RequestParam Long dpi) {
      Map<String, String> response = new HashMap<>();
      try{
         Cliente cliente = clienteService.buscarClientePorId(dpi);
         clienteService.eliminarCliente(cliente);
         response.put("message", "El cliente fue eliminado con exito");
         return ResponseEntity.ok(response);
      } catch (Exception e) {
         response.put("message", "Error");
         response.put("err", "Hubo un error al eliminar el cliente");
         return ResponseEntity.badRequest().body(response);
      }
    }
        
        
}
