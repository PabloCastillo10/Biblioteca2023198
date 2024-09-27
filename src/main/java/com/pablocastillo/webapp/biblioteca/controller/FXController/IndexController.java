package com.pablocastillo.webapp.biblioteca.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pablocastillo.webapp.biblioteca.model.Categoria;
import com.pablocastillo.webapp.biblioteca.service.CategoriaService;
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

@Component // es para generar el bin 
public class IndexController implements Initializable {
    @Setter // evita hacer el set de forma manual
    private Main stage;
    

    @FXML
    TextField tfId, tfNombre, tfCategoria;
    @FXML
    TableView tblCategorias;
    @FXML
    TableColumn colId, colNombre;
    @FXML
    Button btnGuardar, btnEliminar, btnLimpiar, btnBuscar, btnRegresar;

    @Autowired
    CategoriaService categoriaService;

    //Es un metodo que se ejecuta cada que yo levanto la vista 
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarCategorias();
    }

    public void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btnGuardar) {
            if(tfId.getText().isBlank()){ //SI VIENE VACIO 
                agregarCategoria(); //SI VIENE VACIO LLAMA AL AGREGAR CATEGORIA
            }else{
                editarCategoria();
            }
        }else if(event.getSource() == btnEliminar) {
            eliminarCategoria();
        }else if(event.getSource() == btnBuscar) {
                buscarCategoria();
        }else if(event.getSource() == btnRegresar){
            stage.inicioView();
        }
    }

    //cargar datos: llena el tableView
    public void cargarCategorias(){
        tblCategorias.getItems().clear(); //se limpia la tabla
        tblCategorias.setItems(listarCategorias()); //listar
        colId.setCellValueFactory(new PropertyValueFactory<Categoria, Long>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Categoria, String>("nombreCategoria")); //se pone los nombre de los modelos



    }

    public void cargarTextField(){
        Categoria categoria = (Categoria) tblCategorias.getSelectionModel().getSelectedItem();
        if(categoria != null) { //si categoria es diferente a nulo si es diferente ya va cargando los textfield
            tfId.setText(Long.toString(categoria.getId()));
            tfNombre.setText(categoria.getNombreCategoria());

        }
    }
    public ObservableList<Categoria> listarCategorias(){
        return FXCollections.observableArrayList(categoriaService.listaCategorias());
    }

    public void limpiarTextField(){
        tfId.clear();
        tfNombre.clear();
    }

    public void agregarCategoria(){
        Categoria categoria = new Categoria();
        categoria.setNombreCategoria(tfNombre.getText());
        categoriaService.guardarCategoria(categoria);
        cargarCategorias();
    }

    public void editarCategoria(){
       Categoria categoria = categoriaService.busCategoriaPorId(Long.parseLong(tfId.getText()));
       categoria.setNombreCategoria(tfNombre.getText());
       categoriaService.guardarCategoria(categoria);
    }

    public void eliminarCategoria(){
        Categoria categoria  = categoriaService.busCategoriaPorId(Long.parseLong(tfId.getText()));
        categoriaService.eliminarCategoria(categoria);
        cargarCategorias();
    }


    public void buscarCategoria(){
        tblCategorias.getItems().clear();
        if(tfCategoria.getText().isBlank()) {
            cargarCategorias();
        } else {
            Categoria categoria = categoriaService.busCategoriaPorId(Long.parseLong(tfCategoria.getText()));

            if(categoria  != null) {
                ObservableList<Categoria> categoriaBuscada = FXCollections.observableArrayList(categoria);
                tblCategorias.getItems().clear(); 
                tblCategorias.setItems(categoriaBuscada); 
            } else {
                tblCategorias.getItems().clear();
            }
        }
    }

}


