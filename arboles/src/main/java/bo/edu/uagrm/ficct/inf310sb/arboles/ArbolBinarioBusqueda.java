/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.arboles;

import com.sun.jdi.event.StepEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author 59178
 */
public class ArbolBinarioBusqueda<K extends Comparable<K>, V> implements
        IArbolBusqueda<K, V> {

    //Campos
    protected NodoBinario<K, V> raiz;

//------------------------------------------------------------------------------
    @Override
    public void vaciar() {
        this.raiz = (NodoBinario<K, V>) NodoBinario.nodoVacio();
    }
//------------------------------------------------------------------------------

    @Override
    public boolean esArbolVacio() {

        //return (this.raiz == null);//forma 1
        return NodoBinario.esNodoVacio(this.raiz);//forma 2

    }
//------------------------------------------------------------------------------

    @Override
    public int size() {//VERIFIED

        if (this.esArbolVacio()) {
            return 0;
        }
        int cantidadDeNodos = 0;
        Stack<NodoBinario<K, V>> pilaDeNodos = new Stack<>();
        pilaDeNodos.push(this.raiz);

        while (!pilaDeNodos.isEmpty()) {

            NodoBinario<K, V> nodoActual = pilaDeNodos.pop();
            cantidadDeNodos++;

            if (!nodoActual.esVacioHijoDerecho()) {
                pilaDeNodos.push(nodoActual.getHijoDerecho());
            }

            if (!nodoActual.esVacioHijoIzquierdo()) {
                pilaDeNodos.push(nodoActual.getHijoIzquierdo());
            }

        }
        return cantidadDeNodos;
    }
//------------------------------------------------------------------------------

    public int sizeV2() {//VERIFIED
        return sizeV2(this.raiz);
        //llamamos al metodo amigo
    }

    private int sizeV2(NodoBinario<K, V> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        int tamañoPorIzq = sizeV2(nodoActual.getHijoIzquierdo());
        int tamañoPorDer = sizeV2(nodoActual.getHijoDerecho());
        return tamañoPorDer + tamañoPorIzq + 1;
    }

//------------------------------------------------------------------------------  
    @Override
    public int altura() {//VERIFIED
        return altura(this.raiz);
    }

    public int altura(NodoBinario<K, V> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        int alturaPorIzq = altura(nodoActual.getHijoIzquierdo());
        int alturaPorDer = altura(nodoActual.getHijoDerecho());
        return alturaPorIzq > alturaPorDer ? alturaPorIzq + 1 : alturaPorDer + 1;
    }
//------------------------------------------------------------------------------

    @Override
    public int nivel() {//VERIFIED
        return nivel(this.raiz);
    }

    private int nivel(NodoBinario<K, V> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return -1;
        }
        int nivelIzq = nivel(nodoActual.getHijoIzquierdo());
        int nivelDer = nivel(nodoActual.getHijoDerecho());
        if (nivelDer > nivelIzq) {
            return nivelDer + 1;
        } else {
            return nivelIzq + 1;
        }
    }
//------------------------------------------------------------------------------
    //El elemento mas menor

    @Override
    public K minimo() {//VERIFIED
        if (this.esArbolVacio()) {
            return null;
        }

        NodoBinario<K, V> nodoActual = this.raiz;
        NodoBinario<K, V> nodoAnterior
                = (NodoBinario<K, V>) NodoBinario.nodoVacio();
        while (!NodoBinario.esNodoVacio(nodoActual)) {
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.getHijoIzquierdo();
        }
        return nodoAnterior.getClave();
    }
//------------------------------------------------------------------------------
    //El elemento mas mayor

    @Override
    public K maximo() {//VERIFIED
        if (this.esArbolVacio()) {
            return null;
        }

        NodoBinario<K, V> nodoActual = this.raiz;
        NodoBinario<K, V> nodoAnterior
                = (NodoBinario<K, V>) NodoBinario.nodoVacio();
        while (!NodoBinario.esNodoVacio(nodoActual)) {
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.getHijoDerecho();
        }
        return nodoAnterior.getClave();
    }
//------------------------------------------------------------------------------

    @Override
    public void insertar(K claveAInsertar, V valorAInsertar) {//VERIFIED

        if (claveAInsertar == null) {
            throw new IllegalArgumentException("La clave no puede ser nula");
        }
        if (valorAInsertar == null) {
            throw new IllegalArgumentException("El valor no puede ser nulo");
        }

        if (this.esArbolVacio()) {
            this.raiz
                    = new NodoBinario<>(claveAInsertar,
                            valorAInsertar);
            return;
        }

        //NodoBinario<K, V> nodoActual 
        //= new NodoBinario<>(claveAInsertar, valorAInsertar);
        NodoBinario<K, V> nodoActual = this.raiz;
        NodoBinario<K, V> nodoAnterior
                = (NodoBinario<K, V>) NodoBinario.nodoVacio();
        while (!NodoBinario.esNodoVacio(nodoActual)) {
            K claveActual = nodoActual.getClave();
            nodoAnterior = nodoActual;
            if (claveAInsertar.compareTo(claveActual) < 0) {
                nodoActual = nodoActual.getHijoIzquierdo();
            } else if (claveAInsertar.compareTo(claveActual) > 0) {
                nodoActual = nodoActual.getHijoDerecho();
            } else {//La clave existe, entonces reemplazo su valor
                nodoActual.setValor(valorAInsertar);
                return;
            }
        }

        //si llegó hasta este punto quiere decir que la clave no existe en el
        //arbol, entonces debo crear un nodo con la clave y valor a insertar
        //y el nodo anterior es el padre de ese nuevo nodo
        NodoBinario<K, V> nuevoNodo
                = new NodoBinario<>(claveAInsertar, valorAInsertar);
        K claveDelPadre = nodoAnterior.getClave();
        if (claveAInsertar.compareTo(claveDelPadre) < 0) {
            nodoAnterior.setHijoIzquierdo(nuevoNodo);
        } else {
            nodoAnterior.setHijoDerecho(nuevoNodo);
        }
    }
//------------------------------------------------------------------------------

    public void insertarMiManera(K claveAInsertar, V valorAInsertar) {
        if (claveAInsertar == null) {
            throw new IllegalArgumentException("La clave no puede ser nula");
        }
        if (valorAInsertar == null) {
            throw new IllegalArgumentException("El valor no puede ser nulo");
        }
        if (this.esArbolVacio()) {
            this.raiz = new NodoBinario<>(claveAInsertar, valorAInsertar);
            return;
        }
        Stack<NodoBinario<K, V>> pila = new Stack<>();
        pila.push(this.raiz);
        NodoBinario<K, V> nuevoNodo = new NodoBinario<>(claveAInsertar, valorAInsertar);
        while (!pila.isEmpty()) {
            NodoBinario<K, V> nodoActual = pila.pop();
            K claveActual = nodoActual.getClave();
            if (claveAInsertar.compareTo(claveActual) > 0) {
                if (!nodoActual.esVacioHijoDerecho()) {
                    pila.push(nodoActual.getHijoDerecho());
                } else {
                    nodoActual.setHijoDerecho(nuevoNodo);
                }

            } else if (claveAInsertar.compareTo(claveActual) < 0) {
                if (!nodoActual.esVacioHijoIzquierdo()) {
                    pila.push(nodoActual.getHijoIzquierdo());
                } else {
                    nodoActual.setHijoIzquierdo(nuevoNodo);
                }
            } else {//esto es opcional, entra aqui cuando las claves son iguales
                //No es necesario hacer 
                /* nodoActual.setClave(claveAInsertar);
                nodoActual.setValor(valorAInsertar);*/
            }
        }
    }

    @Override
    public V eliminar(K claveAEliminar) {
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
            return nodoActual;
        }
        if (claveAEliminar.compareTo(claveDelNodoActual) > 0) {
            NodoBinario<K, V> supuestoNuevoHijoDerecho
                    = eliminar(nodoActual.getHijoDerecho(), claveAEliminar);
            nodoActual.setHijoDerecho(supuestoNuevoHijoDerecho);
            return nodoActual;
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
//------------------------------------------------------------------------------

    public V buscar(K claveABuscar) {
        if (claveABuscar == null) {
            throw new IllegalArgumentException("La clave no puede ser nula");
        }
        if (this.esArbolVacio()) {//opcional
            return null;
        }
        NodoBinario<K, V> nodoActual = this.raiz;
        while (!NodoBinario.esNodoVacio(nodoActual)) {
            K claveActual = nodoActual.getClave();
            if (claveABuscar.compareTo(claveActual) == 0) {
                return nodoActual.getValor();
            } else if (claveABuscar.compareTo(claveActual) < 0) {
                nodoActual = nodoActual.getHijoIzquierdo();
            } else {
                nodoActual = nodoActual.getHijoDerecho();
            }
        }
        //En este punto significa que no se encontro la claveABuscar en el
        //árbol
        return null;

    }

//------------------------------------------------------------------------------
    @Override
    public boolean contiene(K clave) {//VERIFIED
        return this.buscar(clave) != null;
    }
//------------------------------------------------------------------------------

    @Override
    public List<K> recorridoEnInOrden() {//VERIFIED
        List<K> recorrido = new ArrayList<>();
        Stack<NodoBinario<K, V>> pilaDeNodos = new Stack<>();
        this.apilarParaInOrden(pilaDeNodos, this.raiz);
        while (!pilaDeNodos.isEmpty()) {
            NodoBinario<K, V> nodoActual = nodoActual = pilaDeNodos.pop();
            recorrido.add(nodoActual.getClave());
            apilarParaInOrden(pilaDeNodos, nodoActual.getHijoDerecho());
        }
        return recorrido;
    }

    private void apilarParaInOrden(Stack<NodoBinario<K, V>> pilaDeNodos,
            NodoBinario<K, V> nodoActual) {
        while (!NodoBinario.esNodoVacio(nodoActual)) {
            pilaDeNodos.push(nodoActual);
            nodoActual = nodoActual.getHijoIzquierdo();
        }

    }

//------------------------------------------------------------------------------
    public List<K> recorridoEnInOrdenV2() {
        List<K> recorrido = new ArrayList<>();
        recorridoEnInOrdenV2(this.raiz, recorrido);
        return recorrido;
    }

    private void
            recorridoEnInOrdenV2(NodoBinario<K, V> nodoActual,
                    List<K> recorrido) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return;
        }
        recorridoEnInOrdenV2(nodoActual.getHijoIzquierdo(), recorrido);
        recorrido.add(nodoActual.getClave());
        recorridoEnInOrdenV2(nodoActual.getHijoDerecho(), recorrido);
    }

    //--------------------------------------------------------------------------
    @Override
    public List<K> recorridoEnPreOrden() {
        List<K> recorrido = new ArrayList<>();
        if (this.esArbolVacio()) {
            return recorrido;
        }

        Stack<NodoBinario<K, V>> pilaDeNodos = new Stack<>();
        pilaDeNodos.push(this.raiz);

        while (!pilaDeNodos.isEmpty()) {

            NodoBinario<K, V> nodoActual = pilaDeNodos.pop();
            recorrido.add(nodoActual.getClave());

            if (!nodoActual.esVacioHijoDerecho()) {
                pilaDeNodos.push(nodoActual.getHijoDerecho());
            }

            if (!nodoActual.esVacioHijoIzquierdo()) {
                pilaDeNodos.push(nodoActual.getHijoIzquierdo());
            }

        }
        return recorrido;
    }
//------------------------------------------------------------------------------

    public List<K> recorridoEnPreOrdenV2() {
        List<K> recorrido = new ArrayList<>();
        recorridoEnPreOrdenV2(recorrido, this.raiz);
        return recorrido;
    }

    private void
            recorridoEnPreOrdenV2(List<K> lista, NodoBinario<K, V> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return;
        }
        lista.add(nodoActual.getClave());
        recorridoEnPreOrdenV2(lista, nodoActual.getHijoIzquierdo());
        recorridoEnPreOrdenV2(lista, nodoActual.getHijoDerecho());
    }
//------------------------------------------------------------------------------

    @Override
    public List<K> recorridoEnPostOrden() {
        List<K> recorrido = new ArrayList<>();
        recorridoEnPostOrden(this.raiz, recorrido);
        return recorrido;
    }

    private void
            recorridoEnPostOrden(NodoBinario<K, V> nodoActual,
                    List<K> recorrido) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return;
        }
        recorridoEnPostOrden(nodoActual.getHijoIzquierdo(), recorrido);
        recorridoEnPostOrden(nodoActual.getHijoDerecho(), recorrido);
        recorrido.add(nodoActual.getClave());

    }
//------------------------------------------------------------------------------

    public List<K> recorridoEnPostOrdenIterativo() {
        List<K> recorrido = new LinkedList<>();

        if (this.esArbolVacio()) {
            return recorrido;
        }
        Stack<NodoBinario<K, V>> pilaDeNodos = new Stack<>();
        meterParaPostOrden(pilaDeNodos, this.raiz);
        while (!pilaDeNodos.isEmpty()) {
            NodoBinario<K, V> nodoActual = pilaDeNodos.pop();
            recorrido.add(nodoActual.getClave());
            if (!pilaDeNodos.isEmpty()) {
                NodoBinario<K, V> tope = pilaDeNodos.peek();
                if (!tope.esVacioHijoDerecho()
                        && tope.getHijoDerecho() != nodoActual) {
                    meterParaPostOrden(pilaDeNodos,
                            tope.getHijoDerecho());
                }
            }
        }
        return recorrido;
    }

    private void
            meterParaPostOrden(Stack<NodoBinario<K, V>> pila,
                    NodoBinario<K, V> nodo) {
        while (!NodoBinario.esNodoVacio(nodo)) {
            pila.push(nodo);
            if (!nodo.esVacioHijoIzquierdo()) {
                nodo = nodo.getHijoIzquierdo();
            } else {
                nodo = nodo.getHijoDerecho();
            }
        }
    }
//------------------------------------------------------------------------------

    @Override
    public List<K> recorridoPorNiveles() {//Recomendado solo iterativo
        List<K> recorrido = new ArrayList<>();
        if (this.esArbolVacio()) {
            return recorrido;
        }

        Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);

        while (!colaDeNodos.isEmpty()) {

            NodoBinario<K, V> nodoActual = colaDeNodos.poll();
            recorrido.add(nodoActual.getClave());

            if (!nodoActual.esVacioHijoIzquierdo()) {
                colaDeNodos.offer(nodoActual.getHijoIzquierdo());
            }

            if (!nodoActual.esVacioHijoDerecho()) {
                colaDeNodos.offer(nodoActual.getHijoDerecho());
            }

        }
        return recorrido;
    }

    //--------------------------------------------------------------------------
    //Por profundidad ->logica PostOrden
    public int contarNodoConAmbosHijos() {
        int cant = contarAux(this.raiz);
        return cant;

    }

    //Por profundidad ->logica PostOrden
    private int contarAux(NodoBinario<K, V> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        int cantIzq = contarAux(nodoActual.getHijoIzquierdo());
        int cantDer = contarAux(nodoActual.getHijoDerecho());

        if (!nodoActual.esVacioHijoIzquierdo()
                && !nodoActual.esVacioHijoDerecho()) {
            return cantIzq + cantDer + 1;
        } else {
            return cantIzq + cantDer;
        }

    }
//-----------------------------------------------------------------------------

    public int contarNodosConAmbosHijosPorNiveles() {

        if (this.esArbolVacio()) {
            return 0;
        }
        int nodosConAmbosHijos = 0;
        Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);

        while (!colaDeNodos.isEmpty()) {

            NodoBinario<K, V> nodoActual = colaDeNodos.poll();

            if (!nodoActual.esVacioHijoIzquierdo()
                    && !nodoActual.esVacioHijoDerecho()) {
                nodosConAmbosHijos++;
            }
            if (!nodoActual.esVacioHijoIzquierdo()) {
                colaDeNodos.offer(nodoActual.getHijoIzquierdo());
            }

            if (!nodoActual.esVacioHijoDerecho()) {
                colaDeNodos.offer(nodoActual.getHijoDerecho());
            }

        }
        return nodosConAmbosHijos;
    }

    //--------------------------------------------------------------------------
    //a mi manera
    //Devuelve las claves en una lista de un nivel especifico del arbol
    public LinkedList<K> mostrarNivel(int nivel) {
        LinkedList<K> lista = new LinkedList<>();
        mostrarNivel(lista, this.raiz, nivel, 0);
        return lista;
    }

    private void mostrarNivel(LinkedList<K> lista, NodoBinario<K, V> nodo,
            int nivelABuscar, int nivelActual) {

        if (NodoBinario.esNodoVacio(nodo)) {
            return;
        }
        mostrarNivel(lista, nodo.getHijoIzquierdo(),
                nivelABuscar, nivelActual + 1);
        mostrarNivel(lista, nodo.getHijoDerecho(),
                nivelABuscar, nivelActual + 1);
        if (nivelActual == nivelABuscar) {
            lista.add(nodo.getClave());
        }
    }

    //--------------------------------------------------------------------------
    //Devuelve las claves en una lista de un nivel especifico del arbol
    public LinkedList<K> mostrarNivelOficial(int nivel) {
        LinkedList<K> lista = new LinkedList<>();
        mostrarNivel(lista, this.raiz, nivel, 0);
        return lista;
    }

    private LinkedList<K>
            mostrarNivelOficial(LinkedList<K> lista, NodoBinario<K, V> nodo,
                    int nivelABuscar, int nivelActual) {

        if (NodoBinario.esNodoVacio(nodo)) {
            return lista;
        }
        mostrarNivel(lista, nodo.getHijoIzquierdo(),
                nivelABuscar, nivelActual + 1);
        mostrarNivel(lista, nodo.getHijoDerecho(),
                nivelABuscar, nivelActual + 1);
        if (nivelActual == nivelABuscar) {
            lista.add(nodo.getClave());
        }
        return lista;
    }
//------------------------------------------------------------------------------   

    public boolean pruebaRecorrido() {//esBalanceado?
        // TODO Auto-generated method stub
        List<K> recorrido = new ArrayList<>();
        recorrido = recorridoEnInOrden();
        int h = (int) Math.pow(2, altura() - 1);
        h = contarNodosAnterioresDeUnaAltura(h);
        if ((h == recorrido.size())) {
            return true;
        }
        return false;
    }

    private int contarNodosAnterioresDeUnaAltura(int altura) {
        if (altura == 0) {
            return 0;
        }

        int alturaSuma = contarNodosAnterioresDeUnaAltura(altura / 2);
        return alturaSuma + altura;

    }
//------------------------------------------------------------------------------

    public int cantHijosDerechosDelArbol() {
        //Luego de haber hecho su respectivo análisis nos damos cuenta
        // que trabaja con la lógica del recorrido PreOrden(RID)
        if (esArbolVacio()) {
            return 0;
        }
        Stack<NodoBinario<K, V>> pila = new Stack<>();
        pila.push(this.raiz);
        int cant = 0;
        while (!pila.isEmpty()) {
            NodoBinario<K, V> nodoActual = pila.pop();
            if (!nodoActual.esVacioHijoDerecho()) {
                cant++;
            }
            if (!nodoActual.esVacioHijoDerecho()) {
                pila.push(nodoActual.getHijoDerecho());
            }
            if (!nodoActual.esVacioHijoIzquierdo()) {
                pila.push(nodoActual.getHijoIzquierdo());
            }
        }
        return cant;
    }
//------------------------------------------------------------------------------ 

    public int cantHijosDerechosV2() {
        return cantHijosDerechosV2(this.raiz);
    }

    private int cantHijosDerechosV2(NodoBinario<K, V> nodoActual) {
        //Luego de hacer su respectivo análisis nos damos cuenta que sigue
        //la lógica del recorrido PostOrden(IDR)
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        int hdIzq = cantHijosDerechosV2(nodoActual.getHijoIzquierdo());
        int hdDer = cantHijosDerechosV2(nodoActual.getHijoDerecho());
        return !nodoActual.esVacioHijoDerecho() ? hdIzq + hdDer + 1 : hdIzq + hdDer;
    }
//------------------------------------------------------------------------------

    public V eliminarIterativoMiManera(K claveAEliminar) {
        if (claveAEliminar == null) {
            throw new IllegalArgumentException("La clave no puede ser nula!");
        }
        if (this.esArbolVacio()) {
            return null;
        }
        V valorAEliminar = buscar(claveAEliminar);
        if (valorAEliminar == null) {
            //mandamos error en tiempo de ejecucion
            throw new IllegalArgumentException("La clave a eliminar no existe en"
                    + "el arbol!");
        }
        NodoBinario<K, V> nodoActual = this.raiz;
        NodoBinario<K, V> nodoAnterior = null;
        while (!NodoBinario.esNodoVacio(nodoActual)) {
            K claveActual = nodoActual.getClave();
            if (claveAEliminar.compareTo(claveActual) > 0) {
                nodoAnterior = nodoActual;
                nodoActual = nodoActual.getHijoDerecho();

            } else if (claveAEliminar.compareTo(claveActual) < 0) {
                nodoAnterior = nodoActual;
                nodoActual = nodoActual.getHijoIzquierdo();
            } else {//Entra cuando se encuentra la clave a eliminar

                //Caso I (con sus dos hijos nulos)
                if (nodoActual.esVacioHijoIzquierdo()
                        && nodoActual.esVacioHijoDerecho()) {
                    if (nodoAnterior.getHijoIzquierdo().getClave() == claveAEliminar) {
                        nodoAnterior.setHijoIzquierdo(null);
                    } else {
                        nodoAnterior.setHijoDerecho(null);
                    }
                    return valorAEliminar;
                }

                //Caso II (Opcion A)
                if (nodoActual.esVacioHijoIzquierdo() && !nodoActual.esVacioHijoDerecho()) {
                    if (nodoAnterior.getHijoIzquierdo().getClave() == claveAEliminar) {
                        nodoAnterior.setHijoIzquierdo(nodoActual.getHijoDerecho());
                    } else {
                        nodoAnterior.setHijoDerecho(nodoActual.getHijoDerecho());
                    }
                    return valorAEliminar;
                }
                //Caso II (Opcion B)
                if (!nodoActual.esVacioHijoIzquierdo() && nodoActual.esVacioHijoDerecho()) {
                    if (nodoAnterior.getHijoIzquierdo().getClave() == claveAEliminar) {
                        nodoAnterior.setHijoIzquierdo(nodoActual.getHijoIzquierdo());
                    } else {
                        nodoAnterior.setHijoDerecho(nodoActual.getHijoIzquierdo());
                    }
                    return valorAEliminar;
                }

                //Continua por aqui cuando la clave a eliminar tiene 2 hijos
                //Caso III de eliminacion
                NodoBinario<K, V> nodoAyuda = metodoAyuda(nodoActual.getHijoDerecho());
                nodoAyuda.setHijoIzquierdo(nodoActual.getHijoIzquierdo());
                if (nodoAnterior.getHijoIzquierdo().getClave() == claveAEliminar) {

                    nodoAnterior.setHijoIzquierdo(nodoActual.getHijoDerecho());
                    return valorAEliminar;
                } else {
                    nodoAnterior.setHijoDerecho(nodoActual.getHijoDerecho());
                    return valorAEliminar;
                }
            }
        }
        return valorAEliminar;
    }

    private NodoBinario<K, V> metodoAyuda(NodoBinario<K, V> nodoActual) {
        NodoBinario<K, V> nodoAnterior = null;
        while (!NodoBinario.esNodoVacio(nodoActual)) {
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.getHijoIzquierdo();
        }
        return nodoAnterior;
    }
//------------------------------------------------------------------------------

    public void insertarV2(K claveAInsertar, V valorAInsertar) {
        if (claveAInsertar == null) {
            throw new IllegalArgumentException("La clave no puede ser nula");

        }
        if (valorAInsertar == null) {
            throw new IllegalArgumentException("El valor no puede ser nulo");
        }
        if (NodoBinario.esNodoVacio(this.raiz)) {
            this.raiz = new NodoBinario<>(claveAInsertar, valorAInsertar);
            return;
        }
        insertarV2(this.raiz, null, claveAInsertar, valorAInsertar);
    }

    private void insertarV2(NodoBinario<K, V> nodoActual, NodoBinario<K, V> nodoAnterior, K claveAInsertar, V valorAInsertar) {

        if (NodoBinario.esNodoVacio(nodoActual)) {
            NodoBinario<K, V> nuevoNodo = new NodoBinario<>(claveAInsertar, valorAInsertar);
            if (claveAInsertar.compareTo(nodoAnterior.getClave()) > 0) {
                nodoAnterior.setHijoDerecho(nuevoNodo);
            } else if (claveAInsertar.compareTo(nodoAnterior.getClave()) < 0) {
                nodoAnterior.setHijoIzquierdo(nuevoNodo);
            } else {//esto es opcional(no es necesario!!)
              //  nodoAnterior.setClave(claveAInsertar);
                nodoAnterior.setValor(valorAInsertar);
            }

            return;
        }
        K claveActual = nodoActual.getClave();
        NodoBinario<K, V> nuevoNodo = new NodoBinario<>(claveAInsertar,
                valorAInsertar);

        if (claveAInsertar.compareTo(claveActual) > 0) {
            insertarV2(nodoActual.getHijoDerecho(),
                    nodoActual, claveAInsertar, valorAInsertar);

            return;
        }
        if (claveAInsertar.compareTo(claveActual) < 0) {
            insertarV2(nodoActual.getHijoIzquierdo(), nodoActual,
                    claveAInsertar, valorAInsertar);
            return;
        }
    }
//------------------------------------------------------------------------------

    public int contarNodosConAmbosHijosPorNivel() {//Recomendado solo iterativo
        //28-10-22    
        if (this.esArbolVacio()) {
            return 0;
        }

        Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        int cant = 0;
        while (!colaDeNodos.isEmpty()) {

            NodoBinario<K, V> nodoActual = colaDeNodos.poll();
            if (!nodoActual.esVacioHijoIzquierdo()
                    && !nodoActual.esVacioHijoDerecho()) {
                cant++;
            }

            if (!nodoActual.esVacioHijoIzquierdo()) {
                colaDeNodos.offer(nodoActual.getHijoIzquierdo());
            }

            if (!nodoActual.esVacioHijoDerecho()) {
                colaDeNodos.offer(nodoActual.getHijoDerecho());
            }

        }
        return cant;
    }
//------------------------------------------------------------------------------

    public int contarNodosConAmbosHijos(int nivel) {
        //28-10-22    
        if (this.esArbolVacio()) {
            return 0;
        }

        Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        int cant = 0;
        int nivelAyuda = 0;
        int tamaño = 0;
        while (!colaDeNodos.isEmpty()) {

            if (nivelAyuda == nivel) {
                while (!colaDeNodos.isEmpty()) {
                    NodoBinario<K, V> nodoActual = colaDeNodos.poll();
                    if (!nodoActual.esVacioHijoIzquierdo()
                            && !nodoActual.esVacioHijoDerecho()) {
                        cant++;
                    }
                }
                return cant;
            }
            tamaño = colaDeNodos.size();

            int i = 0;
            while (i < tamaño) {
                NodoBinario<K, V> nodoActual = colaDeNodos.poll();
                if (!nodoActual.esVacioHijoIzquierdo()) {
                    colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                }

                if (!nodoActual.esVacioHijoDerecho()) {
                    colaDeNodos.offer(nodoActual.getHijoDerecho());
                }

                i++;
            }
            nivelAyuda++;
        }
        return -999;//llega aquí cuando dan un nivel que el arbol no existe
    }
//------------------------------------------------------------------------------

    public List<K> recPorNivel(int nivel) {
        //28-10-22    
        List<K> recNivelN = new LinkedList<>();
        if (this.esArbolVacio()) {
            return recNivelN;
        }

        Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        int cant = 0;
        int nivelAyuda = 0;
        int tamaño = 0;
        while (!colaDeNodos.isEmpty()) {

            if (nivelAyuda == nivel) {
                while (!colaDeNodos.isEmpty()) {
                    NodoBinario<K, V> nodoActual = colaDeNodos.poll();
                    recNivelN.add(nodoActual.getClave());
                }
                return recNivelN;
            }
            tamaño = colaDeNodos.size();

            int i = 0;
            while (i < tamaño) {
                NodoBinario<K, V> nodoActual = colaDeNodos.poll();
                if (!nodoActual.esVacioHijoIzquierdo()) {
                    colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                }

                if (!nodoActual.esVacioHijoDerecho()) {
                    colaDeNodos.offer(nodoActual.getHijoDerecho());
                }

                i++;
            }
            nivelAyuda++;
        }
        return recNivelN;//llega aquí cuando dan un nivel que el arbol no existe
    }
/**
 * Si en un nivel n, la cantidad de nodos que hay en dicho nivel es impar
 * entonces devolvemos la clave del medio de dicho nivel asi sucesivamente
 * para todo el árbol, returna en un lista de claves.
 * @return List
 */
    public List<K> clavesDelMedioDelosNivelesDelArbol() {
        List<K> clavesDelMedio = new LinkedList<>();
        if (this.esArbolVacio()) {
            return clavesDelMedio;
        }
       int nivelDelArbol=this.nivel();
       int i=0;
       while(i<=nivelDelArbol){
           List<K>recNivel=recPorNivel(i);
           if(recNivel.size()%2!=0){//Preguntamos si es impar
               if(recNivel.size()==1){
                   clavesDelMedio.add(recNivel.get(0));
               }else{
                   int j=0;
                   while(j<recNivel.size()/2){
                       j++;
                   }
                   clavesDelMedio.add(recNivel.get(j));
               }
           }
           i++;
       }
       return clavesDelMedio;
    }
     

}
