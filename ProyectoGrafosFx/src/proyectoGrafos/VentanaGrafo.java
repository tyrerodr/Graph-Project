/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoGrafos;




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

import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import Datos.Logica;
import TDA.*;
import java.util.Iterator;
import java.util.List;
import static javax.swing.text.StyleConstants.Background;

/**
 *
 * @author Bryan
 */
public final class VentanaGrafo {
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
        ObservableList<Vertex<String>> items = FXCollections.observableArrayList();
        items.addAll(Logica.grafoPeliculas.getVertexe());
        nombres1.setItems(items);
        nombres2.setItems(items);
        Button btFind = new Button("Find Link");
        HBox mensaje = new HBox();
        Label msg1 = new Label("Seleccione la opcion que desea");
        mensaje.getChildren().addAll(msg1);
        mensaje.setAlignment(Pos.CENTER);
        HBox parteBoton = new HBox(nombres1,new Label("to"),nombres2,btFind);
        parteBoton.setAlignment(Pos.CENTER);
        parteBoton.setSpacing(10);
        parteExterna.getChildren().addAll(mensaje, parteBoton);
        parteExterna.setAlignment(Pos.CENTER);
        parteExterna.setSpacing(15);
        root.setCenter(parteExterna);
        
        btFind.setOnMouseClicked((event) -> {
            root.getChildren().clear();
            VBox todo = new VBox();
            HBox dibujo = new HBox();
            Vertex<String> n1 = (Vertex<String>) nombres1.getValue();
            Vertex<String> n2 = (Vertex<String>) nombres2.getValue();
            List<String> num = Logica.grafoPeliculas.caminoMinDijkstra(n1.getData(),n2.getData());
            int aristas = num.size() -1 ;
            if(aristas == -1) aristas = 0;
            Label txtRes = new Label(n1.getData() + " Has a "+ n2.getData() + " Number of: " + aristas);
            dibujo.getChildren().addAll(txtRes);
            dibujo.setSpacing(15);
            dibujo.setAlignment(Pos.CENTER);
            todo.getChildren().addAll(dibujo,dibujarDistancia(n1.getData(),n2.getData(),num));
            todo.setSpacing(10);
            root.setCenter(todo);
            crearPanelTop();
            root.setBottom(parteExterna);
            });
            }
    

    public VBox dibujarDistancia(String ini, String fin,List<Vertex<String>> recorrido){
        VBox rootDibujo = new VBox();
        VBox prueba = new VBox();
        if(ini.equals(fin)){
            rootDibujo.getChildren().add(new Label(ini));
            rootDibujo.setAlignment(Pos.CENTER);
            return rootDibujo;
        }
        
        Iterator<Vertex<String>> iterador = recorrido.iterator();
        while(iterador.hasNext()){
            Vertex<String> v = iterador.next();
            System.out.println(v.getData());
            prueba.getChildren().add(new Label(v.getData()));
            for(Edge<String> e : v.getEdges()){
                if(e.getVOrigen().getData().equals(v.getData()) && !v.isVisited() && !v.getData().equals(fin)){
                    prueba.getChildren().add(new Label("Was in"));
                    prueba.getChildren().add(new Label(e.getPeso()));     
                    prueba.getChildren().add(new Label("With"));
                    e.getVOrigen().setVisited(true);
                }            
             }     
        }
        
        Logica.grafoPeliculas.cleanVertexes();
        prueba.setAlignment(Pos.CENTER);
        rootDibujo.getChildren().addAll(prueba);
        rootDibujo.setAlignment(Pos.CENTER);
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
    