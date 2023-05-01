/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.grafos.excepciones;

/**
 *
 * @author 59178
 */
public class NroVerticesInvalidoExcepcion extends Exception{

    public NroVerticesInvalidoExcepcion() {
        super("Numero de vertices invalido!");
    }

    public NroVerticesInvalidoExcepcion(String message) {
        super(message);
    }
    
}
