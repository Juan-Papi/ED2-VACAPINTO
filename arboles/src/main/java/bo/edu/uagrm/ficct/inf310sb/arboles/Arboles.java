/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.arboles;

/**
 *
 * @author 59178
 */
public class Arboles {

    public static void main(String[] args) {

        System.out.println("Hello World!");
        ArbolBinarioBusqueda<Integer, String> arbol1 = new ArbolBinarioBusqueda<>();

//------------------------------ Arbol I ---------------------------------------

        /*p1.insertar(10, "a");
        arbol1.insertar(7,"b");
        arbol1.insertar(25, "c");
        arbol1.insertar(3, "g");
        arbol1.insertar(8, "q");
        arbol1.insertar(1, "o");
        arbol1.insertar(4, "l");
        arbol1.insertar(50, "s");
        arbol1.insertar(66, "n");
        arbol1.insertar(64, "t");*/
 /*                                                                            */
//------------------------------Arbol II ---------------------------------------
/*                                                                            */
        /*arbol1.insertar(10, "h");
        arbol1.insertar(20, "j");
        arbol1.insertar(15, "h");
        arbol1.insertar(12, "ñ");
        arbol1.insertar(16, "m");
        arbol1.insertar(8, "n");
        arbol1.insertar(6, "y");
        arbol1.insertar(9, "j");*/
        //------------------------------Arbol III ---------------------------------------
/*                                                                            */
  arbol1.insertar(20, "h");
        arbol1.insertar(15, "j");
        arbol1.insertar(30, "h");
        arbol1.insertar(25, "ñ");
        arbol1.insertar(22, "m");
        arbol1.insertar(40, "n");
        arbol1.insertar(35, "y");
        arbol1.insertar(32, "w");
        arbol1.insertar(36, "s");
        arbol1.insertar(50, "t");
 /*                                                                            */
//------------------------------ARBOL IV----------------------------------------
       /* arbol1.insertar(10, "h");
        arbol1.insertar(15, "j");
        arbol1.insertar(13, "h");
        arbol1.insertar(11, "ñ");
        arbol1.insertar(14, "m");
        arbol1.insertar(18, "n");
        arbol1.insertar(16, "y");
        arbol1.insertar(20, "j"); 
        arbol1.insertar(5,"jj");
        arbol1.insertar(2, "kk");
        arbol1.insertar(3,"jj");
        arbol1.insertar(8, "kk");*/
//-------------------------------Insertar Recursivo a mi manera-----------------
/*                                                                            */
       // arbol1.insertarV2(10, "h");
        /*arbol1.insertarV2(20, "j");
        arbol1.insertarV2(15, "h");
        arbol1.insertarV2(12, "ñ");
        arbol1.insertarV2(16, "m");
        arbol1.insertarV2(8, "n");
        arbol1.insertarV2(6, "y");
        arbol1.insertarV2(9, "j");*/
        /*                                                                            */
//------------------------Probando el insertar a mi manera ---------------------
/*                                                                            */
        /*arbol1.insertarMiManera(10, "h");
        arbol1.insertarMiManera(20, "j");
        arbol1.insertarMiManera(15, "h");
        arbol1.insertarMiManera(12, "ñ");
        arbol1.insertarMiManera(16, "m");
        arbol1.insertarMiManera(8, "n");
        arbol1.insertarMiManera(6, "y");
        arbol1.insertarMiManera(9, "j");*/
        // arbol1.insertarMiManera(5, "q");

//-------------------------- Mostrados Recorridos ------------------------------
/*                                                                            */
        //System.out.println(arbol1.recorridoEnPostOrden());//Right!!
        //System.out.println(arbol1.recorridoEnPostOrdenIterativo());//Right!!
        System.out.println("Recorrido en InOrden: "
                + arbol1.recorridoEnInOrden());//Right!!
        //System.out.println(arbol1.recorridoEnInOrdenV2());//Right!!
        //System.out.println(arbol1.recorridoEnPreOrden());//Right!!
        //System.out.println(arbol1.recorridoEnPreOrdenV2());
        //System.out.println(arbol1.recorridoPorNiveles());//Right!!
/*                                                                            */
//------------------- Prueba de funcionamiento de los metodos-------------------
/*                                                                            */
        //System.out.println(arbol1.size());//Right!!
        //System.out.println(arbol1.sizeV2());//Right!!
        //System.out.println(arbol1.nivel());//Right!!
        //System.out.println(arbol1.minimo());//Right!!
        // System.out.println(arbol1.buscar(15));//Right!!
        //System.out.println(arbol1.maximo());//Right!!
        //System.out.println(arbol1.altura());//Right!!
        //System.out.println(arbol1.contiene(50));//Right!!
        /* int x=arbol1.contarNodoConAmbosHijos();
        System.out.println(x);//Right!!*/
 /*int x=arbol1.contarNodosConAmbosHijosPorNiveles();
        System.out.println(x);//Right!!*/
        //System.out.println(arbol1.mostrarNivel(2).toString());//Right!!
        //System.out.println(arbol1.toString());//ojo
        //System.out.println(arbol1.contarNodoConAmbosHijos());//Right!!
        // System.out.println(arbol1.buscar(15));
/*                                                                            */
        //Probando el eliminar CASO I Right!!
        /* String x=arbol1.eliminar(6);
       System.out.println(x);
        System.out.println("Recorrido PostOrden luego de eliminar:");
       System.out.println(arbol1.recorridoEnPostOrdenIterativo());*/
 /*                                                                           */
        //Probando el eliminar CASO II Right!!
        /*String x = arbol1.eliminar(8);
        System.out.println(x);
        System.out.println("Recorrido PostOrden luego de eliminar:");
        System.out.println(arbol1.recorridoEnPostOrdenIterativo());*/
 /*                                                                 */
        //Probando el eliminar CASO III Right!!
        /* String x=arbol1.eliminar(15);
       System.out.println(x);
        System.out.println("Recorrido PostOrden luego de eliminar:");
       System.out.println(arbol1.recorridoEnPostOrdenIterativo());*/
 /*                                                                           */
       /* System.out.println("Cantidad" + " de hijos derechos del arbol: "
                + arbol1.cantHijosDerechosDelArbol());*///Right!!
        //System.out.println(arbol1.cantHijosDerechosV2());//Right!!

        //Probando el eliminar Iterativo a mi manera  right!!
        /* arbol1.eliminarIterativoMiManera(6);
        System.out.println(arbol1.recorridoEnPostOrden());
        System.out.println(arbol1.eliminarIterativoMiManera(30));
        //System.out.println(arbol1.eliminarIterativoMiManera(15));
        //System.out.println(arbol1.eliminarIterativoMiManera(8));
        System.out.println(arbol1.recorridoEnPostOrden());*/
//------------------------------------------------------------------------------ 
  //ARBOLBINARIO NO GENÉRICO 
     /* ArbolBinarioBusquedaEnteroCadena  arbol=
              new ArbolBinarioBusquedaEnteroCadena();
      arbol.insertar(8,"i");
      arbol.insertar(9,"j");
      arbol.insertar(5,"k");
        System.out.println("Tamaño: "+ arbol.size());
        System.out.println("Altura: "+ arbol.altura());
        System.out.println("RecorridoInOrden: "
                +arbol.recorridoEnInOrden());*/
//------------------------------------------------------------------------------
/*                                                                            */
System.out.println("Nodos con ambos hijos: "
        + arbol1.contarNodosConAmbosHijos(1));
System.out.println("Claves del nivel N: "
        +arbol1.recPorNivel(1));
 System.out.println("Claves del medio del árbol:"
         + arbol1.clavesDelMedioDelosNivelesDelArbol());
    }
}
