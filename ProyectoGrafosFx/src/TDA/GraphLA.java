package TDA;


import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Bryan
 */
public class GraphLA<E> {

    private LinkedList<Vertex<E>> vertexes;
    private boolean directed;

    public GraphLA(boolean directed) {
        this.directed = directed;
        vertexes = new LinkedList<>();
    }

    public boolean addVertex(E data) {
        Vertex<E> v = new Vertex<>(data);
        return (data == null || vertexes.contains(v)) ? false : vertexes.add(v);
    }
    
    public boolean addVertex(Vertex<E> data){
        return (data.getData() == null || vertexes.contains(data)) ? false : vertexes.add(data);
    }

    public boolean removeVertex(E data) {
        if (data == null || vertexes.isEmpty()) {
            return false;
        }
        ListIterator<Vertex<E>> iv = vertexes.listIterator();
        while (iv.hasNext()) {
            Vertex<E> v = iv.next();
            ListIterator<Edge<E>> ie = v.getEdges().listIterator();
            while (ie.hasNext()) {
                Edge<E> e = ie.next();
                if (e.getVDestino().getData().equals(data)) {
                    ie.remove();
                }
            }
        }
        Vertex<E> vi = new Vertex<>(data);
        return vertexes.remove(vi);
    }

    public boolean addEdge(E src, E dst, String peso) {
        if (src == null || dst == null) {
            return false;
        }
        Vertex<E> vs = searchVertex(src);
        Vertex<E> vd = searchVertex(dst);
        if (vs == null || vd == null) {
            return false;
        }
        Edge<E> e = new Edge<>(peso, vs, vd);
        if (!vs.getEdges().contains(e)) {
            vs.getEdges().add(e);
        }
        if (!directed) {
            Edge<E> ei = new Edge<>(peso, vd, vs);
            if (!vd.getEdges().contains(ei)) {
                vd.getEdges().add(ei);
            }
        }
        return true;
    }

    private Vertex<E> searchVertex(E data) {
        for (Vertex v : vertexes) {
            if (v.getData().equals(data)) {
                return v;
            }
        }
        return null;
    }
   

    public void cleanVertexes() {
        for (Vertex<E> v : vertexes) {
            v.setVisited(false);
        }
    }

    public boolean removeEdge(E src, E dst) {
        if (src == null || dst == null) {
            return false;
        }
        Vertex<E> vs = searchVertex(src);
        Vertex<E> vd = searchVertex(dst);
        if (vs == null || vd == null) {
            return false;
        }
        Edge<E> e = new Edge<>("", vs, vd);
        vs.getEdges().remove(e);
        if (!directed) {
            e = new Edge<>("", vd, vs);
            vd.getEdges().remove(e);
        }
        return true;
    }

    public int getOutDegree(E data) {
        if (data == null) {
            return -1;
        }
        Vertex<E> v = searchVertex(data);
        return (v == null) ? -1 : v.getEdges().size();
    }

    public int getInDegree(E data) {
        Vertex<E> vertex = searchVertex(data);
        if (vertex == null) {
            return -1;
        }
        int grade = 0;
        for (Vertex<E> v : vertexes) {
            for (Edge<E> e : v.getEdges()) {
                if (e.getVDestino().equals(vertex)) {
                    grade++;
                }
            }
        }
        return grade;
    }

    @Override
    public String toString() {
        StringBuilder sbrv = new StringBuilder();
        sbrv.append(" v={");
        StringBuilder sbra = new StringBuilder();
        sbra.append(" a={");

        for (Vertex<E> vertex : vertexes) {
            sbrv.append(vertex.getData());
            sbrv.append(", ");
            for (Edge<E> e : vertex.getEdges()) {
                sbra.append(e.toString());
                sbra.append(", ");
            }
        }
        if (!vertexes.isEmpty()) {
            sbrv.delete(sbrv.length() - 2, sbrv.length());
        }
        if (sbra.length() > 4) {
            sbra.delete(sbra.length() - 2, sbra.length());
        }

        sbrv.append("}");
        sbra.append("}");
        return sbrv.toString() + "\n" + sbra.toString();
    }
//
//    public boolean isConnected() {
//        if(directed && connectedComponents().size()!=1) return false;
//        if(directed && connectedComponents().size()==1) return true;
//        if(!directed && connectedComponents().size()!=1) return false;
//        if(!directed && connectedComponents().size()==1) return true;
//        return false;
//    }

    public GraphLA<E> reverse() {
        GraphLA<E> grafo = new GraphLA<E>(directed); 
        for(Vertex<E> v :vertexes){
            grafo.vertexes.add(new Vertex<>(v.getData()));
        }
        for (Vertex<E> v : vertexes) {
            for (Edge<E> e : v.getEdges()) {
                grafo.addEdge(e.getVDestino().getData(),e.getVOrigen().getData(),e.getPeso());
            }
        }
        return grafo;
    }
    public List<E> getVertexes(){
        List<E> lista = new LinkedList<>();
        for(Vertex<E> v :vertexes){
            lista.add(v.getData());
        }
        return lista;
    }
    
    public List<Vertex<E>> getVertexe(){
        List<Vertex<E>> lista = new LinkedList<>();
        for(Vertex<E> v :vertexes){
            lista.add(v);
        }
        return vertexes;
    }

//    public List<Set<E>> connectedComponents() {
//        LinkedList<Set<E>> result = new LinkedList<>();
//        GraphLA<E> reversed = reverse();
//        Set<E> componentes = new HashSet<>(getVertexes());
//        while(!componentes.isEmpty()){
//            E origen = (E) componentes.toArray()[0];
//            List<E> bfsNormal = bfs(origen);
//            List<E> bfsInvertido = reversed.bfs(origen);
//            Set<E> conjBfsN = new HashSet<>(bfsNormal);
//            Set<E> conjBfsI = new HashSet<>(bfsInvertido);
//            conjBfsN.retainAll(conjBfsI);
//            componentes.removeAll(conjBfsN);
//            result.add(conjBfsN);    
//        }
//        return result;
//    }
    

    private void dijkstra(E inicio){
        Vertex<E> v = searchVertex(inicio);
        if(v == null) throw new NullPointerException();
        PriorityQueue<Vertex<E>> cola = new PriorityQueue<>((Vertex<E> v1, Vertex<E> v2)->v1.getDistancia()- v2.getDistancia());
        v.setDistancia(0);
        cola.offer(v);
        while(!cola.isEmpty()){
            v = cola.poll();
            v.setVisited(true);
            for(Edge<E> e: v.getEdges()){
                if(!e.getVDestino().isVisited()){
                    if(v.getDistancia()+1 < e.getVDestino().getDistancia()){
                        e.getVDestino().setDistancia(v.getDistancia()+1);
                        e.getVDestino().setAntecesor(v);
                        cola.offer(e.getVDestino());
                    }
                }
            }
        }
    }
    
    private void bfs(E data){
        Vertex<E> v = searchVertex(data);
        if(v == null) throw new NullPointerException();

        Queue<Vertex<E>> cola = new LinkedList<>();
        v.setDistancia(0);      
        cola.offer(v);
        while(!cola.isEmpty()){
            v = cola.poll();
            v.setVisited(true); 
            for(Edge<E> e: v.getEdges()){
                if(!e.getVDestino().isVisited()){
                    e.getVDestino().setDistancia(v.getDistancia()+1);
                    e.getVDestino().setAntecesor(v);
                    cola.offer(e.getVDestino());
                }
            }
        }
    }

    private void dfs(E data){
        Vertex<E> v = searchVertex(data);
        if(v == null) throw new NullPointerException();

        Deque<Vertex<E>> pila = new LinkedList<>();
        v.setDistancia(0);      
        pila.push(v);
        while(!pila.isEmpty()){
            v = pila.pop();
            v.setVisited(true); 
            for(Edge<E> e: v.getEdges()){
                if(!e.getVDestino().isVisited()){
                    e.getVDestino().setDistancia(v.getDistancia()+1);
                    e.getVDestino().setAntecesor(v);
                    pila.push(e.getVDestino());
                }
            }
        }
    }
    
    public int numEdgesDijkstra(E inicio, E fin){
        if(inicio == null || fin == null) return -1;
        if(inicio.equals(fin)) return 0;
        dijkstra(inicio);
        Vertex<E> v = searchVertex(fin);
        if(v==null) return 0;
        int x = v.getDistancia();
        cleanVertexes();
        return x;
    }
    
    public int numEdgesBFS(E inicio, E fin){
        if(inicio == null || fin == null) return -1;
        if(inicio.equals(fin)) return 0;
        bfs(inicio);
        Vertex<E> v = searchVertex(fin);
        if(v==null) return 0;
        int x = v.getDistancia();
        cleanVertexes();
        return x;
    }
    
    public int numEdgesDFS(E inicio, E fin){
        if(inicio == null || fin == null) return -1;
        if(inicio.equals(fin)) return 0;
        dfs(inicio);
        Vertex<E> v = searchVertex(fin);
        if(v==null) return 0;
        int x = v.getDistancia();
        cleanVertexes();
        return x;
    }

    public List<String> getCaminoDijkstra(E inicio, E fin){
        List<String> list = new ArrayList<>();
        Vertex<E> v = searchVertex(fin);
        if(inicio == null || fin == null || v == null) return list;
        dijkstra(inicio);
        list.add(v.getData().toString());
        while(v.getAntecesor() != null){
            v = v.getAntecesor();
            list.add(v.getData().toString());
        }
        cleanVertexes();
        return list;
    }
    
    public List<String> getCaminoBFS(E inicio, E fin){
        List<String> list = new ArrayList<>();
        Vertex<E> v = searchVertex(fin);
        if(inicio == null || fin == null || v == null) return list;
        bfs(inicio);
        list.add(v.getData().toString());
        while(v.getAntecesor() != null){
            v = v.getAntecesor();
            list.add(v.getData().toString());
        }
        cleanVertexes();
        return list;
    }
    
    public List<String> getCaminoDFS(E inicio, E fin){
        List<String> list = new ArrayList<>();
        Vertex<E> v = searchVertex(fin);
        if(inicio == null || fin == null || v == null) return list;
        dfs(inicio);
        list.add(v.getData().toString());
        while(v.getAntecesor() != null){
            v = v.getAntecesor();
            list.add(v.getData().toString());
        }
        cleanVertexes();
        return list;
    }
}


