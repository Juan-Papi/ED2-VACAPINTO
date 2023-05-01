/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.grafos.pesados;

import bo.edu.uagrm.ficct.inf310sb.grafos.Grafo;
import bo.edu.uagrm.ficct.inf310sb.grafos.excepciones.AristaNoExisteExcepcion;
import bo.edu.uagrm.ficct.inf310sb.grafos.excepciones.AristaYaExisteExcepcion;
import bo.edu.uagrm.ficct.inf310sb.grafos.excepciones.NroVerticesInvalidoExcepcion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 59178
 */
public class AlgKruskal {

    GrafoPesado unGrafo;

    public AlgKruskal(GrafoPesado unGrafo) throws NroVerticesInvalidoExcepcion, AristaYaExisteExcepcion, AristaNoExisteExcepcion {
        this.unGrafo = unGrafo;

    }
    //Retorna el grafoPesado de expansion o arbol de expansion

    public GrafoPesado ejecutarAlgKruskal() throws NroVerticesInvalidoExcepcion, AristaYaExisteExcepcion, AristaNoExisteExcepcion {

        GrafoPesado grafoPesadoAuxiliar1 = new GrafoPesado(this.unGrafo.cantidadDeVertices());
        //El grafo2 es para verificar si hay ciclos
        Grafo grafoAuxiliar2 = new Grafo(this.unGrafo.cantidadDeVertices());
        List<AdyacenteConPeso> adyacentesConPesoMasElPadre = new ArrayList<>();

        for (int i = 0; i < this.unGrafo.listasDeAdyacencia.size(); i++) {
            List<AdyacenteConPeso> listaDeAdyacentes = this.unGrafo.listasDeAdyacencia.get(i);
            AdyacenteConPeso adyacenteConPesoMasPadre;
            for (AdyacenteConPeso adyConPeso : listaDeAdyacentes) {
                double peso = adyConPeso.getPeso();
                int indiceVertice = adyConPeso.getIndiceDeVertice();
                adyacenteConPesoMasPadre = new AdyacenteConPeso(
                        indiceVertice, peso, i);
                adyacentesConPesoMasElPadre.add(adyacenteConPesoMasPadre);
            }

        }//cuando sale del for principal todos los adyacentes estan con el dato
        //de quien es su padre
        List<AdyacenteConPeso> adyacentesConPesoMasElPadreOrdenados = ordenarPorPeso(adyacentesConPesoMasElPadre);

        for (AdyacenteConPeso adyacenteConPesoMaElPadre : adyacentesConPesoMasElPadreOrdenados) {
            int padreDeAdy = adyacenteConPesoMaElPadre.getPadreDelAdyacenteConPeso();
            int ady = adyacenteConPesoMaElPadre.getIndiceDeVertice();
            double peso = adyacenteConPesoMaElPadre.getPeso();
            if (!grafoAuxiliar2.existeAdyacencia(padreDeAdy, ady)) {
                grafoPesadoAuxiliar1.insertarArista2(padreDeAdy, ady, peso);
                grafoAuxiliar2.insertarArista(padreDeAdy, ady);
                if (grafoAuxiliar2.tieneCiclos2()) {
                    grafoPesadoAuxiliar1.eliminarArista(padreDeAdy, ady);
                    grafoAuxiliar2.eliminarArista(padreDeAdy, ady);
                }
            }

        }
        return grafoPesadoAuxiliar1;
    }

    public List<AdyacenteConPeso> ordenarPorPeso(List<AdyacenteConPeso> adyacentesAOrdenar) {
        int limite = adyacentesAOrdenar.size();
        List<AdyacenteConPeso> adyacentesOrdenados = new ArrayList<>();
        for (int i = 0; i < limite; i++) {
            AdyacenteConPeso adyConMenorPeso = buscarYEliminarAdyConMenorPeso(adyacentesAOrdenar);
            adyacentesOrdenados.add(adyConMenorPeso);
        }
        return adyacentesOrdenados;
    }

    public AdyacenteConPeso buscarYEliminarAdyConMenorPeso(
            List<AdyacenteConPeso> adyacentes) {
        boolean luzRoja = false;
        double menorPeso = Double.POSITIVE_INFINITY;
        int posicionDondeEstaElMenorPeso = -1;
        for (int i = 0; i < adyacentes.size() && !luzRoja; i++) {
            AdyacenteConPeso adyConPeso = adyacentes.get(i);
            if (adyConPeso.getPeso() < menorPeso) {
                menorPeso = adyConPeso.getPeso();
                posicionDondeEstaElMenorPeso = i;
            }
        }
        AdyacenteConPeso adyConMenorPeso = adyacentes.get(posicionDondeEstaElMenorPeso);
        adyacentes.remove(posicionDondeEstaElMenorPeso);
        return adyConMenorPeso;
    }

    /*public int compararPesos(AdyacenteConPeso adyacente1, AdyacenteConPeso adyacente2) {
        double estePeso1 = adyacente1.getPeso();
        double elOtroPeso2 = adyacente2.getPeso();
        return (int) ((estePeso1 > elOtroPeso2) ? 1.0 : (estePeso1 < elOtroPeso2) ? -1.0 : 0.0);
    }*/
}
