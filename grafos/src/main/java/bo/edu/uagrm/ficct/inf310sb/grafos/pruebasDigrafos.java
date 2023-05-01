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
public class pruebasDigrafos {

    public static void main(String[] args) throws NroVerticesInvalidoExcepcion, AristaYaExisteExcepcion, AristaNoExisteExcepcion {
        /*DiGrafo digrafo1 = new DiGrafo(6);
        digrafo1.insertarArista(5, 0);
        digrafo1.insertarArista(1, 3);
        digrafo1.insertarArista(1, 5);
        digrafo1.insertarArista(4, 3);
        digrafo1.insertarArista(2, 5);
        // digrafo1.eliminarArista(5, 0);
        digrafo1.insertarVertice();
        digrafo1.insertarVertice();
        digrafo1.insertarArista(7, 6);
        System.out.println(digrafo1);

        System.out.println("Cantidad de islas: " + digrafo1.nroDeIslas());
        System.out.println("Es fuertemente conexo 1: " + digrafo1.esFuertementeConexo1());
        System.out.println("Es fuertemente conexo 2:" + digrafo1.esFuertementeConexo2());
        System.out.println("Es debilmente conexo 1: " + digrafo1.esDebilmenteConexo1());
        System.out.println("Es debilmente conexo 2: " + digrafo1.esDebilmenteConexo2());
        System.out.println("Cantidad de vertices: " + digrafo1.cantidadDeVertices());
        System.out.println("Cantidad de aristas: " + digrafo1.cantidadDeAristas());
        //    /*----------------------Para ver el grado de un vertice-------------------------
        int posVertice = 5;
        System.out.println(""
                + "Grado de entrada del vertice "
                + posVertice + ": "
                + digrafo1.gradoDeEntradaDeVertice(posVertice));

        System.out.println(""
                + "Grado de salida del vertice "
                + posVertice + ": "
                + digrafo1.gradoDeSalidaDeVertice(posVertice));*/
//------------------------------------DIGRAFO 2-----------------------------------*/
        /*   System.out.println("--------------DIGRAFO 2-------------");
        DiGrafo digrafo2 = new DiGrafo(3);
        digrafo2.insertarArista(0, 2);
        digrafo2.insertarArista(2, 1);
        digrafo2.insertarArista(2, 0);
        digrafo2.insertarArista(1, 2);
        System.out.println(digrafo2);
        System.out.println("Es fuertemente conexo 1: " + digrafo2.esFuertementeConexo1());
       // System.out.println("Es fuertemente conexo 2:" + digrafo2.esFuertementeConexo2());
       //no funciona entendimos mal al comienzo
        System.out.println("Es debilmente conexo 1: " + digrafo2.esDebilmenteConexo1());
        System.out.println("Es debilmente conexo 2: " + digrafo2.esDebilmenteConexo2());
         */
        //-------------------------------DIGRAFO 3-----------------------------------
       /* //Comprobado lo que hicimos en cuaderno
        DiGrafo digrafo3 = new DiGrafo(6);
        digrafo3.insertarArista(0, 5);
        digrafo3.insertarArista(0, 2);
        digrafo3.insertarArista(2, 3);
        digrafo3.insertarArista(4, 2);
        digrafo3.insertarArista(1, 3);
        digrafo3.insertarArista(3, 0);//Comprobado el tiene ciclos
        //digrafo3.eliminarArista(0, 2);
        System.out.println(digrafo3);
        DFS rec=new DFS(digrafo3, 0);
        List<Integer>recorrido=(List<Integer>) rec.getRecorrido();
        System.out.println("Recorrido: "+recorrido);
        System.out.println("Tiene ciclos: "+digrafo3.tieneCiclos());*/
       //---------------------------DIGRAFO 4--------------------------
        DiGrafo digrafo4 = new DiGrafo(4);
        digrafo4.insertarArista(0, 3);
        digrafo4.insertarArista(3, 0);
        digrafo4.insertarArista(1, 0);
        digrafo4.insertarArista(1, 2);
        digrafo4.insertarArista(3, 2);
        System.out.println("----------------Grafo Original-----------");
        System.out.println(digrafo4);
        System.out.println("----------------Grafo Resultante-----------");
        System.out.println(digrafo4.invertirAristas());

    }
}
