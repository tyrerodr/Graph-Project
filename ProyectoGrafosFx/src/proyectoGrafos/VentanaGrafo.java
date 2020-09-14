/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoGrafos;




import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.scene.paint.Color;
import Datos.Logica;
import TDA.*;
import java.util.Iterator;
import java.util.List;
import javafx.scene.control.ScrollPane;

/**
 *
 * @author Bryan
 */
public final class VentanaGrafo {
    public static BorderPane root = new BorderPane();
    private ComboBox<Vertex<String>> nombres1;
    private ComboBox<Vertex<String>> nombres2;


    public VentanaGrafo() {
        root.setPadding(new Insets(10));
        root.setStyle("-fx-background-color: #ffffff;");
        crearPanelTop();
        center();
    }

    
    private void center() {
        VBox parteExterna = new VBox();
        nombres1 = new ComboBox ();
        nombres2 = new ComboBox ();
        
        nombres1.setItems(FXCollections.observableList(Logica.grafoPeliculas.getVertexe()));
        nombres2.setItems(FXCollections.observableList(Logica.grafoPeliculas.getVertexe()));
        
        
   
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
            VBox general = new VBox();
            HBox dibujo = new HBox();
            dibujo.getChildren().add(dibujarDIJKSTRA(nombres1.getValue(),nombres2.getValue()));
            dibujo.getChildren().add(dibujarBFS(nombres1.getValue(),nombres2.getValue()));
            dibujo.getChildren().add(dibujarDFS(nombres1.getValue(),nombres2.getValue()));
            
            general.getChildren().addAll(parteExterna,dibujo);
            root.setCenter(general);
            crearPanelTop();
                
            }); 
            }
    

    
     private void crearPanelTop() {
        HBox top = new HBox();
        Label titulo = new Label("THE ORACLE OF BACON");
        titulo.setTextFill(Color.WHITE);
        top.getChildren().add(titulo);
        top.setStyle("-fx-background-color: #5ce0d3;");
        top.setAlignment(Pos.CENTER);
        root.setTop(top);
     }
     

    public static BorderPane getRoot() {
        return root;
    }
    
    
    private VBox dibujarDIJKSTRA(Vertex<String> v1, Vertex<String> v2){
        HBox dijkstraHBox = new HBox();
        VBox dijkstraVBox = new VBox();
        Label tituloDijkstra = new Label("DIJKSTRA");
        long tieempoInicio = System.nanoTime();
        int numEdge = Logica.grafoPeliculas.numEdgesDijkstra(v1.getData(), v2.getData());;
        long tiempoFin = System.nanoTime()-tieempoInicio;
        
        if(numEdge!=Integer.MAX_VALUE){
                Label ltimeD = new Label("Tiempo de ejecucion para DIJKSTRA es:"  +  tiempoFin + "ns");
                Label lnumD = new Label("Distancia Minima: " + numEdge);
                dijkstraVBox.getChildren().addAll(tituloDijkstra, ltimeD, lnumD);
                List<Vertex<String>> camD = Logica.grafoPeliculas.caminoMinDijkstra(v1.getData(), v2.getData());
                Iterator<Vertex<String>> litD = camD.listIterator();
                VBox dibujoCaminos = new VBox();
                while(litD.hasNext()){
                    Vertex<String> i = litD.next();
                    for(Vertex<String> vrt : camD){
                        Logica.grafoPeliculas.addEdge(i.getData(), vrt.getData(),"NOMBRE DE PELICULA" );                        
                    }

                    dibujoCaminos.getChildren().add(new Label(i.getData()));
//                    if(litD.previousIndex()%2==0){
//                        Label lAct = new Label(i);
//                        lAct.setId("txtActor");
//                        dibujoCaminos.getChildren().add(lAct);
//                        if(litD.hasNext())
//                            dibujoCaminos.getChildren().add(new Label("was in"));
//                    }else{
//                        Label lMovie = new Label(i);
//                        lMovie.setId("txtMovie");
//                        dibujoCaminos.getChildren().add(lMovie);
//                        dibujoCaminos.getChildren().add(new Label("with"));
//                    }
                }
                ScrollPane PanelCaminos = new ScrollPane();
                PanelCaminos.setContent(dibujoCaminos);
                dijkstraHBox.getChildren().add(PanelCaminos);
                dijkstraVBox.getChildren().addAll(dijkstraHBox);
            }else{
                dijkstraHBox.getChildren().add(new Label("Lo sentimos. Resultado no Encontrado"));
                dijkstraVBox.getChildren().addAll(tituloDijkstra, dijkstraHBox);
            }
        
        return dijkstraVBox;
    }
    
    private VBox dibujarBFS(Vertex<String> v1, Vertex<String> v2){
        HBox bfsHBox = new HBox();
        VBox bfsVBox = new VBox();
        Label tituloBfs = new Label("BFS");
        long tieempoInicio = System.nanoTime();
        int numEdge = Logica.grafoPeliculas.numEdgesBFS(v1.getData(), v2.getData());;
        long tiempoFin = System.nanoTime()-tieempoInicio;
        
    if(numEdge!=Integer.MAX_VALUE){
                Label ltimeD = new Label("Tiempo de ejecucion para BFS es:"  +  tiempoFin + "ns");
                Label lnumD = new Label("Distancia Minima: " + numEdge);
                bfsVBox.getChildren().addAll(tituloBfs, ltimeD, lnumD);
                List<Vertex<String>> camD = Logica.grafoPeliculas.caminoBFS(v1.getData(), v2.getData());
                Iterator<Vertex<String>> litD = camD.listIterator();
                VBox dibujoCaminos = new VBox();
                dibujoCaminos.setId("vResults");
                while(litD.hasNext()){
                    Vertex<String> i = litD.next();
                    dibujoCaminos.getChildren().add(new Label(i.getData()));
//                    if(litD.previousIndex()%2==0){
//                        Label lAct = new Label(i);
//                        lAct.setId("txtActor");
//                        dibujoCaminos.getChildren().add(lAct);
//                        if(litD.hasNext())
//                            dibujoCaminos.getChildren().add(new Label("was in"));
//                    }else{
//                        Label lMovie = new Label(i);
//                        lMovie.setId("txtMovie");
//                        dibujoCaminos.getChildren().add(lMovie);
//                        dibujoCaminos.getChildren().add(new Label("with"));
//                    }
                }
                ScrollPane PanelCaminos = new ScrollPane();
                PanelCaminos.setContent(dibujoCaminos);
                bfsHBox.getChildren().add(PanelCaminos);
                bfsVBox.getChildren().addAll(bfsHBox);
        
            }else{
                bfsHBox.getChildren().add(new Label("Lo sentimos. Resultado no Encontrado"));
                bfsVBox.getChildren().addAll(tituloBfs, bfsHBox);
            }
        
        return bfsVBox;
        
    }
    private VBox dibujarDFS(Vertex<String> v1, Vertex<String> v2){
        HBox dfsHBox = new HBox();
        VBox dfsVBox = new VBox();
        Label tituloDfs = new Label("DFS");
        long tieempoInicio = System.nanoTime();
        int numEdge = Logica.grafoPeliculas.numEdgesDFS(v1.getData(), v2.getData());;
        long tiempoFin = System.nanoTime()-tieempoInicio;
        
        if(numEdge!=Integer.MAX_VALUE){
                Label ltimeD = new Label("Tiempo de ejecucion para DFS es:"  +  tiempoFin + "ns");
                Label lnumD = new Label("Distancia Minima: " + numEdge);
                dfsVBox.getChildren().addAll(tituloDfs, ltimeD, lnumD);
                List<Vertex<String>> camD = Logica.grafoPeliculas.caminoDFS(v1.getData(), v2.getData());
                Iterator<Vertex<String>> litD = camD.listIterator();
                VBox dibujoCaminos = new VBox();
                dibujoCaminos.setId("vResults");
                while(litD.hasNext()){
                    Vertex<String> i = litD.next();
                    dibujoCaminos.getChildren().add(new Label(i.getData()));
//                    if(litD.previousIndex()%2==0){
//                        Label lAct = new Label(i);
//                        lAct.setId("txtActor");
//                        dibujoCaminos.getChildren().add(lAct);
//                        if(litD.hasNext())
//                            dibujoCaminos.getChildren().add(new Label("was in"));
//                    }else{
//                        Label lMovie = new Label(i);
//                        lMovie.setId("txtMovie");
//                        dibujoCaminos.getChildren().add(lMovie);
//                        dibujoCaminos.getChildren().add(new Label("with"));
//                    }
                }
                ScrollPane PanelCaminos = new ScrollPane();
                PanelCaminos.setContent(dibujoCaminos);
                dfsHBox.getChildren().add(PanelCaminos);
                dfsVBox.getChildren().addAll(dfsHBox);
            }else{
                dfsHBox.getChildren().add(new Label("Lo sentimos. Resultado no Encontrado"));
                dfsVBox.getChildren().addAll(tituloDfs, dfsHBox);
            }
        
        return dfsVBox;
    }
            
}
    