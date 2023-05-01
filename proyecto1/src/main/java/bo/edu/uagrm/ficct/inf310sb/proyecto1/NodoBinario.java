/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.proyecto1;

/**
 *
 * @author 59178
 */
public class NodoBinario<K> {

    private K clave;
   
    private NodoBinario<K> hijoIzquierdo;
    private NodoBinario<K> hijoDerecho;

    //Constructor por defecto
    public NodoBinario() {        
    }
    //constructor parametrizado

    public NodoBinario(K clave) {
        this.clave = clave;
        
    }
    
    public K getClave() {
        return clave;
    }
    
    public void setClave(K clave) {
        this.clave = clave;
    }
    
   
    
    
    public NodoBinario<K> getHijoIzquierdo() {
        return hijoIzquierdo;
    }
    
    public void setHijoIzquierdo(NodoBinario<K> hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }
    
    public NodoBinario<K> getHijoDerecho() {
        return hijoDerecho;
    }
    
    public void setHijoDerecho(NodoBinario<K> hijoDerecho) {
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

    public static NodoBinario<?> nodoVacio() {
        return null;
    }
    
}
