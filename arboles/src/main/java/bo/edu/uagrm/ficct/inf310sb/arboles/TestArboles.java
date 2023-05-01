/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.arboles;

import java.util.Scanner;

/**
 *
 * @author 59178
 */
public class TestArboles {

    public static void main(String[] args) {

        IArbolBusqueda<Integer, String> arbolPrueba;
        Scanner entrada = new Scanner(System.in);
        System.out.print("Elija un tipo de árbol(ABB):");
        String tipoArbol = entrada.next();
        tipoArbol = tipoArbol.toUpperCase();//Convierte a mayúscula

        switch (tipoArbol) {
            case "ABB":
                arbolPrueba = new ArbolBinarioBusqueda<Integer, String>();
                break;
            default:
                System.out.println(
                        "Tipo de árbol invalido, eligiendo arbolBinarioBusqueda\n");
                arbolPrueba = new ArbolBinarioBusqueda<Integer, String>();
        }

//------------------------------Arbol I ----------------------------------------
/*                                                                            */
        arbolPrueba.insertar(10, "h");
        arbolPrueba.insertar(20, "j");
        arbolPrueba.insertar(15, "h");
        arbolPrueba.insertar(12, "ñ");
        arbolPrueba.insertar(16, "m");
        arbolPrueba.insertar(8, "n");
        arbolPrueba.insertar(6, "y");
        arbolPrueba.insertar(9, "j");
        /*                                                                            */
//------------------------------------------------------------------------------
        System.out.println(arbolPrueba);

    }
}
