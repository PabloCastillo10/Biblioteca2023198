package com.pablocastillo.webapp.biblioteca.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pablocastillo.webapp.biblioteca.model.Empleado;
import com.pablocastillo.webapp.biblioteca.service.EmpleadoService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;






@Controller
@RestController
@RequestMapping("")
public class EmpleadoController {


    @Autowired
    EmpleadoService empleadoService;

    @GetMapping("/empleados")
    public List<Empleado> listarEmpleados() {
        return empleadoService.listarEmpleados();
    }

    @GetMapping("/empleado")
    public ResponseEntity<Empleado> buscarEmpleadoPorId(@RequestParam Long id) {
        try {
            Empleado empleado = empleadoService.buscarEmpleadoPorId(id);
            return ResponseEntity.ok(empleado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

     @PostMapping("/empleado")
    public ResponseEntity<Map<String, String>> agregarEmpleado(@RequestBody Empleado empleado){
        Map<String, String> response = new HashMap<>();
         try { //BIEN
            empleadoService.guardarEmpleado(empleado);
            response.put("message", "Empleado agregado con éxito");
            return ResponseEntity.ok(response);
         } catch (Exception e) { //MAL
            response.put("message", "Error");
            response.put("err", "Hubo un error al agregar el empleado");
            return ResponseEntity.badRequest().body(response);
         }

    }

    @PutMapping("/empleado")
    public ResponseEntity<Map<String, String>> editarEmpleado(@RequestParam Long id, @RequestBody Empleado empleadoNuevo) {
        Map<String, String> response = new HashMap<>();
        try {
            Empleado empleadoViejo = empleadoService.buscarEmpleadoPorId(id);
            empleadoViejo.setApellido(empleadoNuevo.getApellido());
            empleadoViejo.setDireccion(empleadoNuevo.getDireccion());
            empleadoViejo.setDpi(empleadoNuevo.getDpi());
            empleadoViejo.setNombre(empleadoNuevo.getNombre());
            empleadoViejo.setTelefono(empleadoNuevo.getTelefono());
            empleadoService.guardarEmpleado(empleadoViejo);
            response.put("message", "El empleado fue editado con éxito");
            return ResponseEntity.ok(response);
        } catch (Exception  e) {
            response.put("message" , "Error");
            response.put("err", "Hubo un error al editar el Empleado");
            return ResponseEntity.badRequest().body(response);
        }
    }
    

     @DeleteMapping("/empleado")
    public ResponseEntity<Map<String, String>> eliminarEmpleado(@RequestParam Long id){
        Map<String, String> response = new HashMap<>();
        try {
            Empleado empleado = empleadoService.buscarEmpleadoPorId(id);
            empleadoService.eliminarEmpleado(empleado);
            response.put("message", "El empleado fue eliminado con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al eliminar el empleado");
            return ResponseEntity.badRequest().body(response);
        }

    }
    

    
    
    
}
