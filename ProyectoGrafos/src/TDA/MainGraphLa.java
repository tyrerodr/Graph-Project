package TDA;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bryan
 */
public class MainGraphLa {

    public static void main(String[] args) {
        //Metodos de la tarea
        GraphLA<String> grafo = new GraphLA(false);
        grafo.addVertex("A");
        grafo.addVertex("B");
        grafo.addVertex("C");
        grafo.addVertex("D");
        grafo.addVertex("E");
        grafo.addVertex("F");
        grafo.addVertex("G");
        grafo.addEdge("A", "B", 7);
        grafo.addEdge("A", "D", 5);
        grafo.addEdge("D", "B", 9);
        grafo.addEdge("B", "E", 7);
        grafo.addEdge("B", "C", 8);
        grafo.addEdge("D", "F", 6);
        grafo.addEdge("F", "G", 11);
        grafo.addEdge("D", "E", 15);
        grafo.addEdge("G", "E", 9);
        grafo.addEdge("E", "C", 5);
         
        System.out.println("kruskal");
        System.out.println(grafo.kruskal());
        System.out.println("prim");
        System.out.println(grafo.prim());
        System.out.println("dijkstra");
        grafo.dijkstra("A");
        System.out.println(grafo.menorDistancia("A","C"));
        System.out.println(grafo.caminoMinimo("A","C"));
    }
    
    
}
