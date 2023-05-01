/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.arboles;

/**
 *
 * @author 59178
 */
public class AVL<K extends Comparable<K>, V> extends ArbolBinarioBusqueda<K, V> {

    public static final byte DIFERENCIA_MAXIMA = 1;
    //Nota: super hace referencia a la clase padre de esta clase.
     /**
      * Insertado al estilo AVL, este método trata de mantener el árbol 
      * balanceado.
      * @param claveAInsertar
      * @param valorAInsertar 
      */
    public void insertarAVL(K claveAInsertar, V valorAInsertar) {
        if (claveAInsertar == null) {
            throw new IllegalArgumentException("La clave no debe ser nula");
        }
        if (valorAInsertar == null) {
            throw new IllegalArgumentException("El valor no debe ser nulo");
        }
        /*if(super.contiene(claveAInsertar)){
          throw new RuntimeException("Clave ya existe!");
      }*/
        super.raiz = insertar(super.raiz, claveAInsertar, valorAInsertar);
    }

    private NodoBinario<K, V> insertar(NodoBinario<K, V> nodoActual, K claveAInsertar, V valorAInsertar) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            NodoBinario<K, V> nuevoNodo = new NodoBinario<>(claveAInsertar, valorAInsertar);
            return nuevoNodo;
        }
        K claveActual = nodoActual.getClave();
        if (claveAInsertar.compareTo(claveActual) > 0) {
            NodoBinario<K, V> supuestoNuevoHijoDerecho
                    = insertar(nodoActual.getHijoDerecho(), claveAInsertar, valorAInsertar);
            nodoActual.setHijoDerecho(supuestoNuevoHijoDerecho);
            return this.balancear(nodoActual);
        }
        if(claveAInsertar.compareTo(claveActual) < 0){
          NodoBinario<K, V> supuestoNuevoHijoIzquierdo
                    = insertar(nodoActual.getHijoIzquierdo(), claveAInsertar, valorAInsertar);
            nodoActual.setHijoIzquierdo(supuestoNuevoHijoIzquierdo);
            return this.balancear(nodoActual);  
        }
        //Sí llegó hasta aquí quiere decir que en el nodoActual está la clave
        //a insertar y que las claves son igual por tanto solo modifico el valor
        nodoActual.setValor(valorAInsertar);
        return nodoActual;
    }

    private NodoBinario<K, V> balancear(NodoBinario<K, V> nodoActual) {
        int alturaRamaIzq = altura(nodoActual.getHijoIzquierdo());
        int alturaRamaDer = altura(nodoActual.getHijoDerecho());
        int diferencia = alturaRamaIzq - alturaRamaDer;
        if (diferencia > DIFERENCIA_MAXIMA) {
            //Si hay que balancear
            NodoBinario<K, V> hijoIzquierdo = nodoActual.getHijoIzquierdo();
            alturaRamaIzq = altura(hijoIzquierdo.getHijoIzquierdo());
            alturaRamaDer = altura(hijoIzquierdo.getHijoDerecho());
            if (alturaRamaDer > alturaRamaIzq) {
                return this.rotacionDobleADerecha(nodoActual);
            } else {
                return this.rotacionSimpleADerecha(nodoActual);
            }
        } else if (diferencia < -DIFERENCIA_MAXIMA) {
            //Si hay que balancear
            NodoBinario<K, V> hijoDerecho = nodoActual.getHijoDerecho();
            alturaRamaIzq = altura(hijoDerecho.getHijoIzquierdo());
            alturaRamaDer = altura(hijoDerecho.getHijoDerecho());
            if (alturaRamaIzq > alturaRamaDer) {
                return this.rotacionDobleAIzquierda(nodoActual);
            } else {
                return this.rotacionSimpleAIzquierda(nodoActual);
            }
        }
        return nodoActual;

    }

    private NodoBinario<K, V> rotacionSimpleADerecha(NodoBinario<K, V> nodoActual) {
        NodoBinario<K, V> nodoQueRota = nodoActual.getHijoIzquierdo();
        nodoActual.setHijoIzquierdo(nodoQueRota.getHijoDerecho());
        nodoQueRota.setHijoDerecho(nodoActual);
        return nodoQueRota;
    }

    private NodoBinario<K, V> rotacionDobleADerecha(NodoBinario<K, V> nodoActual) {
        NodoBinario<K, V> nodoQueRotaAIzquierda
                = rotacionSimpleAIzquierda(nodoActual.getHijoIzquierdo());
        nodoActual.setHijoIzquierdo(nodoQueRotaAIzquierda);
        return this.rotacionSimpleADerecha(nodoActual);
    }

    private NodoBinario<K, V> rotacionSimpleAIzquierda(NodoBinario<K, V> nodoActual) {
        NodoBinario<K, V> nodoQueRota = nodoActual.getHijoDerecho();
        nodoActual.setHijoDerecho(nodoQueRota.getHijoIzquierdo());
        nodoQueRota.setHijoIzquierdo(nodoActual);
        return nodoQueRota;
    }

    private NodoBinario<K, V> rotacionDobleAIzquierda(NodoBinario<K, V> nodoActual) {
        NodoBinario<K, V> nodoQueRotaADerecha
                = rotacionSimpleADerecha(nodoActual.getHijoDerecho());
        nodoActual.setHijoDerecho(nodoQueRotaADerecha);
        return this.rotacionSimpleAIzquierda(nodoActual);
    }
//------------------------------------------------------------------------------
    public V eliminarAVL(K claveAEliminar) {
        if (claveAEliminar == null) {
            throw new IllegalArgumentException(""
                    + "La clave no puede ser nula!");
        }
        V valorAEliminar = buscar(claveAEliminar);
        if (valorAEliminar == null) {
            throw new IllegalArgumentException(""
                    + "La clave no existe en el arbol!");
        }
        /*En este caso entra por aqui afirmando que la claveAEliminar
        si existe en el arbol*/
        eliminar(this.raiz, claveAEliminar);
        return valorAEliminar;

    }

    private NodoBinario<K, V> eliminar(NodoBinario<K, V> nodoActual, K claveAEliminar) {
        K claveDelNodoActual = nodoActual.getClave();

        if (claveAEliminar.compareTo(claveDelNodoActual) < 0) {
            NodoBinario<K, V> supuestoNuevoHijoIzquierdo
                    = eliminar(nodoActual.getHijoIzquierdo(), claveAEliminar);
            nodoActual.setHijoIzquierdo(supuestoNuevoHijoIzquierdo);
            return this.balancear(nodoActual);
        }
        if (claveAEliminar.compareTo(claveDelNodoActual) > 0) {
            NodoBinario<K, V> supuestoNuevoHijoDerecho
                    = eliminar(nodoActual.getHijoDerecho(), claveAEliminar);
            nodoActual.setHijoDerecho(supuestoNuevoHijoDerecho);
            return this.balancear(nodoActual);
        }

        /*A partir de aqui claveActual==claveAEliminar
        ahora toca analizar para cada caso*/
        // CASO I
        if (nodoActual.esHoja()) {
            return null;
        }
        //CASO II opcion a
        if (!nodoActual.esVacioHijoIzquierdo() && nodoActual.esVacioHijoDerecho()) {
            return nodoActual.getHijoIzquierdo();
        }
        //CASO II opcion b
        if (nodoActual.esVacioHijoIzquierdo() && !nodoActual.esVacioHijoDerecho()) {
            return nodoActual.getHijoDerecho();
        }

        //CASO III
        //!nodoActual.esVacioHijoIzquierdo() && !nodoActual.esVacioHijoDerecho
        NodoBinario<K, V> nodoAyuda = ayuda(nodoActual.getHijoDerecho());
        nodoAyuda.setHijoIzquierdo(nodoActual.getHijoIzquierdo());
        return nodoActual.getHijoDerecho();

    }
     private NodoBinario<K, V> ayuda(NodoBinario<K, V> nodoAyuda) {
        NodoBinario<K, V> nodoAnterior = new NodoBinario<>();
        while (!NodoBinario.esNodoVacio(nodoAyuda)) {
            nodoAnterior = nodoAyuda;
            nodoAyuda = nodoAyuda.getHijoIzquierdo();
        }

        return nodoAnterior;
    }
}
