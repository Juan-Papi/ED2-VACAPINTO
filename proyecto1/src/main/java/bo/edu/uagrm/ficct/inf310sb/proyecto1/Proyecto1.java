/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package bo.edu.uagrm.ficct.inf310sb.proyecto1;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author 59178
 */
public class Proyecto1 {

    public static void main(String[] args) {
        System.out.println("Hello World!");
         PostFija rpn = new PostFija();
       //rpn.setEntrada("((45+88)-49)/8");
       //rpn.setEntrada("((((58+34)*(43+90))+90)-40)/7");
       //rpn.setEntrada("    3  +  4*  2   ");
       //rpn.setEntrada("    (3  *  4- 2)   ");
       //rpn.setEntrada("5+((1+2)*4)-3");
       //rpn.setEntrada("(1-2)^4*(4*(5/((5-3)^2)))");
       //rpn.setEntrada("((2^2^2^0)/100)^3-2-2*4");
       rpn.setEntrada("((2^2^2^0)/100)^3-2*1-2*4");//--
       // rpn.setEntrada("-2+1");
       //rpn.setEntrada("(1-2)*4*(4*(5/((5-3)*2)))");//
       //rpn.setEntrada("4*5");
       //rpn.setEntrada("850/9");
       //rpn.setEntrada("(3+4)*2-4");
       //rpn.setEntrada("8-8*7-3*6");
       //  rpn.setEntrada("-2");
       //System.out.println(rpn.Entrada);
           //Funcionan correctamente!!!
        rpn.convertir();
        System.out.println(rpn.salida); 
//-------------------------------------------------------       
        Queue<String>cola=rpn.colaDeCadenas();
        
        ArbolBinario<String>arbolPrueba=new ArbolBinario<>();
        arbolPrueba.expressionTree(cola);
        
        System.out.println(arbolPrueba.recorridoEnInOrden());
        System.out.println(arbolPrueba.recorridoEnPostOrden());
        //arbolPrueba.calcular();
        System.out.println(arbolPrueba.calcular());
        System.out.println(rpn.toString());
        
    }
}
