/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.arboles;

import bo.edu.uagrm.ficct.inf310sb.arboles.excepciones.ExcepcionOrdenInvalido;

/**
 *
 * @author 59178
 */
public class PruebaMVias {

    public static void main(String[] args) throws ExcepcionOrdenInvalido {
        ArbolMViasBusqueda<Integer, String> tree1
                = new ArbolMViasBusqueda<>(5);
//---------------------------TREE 1---------------------------------------------
        /* tree1.insertar(20, "a");
         tree1.insertar(60, "c");
        tree1.insertar(80, "d");
        tree1.insertar(40, "b");
       
       tree1.insertar(22, "e");
         tree1.insertar(23, "f");
        tree1.insertar(24, "g");
        tree1.insertar(25, "h");
        
        tree1.insertar(21, "i");
        tree1.insertar(30, "j");*/
//------------------------------------TREE 2----------------------------------------
       tree1.insertar(20, "a");
         tree1.insertar(50, "c");
        tree1.insertar(80, "d");
        tree1.insertar(100, "b");
       
       tree1.insertar(60, "e");
         tree1.insertar(65, "f");
        tree1.insertar(70, "g");
//------------------------------------------------------------------------------
        System.out.println("TREE1:");
        System.out.println(
                "RecorridoPorNiveles: " + tree1.recorridoPorNiveles());
        int x = 50;
        System.out.println("Eliminando el dato: " + x);
        tree1.eliminar(x);
        System.out.println(
                "RecorridoPorNiveles: " + tree1.recorridoPorNiveles());

    }
}
