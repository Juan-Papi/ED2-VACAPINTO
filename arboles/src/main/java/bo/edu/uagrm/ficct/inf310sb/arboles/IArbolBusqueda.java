/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.arboles;

import java.util.List;

/**
 *
 * @author 59178
 */
public interface IArbolBusqueda<K extends Comparable<K>,V> {
   //todos los arboles minimamente deben tener estos metodos
    void vaciar();
    boolean esArbolVacio();
    int size();
    int altura();
    int nivel();
    K minimo();
    K maximo();
    void insertar(K clave,V valor);
    V eliminar(K clave);
    boolean contiene(K clave);
    V buscar(K clave);
    List<K> recorridoEnInOrden();
    List<K> recorridoEnPreOrden();
    List<K> recorridoEnPostOrden();
    List<K> recorridoPorNiveles(); 
    //todos los arboles minimamente deben tener estos metodos
    
}
