/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.arboles;

/**
 *
 * @author 59178
 */
public class NodoBinario<K, V> {

    private K clave;
    private V valor;
    private NodoBinario<K, V> hijoIzquierdo;
    private NodoBinario<K, V> hijoDerecho;

    //Constructor por defecto
    public NodoBinario() {        
    }
    //constructor parametrizado

    public NodoBinario(K clave, V valor) {
        this.clave = clave;
        this.valor = valor;
    }
    
    public K getClave() {
        return clave;
    }
    
    public void setClave(K clave) {
        this.clave = clave;
    }
    
    public V getValor() {
        return valor;
    }
    
    public void setValor(V valor) {
        this.valor = valor;
    }
    
    public NodoBinario<K, V> getHijoIzquierdo() {
        return hijoIzquierdo;
    }
    
    public void setHijoIzquierdo(NodoBinario<K, V> hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }
    
    public NodoBinario<K, V> getHijoDerecho() {
        return hijoDerecho;
    }
    
    public void setHijoDerecho(NodoBinario<K, V> hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    public boolean esVacioHijoIzquierdo() {
        
        return NodoBinario.esNodoVacio(this.getHijoIzquierdo());
        
    }

    public boolean esVacioHijoDerecho() {
        
        return esNodoVacio(this.getHijoDerecho());
        
    }

    public boolean esHoja() {
        return this.esVacioHijoIzquierdo() && this.esVacioHijoDerecho();
    }

    public boolean esNodoCompleto() {
        return !esVacioHijoIzquierdo() && !esVacioHijoDerecho();
    }

    //Metodo compartido(no se puede usar lo generico)
    public static boolean esNodoVacio(NodoBinario nodo) {
        return nodo==null;//forma 1
        //return nodo==nodoVacio();//forma 2
        //return nodo == NodoBinario.nodoVacio();//forma 3
    }

    public static NodoBinario<?, ?> nodoVacio() {
        return null;
    }
    
}
