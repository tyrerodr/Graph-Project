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

    private VBox dibujarDIJKSTRA(Vertex<String> v1, Vertex<String> v2) {
        HBox dijkstraHBox = new HBox();
        VBox dijkstraVBox = new VBox();
        Label tituloDijkstra = new Label("DIJKSTRA");
        long tieempoInicio = System.nanoTime();
        int numEdge = Logica.grafoPeliculas.numEdgesDijkstra(v1.getData(), v2.getData());;
        long tiempoFin = System.nanoTime() - tieempoInicio;

        Label tiempoEjec = new Label("Tiempo de ejecucion para DIJKSTRA es:" + tiempoFin + "ns");
        Label minDistancia = new Label("Distancia Minima: " + numEdge);
        dijkstraVBox.getChildren().addAll(tituloDijkstra, tiempoEjec, minDistancia);
        List<Vertex<String>> caminoLista = Logica.grafoPeliculas.caminoMinDijkstra(v1.getData(), v2.getData());
        Iterator<Vertex<String>> list = caminoLista.listIterator();
        VBox dibujoCaminos = new VBox();
        while (list.hasNext()) {
            Vertex<String> i = list.next();
            Label lAct = new Label(i.getData());
            dibujoCaminos.getChildren().add(lAct);
            if (list.hasNext()) {
                dibujoCaminos.getChildren().add(new Label("was in"));

                Label lMovie = new Label(i.getMovie());
                lMovie.setId("txtMovie");
                dibujoCaminos.getChildren().add(lMovie);
                dibujoCaminos.getChildren().add(new Label("with"));
            }
        }

        ScrollPane PanelCaminos = new ScrollPane();
        PanelCaminos.setContent(dibujoCaminos);
        dijkstraHBox.getChildren().add(PanelCaminos);
        dijkstraVBox.getChildren().addAll(dijkstraHBox);

        return dijkstraVBox;
    }

    private VBox dibujarBFS(Vertex<String> v1, Vertex<String> v2) {
        HBox bfsHBox = new HBox();
        VBox bfsVBox = new VBox();
        Label tituloBfs = new Label("BFS");
        long tieempoInicio = System.nanoTime();
        int numEdge = Logica.grafoPeliculas.numEdgesBFS(v1.getData(), v2.getData());;
        long tiempoFin = System.nanoTime() - tieempoInicio;

        Label timeEjec = new Label("Tiempo de ejecucion para BFS es:" + tiempoFin + "ns");
        Label minDistancia = new Label("Distancia Minima: " + numEdge);
        bfsVBox.getChildren().addAll(tituloBfs, timeEjec, minDistancia);
        List<Vertex<String>> caminoLista = Logica.grafoPeliculas.caminoBFS(v1.getData(), v2.getData());
        Iterator<Vertex<String>> list = caminoLista.listIterator();
        VBox dibujoCaminos = new VBox();
        dibujoCaminos.setId("vResults");
        while (list.hasNext()) {
            Vertex<String> i = list.next();
            Label lAct = new Label(i.getData());
            dibujoCaminos.getChildren().add(lAct);
            if (list.hasNext()) {
                dibujoCaminos.getChildren().add(new Label("was in"));

                Label lMovie = new Label(i.getMovie());
                lMovie.setId("txtMovie");
                dibujoCaminos.getChildren().add(lMovie);
                dibujoCaminos.getChildren().add(new Label("with"));
            }
        }
        ScrollPane PanelCaminos = new ScrollPane();
        PanelCaminos.setContent(dibujoCaminos);
        bfsHBox.getChildren().add(PanelCaminos);
        bfsVBox.getChildren().addAll(bfsHBox);

        return bfsVBox;

    }

    private VBox dibujarDFS(Vertex<String> v1, Vertex<String> v2) {
        HBox dfsHBox = new HBox();
        VBox dfsVBox = new VBox();
        Label tituloDfs = new Label("DFS");
        long tieempoInicio = System.nanoTime();
        int numEdge = Logica.grafoPeliculas.numEdgesDFS(v1.getData(), v2.getData());;
        long tiempoFin = System.nanoTime() - tieempoInicio;

        Label timeEjec = new Label("Tiempo de ejecucion para DFS es:" + tiempoFin + "ns");
        Label minDistancia = new Label("Distancia Minima: " + numEdge);
        dfsVBox.getChildren().addAll(tituloDfs, timeEjec, minDistancia);
        List<Vertex<String>> caminoLista = Logica.grafoPeliculas.caminoDFS(v1.getData(), v2.getData());
        Iterator<Vertex<String>> list = caminoLista.listIterator();
        VBox dibujoCaminos = new VBox();
        dibujoCaminos.setId("vResults");
        while (list.hasNext()) {
            Vertex<String> i = list.next();
            Label lAct = new Label(i.getData());
            dibujoCaminos.getChildren().add(lAct);
            if (list.hasNext()) {
                dibujoCaminos.getChildren().add(new Label("was in"));

                Label lMovie = new Label(i.getMovie());
                lMovie.setId("txtMovie");
                dibujoCaminos.getChildren().add(lMovie);
                dibujoCaminos.getChildren().add(new Label("with"));
            }
        }
        ScrollPane PanelCaminos = new ScrollPane();
        PanelCaminos.setContent(dibujoCaminos);
        dfsHBox.getChildren().add(PanelCaminos);
        dfsVBox.getChildren().addAll(dfsHBox);

        return dfsVBox;
    }

}
    