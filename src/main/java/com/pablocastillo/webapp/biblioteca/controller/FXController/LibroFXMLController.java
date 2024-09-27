package com.pablocastillo.webapp.biblioteca.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pablocastillo.webapp.biblioteca.model.Categoria;
import com.pablocastillo.webapp.biblioteca.model.Libro;
import com.pablocastillo.webapp.biblioteca.service.CategoriaService;
import com.pablocastillo.webapp.biblioteca.service.LibroService;
import com.pablocastillo.webapp.biblioteca.system.Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;

@Component
public class LibroFXMLController implements Initializable {
    @Setter
    private Main stage;

    @FXML
    TextField tfId,tfAutor, tfCluster,  tfEditorial, tfIsbn, tfNombre, tfNumEs, tfSinopsis, tfLibro;
    @FXML
    TableView tblLibros;
    @FXML
    TableColumn colId, colAutor, colCluster, colDisponibilidad, colEditorial, colIsbn, colNombre, colNumEs, colSinopsis, colCategoria;
    @FXML
    Button btnGuardar, btnEliminar, btnLimpiar, btnBuscar, btnRegresar;
    @FXML
    ComboBox cmbCategorias, cmbDisponibilidad;

    @Autowired
    LibroService libroService;
    @Autowired
    CategoriaService categoriaService;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
      cargarDatos();
      cargarCategorias();
    }

    public void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btnGuardar) {
            if(tfId.getText().isBlank()){ 
                agregarLibro(); 
            }else{
                editarLibro();
            }
        }else if(event.getSource() == btnEliminar) {
           eliminarLibro();
        }else if(event.getSource() == btnBuscar) {
                buscarLibro();
        }else if(event.getSource() == btnLimpiar) {
            limpiarTextField();
        }else if(event.getSource() == btnRegresar){
            stage.inicioView();
        }
    }

    public void cargarDatos(){
        tblLibros.getItems().clear();
        tblLibros.setItems(listarLibros());
        cmbCategorias.getSelectionModel().clearSelection();
        
        colId.setCellValueFactory(new PropertyValueFactory<Libro, Long>("id"));
        colAutor.setCellValueFactory(new PropertyValueFactory<Libro, String>("autor"));
        colCluster.setCellValueFactory(new PropertyValueFactory<Libro, String>("cluster"));
        colDisponibilidad.setCellValueFactory(new PropertyValueFactory<Libro, Boolean>("disponibilidad"));
        colEditorial.setCellValueFactory(new PropertyValueFactory<Libro, String>("editorial"));
        colIsbn.setCellValueFactory(new PropertyValueFactory<Libro, String>("isbn"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Libro, String>("nombre"));
        colNumEs.setCellValueFactory(new PropertyValueFactory<Libro, String>("numeroEstanteria"));
        colSinopsis.setCellValueFactory(new PropertyValueFactory<Libro, String>("sinopsis"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<Libro, Categoria>("categoria"));
    }

    public void cargarTextField(){
        Libro libro = (Libro) tblLibros.getSelectionModel().getSelectedItem();
        if (libro != null) {
            tfId.setText(Long.toString(libro.getId()));
            tfAutor.setText(libro.getAutor());
            tfCluster.setText(libro.getCluster());
            cmbDisponibilidad.getItems().add("Disponible");
            cmbDisponibilidad.getItems().add("No disponible");
            tfEditorial.setText(libro.getEditorial());
            tfIsbn.setText(libro.getIsbn());
            tfNombre.setText(libro.getNombre());
            tfNumEs.setText(libro.getNumeroEstanteria());
            tfSinopsis.setText(libro.getSinopsis());
            cmbCategorias.getSelectionModel().select(0);
            
        }
    }

    public ObservableList<Libro> listarLibros(){
        return FXCollections.observableArrayList(libroService.listarLibros());
    }

    public void limpiarTextField(){
        tfId.clear();
        tfAutor.clear();
        tfCluster.clear();
        cmbDisponibilidad.getSelectionModel().clearSelection();
        tfEditorial.clear();
        tfIsbn.clear();
        tfNombre.clear();
        tfNumEs.clear();
        tfSinopsis.clear();
        cmbCategorias.getSelectionModel().clearSelection();
    }

    public void agregarLibro(){
        Libro libro = new Libro();
        libro.setAutor(tfAutor.getText());
        libro.setCluster(tfCluster.getText());
        libro.setDisponibilidad((String)cmbDisponibilidad.getSelectionModel().getSelectedItem() == "Disponible" ? true : false);
        libro.setEditorial(tfEditorial.getText());
        libro.setIsbn(tfIsbn.getText());
        libro.setNombre(tfNombre.getText());
        libro.setSinopsis(tfSinopsis.getText());
        Categoria categoria = (Categoria) cmbCategorias.getSelectionModel().getSelectedItem();
        libro.setCategoria(categoria);

        libroService.guardarLibro(libro);
        cargarDatos();
        limpiarTextField();
    }

    public void editarLibro() {
        Libro libro = libroService.buscarLibroPorId(Long.parseLong(tfId.getText()));
        libro.setAutor(tfAutor.getText());
        libro.setCluster(tfCluster.getText());
        libro.setDisponibilidad(cmbDisponibilidad.getSelectionModel().getSelectedItem() == "Disponible" ? true : false);
        libro.setEditorial(tfEditorial.getText());
        libro.setIsbn(tfIsbn.getText());
        libro.setNombre(tfNombre.getText());
        libro.setNumeroEstanteria(tfNumEs.getText());
        libro.setSinopsis(tfSinopsis.getText());
        Categoria categoriaSeleccionada = (Categoria) cmbCategorias.getSelectionModel().getSelectedItem();
        libro.setCategoria(categoriaSeleccionada);
        
        libroService.guardarLibro(libro);
        cargarDatos();
        limpiarTextField();
    }

    public void eliminarLibro() {
        Libro libro = libroService.buscarLibroPorId(Long.parseLong(tfId.getText()));
        libroService.eliminarLibro(libro);
        cargarDatos();
        limpiarTextField();
    }


    public void buscarLibro() {
        tblLibros.getItems().clear();
        if (tfLibro.getText().isBlank()) {
            cargarDatos();
        } else {
            Libro libro = libroService.buscarLibroPorId(Long.parseLong(tfLibro.getText()));
            if (libro != null) {
                ObservableList<Libro> libroBuscado = FXCollections.observableArrayList(libro);  
                tblLibros.setItems(libroBuscado);
            } else {
                tblLibros.getItems().clear();
            }
        }
    }

    public void cargarCategorias(){
        ObservableList<Categoria> categorias = FXCollections.observableArrayList(categoriaService.listaCategorias());
        cmbCategorias.setItems(categorias);
    }
        
    

    
    
}
