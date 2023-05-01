/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.grafos;

import bo.edu.uagrm.ficct.inf310sb.grafos.excepciones.AristaNoExisteExcepcion;
import bo.edu.uagrm.ficct.inf310sb.grafos.excepciones.AristaYaExisteExcepcion;
import bo.edu.uagrm.ficct.inf310sb.grafos.excepciones.NroVerticesInvalidoExcepcion;
import java.util.List;

/**
 *
 * @author 59178
 */
public class pruebasGrafos {

    public static void main(String[] args) throws NroVerticesInvalidoExcepcion, AristaYaExisteExcepcion, AristaNoExisteExcepcion {
     /*   Grafo grafo1 = new Grafo(5);
        grafo1.insertarArista(1, 3);
        grafo1.eliminarVertice(0);
        grafo1.insertarArista(1, 3);
        grafo1.eliminarArista(1, 3);
        grafo1.insertarArista(1, 0);
        grafo1.insertarArista(2, 3);
        System.out.println(grafo1);
        System.out.println("Cantidad de vertices: "+grafo1.cantidadDeVertices());
        System.out.println("Cantidad de Aristas: "+grafo1.cantidadDeAristas());
        System.out.println("Es conexo : "+grafo1.esConexo2());
        System.out.println("Costo camino: "+grafo1.costoCamino(
                0, 1));
        System.out.println("Costo camino: "+grafo1.costoCamino(2, 1));*/
/*----------------------Para ver el grado de un vertice-------------------------
        int posVertice = 1;
        System.out.println(""
                + "Grado del vertice "
                + posVertice + ": "
                + grafo1.gradoDeVertice(posVertice));
//------------------------------------------------------------------------------*/
     /*   Grafo grafo2=new Grafo(3);
        grafo2.insertarArista(0, 2);
        grafo2.insertarArista(2, 1);
        System.out.println(grafo2);
        System.out.println("es conexo: "+grafo2.esConexo2());*/
//-------------------------------GRAFO 3---------------------------------------
    /*Grafo grafo3=new Grafo(7);
      grafo3.insertarArista(0, 3);
      grafo3.insertarArista(0, 4);
      grafo3.insertarArista(3, 1);
      
      grafo3.insertarArista(2, 6);
      grafo3.insertarArista(2, 5);
        System.out.println(grafo3);
        System.out.println("Costo Camino : "+grafo3.costoCamino(
                0, 1));
        System.out.println("Costo Camino : "+grafo3.costoCamino(
                0, 5));*/
//----------------------------GRAFO 4------------------------------------------
   Grafo grafo4=new Grafo(10);
      grafo4.insertarArista(0, 1);
      grafo4.insertarArista(1, 3);
      grafo4.insertarArista(1, 2);      
      grafo4.insertarArista(2, 4);
      grafo4.insertarArista(2, 5);
      grafo4.insertarArista(0, 4);
      grafo4.insertarArista(6, 7);
      grafo4.insertarArista(6, 8);
      grafo4.insertarArista(7, 8);
     // DFS rec=new DFS(grafo4, 0);
      //  System.out.println((List<Integer>)rec.getRecorrido());
       // System.out.println("Tiene ciclos: "+grafo4.tieneCiclos2());
        System.out.println(grafo4);
        System.out.println(grafo4.imprimirIslas());
    }
}
