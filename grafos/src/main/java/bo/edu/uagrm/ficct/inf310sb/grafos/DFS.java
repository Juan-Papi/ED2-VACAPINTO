/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.grafos;

import bo.edu.uagrm.ficct.inf310sb.grafos.excepciones.AristaYaExisteExcepcion;
import bo.edu.uagrm.ficct.inf310sb.grafos.utilería.RecorridoUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author 59178
 */
public class DFS {//Recorrido en profundidad
    //Campos

    protected RecorridoUtils controlMarcados;
    private Grafo grafo;
    private List<Integer> recorrido;

    public DFS(Grafo unGrafo, int unaPosDeVerticeDePartida) throws AristaYaExisteExcepcion {
        this.grafo = unGrafo;//No es una copia,mas bien lo que
        //hacemos es guardar su referencia
        grafo.validarVertice(unaPosDeVerticeDePartida);
        recorrido = new ArrayList<>();
        controlMarcados = new RecorridoUtils(this.grafo.cantidadDeVertices());
        ejecutarDFS(unaPosDeVerticeDePartida);
        //ejecutarDFSIterativo(unaPosDeVerticeDePartida);//Right
        //ejecutarDFSPersonalizado(unaPosDeVerticeDePartida);

    }
    public DFS(Grafo unGrafo) throws AristaYaExisteExcepcion {
        this.grafo = unGrafo;//No es una copia,mas bien lo que
        //hacemos es guardar su referencia
       
        recorrido = new ArrayList<>();
        controlMarcados = new RecorridoUtils(this.grafo.cantidadDeVertices());

    }
    public void ejecutarDFS(int posDeVerticeDePartida) {
        recorrido.add(posDeVerticeDePartida);
        controlMarcados.marcarVertice(posDeVerticeDePartida);
        Iterable<Integer> integerIterable
                = this.grafo.adyacentesDeVertice(posDeVerticeDePartida);
        for (Integer posDeVerticeAdy : integerIterable) {
            if (!this.controlMarcados.estaVerticeMarcado(posDeVerticeAdy)) {
                ejecutarDFS(posDeVerticeAdy);
            }
        }
    }
     public void ejecutarDFSPersonalizado(List<Integer>recorridoPorIsla,int posDeVerticeDePartida) throws AristaYaExisteExcepcion {
        recorridoPorIsla.add(posDeVerticeDePartida);
        recorrido.add(posDeVerticeDePartida);
        controlMarcados.marcarVertice(posDeVerticeDePartida);
        Iterable<Integer> integerIterable
                = this.grafo.adyacentesDeVertice(posDeVerticeDePartida);
        
        for (Integer posDeVerticeAdy : integerIterable) {
            if (!this.controlMarcados.estaVerticeMarcado(posDeVerticeAdy)) {
                
                ejecutarDFSPersonalizado(recorridoPorIsla,posDeVerticeAdy);
            }
        }
        
    }


    public void ejecutarDFSIterativo(int posVerticeDePartida) {
        Stack<Integer> pila = new Stack<>();
        pila.push(posVerticeDePartida);
        while (!pila.isEmpty()) {
            int posVerticeEnTurno = pila.pop();
            //obligatorio poner esta condicion porque sino se repiten
            //vertices cuando hay ciclos
            if(!controlMarcados.estaVerticeMarcado(posVerticeEnTurno)){
               recorrido.add(posVerticeEnTurno); 
               controlMarcados.marcarVertice(posVerticeEnTurno);
            }           
            List<Integer> listaDeAdyacentesEnTurno
                    = grafo.listasDeAdyacencia.get(posVerticeEnTurno);
            int aux = listaDeAdyacentesEnTurno.size() - 1;
            for (int i = aux; i >= 0; i--) {
                int posVerticeAdy = listaDeAdyacentesEnTurno.get(i);
                if (!controlMarcados.estaVerticeMarcado(posVerticeAdy)) {
                    pila.push(posVerticeAdy);
                }
            }
        }

    }

   /* public boolean ejecutarDFSPersonalizado(int posDeVerticeDePartida) {
//ojo a esto no se sabe que hace
        recorrido.add(posDeVerticeDePartida);
        controlMarcados.marcarVertice(posDeVerticeDePartida);
        Iterable<Integer> integerIterable
                = this.grafo.adyacentesDeVertice(posDeVerticeDePartida);
        for (Integer posDeVerticeAdy : integerIterable) {
            if (!this.controlMarcados.estaVerticeMarcado(posDeVerticeAdy)) {
                ejecutarDFSPersonalizado(posDeVerticeAdy);
            }
        }
        return true;
    }*/

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
