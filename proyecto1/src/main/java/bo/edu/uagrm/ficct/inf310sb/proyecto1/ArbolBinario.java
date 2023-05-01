/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.proyecto1;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author 59178
 */
public class ArbolBinario<K> {

 //Campos
    protected NodoBinario<K> raiz;

//------------------------------------------------------------------------------
    public void expressionTree(Queue<K> operation) {
        if(operation.isEmpty()){
            return;
        }
        Stack<NodoBinario<K>> pilaDeNodos = new Stack<>();
        NodoBinario<K> nuevoNodo = (NodoBinario<K>) NodoBinario.nodoVacio();

        while (!operation.isEmpty()) {

            nuevoNodo = new NodoBinario<>(operation.poll());

            if (isOperator((String) nuevoNodo.getClave())) {

                nuevoNodo.setHijoDerecho((NodoBinario<K>) pilaDeNodos.pop());
                nuevoNodo.setHijoIzquierdo((NodoBinario<K>) pilaDeNodos.pop());

            }

            pilaDeNodos.push(nuevoNodo);

        }
        this.raiz = nuevoNodo;

    }
//------------------------------------------------------------------------------

    public List<K> recorridoEnInOrden() {
        List<K> recorrido = new LinkedList<>();
        return recorridoEnInOrden(recorrido, this.raiz);
    }

    private List<K> recorridoEnInOrden(List<K> recorrido, NodoBinario<K> nodoActual) {

        if (NodoBinario.esNodoVacio(nodoActual)) {
            return recorrido;
        }
        recorridoEnInOrden(recorrido, nodoActual.getHijoIzquierdo());
        recorrido.add(nodoActual.getClave());
        recorridoEnInOrden(recorrido, nodoActual.getHijoDerecho());

        return recorrido;
    }
//------------------------------------------------------------------------------

    public List<K> recorridoEnPostOrden() {
        List<K> recorrido = new LinkedList<>();
        return recorridoEnPostOrden(recorrido, this.raiz);
    }

    private List<K> recorridoEnPostOrden(List<K> recorrido, NodoBinario<K> nodoActual) {

        if (NodoBinario.esNodoVacio(nodoActual)) {
            return recorrido;
        }
        recorridoEnPostOrden(recorrido, nodoActual.getHijoIzquierdo());
        recorridoEnPostOrden(recorrido, nodoActual.getHijoDerecho());
        recorrido.add(nodoActual.getClave());

        return recorrido;
    }
//------------------------------------------------------------------------------

    public double calcular() {

        return calcular(this.raiz);

    }

    private double calcular(NodoBinario<K> nodoActual) {

        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        double calculoPorIzq = calcular(nodoActual.getHijoIzquierdo());
        double calculoPorDer = calcular(nodoActual.getHijoDerecho());
        String claveActual = (String) nodoActual.getClave();

        if (isOperator(claveActual)) {
            return auxiliar(calculoPorIzq, claveActual, calculoPorDer);
        }
        return Double.parseDouble(claveActual);

    }

    private double auxiliar(double izq, String operador, double der) {
        if (operador.equals("+")) {
            return izq + der;
        } else if (operador.equals("-")) {
            return izq - der;
        } else if (operador.equals("*")) {
            return izq * der;
        } else if (operador.equals("/")) {
            if (der == 0) {//Error en tiempo de ejecución
                throw new IllegalArgumentException(""
                        + "MATH Error, denominador no puede ser cero");
            }
            return izq / der;
        } else {
            return Math.pow(izq, der);
        }

    }
//-----------------------------------------------------------------------------

    public boolean isOperator(String cad) {
        return ("+".equals(cad) || "-".equals(cad)
                || "*".equals(cad)
                || "/".equals(cad)) || "^".equals(cad);
    }

}
//-----------------------------------------------------------------------------

