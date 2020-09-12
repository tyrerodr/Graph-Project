package TDA;


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

    public boolean addEdge(E src, E dst, int peso) {
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

    public List<E> bfs(E data) {
        List<E> result = new LinkedList<>();
        if (data == null) {
            return result;
        }
        Vertex<E> v = searchVertex(data);
        if (v == null) {
            return result;
        }
        Queue<Vertex<E>> cola = new LinkedList<>();
        v.setVisited(true);
        cola.offer(v);
        while (!cola.isEmpty()) {
            v = cola.poll();
            result.add(v.getData());
            for (Edge<E> e : v.getEdges()) {
                if (!e.getVDestino().isVisited()) {
                    e.getVDestino().setVisited(true);
                    cola.offer(e.getVDestino());
                }
            }
        }
        cleanVertexes();
        return result;
    }

    public List<E> dfs(E data) {
        List<E> result = new LinkedList<>();
        if (data == null) {
            return result;
        }
        Vertex<E> v = searchVertex(data);
        if (v == null) {
            return result;
        }
        Deque<Vertex<E>> pila = new LinkedList<>();
        v.setVisited(true);
        pila.offer(v);
        while (!pila.isEmpty()) {
            v = pila.poll();
            result.add(v.getData());
            for (Edge<E> e : v.getEdges()) {
                if (!e.getVDestino().isVisited()) {
                    e.getVDestino().setVisited(true);
                    pila.offer(e.getVDestino());
                }
            }
        }
        cleanVertexes();
        return result;
    }

    private void cleanVertexes() {
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
        Edge<E> e = new Edge<>(0, vs, vd);
        vs.getEdges().remove(e);
        if (!directed) {
            e = new Edge<>(0, vd, vs);
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

    public boolean isConnected() {
        if(directed && connectedComponents().size()!=1) return false;
        if(directed && connectedComponents().size()==1) return true;
        if(!directed && connectedComponents().size()!=1) return false;
        if(!directed && connectedComponents().size()==1) return true;
        return false;
    }

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

    public List<Set<E>> connectedComponents() {
        LinkedList<Set<E>> result = new LinkedList<>();
        GraphLA<E> reversed = reverse();
        Set<E> componentes = new HashSet<>(getVertexes());
        while(!componentes.isEmpty()){
            E origen = (E) componentes.toArray()[0];
            List<E> bfsNormal = bfs(origen);
            List<E> bfsInvertido = reversed.bfs(origen);
            Set<E> conjBfsN = new HashSet<>(bfsNormal);
            Set<E> conjBfsI = new HashSet<>(bfsInvertido);
            conjBfsN.retainAll(conjBfsI);
            componentes.removeAll(conjBfsN);
            result.add(conjBfsN);    
        }
        return result;
    }
    
    public GraphLA<E> kruskal(){
        GraphLA<E> kruskal = new GraphLA<>(directed);
        PriorityQueue<Edge<E>> cola = new PriorityQueue<>((Edge<E> e1,Edge<E> e2)->(e1.getPeso()-e2.getPeso()));
        for(Vertex<E> v :vertexes){
            kruskal.addVertex(v.getData());
        }
        for(Vertex<E> v : vertexes){
            for(Edge<E> e: v.getEdges()){
                cola.offer(e);
            }
        }
        
        while(!kruskal.isConnected()){
            List<Set<E>> lista = kruskal.connectedComponents();
            Edge<E> edge = cola.poll();
            for(Set<E> conjunto:lista){
                if(conjunto.contains(edge.getVOrigen().getData()) && !conjunto.contains(edge.getVDestino().getData())){
                    kruskal.addEdge(edge.getVOrigen().getData(),edge.getVDestino().getData(),edge.getPeso());
                }
            }
        }
        return kruskal;
        
    }
    public GraphLA prim(){
        GraphLA<E> prim = new GraphLA<>(directed);
        PriorityQueue<Edge<E>> colaEdges = new PriorityQueue<>((Edge<E> e1, Edge<E> e2)->(e1.getPeso()-e2.getPeso()));
        for(Vertex<E> v :vertexes){
            prim.addVertex(v.getData());
        }
        Vertex<E> vo = vertexes.get(0);
        vo.setVisited(true);
        int visitados=1;
        while(visitados<prim.vertexes.size()){
            for(Edge<E> e: vo.getEdges()){
                if(!e.getVDestino().isVisited())
                    colaEdges.add(e);
            }
            Edge<E> edge = colaEdges.poll();
            edge.getVDestino().setVisited(true);
            prim.addEdge(edge.getVOrigen().getData(),edge.getVDestino().getData(),edge.getPeso());
            vo = edge.getVDestino();
            visitados++;
        }
        cleanVertexes();
        return prim;
    }


    public void dijkstra(E inicio){
        Vertex<E> v = searchVertex(inicio);
        PriorityQueue<Vertex<E>> cola = new PriorityQueue<>((Vertex<E> v1, Vertex<E> v2) -> v1.getDistancia() - v2.getDistancia());
        v.setDistancia(0);
        cola.offer(v);
        while(!cola.isEmpty()){
            v = cola.poll();
            v.setVisited(true);
            for(Edge<E> e : v.getEdges()){
                if(!e.getVDestino().isVisited()){
                    if(v.getDistancia() + 1 < e.getVDestino().getDistancia()){
                        e.getVDestino().setDistancia(v.getDistancia() + 1);
                        e.getVDestino().setAntecesor(v);
                        cola.offer(e.getVDestino());
                    }
                }
            }
        }
    }

    public int menorDistancia(E inicio, E fin){
        if(inicio==null || fin == null) return -1;
        if(inicio.equals(fin)) return 0;
        dijkstra(inicio);
        int x =searchVertex(fin).getDistancia();
        cleanVertexes();
        return x;
    }

    public List<E> caminoMinimo(E inicio, E fin){
        List<E> lista = new LinkedList<>();
        if(inicio==null || fin == null) return lista;
        if(inicio.equals(fin)) return lista;
        dijkstra(inicio);
        Vertex<E> v = searchVertex(fin);
        Stack<Vertex<E>> pila = new Stack<>();
        while(v.getAntecesor()!=null){
            pila.push(v);
            v=v.getAntecesor();
        }
        while(!pila.empty()){
            lista.add(pila.pop().getData());
        }
        return lista;    
    }

}
