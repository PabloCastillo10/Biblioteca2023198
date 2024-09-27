package com.pablocastillo.webapp.biblioteca.system;


import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.pablocastillo.webapp.biblioteca.BibliotecaIn5CmApplication;
import com.pablocastillo.webapp.biblioteca.controller.PrestamoController;
import com.pablocastillo.webapp.biblioteca.controller.FXController.ClienteFXMLController;
import com.pablocastillo.webapp.biblioteca.controller.FXController.EmpleadoFXMLController;
import com.pablocastillo.webapp.biblioteca.controller.FXController.IndexController;
import com.pablocastillo.webapp.biblioteca.controller.FXController.InicioControllerFX;
import com.pablocastillo.webapp.biblioteca.controller.FXController.LibroFXMLController;
import com.pablocastillo.webapp.biblioteca.controller.FXController.PrestamoControllerFX;
import com.pablocastillo.webapp.biblioteca.model.Prestamo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.InputStream;
import java.io.IOException;

public class Main extends Application{

    //atributos
    private ConfigurableApplicationContext applicationContext;
    private Stage stage;
    private Scene scene;

    //Se ejecuta cada vez que yo instancie la clase MAIN
    @Override
    public void init(){
        this.applicationContext = new SpringApplicationBuilder(BibliotecaIn5CmApplication.class) .run();
    }

    //El metodo start es el que se ejecuta al iniciar la aplicaicon de javaFX
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        stage.setTitle("Biblioteca Springboot");
        inicioView();
        //carga la escene principal
        stage.show();
    }

    //Metodo para cambiar la escena del stage
    public Initializable cambiarEscena(String fxmlName, int width, int height) throws IOException {
        // recibe un string que identifica el nombre del fxml y dos enteros para el ancho y el alto
        Initializable initializable = null;
        FXMLLoader loader = new FXMLLoader(); // es una clase que ayuda cargar los archivos fxml y transformarlos en escena para poder cargalos en stage

        loader.setControllerFactory(applicationContext::getBean); //genera un controlador del metodo main para poder cambiar las escenas desde aca.
        InputStream archivo = Main.class.getResourceAsStream("/templates/" + fxmlName);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource("/templates/" + fxmlName));

        scene = new Scene((AnchorPane)loader.load(archivo), width, height);
        stage.setScene(scene);
        stage.sizeToScene();

        initializable = (Initializable)loader.getController(); 

        return initializable;
    }

    //metodo para mostrar el index
    public void indexView(){
        try{
            IndexController indexView = (IndexController)cambiarEscena("index.fxml", 600, 400);
            indexView.setStage(this);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void libroView(){
        try{
            LibroFXMLController libroView = (LibroFXMLController)cambiarEscena("libro.fxml", 1200, 750);
            libroView.setStage(this);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void clienteView(){
        try{
            ClienteFXMLController clienteView = (ClienteFXMLController) cambiarEscena("cliente.fxml", 1200 , 750);
            clienteView.setStage(this);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void empleadoView(){
        try{
            EmpleadoFXMLController empleadoView = (EmpleadoFXMLController) cambiarEscena("empleado.fxml", 1200, 750);
            empleadoView.setStage(this);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void prestamoView(){
        try{
            PrestamoControllerFX prestamoView = (PrestamoControllerFX) cambiarEscena("prestamo.fxml", 1200, 750);
            prestamoView.setStage(this);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void inicioView(){
        try{
            InicioControllerFX  inicioView  =  (InicioControllerFX) cambiarEscena("inicio.fxml", 600, 400);
            inicioView.setStage(this);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
