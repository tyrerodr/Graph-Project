
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Robert
 */
public class Prueba {

    public static void main(String[] arg) {
        System.out.println(leerArchivo());
    }

    public static HashMap<String, List<String>> leerArchivo() {

        HashMap<String, List<String>> mapa = new HashMap<String, List<String>>();
        try (BufferedReader bff = new BufferedReader(new FileReader("src/recursos/archivo/traducciones.txt"))) {
            String linea;
            while ((linea = bff.readLine()) != null) {
                String[] array = linea.split("\\|");
                List<String> lista = new LinkedList<>();
                for(int i=1 ; i<array.length ; i++){
                    lista.add(array[i]);
                }
                mapa.put(array[0], lista);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mapa;
    }
}
