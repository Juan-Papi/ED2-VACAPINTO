/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.arboles;

import bo.edu.uagrm.ficct.inf310sb.arboles.excepciones.ExcepcionClaveNoExiste;
import bo.edu.uagrm.ficct.inf310sb.arboles.excepciones.ExcepcionOrdenInvalido;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author 59178
 */
public class ArbolMViasBusqueda<K extends Comparable<K>, V>
        implements IArbolBusqueda<K, V> {

    protected NodoMVias<K, V> raiz;
    protected int orden;
    protected int POSICION_INVALIDA = -1;

    public ArbolMViasBusqueda() {
        this.orden = 3;//Como minimo porque si es de orden 2
        //sería como si fuera un arbol binario
        //this.raiz=new NodoMVias<>(3);
    }

    public ArbolMViasBusqueda(int orden) throws ExcepcionOrdenInvalido {
        if (orden < 3) {
            throw new ExcepcionOrdenInvalido();
            //throw new ExcepcionOrdenInvalido("Mensaje personalizado!");
        }
        this.orden = orden;
        //this.raiz=new NodoMVias<>(orden);
    }

    @Override
    public void vaciar() {
        this.raiz = NodoMVias.nodoVacio();
    }

    @Override
    public boolean esArbolVacio() {
        return NodoMVias.esNodoVacio(raiz);
    }

    /**
     * Retorna la cantidad de nodos que hay en el árbol.
     *
     * @return
     */
    @Override
    public int size() {

        if (this.esArbolVacio()) {
            return 0;
        }
        int cant = 0;
        Queue<NodoMVias<K, V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);

        while (!colaDeNodos.isEmpty()) {

            NodoMVias<K, V> nodoActual = colaDeNodos.poll();
            cant++;
            for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {

                if (!nodoActual.esHijoVacio(i)) {
                    colaDeNodos.offer(nodoActual.getHijo(i));
                }
            }//Fin del for

            if (!nodoActual.esHijoVacio(nodoActual.cantidadDeClavesNoVacias())) {
                colaDeNodos.offer(nodoActual.getHijo(
                        nodoActual.cantidadDeClavesNoVacias()));
            }

        }
        return cant;
    }

    @Override
    public int altura() {
        return altura(this.raiz);
    }

    private int altura(NodoMVias<K, V> nodoActual) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return 0;
        }
        int alturaMayor = 0;
        for (int i = 0; i < this.orden; i++) {
            int alturaDeHijo = altura(nodoActual.getHijo(i));
            if (alturaDeHijo > alturaMayor) {
                alturaMayor = alturaDeHijo;
            }
        }
        return alturaMayor + 1;//El +1 hace referencia al nodoActual
    }

    @Override
    public int nivel() {
        return nivel(this.raiz);
    }

    private int nivel(NodoMVias<K, V> nodoActual) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return -1;
        }
        int nivelMayor = -1;
        for (int i = 0; i < orden; i++) {
            int nivelDeHijo = nivel(nodoActual.getHijo(i));
            if (nivelDeHijo > nivelMayor) {
                nivelMayor = nivelDeHijo;
            }

        }
        return nivelMayor + 1;
    }

    @Override
    public K minimo() {
        if (this.esArbolVacio()) {
            return null;
        }
        NodoMVias<K, V> nodoActual = this.raiz;
        NodoMVias<K, V> nodoAnterior = (NodoMVias<K, V>) NodoMVias.nodoVacio();
        while (!NodoMVias.esNodoVacio(nodoActual)) {
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.getHijo(0);
        }
        return nodoAnterior.getClave(0);
    }

    @Override
    public K maximo() {
        if (this.esArbolVacio()) {
            return null;
        }
        NodoMVias<K, V> nodoActual = this.raiz;
        NodoMVias<K, V> nodoAnterior = (NodoMVias<K, V>) NodoMVias.nodoVacio();
        while (!NodoMVias.esNodoVacio(nodoActual)) {
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.getHijo(
                    nodoActual.cantidadDeClavesNoVacias());
        }
        return nodoAnterior.getClave(
                nodoAnterior.cantidadDeClavesNoVacias() - 1);

    }

    @Override
    public void insertar(K claveAInsertar, V valorAInsertar) {
        if (this.esArbolVacio()) {
            this.raiz = new NodoMVias<>(
                    this.orden, claveAInsertar, valorAInsertar);
            return;
        }
        NodoMVias<K, V> nodoActual = this.raiz;
        while (!NodoMVias.esNodoVacio(nodoActual)) {
            int posicionClaveExistente = this.existeClaveEnNodo(
                    nodoActual, claveAInsertar);
            if (posicionClaveExistente != POSICION_INVALIDA) {
                nodoActual.setValor(posicionClaveExistente, valorAInsertar);
                return;
            }
            if (nodoActual.esHoja()) {
                if (nodoActual.estanClavesLlenas()) {
                    int posicionPorDondeBajar = this.porDondeBajar(
                            nodoActual, claveAInsertar);

                    NodoMVias<K, V> nuevoHijo = new NodoMVias<>(
                            this.orden, claveAInsertar, valorAInsertar);
                    nodoActual.setHijo(posicionPorDondeBajar, nuevoHijo);

                } else {
                    this.insertarDatosOrdenadoEnNodo(
                            nodoActual, claveAInsertar, valorAInsertar);
                }
                return;
            } else {
                int posicionPorDondeBajar = this.porDondeBajar(
                        nodoActual, claveAInsertar);
                if (NodoMVias.esNodoVacio(
                        nodoActual.getHijo(posicionPorDondeBajar))) {
                    NodoMVias<K, V> nuevoHijo = new NodoMVias<>(
                            this.orden, claveAInsertar, valorAInsertar);
                    nodoActual.setHijo(posicionPorDondeBajar, nuevoHijo);
                    return;
                    //nodoActual=NodoMVias.nodoVacio(); otra forma de terminar
                }

                nodoActual = nodoActual.getHijo(posicionPorDondeBajar);

            }
        }
    }
//--------------------------------------------------------------------------------

    private void insertarDatosOrdenadoEnNodo(
            NodoMVias<K, V> nodoActual, K claveAInsertar, V valorAInsertar) {

        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            K claveActual = nodoActual.getClave(i);
            if (claveAInsertar.compareTo(claveActual) < 0) {
                recorrerADerecha(nodoActual, i);
                nodoActual.setClave(i, claveAInsertar);
                nodoActual.setValor(i, valorAInsertar);
                return;
            } else {
                int cant1 = i + 1;
                int cant2 = nodoActual.cantidadDeClavesNoVacias();
                if (cant1 != cant2) {
                    K claveSiguiente = nodoActual.getClave(i + 1);
                    if (claveAInsertar.compareTo(claveActual) > 0
                            && claveAInsertar.compareTo(claveSiguiente) < 0) {
                        recorrerADerecha(nodoActual, i + 1);
                        nodoActual.setClave(i + 1, claveAInsertar);
                        nodoActual.setValor(i + 1, valorAInsertar);
                        return;
                    }
                }
            }

        }
        //Entra aquí cuando vamos a poner la clave al final del nodo
        nodoActual.setClave(
                nodoActual.cantidadDeClavesNoVacias(), claveAInsertar);
        nodoActual.setValor(
                nodoActual.cantidadDeClavesNoVacias() - 1, valorAInsertar);
        //En el ultimo setValor le restamos 1 
        //a nodoActual.cantidadDeClavesNoVacias porque no tomamos en
        //cuenta la última clave insertada
        return;
    }

    private void recorrerADerecha(NodoMVias<K, V> nodoActual, int puntoMinimo) {
        for (int i = orden - 1; i > (puntoMinimo + 1); i--) {
            nodoActual.setClave(i - 1, nodoActual.getClave(i - 2));
            nodoActual.setValor(i - 1, nodoActual.getValor(i - 2));

        }
    }
//---------------------------------------------------------------------------------

    private int porDondeBajar(NodoMVias<K, V> nodoActual, K claveDeAnalisis) {

        for (int i = 0; i < orden - 1; i++) {
            K claveActual = nodoActual.getClave(i);
            if (claveDeAnalisis.compareTo(claveActual) < 0) {
                return i;
            }

        }
        //cuando llega a este punto significa que vamos a bajar por el último hijo!    
        return orden - 1;

    }

    private int existeClaveEnNodo(NodoMVias<K, V> nodoActual, K claveABuscar) {

        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            K claveActual = nodoActual.getClave(i);
            if (claveActual.compareTo(claveABuscar) == 0) {
                return i;
            }
        }
        return POSICION_INVALIDA;
    }

    @Override
    public V eliminar(K claveAEliminar) {
        if (claveAEliminar == null) {
            throw new IllegalArgumentException("Clave a eliminar no "
                    + "puede ser nula");
        }
        V valorARetornar = this.buscar(claveAEliminar);
        if (valorARetornar == null) {
            throw new ExcepcionClaveNoExiste("La clave no existe en el árbol");
        }
        this.raiz = eliminar(this.raiz, claveAEliminar);
        return valorARetornar;
    }

    private NodoMVias<K, V> eliminar(NodoMVias<K, V> nodoActual, K claveAEliminar) {
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            K claveActual = nodoActual.getClave(i);
            if (claveAEliminar.compareTo(claveActual) == 0) {
                if (nodoActual.esHoja()) {
                    this.eliminarDatosDeNodo(nodoActual, i);
                    if (nodoActual.cantidadDeClavesNoVacias() == 0) {
                        return NodoMVias.nodoVacio();
                    }
                    return nodoActual;
                }
                //Si llego aquí la clave esta en un nodo no hoja
                K claveDeReemplazo;
                if (this.hayHijosMasAdelante(nodoActual, i)) {
                    claveDeReemplazo = this.buscarClaveSucesoraInOrden(
                            nodoActual, claveAEliminar);
                } else {

                    claveDeReemplazo = this.buscarClavePredecesoraInOrden(
                            nodoActual, claveAEliminar);
                }

                V valorDeReemplazo = this.buscar(claveDeReemplazo);

                nodoActual = eliminar(nodoActual, claveDeReemplazo);

                nodoActual.setClave(i, claveDeReemplazo);
                nodoActual.setValor(i, valorDeReemplazo);

                return nodoActual;
            }
            if (claveAEliminar.compareTo(claveActual) < 0) {
                NodoMVias<K, V> supuestoNuevoHijo = eliminar(
                        nodoActual.getHijo(i), claveAEliminar);
                nodoActual.setHijo(i, supuestoNuevoHijo);
                return nodoActual;
            }

        }//fin del for
        NodoMVias<K, V> supuestoNuevoHijo = eliminar(nodoActual.getHijo(
                nodoActual.cantidadDeClavesNoVacias()), claveAEliminar);
        nodoActual.setHijo(nodoActual.cantidadDeClavesNoVacias(), supuestoNuevoHijo);
        return nodoActual;
    }

    private void eliminarDatosDeNodo(NodoMVias<K, V> nodoActual, int pos) {
        for (int i = pos; i < nodoActual.cantidadDeClavesNoVacias() - 1; i++) {
            nodoActual.setClave(i, nodoActual.getClave(i + 1));
            nodoActual.setValor(i, nodoActual.getValor(i + 1));

        }
        nodoActual.setClave(nodoActual.cantidadDeClavesNoVacias() - 1, (K) NodoMVias.datoVacio());
        nodoActual.setValor(nodoActual.cantidadDeClavesNoVacias(), (V) NodoMVias.datoVacio());
    }

    private boolean hayHijosMasAdelante(NodoMVias<K, V> nodoActual, int pos) {
        for (int i = pos + 1; i <= nodoActual.cantidadDeClavesNoVacias(); i++) {
            if (!nodoActual.esHijoVacio(i)) {
                return true;
            }

        }
        return false;
    }

    private K buscarClaveSucesoraInOrden(NodoMVias<K, V> nodoActual, K claveAEliminar) {
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            K claveActual = nodoActual.getClave(i);
            if (claveAEliminar.compareTo(claveActual) == 0) {
                if (nodoActual.esHijoVacio(i + 1)) {
                    return nodoActual.getClave(i + 1);
                }
                nodoActual = nodoActual.getHijo(i + 1);
                NodoMVias<K, V> nodoAnterior = (NodoMVias<K, V>) NodoMVias.nodoVacio();
                while (!NodoMVias.esNodoVacio(nodoActual)) {
                    nodoAnterior = nodoActual;
                    nodoActual = nodoActual.getHijo(0);
                }
                return nodoAnterior.getClave(0);

            }

        }
        return (K) NodoMVias.datoVacio();
    }

    private K buscarClavePredecesoraInOrden(NodoMVias<K, V> nodoActual, K claveAEliminar) {

        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            K claveActual = nodoActual.getClave(i);
            if (claveAEliminar.compareTo(claveActual) == 0) {
                if (nodoActual.esHijoVacio(i)) {
                    return nodoActual.getClave(i - 1);
                }
                nodoActual = nodoActual.getHijo(i);
                List<K> recorrido = new ArrayList<>();
                recorridoEnInOrden(nodoActual, recorrido);
                K ultimaClaveInOrden = recorrido.get(recorrido.size() - 1);
                return ultimaClaveInOrden;

            }

        }
        return (K) NodoMVias.datoVacio();
    }

    @Override
    public boolean contiene(K clave) {
        return this.buscar(clave) != null;
    }

    @Override
    public V buscar(K claveABuscar) {
        NodoMVias<K, V> nodoActual = this.raiz;
        while (!NodoMVias.esNodoVacio(nodoActual)) {
            boolean huboCambioDeNodoActual = false;
            for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias()
                    && !huboCambioDeNodoActual; i++) {
                K claveActual = nodoActual.getClave(i);
                if (claveABuscar.compareTo(claveActual) == 0) {
                    return nodoActual.getValor(i);
                }
                if (claveABuscar.compareTo(claveActual) < 0) {
                    nodoActual = nodoActual.getHijo(i);
                    huboCambioDeNodoActual = true;
                }

            }//Fin del for ya no existe i
            if (!huboCambioDeNodoActual) {
                nodoActual = nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacias());
            }
        }
        return (V) NodoMVias.datoVacio();
    }

    @Override
    public List<K> recorridoEnInOrden() {
        List<K> recorrido = new ArrayList<>();
        recorridoEnInOrden(this.raiz, recorrido);
        return recorrido;
    }

    private void
            recorridoEnInOrden(NodoMVias<K, V> nodoActual,
                    List<K> recorrido) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return;
        }
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            recorridoEnInOrden(nodoActual.getHijo(i), recorrido);
            recorrido.add(nodoActual.getClave(i));
        }
        recorridoEnInOrden(nodoActual.getHijo(
                nodoActual.cantidadDeClavesNoVacias()), recorrido);
    }

    @Override
    public List<K> recorridoEnPreOrden() {
        List<K> recorrido = new ArrayList<>();
        recorridoEnPreOrden(this.raiz, recorrido);
        return recorrido;
    }

    private void
            recorridoEnPreOrden(NodoMVias<K, V> nodoActual, List<K> recorrido) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return;
        }
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {

            recorrido.add(nodoActual.getClave(i));
            recorridoEnPreOrden(nodoActual.getHijo(i), recorrido);
        }
        recorridoEnPreOrden(nodoActual.getHijo(
                nodoActual.cantidadDeClavesNoVacias()), recorrido);
    }

    @Override
    public List<K> recorridoEnPostOrden() {
        List<K> recorrido = new ArrayList<>();
        recorridoEnPostOrden(this.raiz, recorrido);
        return recorrido;
    }

    private void
            recorridoEnPostOrden(NodoMVias<K, V> nodoActual,
                    List<K> recorrido) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return;
        }
        recorridoEnPostOrden(nodoActual.getHijo(0), recorrido);
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            recorridoEnPostOrden(nodoActual.getHijo(i + 1), recorrido);
            recorrido.add(nodoActual.getClave(i));
            //Es una simulación a un arbolBinario!!
        }
    }

    @Override
    public List<K> recorridoPorNiveles() {
        List<K> recorrido = new ArrayList<>();
        if (this.esArbolVacio()) {
            return recorrido;
        }

        Queue<NodoMVias<K, V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);

        while (!colaDeNodos.isEmpty()) {

            NodoMVias<K, V> nodoActual = colaDeNodos.poll();
            for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
                recorrido.add(nodoActual.getClave(i));

                if (!nodoActual.esHijoVacio(i)) {
                    colaDeNodos.offer(nodoActual.getHijo(i));
                }
            }//Fin del for

            if (!nodoActual.esHijoVacio(nodoActual.cantidadDeClavesNoVacias())) {
                colaDeNodos.offer(nodoActual.getHijo(
                        nodoActual.cantidadDeClavesNoVacias()));
            }

        }
        return recorrido;
    }

    /**
     * Retorna la cantidad de datos vacios que hay en el árbol
     *
     * @return
     */
    public int cantidadDatosVacios() {
        return cantidadDatosVacios(this.raiz);
    }

    private int cantidadDatosVacios(NodoMVias<K, V> nodoActual) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return 0;
        }
        int cantidad = 0;
        for (int i = 0; i < orden - 1; i++) {
            cantidad += cantidadDatosVacios(nodoActual.getHijo(i));
            if (nodoActual.esClaveVacia(i)) {
                cantidad++;
            }
        }
        cantidad += cantidadDatosVacios(nodoActual.getHijo(orden - 1));
        //Equivale a decir:
        //cantidad=cantidad+cantidadDeDatosVacios(nodoActual.getHijo(orden-1));
        return cantidad;
    }

    /**
     * Retorna la cantidad de hojas de un arbol MVias
     *
     * @return
     */
    public int cantidadDeHojas() {
        return cantidadDeHojas(this.raiz);
    }

    private int cantidadDeHojas(NodoMVias<K, V> nodoActual) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return 0;
        }
        if (nodoActual.esHoja()) {
            return 1;
        }
        int cantidad = 0;
        for (int i = 0; i < orden; i++) {
            cantidad = cantidad + cantidadDeHojas(nodoActual.getHijo(i));

        }
        return cantidad;

    }
/**
 *  Retorna la cantidad de nodos hojas del arbol MVias desde un nivel n
 * @param nivelBase
 * @return 
 */
    public int cantidadDeHojas(int nivelBase) {
        return cantidadDeHojas(this.raiz, nivelBase, 0);
    }

    private int cantidadDeHojas(NodoMVias<K, V> nodoActual, int nivelBase, int nivelActual) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return 0;
        }
        if (nivelActual>=nivelBase) {
            if (nodoActual.esHoja()) {
                return 1;
            }
        }
        int cantidad = 0;
        for (int i = 0; i < orden; i++) {
            cantidad = cantidad + cantidadDeHojas(
                    nodoActual.getHijo(i),nivelBase,nivelActual+1);

        }
        return cantidad;

    }

}
