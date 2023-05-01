/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.grafos.pesados;

import bo.edu.uagrm.ficct.inf310sb.grafos.Grafo;
import bo.edu.uagrm.ficct.inf310sb.grafos.excepciones.AristaNoExisteExcepcion;
import bo.edu.uagrm.ficct.inf310sb.grafos.excepciones.AristaYaExisteExcepcion;
import bo.edu.uagrm.ficct.inf310sb.grafos.excepciones.NroVerticesInvalidoExcepcion;
import java.util.List;

/**
 *
 * @author 59178
 */
public class pruebasGrafosPesados {

    public static void main(String[] args) throws NroVerticesInvalidoExcepcion, AristaYaExisteExcepcion, AristaNoExisteExcepcion {
        /*GrafoPesado grafo1 = new GrafoPesado(5);
        grafo1.insertarArista2(0, 1, 20.0);
        grafo1.insertarArista2(1, 3, 30.0);
        grafo1.insertarArista2(0, 4, 15);
        grafo1.insertarArista2(0, 2, 5);
        grafo1.insertarArista2(2, 3, 10);
        System.out.println(grafo1);
        int origen = 4;
        int destino = 3;
        //El dijkstra funciona correctamente!
        AlgDijkstra alg = new AlgDijkstra(grafo1, origen,
                destino);
        System.out.println("Vertice de origen: "+origen);
        System.out.println("Vertice de destino: "+destino);
        List<Integer> pred = alg.getPredecesores();
        List<Double> costo = alg.getCosto();
        List<Integer>verticesCamino=alg.verticesATomar(origen, destino);
        System.out.println("Predecesores: " + pred);
        System.out.println("Vertices a tomar: "+verticesCamino);
        System.out.println("Costo del camino minimo: " + costo.get(destino));*/
//----------------------------------GRAFO II-----------------------------------
      
/*GrafoPesado grafo2 = new GrafoPesado(8);
        grafo2.insertarArista2(0, 1, 15.0);
        grafo2.insertarArista2(0, 2, 10.0);
        grafo2.insertarArista2(1, 3, 20);
        grafo2.insertarArista2(2, 3, 40);
        grafo2.insertarArista2(3, 7, 35);
        grafo2.insertarArista2(3, 4, 40); 
        grafo2.insertarArista2(2, 7, 28);
        grafo2.insertarArista2(2, 6, 19);
        grafo2.insertarArista2(6, 7, 39);
        grafo2.insertarArista2(6, 5, 50);
        grafo2.insertarArista2(4, 5, 25);
        System.out.println(grafo2);
        //El prim funciona correctamente!(cuaderno)
        AlgPrim alg=new AlgPrim(grafo2);
        System.out.println( "Resultado:\n"+alg.arbolGrafoDeMinimoCosto());*/
//-----------------------------------GRAFO III----------------------------------
   GrafoPesado grafo3 = new GrafoPesado(6);
        grafo3.insertarArista2(0, 1, 10);
        grafo3.insertarArista2(1, 3, 40);
        grafo3.insertarArista2(3, 4, 30);
        grafo3.insertarArista2(4, 5, 5);
        grafo3.insertarArista2(2, 5, 15);
        grafo3.insertarArista2(0, 2, 19); 
        grafo3.insertarArista2(3, 2, 35);
        grafo3.insertarArista2(4, 2, 28);
        System.out.println(grafo3);
        //El kruskal funciona correctamente!(cuaderno)
        AlgKruskal alg=new AlgKruskal(grafo3);
        System.out.println("Resultado: \n"+alg.ejecutarAlgKruskal());
        
    }
}
