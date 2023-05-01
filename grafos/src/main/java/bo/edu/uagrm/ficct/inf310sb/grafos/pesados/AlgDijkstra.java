/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.grafos.pesados;

import bo.edu.uagrm.ficct.inf310sb.grafos.utilería.RecorridoUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author 59178
 */
public class AlgDijkstra {

    private RecorridoUtils controlMarcados;
    private GrafoPesado grafo;
    private List<Integer> predecesores;
    private List<Double> costo;

    public AlgDijkstra(GrafoPesado unGrafo, int unaPosDeVerticeDePartida,
            int unPosVerticeDeDestino) {
        this.grafo = unGrafo;
        this.grafo.validarVertice(unaPosDeVerticeDePartida);
        costo = new ArrayList<>();
        predecesores = new ArrayList<>();
        controlMarcados = new RecorridoUtils(this.grafo.cantidadDeVertices());
        for (int i = 0; i < this.grafo.listasDeAdyacencia.size(); i++) {
            predecesores.add(-1);
            costo.add(Double.POSITIVE_INFINITY);
        }
        costo.set(unaPosDeVerticeDePartida, 0.0);
        ejecutarAlgDijkstra(
                unPosVerticeDeDestino);
    }

    public void ejecutarAlgDijkstra(int posVerticeDestino) {
        int posVertice = buscarVerticeNoMarcadoDeMenorCosto();
        while (!controlMarcados.estaVerticeMarcado(posVerticeDestino)
                && (posVertice != -1)) {
            controlMarcados.marcarVertice(posVertice);
            List<AdyacenteConPeso> listaDeAdyacentesDePosVertice
                    = this.grafo.listasDeAdyacencia.get(posVertice);
            for (AdyacenteConPeso adyacenteConPeso : listaDeAdyacentesDePosVertice) {
                boolean marcado = controlMarcados.estaVerticeMarcado(
                        adyacenteConPeso.getIndiceDeVertice());
                if (!marcado) {
                    if (costo.get(adyacenteConPeso.getIndiceDeVertice())
                            > (costo.get(posVertice) + adyacenteConPeso.getPeso())) {
                        double sumaCostoPosVerticeMasPesoDePosVerticeASuAdy
                                = costo.get(posVertice) + adyacenteConPeso.getPeso();
                        costo.set(adyacenteConPeso.getIndiceDeVertice(), sumaCostoPosVerticeMasPesoDePosVerticeASuAdy);
                        predecesores.set(adyacenteConPeso.getIndiceDeVertice(), posVertice);
                    }
                }
            }
            posVertice = buscarVerticeNoMarcadoDeMenorCosto();
        }
    }

    private int buscarVerticeNoMarcadoDeMenorCosto() {
        double menorCosto = Double.POSITIVE_INFINITY;
        int verticeConMenorCosto = -1;
        for (int i = 0; i < this.grafo.cantidadDeVertices(); i++) {
            boolean marcado = this.controlMarcados.estaVerticeMarcado(i);
            if (!marcado) {
                if (this.costo.get(i) < menorCosto) {
                    menorCosto = this.costo.get(i);
                    verticeConMenorCosto = i;
                }

            }

        }
        return verticeConMenorCosto;
    }

    public List<Integer> verticesATomar(int posVerOrigen, int posVerDestino) {
        List<Integer>listaDeVertices=new ArrayList<>();
        if (controlMarcados.estaVerticeMarcado(posVerDestino)) {
            listaDeVertices.add(posVerDestino);
            int posVertice = predecesores.get(posVerDestino);
            while (posVertice != posVerOrigen) {
                listaDeVertices.add(posVertice);
                posVertice=predecesores.get(posVertice);
                
            }
            listaDeVertices.add(posVertice);
            Collections.reverse(listaDeVertices);
        }
        return listaDeVertices;
    }

    public List<Integer> getPredecesores() {
        return this.predecesores;
    }

    public List<Double> getCosto() {
        return this.costo;
    }
    public Double getCostoDeOrigenToDestino(int posVertDestino){
        return this.costo.get(posVertDestino);
    }
}
