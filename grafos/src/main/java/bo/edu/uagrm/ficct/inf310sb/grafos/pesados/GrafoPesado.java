/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.grafos.pesados;

import bo.edu.uagrm.ficct.inf310sb.grafos.BFS;
import bo.edu.uagrm.ficct.inf310sb.grafos.DFS;
import bo.edu.uagrm.ficct.inf310sb.grafos.excepciones.AristaNoExisteExcepcion;
import bo.edu.uagrm.ficct.inf310sb.grafos.excepciones.AristaYaExisteExcepcion;
import bo.edu.uagrm.ficct.inf310sb.grafos.excepciones.NroVerticesInvalidoExcepcion;
import bo.edu.uagrm.ficct.inf310sb.grafos.utilería.RecorridoUtils;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author 59178
 */
public class GrafoPesado {

    protected List<List<AdyacenteConPeso>> listasDeAdyacencia;

    public GrafoPesado() {
        this.listasDeAdyacencia = new ArrayList<>();
    }

    public GrafoPesado(int cantidadDeVertices) throws NroVerticesInvalidoExcepcion {
        if (cantidadDeVertices <= 0) {
            throw new NroVerticesInvalidoExcepcion();
        }
        this.listasDeAdyacencia = new ArrayList<>();
        for (int i = 0; i < cantidadDeVertices; i++) {
            insertarVertice();

        }
    }

    public final void insertarVertice() {
        List<AdyacenteConPeso> listaDeAdyacentesDelNuevoVertice = new ArrayList<>();
        this.listasDeAdyacencia.add(listaDeAdyacentesDelNuevoVertice);

    }

    public void validarVertice(int posDeVertice) {
        if (posDeVertice < 0 || posDeVertice >= this.cantidadDeVertices()) {
            throw new IllegalArgumentException("Vértice Invalido");
        }
    }

    public int cantidadDeVertices() {//ojo
        return this.listasDeAdyacencia.size();
    }

    public boolean existeAdyacencia(
            int posDeVerticeOrigen, int posDeVerticeDestino) {
        validarVertice(posDeVerticeOrigen);
        validarVertice(posDeVerticeDestino);
        List<AdyacenteConPeso> listaDeAdyacentesDelOrigen
                = this.listasDeAdyacencia.get(posDeVerticeOrigen);
        AdyacenteConPeso adyacenteConPesoDestino
                = new AdyacenteConPeso(posDeVerticeDestino);
        return listaDeAdyacentesDelOrigen.contains(adyacenteConPesoDestino);
    }

    public void insertarArista(
            int posDeVerticeOrigen, int posDeVerticeDestino) throws AristaYaExisteExcepcion {
        if (this.existeAdyacencia(posDeVerticeOrigen, posDeVerticeDestino)) {
            throw new AristaYaExisteExcepcion();
        }
        List<AdyacenteConPeso> listaDeAdyacentesDelOrigen
                = this.listasDeAdyacencia.get(posDeVerticeOrigen);
        AdyacenteConPeso adyacenteConPesoDestino
                = new AdyacenteConPeso(posDeVerticeDestino);
        listaDeAdyacentesDelOrigen.add(adyacenteConPesoDestino);
        Collections.sort(listaDeAdyacentesDelOrigen);
        if (posDeVerticeOrigen != posDeVerticeDestino) {
            List<AdyacenteConPeso> listaDeAdyacentesDelDestino
                    = this.listasDeAdyacencia.get(posDeVerticeDestino);
            AdyacenteConPeso adyacenteConPesoOrigen
                    = new AdyacenteConPeso(posDeVerticeOrigen);
            listaDeAdyacentesDelDestino.add(adyacenteConPesoOrigen);
            Collections.sort(listaDeAdyacentesDelDestino);
        }

    }

    public void insertarArista2(
            int posDeVerticeOrigen, int posDeVerticeDestino, double peso) throws AristaYaExisteExcepcion {
        if (this.existeAdyacencia(posDeVerticeOrigen, posDeVerticeDestino)) {
            throw new AristaYaExisteExcepcion();
        }
        List<AdyacenteConPeso> listaDeAdyacentesDelOrigen
                = this.listasDeAdyacencia.get(posDeVerticeOrigen);
        AdyacenteConPeso adyacenteConPesoDestino
                = new AdyacenteConPeso(posDeVerticeDestino, peso);
        listaDeAdyacentesDelOrigen.add(adyacenteConPesoDestino);
        Collections.sort(listaDeAdyacentesDelOrigen);
        if (posDeVerticeOrigen != posDeVerticeDestino) {
            List<AdyacenteConPeso> listaDeAdyacentesDelDestino
                    = this.listasDeAdyacencia.get(posDeVerticeDestino);
            AdyacenteConPeso adyacenteConPesoOrigen
                    = new AdyacenteConPeso(posDeVerticeOrigen, peso);
            listaDeAdyacentesDelDestino.add(adyacenteConPesoOrigen);
            Collections.sort(listaDeAdyacentesDelDestino);
        }

    }

    public int gradoDeVertice(int posDelVertice) {
        validarVertice(posDelVertice);
        List<AdyacenteConPeso> listaDeAdyacentesDelVertice
                = this.listasDeAdyacencia.get(posDelVertice);
        return listaDeAdyacentesDelVertice.size();

    }

    public void eliminarVertice(int posDeVerticeAEliminar) {
        validarVertice(posDeVerticeAEliminar);
        this.listasDeAdyacencia.remove(posDeVerticeAEliminar);

        for (List<AdyacenteConPeso> listaDeAdyDeUnVertice : this.listasDeAdyacencia) {
            AdyacenteConPeso adyacenteConPesoAEliminar
                    = new AdyacenteConPeso(posDeVerticeAEliminar);
            int posDeVerticeAEliminarEnAdy
                    = listaDeAdyDeUnVertice.indexOf(adyacenteConPesoAEliminar);
            if (posDeVerticeAEliminarEnAdy >= 0) {
                listaDeAdyDeUnVertice.remove(posDeVerticeAEliminarEnAdy);

            }

            for (int i = 0; i < listaDeAdyDeUnVertice.size(); i++) {
                AdyacenteConPeso adyacenteConPesoEnTurno
                        = listaDeAdyDeUnVertice.get(i);
                if (adyacenteConPesoEnTurno.getIndiceDeVertice() > posDeVerticeAEliminar) {
                    adyacenteConPesoEnTurno.setIndiceDeVertice(
                            adyacenteConPesoEnTurno.getIndiceDeVertice() - 1);
                    // listaDeAdyDeUnVertice.set(i, datoDePosDeAdy);
                    /*Nota: El set es muy importante aqui porque lo que hace es 
                    como si estuviera reemplazando un dato en la posicion i,
                    en cambio el add lo que hace es agregar un dato en la
                    posicion i y el dato que estaba ahi anteriormente es despla-
                    zado una posicion hacia la derecha y el metodo no funcionaria
                    como debe por que en vez de reemplazar el dato estaria agre-
                    gando mas datos.
                     */
                }

            }

        }
    }

    public void eliminarArista(int posVerticeOrigen, int posVerticeDestino)
            throws AristaNoExisteExcepcion {
        if (!this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new AristaNoExisteExcepcion();
        }
        List<AdyacenteConPeso> listaDeAdyacentesDelVerticeOrigen
                = this.listasDeAdyacencia.get(posVerticeOrigen);
        AdyacenteConPeso adyacenteConPesoDestino
                = new AdyacenteConPeso(posVerticeDestino);
        int posVerticeParaDesenlazar1
                = listaDeAdyacentesDelVerticeOrigen.indexOf(adyacenteConPesoDestino);
        listaDeAdyacentesDelVerticeOrigen.remove(posVerticeParaDesenlazar1);

        /*A partir de aqui obtendremos la lista de adyacentes del vertice destino
        En caso de que sean iguales los vertices no hay nada mas
        que hacer porque ya se habra eliminado la arista*/
        if (posVerticeOrigen != posVerticeDestino) {

            List<AdyacenteConPeso> listaDeAdyacentesDelVerticeDestino
                    = this.listasDeAdyacencia.get(posVerticeDestino);
            AdyacenteConPeso adyacenteConPesoOrigen
                    = new AdyacenteConPeso(posVerticeOrigen);
            int posVerticeParaDesenlazar2
                    = listaDeAdyacentesDelVerticeDestino.indexOf(adyacenteConPesoOrigen);
            listaDeAdyacentesDelVerticeDestino.remove(posVerticeParaDesenlazar2);
        }
    }

    public Iterable<AdyacenteConPeso> adyacentesDeVertice(int posDeVertice) {//ojo
        validarVertice(posDeVertice);
        List<AdyacenteConPeso> listaDeAdyacentesDelVertice
                = this.listasDeAdyacencia.get(posDeVertice);
        Iterable<AdyacenteConPeso> adyConPesoIterable = listaDeAdyacentesDelVertice;
        return adyConPesoIterable;
    }

    public int cantidadDeAristas() {
        int cant = 0;
        for (int i = 0; i < this.listasDeAdyacencia.size(); i++) {
            cant = cant + this.gradoDeVertice(i);
        }
        cant = cant / 2;
        return cant;
    }

   

    /*  public boolean esConexo2() {
        BFS rec = new BFS(this, 0);
        return rec.hayCaminoATodosLosVertice();
    }

    public boolean esConexo() {//Mi manera (Version novato)
        List<Boolean> marcas = new ArrayList<>();
        for (int i = 0; i < this.listasDeAdyacencia.size(); i++) {
            marcas.add(Boolean.FALSE);
        }
        for (int i = 0; i < this.listasDeAdyacencia.size(); i++) {
            List<Integer> listaDeAdyacentes = this.listasDeAdyacencia.get(i);
            int contadorTemporal = 0;
            for (int j = 0; j < this.listasDeAdyacencia.size(); j++) {
                if (j != i) {
                    if (listaDeAdyacentes.contains(j)) {
                        contadorTemporal++;
                    }
                }
            }
            if (contadorTemporal > 0) {
                marcas.set(i, Boolean.TRUE);
            }

        }

        for (int i = 0; i < marcas.size(); i++) {
            if (marcas.get(i) == false) {
                return false;
            }
        }
        return true;
    }*/
    @Override
    public String toString() {

        String graph = "------------------------GRAPH:----------------------\n";
        for (int i = 0; i < this.listasDeAdyacencia.size(); i++) {
            List<AdyacenteConPeso> listaDeAdy = this.listasDeAdyacencia.get(i);
            if (listaDeAdy.isEmpty()) {
                graph = graph + i + " | |-> null";
            } else {
                graph = graph + i + " | |-> ";
                for (AdyacenteConPeso posDatoAdy : listaDeAdy) {
                    graph = graph + posDatoAdy + " -> ";
                }
                graph = graph + "null";
            }
            graph = graph + "\n";
        }
        graph = graph + "-----------------------------------------";
        return graph;
    }
}
