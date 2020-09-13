/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoGrafos;

import javafx.scene.shape.Circle;

/**
 *
 * @author Tyrone
 */
public class Circulo {
    public Circle circle;
    public String referencia;

    public Circle getCircle() {
        return circle;
    }

    public String getReferencia() {
        return referencia;
    }


    public Circulo(Circle c, String referencia) {
        this.circle = c;
        this.referencia = referencia;
    }
    
    
    
    
}
