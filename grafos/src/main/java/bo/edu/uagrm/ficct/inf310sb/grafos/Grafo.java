/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.grafos;

import bo.edu.uagrm.ficct.inf310sb.grafos.excepciones.AristaNoExisteExcepcion;
import bo.edu.uagrm.ficct.inf310sb.grafos.excepciones.AristaYaExisteExcepcion;
import bo.edu.uagrm.ficct.inf310sb.grafos.excepciones.NroVerticesInvalidoExcepcion;
import bo.edu.uagrm.ficct.inf310sb.grafos.utilería.RecorridoUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author 59178
 */
public class Grafo {

    protected List<List<Integer>> listasDeAdyacencia;

    public Grafo() {
        this.listasDeAdyacencia = new ArrayList<>();
    }

    public Grafo(int cantidadDeVertices) throws NroVerticesInvalidoExcepcion {
        if (cantidadDeVertices <= 0) {
            throw new NroVerticesInvalidoExcepcion();
        }
        this.listasDeAdyacencia = new ArrayList<>();
        for (int i = 0; i < cantidadDeVertices; i++) {
            insertarVertice();

        }
    }

    public final void insertarVertice() {
        List<Integer> listaDeAdyacentesDelNuevoVertice = new ArrayList<>();
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
        List<Integer> listaDeAdyacentesDelOrigen
                = this.listasDeAdyacencia.get(posDeVerticeOrigen);
        return listaDeAdyacentesDelOrigen.contains(posDeVerticeDestino);
    }

    public void insertarArista(
            int posDeVerticeOrigen, int posDeVerticeDestino) throws AristaYaExisteExcepcion {
        if (this.existeAdyacencia(posDeVerticeOrigen, posDeVerticeDestino)) {
            throw new AristaYaExisteExcepcion();
        }
        List<Integer> listaDeAdyacentesDelOrigen
                = this.listasDeAdyacencia.get(posDeVerticeOrigen);
        listaDeAdyacentesDelOrigen.add(posDeVerticeDestino);
        Collections.sort(listaDeAdyacentesDelOrigen);
        if (posDeVerticeOrigen != posDeVerticeDestino) {
            List<Integer> listaDeAdyacentesDelDestino
                    = this.listasDeAdyacencia.get(posDeVerticeDestino);
            listaDeAdyacentesDelDestino.add(posDeVerticeOrigen);
            Collections.sort(listaDeAdyacentesDelDestino);
        }

    }

    public int gradoDeVertice(int posDelVertice) {
        validarVertice(posDelVertice);
        List<Integer> listaDeAdyacentesDelVertice
                = this.listasDeAdyacencia.get(posDelVertice);
        return listaDeAdyacentesDelVertice.size();

    }

    public void eliminarVertice(int posDeVerticeAEliminar) {
        validarVertice(posDeVerticeAEliminar);
        this.listasDeAdyacencia.remove(posDeVerticeAEliminar);

        for (List<Integer> listaDeAdyDeUnVertice : this.listasDeAdyacencia) {
            int posDeVerticeAEliminarEnAdy
                    = listaDeAdyDeUnVertice.indexOf(posDeVerticeAEliminar);
            if (posDeVerticeAEliminarEnAdy >= 0) {
                listaDeAdyDeUnVertice.remove(posDeVerticeAEliminarEnAdy);

            }

            for (int i = 0; i < listaDeAdyDeUnVertice.size(); i++) {
                int datoDePosDeAdy = listaDeAdyDeUnVertice.get(i);
                if (datoDePosDeAdy > posDeVerticeAEliminar) {
                    datoDePosDeAdy--;
                    listaDeAdyDeUnVertice.set(i, datoDePosDeAdy);
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
        List<Integer> listaDeAdyacentesDelVerticeOrigen
                = this.listasDeAdyacencia.get(posVerticeOrigen);
        int posVerticeParaDesenlazar1
                = listaDeAdyacentesDelVerticeOrigen.indexOf(posVerticeDestino);
        listaDeAdyacentesDelVerticeOrigen.remove(posVerticeParaDesenlazar1);

        /*A partir de aqui obtendremos la lista de adyacentes del vertice destino
        En caso de que sean iguales los vertices no hay nada mas
        que hacer porque ya se habra eliminado la arista*/
        if (posVerticeOrigen != posVerticeDestino) {

            List<Integer> listaDeAdyacentesDelVerticeDestino
                    = this.listasDeAdyacencia.get(posVerticeDestino);
            int posVerticeParaDesenlazar2
                    = listaDeAdyacentesDelVerticeDestino.indexOf(posVerticeOrigen);
            listaDeAdyacentesDelVerticeDestino.remove(posVerticeParaDesenlazar2);
        }
    }

    public Iterable<Integer> adyacentesDeVertice(int posDeVertice) {
        validarVertice(posDeVertice);
        List<Integer> listaDeAdyacentesDelVertice
                = this.listasDeAdyacencia.get(posDeVertice);
        Iterable<Integer> integerIterable = listaDeAdyacentesDelVertice;
        return integerIterable;
    }

    public int cantidadDeAristas() {
        int cant = 0;
        for (int i = 0; i < this.listasDeAdyacencia.size(); i++) {
            cant = cant + this.gradoDeVertice(i);
        }
        cant = cant / 2;
        return cant;
    }

    public boolean esConexo2() {
        BFS rec = new BFS(this, 0);
        return rec.hayCaminoATodosLosVertice();
    }

   /* public boolean esConexo() {//Mi manera (Version novato)
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
//------------------------------------------------------------------------------

    public boolean tieneCiclos2() throws NroVerticesInvalidoExcepcion, AristaYaExisteExcepcion {
        //Funciona correctamente!
        RecorridoUtils controlMarcados
                = new RecorridoUtils(this.cantidadDeVertices());
        List<Integer> recorrido = new ArrayList<>();
        boolean luzRoja = false;
        List<Integer> anteriores = new ArrayList<>();
        for (int i = 0; i < this.cantidadDeVertices(); i++) {
            anteriores.add(-1);
        }

        Grafo grafoAuxiliar = new Grafo(this.cantidadDeVertices());
        int verticeNoMarcado = 0;
        while (!controlMarcados.estanTodosMarcados() && !luzRoja) {
            luzRoja = ejecutarTieneCiclos(verticeNoMarcado,
                    controlMarcados, recorrido,
                    anteriores, grafoAuxiliar);
            if (!luzRoja) {
                verticeNoMarcado = buscarVertNoMarcado(controlMarcados);
            }

        }
        return luzRoja;
    }

    private int buscarVertNoMarcado(RecorridoUtils controlMarcados) {
        for (int i = 0; i < this.listasDeAdyacencia.size(); i++) {
            boolean estaMarcado = controlMarcados.estaVerticeMarcado(i);
            if (!estaMarcado) {
                return i;
            }
        }
        return -1;
    }

    private boolean ejecutarTieneCiclos(int posVerticeDePartida,
            RecorridoUtils controlMarcados, List<Integer> recorrido,
            List<Integer> anteriores,
            Grafo grafoAuxiliar) throws NroVerticesInvalidoExcepcion, AristaYaExisteExcepcion {

        Stack<Integer> pila = new Stack<>();

        pila.push(posVerticeDePartida);
        int anteriorTurno;

        while (!pila.isEmpty()) {
            int posVerticeEnTurno = pila.pop();
            if (!controlMarcados.estaVerticeMarcado(posVerticeEnTurno)) {
                recorrido.add(posVerticeEnTurno);
            }
            anteriorTurno = anteriores.get(posVerticeEnTurno);
            if (anteriorTurno != -1) {
                if (!controlMarcados.estaVerticeMarcado(posVerticeEnTurno)) {
                    if (existeAdyacencia(anteriorTurno, posVerticeEnTurno)) {
                        grafoAuxiliar.insertarArista(
                                anteriorTurno, posVerticeEnTurno);

                    }
                }
            }

            controlMarcados.marcarVertice(posVerticeEnTurno);

            List<Integer> listaDeAdyacentesEnTurno
                    = this.listasDeAdyacencia.get(posVerticeEnTurno);
            int aux = listaDeAdyacentesEnTurno.size() - 1;
            for (int i = aux; i >= 0; i--) {
                int posVerticeAdy = listaDeAdyacentesEnTurno.get(i);
                if (!controlMarcados.estaVerticeMarcado(posVerticeAdy)) {
                    pila.push(posVerticeAdy);
                    anteriores.set(posVerticeAdy, posVerticeEnTurno);
                } else {
                    if (!grafoAuxiliar.existeAdyacencia(
                            posVerticeEnTurno, posVerticeAdy)) {
                        return true;
                    }
                }

            }

        }
        return false;
    }

    //--------------------------------------------------------------------------
   /* public boolean tieneCiclos1() throws NroVerticesInvalidoExcepcion, AristaYaExisteExcepcion {
        //Metodo inicial valido para una isla right!!
        Grafo grafoAuxiliar = new Grafo(this.cantidadDeVertices());
        RecorridoUtils controlMarcados
                = new RecorridoUtils(this.cantidadDeVertices());
        List<Integer> recorrido = new ArrayList<>();
        Stack<Integer> pila = new Stack<>();
        List<Integer> anteriores = new ArrayList<>();
        for (int i = 0; i < this.cantidadDeVertices(); i++) {
            anteriores.add(-1);
        }
        pila.push(0);
        int anteriorTurno;

        while (!pila.isEmpty()) {
            int posVerticeEnTurno = pila.pop();
            if (!controlMarcados.estaVerticeMarcado(posVerticeEnTurno)) {
                recorrido.add(posVerticeEnTurno);
            }
            anteriorTurno = anteriores.get(posVerticeEnTurno);
            if (anteriorTurno != -1) {
                if (!controlMarcados.estaVerticeMarcado(posVerticeEnTurno)) {
                    if (existeAdyacencia(anteriorTurno, posVerticeEnTurno)) {
                        grafoAuxiliar.insertarArista(
                                anteriorTurno, posVerticeEnTurno);

                    }
                }
            }

            controlMarcados.marcarVertice(posVerticeEnTurno);

            List<Integer> listaDeAdyacentesEnTurno
                    = this.listasDeAdyacencia.get(posVerticeEnTurno);
            int aux = listaDeAdyacentesEnTurno.size() - 1;
            for (int i = aux; i >= 0; i--) {
                int posVerticeAdy = listaDeAdyacentesEnTurno.get(i);
                if (!controlMarcados.estaVerticeMarcado(posVerticeAdy)) {
                    pila.push(posVerticeAdy);
                    anteriores.set(posVerticeAdy, posVerticeEnTurno);
                    /*if (!grafoAuxiliar.existeAdyacencia(
                            posVerticeEnTurno, posVerticeAdy)) {
                        grafoAuxiliar.insertarArista(
                                posVerticeEnTurno, posVerticeAdy);
                    }*/
       /*         } else {
                    if (!grafoAuxiliar.existeAdyacencia(
                            posVerticeEnTurno, posVerticeAdy)) {
                        return true;
                    }
                }

            }

        }
        return false;
    }*/
    //Ejercicio extra

    /*   public int costoCamino(int posVerticeOrigen, int posVerticeDestino) {
        DFS recorridoAyudante = new DFS(
                this, posVerticeOrigen);
        if (recorridoAyudante.controlMarcados.estaVerticeMarcado(
                posVerticeDestino)) {
            List<Integer> rec = (List<Integer>) recorridoAyudante.getRecorrido();
            int costo = 0;
            boolean luzRoja = false;
            for (int i = 0; i < rec.size() && !luzRoja; i++) {

                if (i + 1 != rec.size()) {
                    int posVerticeA = rec.get(i);
                    int posVerticeB = rec.get(i + 1);
                    if (posVerticeB == posVerticeDestino) {
                        luzRoja = true;
                    }
                    costo = costo + posVerticeA + posVerticeB;
                }
            }
            return costo;
        } else {
            return -1;
        }
    }*/
    public String imprimirIslas() throws AristaYaExisteExcepcion {
        DFS rec = new DFS(this);
        List<Integer> verticesPorIsla = new ArrayList<>();
        rec.ejecutarDFSPersonalizado(verticesPorIsla, 0);
        String islas = "-----------------Islas del Grafo----------------\n";
        islas = islas + verticesPorIsla.toString() + "\n";
        while (!rec.controlMarcados.estanTodosMarcados()) {
            int posVerticeNoMarcado = buscarVertNoMarcado(
                    rec.controlMarcados);
            verticesPorIsla=new ArrayList<>();
            rec.ejecutarDFSPersonalizado(verticesPorIsla,
                    posVerticeNoMarcado);
            islas = islas + verticesPorIsla.toString() + "\n";
        }
        System.out.println((List<Integer>)rec.getRecorrido());
        /*como idea se lo puede poner en el sector de campos o atributos con
          una variable llamada recorridoDeTodoElGrafo*/
        return islas;
    }

    @Override
    public String toString() {

        String graph = "------------------------GRAPH:----------------------\n";
        for (int i = 0; i < this.listasDeAdyacencia.size(); i++) {
            List<Integer> listaDeAdy = this.listasDeAdyacencia.get(i);
            if (listaDeAdy.isEmpty()) {
                graph = graph + i + " | |-> null";
            } else {
                graph = graph + i + " | |-> ";
                for (Integer posDatoAdy : listaDeAdy) {
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
