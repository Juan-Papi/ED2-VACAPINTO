/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.grafos.pesados;

/**
 *
 * @author 59178
 */
public class AdyacenteConPeso implements Comparable<AdyacenteConPeso>{

    private int indiceDeVertice;
    private double peso;
    private int padreDelAdyacenteConPeso;

    public AdyacenteConPeso(int indiceDeVertice) {
        this.indiceDeVertice = indiceDeVertice;
    }

    public AdyacenteConPeso(int indiceDeVertice, double peso) {
        this.indiceDeVertice = indiceDeVertice;
        this.peso = peso;
    }
    //extra
    public AdyacenteConPeso(int indiceDeVertice, double peso,int padre) {
        this.indiceDeVertice = indiceDeVertice;
        this.peso = peso;
        this.padreDelAdyacenteConPeso=padre;
    }
    public int getIndiceDeVertice() {
        return indiceDeVertice;
    }

    public double getPeso() {
        return peso;
    }
    //extra
    public int getPadreDelAdyacenteConPeso(){
        return this.padreDelAdyacenteConPeso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setIndiceDeVertice(int indiceDeVertice) {
        this.indiceDeVertice = indiceDeVertice;
    }
    
    @Override
    public int compareTo(AdyacenteConPeso elOtroAdyacente) {
        Integer esteVertice=this.indiceDeVertice;
        Integer elOtroVertice=elOtroAdyacente.indiceDeVertice;
        return esteVertice.compareTo(elOtroVertice);
    }
    
   
    @Override
    public boolean equals(Object otroAdyacente){
        if(this==otroAdyacente) return true;
        if(otroAdyacente==null ||getClass()!=otroAdyacente.getClass())return false;
        AdyacenteConPeso that=(AdyacenteConPeso) otroAdyacente;
        return indiceDeVertice==that.indiceDeVertice;
    }

    @Override
    public String toString() {
         String adyConPeso="";
         adyConPeso="{ "+this.indiceDeVertice+", "+this.peso+" }";
        return adyConPeso;
    }
    
    

}
