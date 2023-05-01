/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.grafos.excepciones;

/**
 *
 * @author 59178
 */
public class AristaYaExisteExcepcion extends Exception {

    public AristaYaExisteExcepcion() {
        super("Ya existe una arista entre esos vertices");
    }

    public AristaYaExisteExcepcion(String message) {
        super(message);
    }
    
}
