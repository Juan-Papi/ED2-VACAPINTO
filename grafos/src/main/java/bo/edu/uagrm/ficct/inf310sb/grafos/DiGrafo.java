/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.grafos;

import bo.edu.uagrm.ficct.inf310sb.grafos.excepciones.AristaNoExisteExcepcion;
import bo.edu.uagrm.ficct.inf310sb.grafos.excepciones.AristaYaExisteExcepcion;
import bo.edu.uagrm.ficct.inf310sb.grafos.excepciones.NroVerticesInvalidoExcepcion;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author 59178
 */
public class DiGrafo extends Grafo {

    public DiGrafo() {
        super();//Llama al constructor de la clase padre
    }

    public DiGrafo(int nroVertices) throws NroVerticesInvalidoExcepcion {
        super(nroVertices);
    }

    @Override
    public void insertarArista(int posDeVerticeOrigen, int posDeVerticeDestino)
            throws AristaYaExisteExcepcion {
        if (this.existeAdyacencia(posDeVerticeOrigen, posDeVerticeDestino)) {
            throw new AristaYaExisteExcepcion();
        }
        List<Integer> listaDeAdyacentesDelOrigen
                = this.listasDeAdyacencia.get(posDeVerticeOrigen);
        listaDeAdyacentesDelOrigen.add(posDeVerticeDestino);
        Collections.sort(listaDeAdyacentesDelOrigen);
    }

    @Override
    public int gradoDeVertice(int posDeVertice) {
        throw new UnsupportedOperationException("Metodo no soportado en "
                + "grafos dirigidos");
    }

    public int gradoDeEntradaDeVertice(int posVertice) {
        validarVertice(posVertice);
        int entradasDeVertice = 0;
        for (int i = 0; i < super.listasDeAdyacencia.size(); i++) {
            Iterable<Integer> adyacenteDeUnVertice
                    = super.adyacentesDeVertice(i);
            for (Integer posDeAdyacente : adyacenteDeUnVertice) {
                if (posDeAdyacente == posVertice) {
                    entradasDeVertice++;
                }
            }
        }
        return entradasDeVertice;
    }

    public int gradoDeSalidaDeVertice(int posVertice) {
        return super.gradoDeVertice(posVertice);
    }

    public int cantidadDeAristas() {
        int cant = 0;
        for (int i = 0; i < this.listasDeAdyacencia.size(); i++) {
            cant = cant + super.gradoDeVertice(i);
        }
        return cant;
    }

    @Override
    public void eliminarArista(int posVerticeOrigen, int posVerticeDestino)
            throws AristaNoExisteExcepcion {
        if (!super.existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new AristaNoExisteExcepcion();
        }
        List<Integer> listaDeAdyacentesDelVerticeOrigen
                = this.listasDeAdyacencia.get(posVerticeOrigen);
        int posVerticeParaDesenlazar1
                = listaDeAdyacentesDelVerticeOrigen.indexOf(posVerticeDestino);
        listaDeAdyacentesDelVerticeOrigen.remove(posVerticeParaDesenlazar1);
    }

//------------------------------------------------------------------------------
    public boolean esDebilmenteConexo1() throws AristaYaExisteExcepcion {

        DFS recPorVertice = new DFS(this, 0);
        if (recPorVertice.controlMarcados.estanTodosMarcados()) {
            return true;
        }
        int busquedaPrevia = buscarVertNoMarcadoConAdyMarcado(recPorVertice);
        if (busquedaPrevia == -1) {
            return false;
        }
        for (int i = 0; i < this.listasDeAdyacencia.size()
                && !recPorVertice.controlMarcados.estanTodosMarcados(); i++) {
            int verticeBuscado = buscarVertNoMarcadoConAdyMarcado(recPorVertice);
            if (verticeBuscado >= 0) {
                recPorVertice.ejecutarDFS(verticeBuscado);
            }

        }

        return recPorVertice.controlMarcados.estanTodosMarcados();
    }

    private int buscarVertNoMarcadoConAdyMarcado(DFS recorrido) {

        for (int i = 0; i < this.listasDeAdyacencia.size(); i++) {
            boolean estaMarcado = recorrido.controlMarcados.estaVerticeMarcado(i);
            if (!estaMarcado) {
                List<Integer> listaDeAdyacentes = this.listasDeAdyacencia.get(i);

                for (int j = 0; j < listaDeAdyacentes.size(); j++) {
                    int posVerticeAdy = listaDeAdyacentes.get(j);
                    boolean tieneAdyMarcado = recorrido.controlMarcados.estaVerticeMarcado(
                            posVerticeAdy);
                    if (tieneAdyMarcado) {
                        return i;
                    }
                }
            }
        }

        return -1;
    }

    //-----------------------------------------------------------------------------
    public boolean esDebilmenteConexo2() throws NroVerticesInvalidoExcepcion, AristaYaExisteExcepcion {
        Grafo grafoAuxiliar = new Grafo(
                this.listasDeAdyacencia.size());
        for (int i = 0; i < this.listasDeAdyacencia.size(); i++) {
            List<Integer> listaDeAdyacentes = this.listasDeAdyacencia.get(i);
            if (listaDeAdyacentes != null) {
                for (Integer posVerticeAdy : listaDeAdyacentes) {
                    boolean existeAdyacencia = grafoAuxiliar.existeAdyacencia(
                            i, posVerticeAdy);
                    if (!existeAdyacencia) {
                        grafoAuxiliar.insertarArista(
                                i, posVerticeAdy);
                    }
                }

            }

        }

        return grafoAuxiliar.esConexo2();
    }

    public boolean esFuertementeConexo1() {//Mejor Forma

        for (int i = 0; i < this.listasDeAdyacencia.size(); i++) {
            BFS rec = new BFS(this, i);
            if (!rec.hayCaminoATodosLosVertice()) {
                //(rec.hayCaminoATodosLosVertice()==false) es lo mismo           
                return false;
            }
        }
        return true;
    }

   /* public boolean esFuertementeConexo2() {//Mi manera (Version novato)
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
            if (contadorTemporal == (this.listasDeAdyacencia.size() - 1)) {
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

    public int nroDeIslas() throws AristaYaExisteExcepcion {
        if (this.listasDeAdyacencia.size() == 0) {
            return 0;
        }
        DFS recPorVertice = new DFS(this, 0);
        if (recPorVertice.controlMarcados.estanTodosMarcados()) {
            return 1;
        }
        int cantDeIslas = 1;
        for (int i = 0; i < this.listasDeAdyacencia.size()
                && !recPorVertice.controlMarcados.estanTodosMarcados(); i++) {
            int verticeBuscado = buscarVertNoMarcadoConAdyMarcado(recPorVertice);
            
            if (verticeBuscado >= 0) {
                recPorVertice.ejecutarDFS(verticeBuscado);
            } else {
                verticeBuscado = buscarVertNoMarcado(recPorVertice);
                if (verticeBuscado >= 0) {
                    cantDeIslas++;
                    recPorVertice.ejecutarDFS(verticeBuscado);
                }
            }
        }
        return cantDeIslas;
    }

    private int buscarVertNoMarcado(DFS recorrido) {
        for (int i = 0; i < this.listasDeAdyacencia.size(); i++) {
            boolean estaMarcado = recorrido.controlMarcados.estaVerticeMarcado(i);
            if (!estaMarcado) {
                return i;
            }
        }
        return -1;
    }

    public boolean tieneCiclos() {
        AlgWarshall algoritmoAux = new AlgWarshall(this);
        boolean[][] matrizDeCaminos = algoritmoAux.getMatrizDeCaminos();

        for (int i = 0; i < matrizDeCaminos.length; i++) {
            if (matrizDeCaminos[i][i] == true) {
                return true;
            }
        }
        return false;
    }

    public DiGrafo invertirAristas() throws NroVerticesInvalidoExcepcion, AristaYaExisteExcepcion {
        {
            DiGrafo unDigrafo = new DiGrafo(this.cantidadDeVertices());
            List<Integer> listaDeAdyacentes = new ArrayList<>();
            for (int i = 0; i < this.listasDeAdyacencia.size(); i++) {
                listaDeAdyacentes = this.listasDeAdyacencia.get(i);
                for (int j = 0; j < listaDeAdyacentes.size(); j++) {
                    int posVerticeAdy = listaDeAdyacentes.get(j);
                    if (!existeAdyacencia(posVerticeAdy, i)) {
                        unDigrafo.insertarArista(posVerticeAdy, i);

                    } else {
                        if (!unDigrafo.existeAdyacencia(i, posVerticeAdy)
                                && !unDigrafo.existeAdyacencia(posVerticeAdy, i)) {
                            unDigrafo.insertarArista(i, posVerticeAdy);
                            unDigrafo.insertarArista(posVerticeAdy, i);
                        }
                    }

                }
            }
return unDigrafo;
        }
    }
}
