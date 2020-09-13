/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoGrafos;



import java.io.File;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JButton;

/**
 *
 * @author Bryan
 */
public final class VentanaGrafo {
    final FileChooser fileChooser = new FileChooser();
    public static BorderPane root = new BorderPane();


    public VentanaGrafo() {
        root.setPadding(new Insets(10));
        root.setStyle("-fx-background-color: #ffffff;");
        crearPanelTop();
        center();
        
    }

    
    public void center() {
        VBox parteExterna = new VBox();
        ComboBox nombres1 = new ComboBox ();
        ComboBox nombres2 = new ComboBox ();
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll("Apple", "Banana", "pera","fresa", "Peach", "naranja", "ciruelo","Apple", "Banana", "pera","fresa", "Peach", "naranja", "ciruelo","Apple", "Banana", "pera","fresa", "Peach", "naranja", "ciruelo");
        nombres1.setItems(items);
        nombres2.setItems(items);
        Button btFind = new Button("Find Link");
        Button btMore = new Button("More Options>>");
        HBox mensaje = new HBox();
        Label msg1 = new Label("Seleccione la opcion que desea");
        mensaje.getChildren().addAll(msg1);
        mensaje.setAlignment(Pos.CENTER);
        HBox parteBoton = new HBox(nombres1,new Label("to"),nombres2,btFind,btMore);
        parteBoton.setAlignment(Pos.CENTER);
        parteBoton.setSpacing(10);
        parteExterna.getChildren().addAll(mensaje, parteBoton);
        parteExterna.setAlignment(Pos.CENTER);
        parteExterna.setSpacing(15);
        root.setCenter(parteExterna);
        
        
        btFind.setOnMouseClicked((event) -> {
            root.getChildren().clear();
            crearPanelTop();
            root.setBottom(parteExterna);
            VBox todo = new VBox();
            HBox dibujo = new HBox();
            VBox dibujado = dibujarDistancia(); 
            Label txtRes = new Label(nombres1.getValue() + "Has a"+ nombres2.getValue() + "Number of:" );
            Button btFinf2 = new Button("Find a different link");
            dibujo.getChildren().addAll(txtRes,btFinf2);
            dibujo.setSpacing(15);
            dibujo.setAlignment(Pos.CENTER);
            todo.getChildren().addAll(dibujo,dibujado);
  
            
            root.setCenter(todo);
            
            btFinf2.setOnMouseClicked((e) -> {  
            });
            
                });
                }
    

    public VBox dibujarDistancia(){
        VBox rootDibujo = new VBox();
        
        return rootDibujo;
    }
    
     public void crearPanelTop() {
        HBox top = new HBox();
        Label icono = new Label("MENÚ DE OPCIONES");
        icono.setTextFill(Color.WHITE);
        VBox iconoBox = new VBox(icono);
        top.getChildren().add(iconoBox);
        top.setStyle("-fx-background-color: #5ce0d3;");
        top.setAlignment(Pos.CENTER);
        root.setTop(top);
     }
     
    
 
        
    public static BorderPane getRoot() {
        return root;
    }
}
    