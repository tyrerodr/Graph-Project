package TDA;


import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bryan
 */
public class Edge <E>{
    private String peso;
    private Vertex<E> vo;
    private Vertex<E> vd;

    public Edge(String peso, Vertex<E> vo, Vertex<E> vd) {
        this.peso = peso;
        this.vo = vo;
        this.vd = vd;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public Vertex<E> getVOrigen() {
        return vo;
    }

    public void setVorigen(Vertex<E> vo) {
        this.vo = vo;
    }

    public Vertex<E> getVDestino() {
        return vd;
    }

    public void setVDestino(Vertex<E> vd) {
        this.vd = vd;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Edge<?> other = (Edge<?>) obj;
        return Objects.equals(this.vo,other.vo) && Objects.equals(this.vd,other.vd);
    }

    @Override
    public String toString() {
        return  "("+vo + ","+vd+","+peso+")";
    }
    
    
    
}
