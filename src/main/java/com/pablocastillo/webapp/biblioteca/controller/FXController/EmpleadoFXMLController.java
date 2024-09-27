package com.pablocastillo.webapp.biblioteca.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pablocastillo.webapp.biblioteca.model.Empleado;
import com.pablocastillo.webapp.biblioteca.service.EmpleadoService;
import com.pablocastillo.webapp.biblioteca.system.Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;

@Component
public class EmpleadoFXMLController implements Initializable {
    @Setter
    private Main stage;

    @FXML
    TextField tfId, tfNombre, tfApellido, tfTelefono, tfDireccion, tfDpi, tfEmpleado;
    @FXML
    TableView tblEmpleados;
    @FXML
    TableColumn colId, colNombre, colApellido, colTelefono, colDireccion, colDpi;
    @FXML
    Button btnGuardar, btnEliminar, btnLimpiar, btnBuscar, btnRegresar;

    @Autowired
    EmpleadoService empleadoService;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       cargarEmpleados();
    }

    public void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btnGuardar) {
            if(tfId.getText().isBlank()){ 
                agregarEmpleado(); 
            }else{
                editarEmpleado();
            }
        }else if(event.getSource() == btnEliminar) {
           eliminarEmpleado();
        }else if(event.getSource() == btnBuscar) {
                buscarEmpleado();
        }else if(event.getSource() == btnLimpiar) {
            limpiarTextField();
        }else if(event.getSource() == btnRegresar){
            stage.inicioView();
        }
    }


    public void cargarEmpleados(){
        tblEmpleados.getItems().clear();
        tblEmpleados.setItems(listarEmpleados());
        colId.setCellValueFactory(new PropertyValueFactory<Empleado, Long>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Empleado, String>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<Empleado, String>("apellido"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<Empleado, String>("direccion"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<Empleado, String>("telefono"));
        colDpi.setCellValueFactory(new PropertyValueFactory<Empleado, String>("dpi"));
    }

    public void cargarTextField(){
        Empleado empleado = (Empleado) tblEmpleados.getSelectionModel().getSelectedItem();
        if (empleado != null) {
            tfId.setText(Long.toString(empleado.getId()));
            tfNombre.setText(empleado.getNombre());
            tfApellido.setText(empleado.getApellido());
            tfDireccion.setText(empleado.getDireccion());
            tfTelefono.setText(empleado.getTelefono());
            tfDpi.setText(empleado.getDpi());
        }
    }

    public ObservableList<Empleado> listarEmpleados(){
        return FXCollections.observableArrayList(empleadoService.listarEmpleados());
    }

    public void limpiarTextField(){
        tfId.clear();
        tfNombre.clear();
        tfApellido.clear();
        tfDireccion.clear();
        tfTelefono.clear();
        tfDpi.clear();
    }

    public void agregarEmpleado(){
        Empleado empleado = new Empleado();
        empleado.setNombre(tfNombre.getText());
        empleado.setApellido(tfApellido.getText());
        empleado.setDireccion(tfDireccion.getText());
        empleado.setTelefono(tfTelefono.getText());
        empleado.setDpi(tfDpi.getText());
        empleadoService.guardarEmpleado(empleado);
        cargarEmpleados();
        limpiarTextField();
    }

    public void editarEmpleado() {
        Empleado empleado = empleadoService.buscarEmpleadoPorId(Long.parseLong(tfId.getText()));
        empleado.setNombre(tfNombre.getText());
        empleado.setApellido(tfApellido.getText());
        empleado.setDireccion(tfDireccion.getText());
        empleado.setTelefono(tfTelefono.getText());
        empleado.setDpi(tfDpi.getText());
        empleadoService.guardarEmpleado(empleado);
        cargarEmpleados();
        limpiarTextField();
        
       
    }

    public void eliminarEmpleado() {
        Empleado empleado = empleadoService.buscarEmpleadoPorId(Long.parseLong(tfId.getText()));
        empleadoService.eliminarEmpleado(empleado);
        cargarEmpleados();
        limpiarTextField();
    }


    public void buscarEmpleado() {
        tblEmpleados.getItems().clear();
        if (tfEmpleado.getText().isBlank()) {
            cargarEmpleados();
        } else {
            Empleado empleado = empleadoService.buscarEmpleadoPorId(Long.parseLong(tfEmpleado.getText()));
            if (empleado != null) {
                ObservableList<Empleado> empleadoBuscado = FXCollections.observableArrayList(empleado);  
                tblEmpleados.setItems(empleadoBuscado);
            } else {
                tblEmpleados.getItems().clear();
            }
        }
    }

    

}
