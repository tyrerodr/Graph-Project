/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoGrafos;


import Datos.Logica;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

/**
 *
 * @author Robert
 */
public class ProyectoGrafos extends Application {
    public static Scene scene;
    
    @Override
    public void init(){
       Logica.cargarPeliculas("data.txt");
           
    }

    public void start(Stage primaryStage){      
        VentanaGrafo i = new VentanaGrafo();
        scene = new Scene(i.getRoot(), 700, 500);
        primaryStage.setTitle("PROYECTO GRAFOS");
        primaryStage.setScene(scene);
        primaryStage.show();
       
    }
    
    @Override
    public void stop(){
        System.exit(0);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
