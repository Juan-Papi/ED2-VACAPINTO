/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.arboles.excepciones;

/**
 *
 * @author 59178
 */
public class ExcepcionClaveNoExiste extends RuntimeException {

    public ExcepcionClaveNoExiste() {
        this("Clave no existe en su estructura");
    }

    public ExcepcionClaveNoExiste(String message) {
        super(message);
    }
    
}
