/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.arboles;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author 59178
 */
public class NodoMVias<K, V> {

    private List<K> listaDeClaves;
    private List<V> listaDeValores;
    private List<NodoMVias<K, V>> listaDeHijos;

    public NodoMVias(int orden) {
        listaDeClaves = new LinkedList<>();
        listaDeValores = new LinkedList<>();
        listaDeHijos = new LinkedList<>();
//si lo vemos al arbol binario como un nodo MVias 
//sería orden 2 -> un dato y dos hijos
        for (int i = 0; i < orden-1; i++) {
            listaDeHijos.add(NodoMVias.nodoVacio());
            listaDeClaves.add((K) NodoMVias.datoVacio());
            listaDeValores.add((V) NodoMVias.datoVacio());
        }
        listaDeHijos.add(null);
    }

    public NodoMVias(int orden, K primerClave, V primerValor) {
        //invoca a un constructor de la misma clase
        this(orden);//Llamá a otro constructor de la clase solo cuando esta
        //en la primera linea del metodo o constructor
        this.listaDeClaves.set(0, primerClave);
        this.listaDeValores.set(0, primerValor);
    }

    public static NodoMVias nodoVacio() {
        return null;
    }

    public static Object datoVacio() {
        return null;
    }

    /**
     * Retorna la clave de la posicion dada. Precondición: El parámetro posicion
     * indica una posicion válida en la lista de claves.
     *
     * @param posicion
     * @return
     */
    /**
     * Precondición: Asumimos una posicion válida.
     *
     * @param posicion
     * @param clave
     */
    public void setClave(int posicion, K clave) {
        this.listaDeClaves.set(posicion, clave);
    }

    public K getClave(int posicion) {
        return this.listaDeClaves.get(posicion);
    }

    /**
     * Precondicion:Asumimos una posicion válida.
     *
     * @param posicion
     * @param valor
     */
    public void setValor(int posicion, V valor) {
        this.listaDeValores.set(posicion, valor);
    }

    /**
     * Retorna el valor de la posicion dada. Precondicion: El parámetro posicion
     * indica una posicion válida en la lista de valores.
     *
     * @param posicion
     * @return
     */
    public V getValor(int posicion) {
        return this.listaDeValores.get(posicion);
    }

    public void setHijo(int posicion, NodoMVias<K, V> nodo) {
        this.listaDeHijos.set(posicion, nodo);
    }

    public NodoMVias<K, V> getHijo(int posicion) {
        return this.listaDeHijos.get(posicion);
    }

    public static boolean esNodoVacio(NodoMVias nodo) {
        return (nodo == NodoMVias.nodoVacio());
    }

    public boolean esClaveVacia(int posicion) {
        return (this.listaDeClaves.get(posicion) == NodoMVias.datoVacio());
    }

    public boolean esHijoVacio(int posicion) {
        return (this.listaDeHijos.get(posicion) == NodoMVias.nodoVacio());
    }

    public boolean esHoja() {
        for (int i = 0; i < this.listaDeHijos.size(); i++) {
            if (!esHijoVacio(i)) {
                return false;
            }
        }
        return true;
    }

    public boolean estanClavesLlenas() {
        for (int i = 0; i < this.listaDeClaves.size(); i++) {
            if (esClaveVacia(i)) {
                return false;
            }
        }
        return true;
    }

    public int cantidadDeClavesNoVacias() {
        int cant = 0;
        for (int i = 0; i < this.listaDeClaves.size(); i++) {
            if (!esClaveVacia(i)) {
                cant++;
            }
        }
        return cant;
    }
    public int cantidadDeHijosVacios(){
         int cant = 0;
        for (int i = 0; i < this.listaDeHijos.size(); i++) {
            if (esHijoVacio(i)) {
                cant++;
            }
        }
        return cant;
    }
     public int cantidadDeHijosNoVacios(){
         int cant = 0;
        for (int i = 0; i < this.listaDeHijos.size(); i++) {
            if (!esHijoVacio(i)) {
                cant++;
            }
        }
        return cant;
    }
    

}
