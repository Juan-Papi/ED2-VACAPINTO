/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.grafos.pesados;

import bo.edu.uagrm.ficct.inf310sb.grafos.excepciones.AristaYaExisteExcepcion;
import bo.edu.uagrm.ficct.inf310sb.grafos.excepciones.NroVerticesInvalidoExcepcion;
import bo.edu.uagrm.ficct.inf310sb.grafos.utilería.RecorridoUtils;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 59178
 */
public class AlgPrim {

    private GrafoPesado grafo;
    //private boolean[] marcados;
    private RecorridoUtils controlMarcados;

    public AlgPrim(GrafoPesado unGrafo) {
        this.grafo = unGrafo;
        controlMarcados = new RecorridoUtils(this.grafo.cantidadDeVertices());
    }

    public GrafoPesado arbolGrafoDeMinimoCosto() throws NroVerticesInvalidoExcepcion, AristaYaExisteExcepcion {
        int n = this.grafo.cantidadDeVertices();
        GrafoPesado arbol = new GrafoPesado(n);
        controlMarcados.marcarVertice(0);
        while (!controlMarcados.estanTodosMarcados()) {
            double menorPeso = Double.POSITIVE_INFINITY;
            int origen = 0;
            int destino = 0;
            List<Integer> listaDeMarcados = getListaDeElementosMarcados();
            for (Integer posVerticeMarcado : listaDeMarcados) {
                Iterable<AdyacenteConPeso> adyacentesDelVertMarcado
                        = adyacentesDelVerticeConPeso(posVerticeMarcado);
                for (AdyacenteConPeso adya : adyacentesDelVertMarcado) {
                    if (!controlMarcados.estaVerticeMarcado(adya.getIndiceDeVertice())
                            && adya.getPeso() < menorPeso) {
                        origen = posVerticeMarcado;
                        destino = adya.getIndiceDeVertice();
                        menorPeso = adya.getPeso();
                    }
                }
            }
            arbol.insertarArista2(origen, destino, menorPeso);
            controlMarcados.marcarVertice(destino);
        }
        return arbol;
    }

    private List<Integer> getListaDeElementosMarcados() {
        List<Integer> lista = new ArrayList<>();
        for (int i = 0; i < this.grafo.cantidadDeVertices(); i++) {
            if (controlMarcados.estaVerticeMarcado(i)) {
                lista.add(i);
            }
        }
        return lista;
    }

    private Iterable<AdyacenteConPeso> adyacentesDelVerticeConPeso(int posVertice) {
        /*Esto es hacer una copia de la lista
      por que si la obtenemos mediante las listas de adyacencia
       solo se pasa la referencia y no es una copia*/
        List<AdyacenteConPeso> adysConPeso = new ArrayList<>();
        boolean estaEncontrado = false;
        for (int i = 0; i < this.grafo.cantidadDeVertices()
                && !estaEncontrado; i++) {
            if (i == posVertice) {
                List<AdyacenteConPeso> listaDeAdys
                        = this.grafo.listasDeAdyacencia.get(i);
                for (AdyacenteConPeso adyConPeso : listaDeAdys) {
                    adysConPeso.add(adyConPeso);
                }
                estaEncontrado = true;
            }
        }
        return adysConPeso;
    }

}
