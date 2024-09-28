package com.pablocastillo.webapp.biblioteca.controller.FXController;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.text.SimpleDateFormat;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pablocastillo.webapp.biblioteca.model.Cliente;
import com.pablocastillo.webapp.biblioteca.model.Empleado;
import com.pablocastillo.webapp.biblioteca.model.Libro;
import com.pablocastillo.webapp.biblioteca.model.Prestamo;
import com.pablocastillo.webapp.biblioteca.service.ClienteService;
import com.pablocastillo.webapp.biblioteca.service.EmpleadoService;
import com.pablocastillo.webapp.biblioteca.service.LibroService;
import com.pablocastillo.webapp.biblioteca.service.PrestamoService;
import com.pablocastillo.webapp.biblioteca.system.Main;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.stream.Collectors;
import javafx.util.Callback;
import lombok.Setter;

@Component
public class PrestamoControllerFX implements Initializable {

    @FXML
    TextField tfId, tfPrestamo, tfDevolucion, tfVigencia, tfBuscar;

    @FXML
    Button btnGuardar, btnLimpiar, btnRegresar, btnEliminar, btnBuscar, btnPrestar;

    @FXML
    ComboBox   cmbEmpleado, cmbCliente;

    @FXML
    ListView<Libro> lvLibros;

    @FXML
    TableView tblPrestamos;

    @FXML
    TableColumn colId, colPrestamo, colDevolucion, colVigencia, colEmpleado, colCliente, colLibro;

    private List<Libro> librosSeleccionados = new ArrayList<>();
    private int estado = 0;

    @Setter
    private Main stage;

    @Autowired
    PrestamoService prestamoService;

    @Autowired
    ClienteService clienteService;

    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    LibroService libroService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    lvLibros.setItems(FXCollections.observableList(libroService.listarLibros()));
    lvLibros.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    cargarClientes();
    cargarEmpleados();
    
    cargarDatos();
    }

    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnGuardar) {
            if (tfId.getText().isBlank()) {
                agregarPrestamo();
            } else {
                eliminarPrestamo();
            }
        } else if (event.getSource() == btnLimpiar) {
            limpiarForm();
        } else if (event.getSource() == btnRegresar) {
            stage.inicioView();
        } else if (event.getSource() == btnEliminar) {
            eliminarPrestamo();
        } else if (event.getSource() == btnBuscar) {
            tblPrestamos.getItems().clear();
            if (tfBuscar.getText().isBlank()) {
                cargarDatos();
            } else {
                tblPrestamos.getItems().add(buscarPrestamo());
            }
        }
    }

    public void cargarDatos() {
        tblPrestamos.setItems(listarPrestamos());
        colId.setCellValueFactory(new PropertyValueFactory<Prestamo, Long>("id"));
        colPrestamo.setCellValueFactory(new PropertyValueFactory<Prestamo, Date>("fechaDePrestamo"));
        colDevolucion.setCellValueFactory(new PropertyValueFactory<Prestamo, Date>("fechaDeDevolucion"));
        colVigencia.setCellValueFactory(new PropertyValueFactory<Prestamo, Boolean>("vigencia"));
        colEmpleado.setCellValueFactory(new PropertyValueFactory<Prestamo, Empleado>("empleado"));
        colCliente.setCellValueFactory(new PropertyValueFactory<Prestamo, Cliente>("cliente"));
        colLibro.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Prestamo, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Prestamo, String> data) {
                // Convertir la lista de libros a un solo String con los nombres separados por comas
                List<Libro> libros = data.getValue().getLibros();
                String librosText = libros.stream()
                                          .map(Libro::getNombre) 
                                          .collect(Collectors.joining(", "));
                return new SimpleStringProperty(librosText);
            }
        });
    }

    public ObservableList<Prestamo> listarPrestamos() {
        return FXCollections.observableList(prestamoService.listarPrestamos());
    }

    public void cargarForm() {
        Prestamo prestamo = (Prestamo) tblPrestamos.getSelectionModel().getSelectedItem();
        if (prestamo != null) {
            tfId.setText(prestamo.getId().toString());
            tfPrestamo.setText(prestamo.getFechaDePrestamo().toString());
            tfDevolucion.setText(prestamo.getFechaDeDevolucion().toString());
            tfVigencia.setText(prestamo.getVigencia().toString());
            cmbEmpleado.getSelectionModel().select(obtenerIndexEmpleado());
            cmbCliente.getSelectionModel().select(obtenerIndexCliente());
            
            
            lvLibros.getSelectionModel().clearSelection();
            for (Libro libro : prestamo.getLibros()) {
                lvLibros.getSelectionModel().select(libro);
            }
        }
    }

    public void limpiarForm() {
        tfId.clear();
        tfPrestamo.clear();
        tfDevolucion.clear();
        tfVigencia.clear();
        cmbCliente.getSelectionModel().clearSelection();
        cmbEmpleado.getSelectionModel().clearSelection();
        
       
        lvLibros.getSelectionModel().clearSelection();
    }

    public void agregarPrestamo() {
        try {
            Prestamo prestamo = new Prestamo();

            
            librosSeleccionados = lvLibros.getSelectionModel().getSelectedItems();
            if (librosSeleccionados.size() > 3) {
                mostrarAlerta("No se pueden seleccionar más de 3 libros por préstamo.");
                return; 
            }

            
            for (Libro libro : librosSeleccionados) {
                Libro libroVigente = libroService.buscarLibroPorId(libro.getId());
                if (libroVigente == null || !libroVigente.getDisponibilidad()) {
                    mostrarAlerta("El libro con el Id " + libro.getId() + " no está disponible.");
                    return; 
                }
            }

            
            Cliente clienteSeleccionado = (Cliente) cmbCliente.getSelectionModel().getSelectedItem();
            if (prestamoService.PrestamoVigente(clienteSeleccionado.getDpi())) {
                mostrarAlerta("El cliente tiene un préstamo vigente.");
                return; 
            }

            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date fechaPrestamo = formatter.parse(tfPrestamo.getText());
            java.util.Date fechaDevolucion = formatter.parse(tfDevolucion.getText());

            prestamo.setFechaDePrestamo(new java.sql.Date(fechaPrestamo.getTime()));
            prestamo.setFechaDeDevolucion(new java.sql.Date(fechaDevolucion.getTime()));
            prestamo.setVigencia(Boolean.valueOf(tfVigencia.getText()));
            prestamo.setCliente(clienteSeleccionado);
            prestamo.setEmpleado((Empleado) cmbEmpleado.getSelectionModel().getSelectedItem());
            prestamo.setLibros(lvLibros.getSelectionModel().getSelectedItems());

            prestamoService.guardarPrestamo(prestamo);
            cargarDatos();

        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Hubo un error al agregar el préstamo.");
        }
    }

    public void editarPrestamo() {
        try {
            Prestamo prestamo = prestamoService.buscarPrestamoPorId(Long.parseLong(tfId.getText()));
            createSelectBook();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date fechaPrestamo = formatter.parse(tfPrestamo.getText());
            java.util.Date fechaDevolucion = formatter.parse(tfDevolucion.getText());
            prestamo.setFechaDePrestamo(new java.sql.Date(fechaPrestamo.getTime()));
            prestamo.setFechaDeDevolucion(new java.sql.Date(fechaDevolucion.getTime()));
            prestamo.setVigencia(Boolean.valueOf(tfVigencia.getText()));
            prestamo.setCliente((Cliente)cmbCliente.getSelectionModel().getSelectedItem());
            prestamo.setEmpleado((Empleado)cmbEmpleado.getSelectionModel().getSelectedItem());
            prestamo.setLibros(librosSeleccionados);
            prestamoService.guardarPrestamo(prestamo);
            cargarDatos();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public void eliminarPrestamo() {
        Prestamo prestamo = prestamoService.buscarPrestamoPorId(Long.parseLong(tfId.getText()));
        prestamoService.eliminarPrestamo(prestamo);
        cargarDatos();
    }

    public Prestamo buscarPrestamo() {
        return prestamoService.buscarPrestamoPorId(Long.parseLong(tfBuscar.getText()));
    }

    public void createSelectBook() {
        librosSeleccionados.clear();
        librosSeleccionados.addAll(lvLibros.getSelectionModel().getSelectedItems());
        System.out.println(librosSeleccionados);
    }

    public int obtenerIndexEmpleado() {
        int index = 0;
        for (int i = 0; i < cmbEmpleado.getItems().size(); i++) {
            String empleadoCmb = cmbEmpleado.getItems().get(i).toString();
            String empleadoTbl = ((Prestamo) tblPrestamos.getSelectionModel().getSelectedItem()).getEmpleado().toString();
            System.out.println(empleadoCmb);
            System.out.println(empleadoTbl);
            if (empleadoCmb.equals(empleadoTbl)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public int obtenerIndexCliente() {
        int index = 0;
        for (int i = 0; i < cmbCliente.getItems().size(); i++) {
            String clienteCmb = cmbCliente.getItems().get(i).toString();
            String clienteTbl = ((Prestamo) tblPrestamos.getSelectionModel().getSelectedItem()).getCliente().toString();
            System.out.println(clienteCmb);
            System.out.println(clienteTbl);
            if (clienteCmb.equals(clienteTbl)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public void cargarClientes() {
        ObservableList<Cliente> listaClientes = FXCollections.observableList(clienteService.listaClientes());
        cmbCliente.setItems(listaClientes);
    }
    
    public void cargarEmpleados() {
        ObservableList<Empleado> listaEmpleados = FXCollections.observableList(empleadoService.listarEmpleados());
        cmbEmpleado.setItems(listaEmpleados);
    }

      private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Advertencia");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }


}