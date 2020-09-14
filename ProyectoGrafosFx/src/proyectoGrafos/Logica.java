/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectografos;

import TDA.Edge;
import TDA.GraphLA;
import TDA.Vertex;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bryan
 */
public class Logica {
    public static final GraphLA<String>  grafoPeliculas = new GraphLA<>(false);
    private static void dumpJSONElement(JsonElement datos) {
        if(datos.isJsonObject()){
            JsonObject obj = datos.getAsJsonObject();
            java.util.Set<Map.Entry<String,JsonElement>> entradas = obj.entrySet();
            LinkedList<String> listaActores = new LinkedList<>();
            Map<String,String[]> map = new HashMap<>();
             for(Map.Entry<String,JsonElement> entry : entradas){
                //System.out.println(entry.getValue()+"go");
                if(entry.getKey().equals("cast")){
                    //System.out.println(entry);
                    for(String actors :dumpJSONElement(entry.getValue().getAsJsonArray())){
                        grafoPeliculas.addVertex(actors);
                        listaActores.add(actors);
                    }    
                }
            } 
            System.out.println("ACTORES");
            for(String actI : listaActores){
                for(String actS : listaActores){
                    if(!actI.equals(actS))
                        grafoPeliculas.addEdge(actI , actS , (int) Math.random()); 
                }                        
            }
            System.out.println("EDGES");
            for(Vertex<String> v : grafoPeliculas.getVertexe()){
                System.out.println(v.getEdges());
            }
//            System.out.println(grafoPeliculas);
            
        }else if(datos.isJsonArray()){
            
        }else if(datos.isJsonPrimitive()){
            
        }else if(datos.isJsonNull()){
            
        }else{
            System.out.println("Es otra cosa");
        }
    
    
    }
    private static List<String> dumpJSONElement(JsonArray datos){
        List<String> lista = new LinkedList<>();
        for(JsonElement e:datos){
            System.out.println(e.getAsJsonPrimitive()+" medotod list");
            if(e.isJsonPrimitive())
                lista.add(e.getAsString());
            
        }
        return lista;
    }
    
    
    


    public static void cargarPeliculas(String texto){
     //Puede que se lea de un txt   
     
     JsonParser parser = new JsonParser();
        try {
            BufferedReader fr = new BufferedReader(new FileReader("src\\Datos\\"+texto));
            String linea="";
            while(linea!=null){
                linea=fr.readLine();
                JsonElement datos = parser.parse(linea);
                dumpJSONElement(datos);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Logica.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Logica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args){
        cargarPeliculas("data2.txt");
    }
    
    



}
