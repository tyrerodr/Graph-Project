/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;


import TDA.GraphLA;
import TDA.Vertex;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bryan
 */
public class Logica {
    public static GraphLA<String>  grafoPeliculas = new GraphLA<>(false);
    
    
    private static void dumpJSONElement(JsonElement datos) {
        if(datos.isJsonObject()){
            JsonObject obj = datos.getAsJsonObject();
            java.util.Set<Map.Entry<String,JsonElement>> entradas = obj.entrySet();
            LinkedList<String> listaActores = new LinkedList<>();
            String tmp = "";
            Map<String,String[]> map = new HashMap<>();
             for(Map.Entry<String,JsonElement> entry : entradas){
                if(entry.getKey().equals("title")){
                    tmp = entry.getValue().getAsString();
                }   
                if(entry.getKey().equals("cast")){
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
                        grafoPeliculas.addEdge(actI , actS, tmp); 
                }                        
            }
            System.out.println("EDGES");
            for(Vertex<String> v : grafoPeliculas.getVertexe()){
                System.out.println(v.getEdges());
            }
                  
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
     JsonParser parser = new JsonParser();
        try {
            BufferedReader fr = new BufferedReader(new FileReader("src\\Datos\\"+texto));
            String linea="";
            while((linea = fr.readLine()) != null){
                JsonElement datos = parser.parse(linea);
                dumpJSONElement(datos);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Logica.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Logica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
