/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.grafos;

/**
 *
 * @author 59178
 */
public class AlgWarshall {

    //private int[][] matrizDeAdyacencia;
    /*Matriz de camino (Warshall path)*/
    private boolean[][] warshallC;
    DiGrafo unDiGrafo;

    public AlgWarshall(DiGrafo unDiGrafo) {
        this.unDiGrafo = unDiGrafo;
        int cantVertices = this.unDiGrafo.cantidadDeVertices();
        // this.matrizDeAdyacencia = new int[cantVertices][cantVertices];
        this.warshallC = new boolean[cantVertices][cantVertices];
        for (int i = 0; i < cantVertices; i++) {
            for (int j = 0; j < cantVertices; j++) {
                if (this.unDiGrafo.existeAdyacencia(i, j)) {
                    //     this.matrizDeAdyacencia[i][j] = 1;
                    this.warshallC[i][j] = Boolean.TRUE;
                } else {
                    //   this.matrizDeAdyacencia[i][j] = 0;
                    this.warshallC[i][j] = Boolean.FALSE;
                }
            }
        }
        ejecutarWarshall();
    }

    public void ejecutarWarshall() {
        int cantVertices = this.unDiGrafo.cantidadDeVertices();
        for (int k = 0; k < cantVertices; k++) {
            for (int i = 0; i < cantVertices; i++) {
                for (int j = 0; j < cantVertices; j++) {
                    this.warshallC[i][j] = (warshallC[i][j]
                            || (warshallC[i][k] && warshallC[k][j]));
                }
            }
        }
    }

    public boolean[][] getMatrizDeCaminos() {
        return this.warshallC;
    }
}
