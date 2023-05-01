/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.grafos.utilería;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 59178
 */
public class RecorridoUtils {
    private List<Boolean> marcados;
    
    public RecorridoUtils(int nroVertices){
        this.marcados=new ArrayList<>();
        for (int i = 0; i < nroVertices; i++) {
            this.marcados.add(Boolean.FALSE);
        }
    }
    public void desmarcarTodos(){
        for (int i = 0; i < this.marcados.size(); i++) {
            this.marcados.set(i, Boolean.FALSE);
        }
    }
    public void marcarVertice(int posVertice){
        //Precondicion: posicion vertice es una posicion valida
        this.marcados.set(posVertice, Boolean.TRUE);
    }
    public boolean estaVerticeMarcado(int posVertice){
        return this.marcados.get(posVertice);
    }
    public boolean estanTodosMarcados(){
        for (boolean marcado:this.marcados) {
            if(!marcado){
                return false;
            }
        }
        return true;
    }
    
    
}
