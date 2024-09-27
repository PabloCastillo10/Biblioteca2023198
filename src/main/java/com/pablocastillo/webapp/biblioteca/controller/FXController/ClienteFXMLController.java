package com.pablocastillo.webapp.biblioteca.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pablocastillo.webapp.biblioteca.model.Cliente;
import com.pablocastillo.webapp.biblioteca.service.ClienteService;
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
public class ClienteFXMLController implements Initializable {
    @Setter
    private Main stage;

    @FXML
    TextField tfDpi, tfNombre, tfApellido, tfTelefono, tfCliente;

    @FXML
    TableView tblClientes;

    @FXML
    TableColumn colDpi, colNombre, colApellido, colTelefono;
    
    @FXML
    Button btnGuardar, btnEliminar, btnLimpiar, btnBuscar, btnRegresar;

    @Autowired
    ClienteService clienteService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarClientes();
    }

    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnGuardar) {
            if (tfDpi.getText().isBlank()) { 
                System.out.println("DPI no puede estar vacío.");
            } else {
                Cliente clienteExistente = clienteService.buscarClientePorId(Long.parseLong(tfDpi.getText()));
                if (clienteExistente == null) {
                    agregarCliente();
                } else {
                    editarCliente();
                }
            }
        } else if (event.getSource() == btnEliminar) {
            eliminarCliente();
        } else if (event.getSource() == btnBuscar) {
            buscarCliente();
        }else if(event.getSource() == btnRegresar){
            stage.inicioView();
        }
    }
    

    public void cargarClientes(){
        tblClientes.getItems().clear();
        tblClientes.setItems(listarClientes());
        colDpi.setCellValueFactory(new PropertyValueFactory<Cliente, Long>("dpi"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<Cliente, String>("apellido"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefono"));
    }

    public void cargarTextField(){
        Cliente cliente = (Cliente) tblClientes.getSelectionModel().getSelectedItem();
        if(cliente != null) {
            tfDpi.setText(Long.toString(cliente.getDpi()));
            tfNombre.setText(cliente.getNombre());
            tfApellido.setText(cliente.getApellido());
            tfTelefono.setText(cliente.getTelefono());
        }
    }
    public ObservableList<Cliente> listarClientes(){
        return FXCollections.observableArrayList(clienteService.listaClientes());
    }

    public void limpiarTextField(){
        tfDpi.clear();
        tfNombre.clear();
        tfApellido.clear();
        tfTelefono.clear();
    }

    public void agregarCliente(){
        Cliente cliente = new Cliente();
        cliente.setDpi(Long.parseLong(tfDpi.getText())); 
        cliente.setNombre(tfNombre.getText());
        cliente.setApellido(tfApellido.getText());
        cliente.setTelefono(tfTelefono.getText());
        clienteService.guardarCliente(cliente); 
        cargarClientes();
    }

    public void editarCliente() {
        if (!tfDpi.getText().isBlank()) {
            Cliente cliente = clienteService.buscarClientePorId(Long.parseLong(tfDpi.getText()));
            if (cliente != null) {
                cliente.setNombre(tfNombre.getText());
                cliente.setApellido(tfApellido.getText());
                cliente.setTelefono(tfTelefono.getText());

                
                clienteService.guardarCliente(cliente);
                cargarClientes();
                limpiarTextField();
            }
        }
    }

    public void eliminarCliente(){
        Cliente cliente  = clienteService.buscarClientePorId(Long.parseLong(tfDpi.getText()));
        clienteService.eliminarCliente(cliente);
        cargarClientes();
    }


    public void buscarCliente(){
        tblClientes.getItems().clear();
        if(tfCliente.getText().isBlank()) {
            cargarClientes();
        } else {
            Cliente cliente = clienteService.buscarClientePorId(Long.parseLong(tfDpi.getText()));

            if(cliente  != null) {
                ObservableList<Cliente> clienteBuscado = FXCollections.observableArrayList(cliente);
                tblClientes.setItems(clienteBuscado); 
            } else {
                tblClientes.getItems().clear();
            }
        }
    }

    
}
