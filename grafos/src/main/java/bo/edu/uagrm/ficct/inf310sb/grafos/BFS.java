/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.grafos;

import bo.edu.uagrm.ficct.inf310sb.grafos.utilería.RecorridoUtils;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author 59178
 */
public class BFS {//Recorrido en anchura o amplitud (Por niveles)

    protected RecorridoUtils controlMarcados;
    private Grafo grafo;
    private List<Integer> recorrido;

    public BFS(Grafo unGrafo, int unaPosDeVerticeDePartida) {
        this.grafo = unGrafo;//No es una copia, mas bien lo que hacemos es
        //guardar su referencia
        grafo.validarVertice(unaPosDeVerticeDePartida);
        recorrido = new ArrayList<>();
        controlMarcados = new RecorridoUtils(this.grafo.cantidadDeVertices());
        ejecutarBFS(unaPosDeVerticeDePartida);
    }

    public void ejecutarBFS(int posDeVerticeDePartida) {
        Queue<Integer> cola = new LinkedList<>();
        cola.offer(posDeVerticeDePartida);
        controlMarcados.marcarVertice(posDeVerticeDePartida);
        do {
            int posDeVerticeEnTurno = cola.poll();
            recorrido.add(posDeVerticeEnTurno);
            Iterable<Integer> integerIterable
                    = this.grafo.adyacentesDeVertice(posDeVerticeEnTurno);
            for (Integer posDeVerticeAdy : integerIterable) {
                if (!this.controlMarcados.estaVerticeMarcado(posDeVerticeAdy)) {
                    cola.offer(posDeVerticeAdy);
                    controlMarcados.marcarVertice(posDeVerticeAdy);
                }
            }
        } while (!cola.isEmpty());
    }

    public Iterable<Integer> getRecorrido() {
        return this.recorrido;
    }

    public boolean hayCaminoAVertice(int posDeVerticeDestino) {
        this.grafo.validarVertice(posDeVerticeDestino);
        return this.controlMarcados.estaVerticeMarcado(posDeVerticeDestino);
    }

    public boolean hayCaminoATodosLosVertice() {
        return this.controlMarcados.estanTodosMarcados();
    }
}
