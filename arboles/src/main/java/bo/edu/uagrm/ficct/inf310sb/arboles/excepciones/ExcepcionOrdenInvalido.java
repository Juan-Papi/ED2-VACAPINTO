/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.arboles.excepciones;

/**
 *
 * @author 59178
 */
public class ExcepcionOrdenInvalido extends Exception {
 
      //Tambien hay-> RunTimeException
    //Luego si queremos podemos sobreescribir algunos de los métodos
    
    public ExcepcionOrdenInvalido() {
        super("Arbol con orden inválido");
    //Con super decimos que "La clase padre que reciba Arbol con orden inv..."
    }

    public ExcepcionOrdenInvalido(String message) {
        super(message);
    }
  
    
}
