/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.proyecto1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author 59178
 */
public class PostFija {

    //Atributos(Campos)
    String entrada;//la expresion que queremos leer
    int lector;//Recorre la cadena de entrada 
    //variable global

    Queue<String> salida;//PostFija(RPN)(los numeros pasan directo)
    //Stack
    Stack<String> pila1;//(Entran Operadores y paréntesis)

    //Constructor default(De oficio)
    public PostFija() {
        entrada = "";
        salida = new LinkedList<>();
        pila1 = new Stack();
        this.lector = 0;
    }
    //Modificadores
    /* public void setEntrada(String Entrada) {//Antiguo
        int i=0;
        String aux=""; 
        
        //Eliminamos los espacios en blanco de la cadena de entrada
        while(i<=Entrada.length()-1){
            if(Entrada.charAt(i)!=' '){
                aux=aux+Entrada.charAt(i);
            }
            i++;
        }
        this.Entrada =aux;
    }*/
    /**
     * La entrada es la expresión que queremos leer para convertir
     * a notacion postfija o RPN.
     * @param entrada 
     */
    public void setEntrada(String entrada) {//soporta ejemplo:(-2+3)
        int i = 0;
        String aux = "";
        
        //Eliminamos los espacios en blanco de la cadena de entrada
        while (i <= entrada.length() - 1) {
            //System.out.println(lector);  

            //nueva condicion: soporta ejemplo: -(8*40)
            if (entrada.charAt(i) == '-' && i == 0 && entrada.charAt(i + 1) == '(') {
                aux = aux + '(' + '0' + '-' + '1' + ')' + '*';
            }
            if (entrada.charAt(0) == '-' && i == 0) {
                aux = aux + '(' + '0' + '-';
                i++;
                while ((i <= entrada.length() - 1)&& esDigito(String.valueOf(entrada.charAt(i)))) {
                    aux = aux + entrada.charAt(i);
                    i++;
                }
                aux = aux + ')';
                if(i > entrada.length() - 1){
                    this.entrada=aux;
                    return;
                }
                //System.out.println(i+"hola");
            }
            if (entrada.charAt(i) == '-' && entrada.charAt(i - 1) == '(') {
                aux = aux + '(' + '0' + '-';
                i++;
                while (esDigito(String.valueOf(entrada.charAt(i)))) {
                    aux = aux + entrada.charAt(i);
                    i++;
                }
                aux = aux + ')';
                //System.out.println(i+"hola");
            }

            if (entrada.charAt(i) != ' ') {
                aux = aux + entrada.charAt(i);
            }

            //System.out.println(aux + " hola");
            i++;
        }
        
        this.entrada = aux;
    }

    public void setLector(int lec) {
        this.lector = lec;
    }

    //Selectores
    public String getEntrada() {
        return this.entrada;
    }

    //double 8bytes; 1 byte 8 bits =64 bits [-2^(64-1),+2^(64-1)-1]
    public boolean esDigito(String car) {
        String dig = "0123456789";
        return (dig.contains(car));
        //dig.contains verifica si lo que hay en car pertenece a la cadena dig 
        //Devuelve true si pertenece

    }

    public boolean esOperador(String cad) {
        return ("+".equals(cad) || "-".equals(cad) || "*".equals(cad) || "/".equals(cad)) || "^".equals(cad);
    }

    public boolean esParentesis(String cad) {
        return (cad.equals("(") || (cad.equals(")")));
    }
    /**
     * Lee el número, operador o paréntesis y lo devuelve
     * en una cadena.
     * @return 
     */
    public String leerToken() {
        String token = "";
        if (esOperador(String.valueOf(entrada.charAt(lector)))) {
            token = String.valueOf(entrada.charAt(lector));
        } else {
            if (!esParentesis(String.valueOf(entrada.charAt(lector)))) {//entrada=85+(92*1) //op=85
                //entra si es numero                              //j=0,1,2 //token=85 //Lector=1,2,

                String operando = "";
                int j = lector;
                while ((j <= entrada.length() - 1) && (!esOperador(String.valueOf(entrada.charAt(j))))
                        && (!esParentesis(String.valueOf(entrada.charAt(j))))) {
                    operando = operando + String.valueOf(entrada.charAt(j));
                    j++;
                }
                token = operando;
                lector = j - 1;
            } else {
                //Devuelve el paréntesis
                token = String.valueOf(entrada.charAt(lector));
            }
        }

        return token;
    }
   /**
    * Dado un operador retorna cual es su jerarquía
    * @param operador
    * @return 
    */
    public int jerarquia(String operador) {
        int jerarquia;
        if ("+".equals(operador) || "-".equals(operador)) {
            jerarquia = 0;
        } else {
            if ("*".equals(operador) || "/".equals(operador)) {
                jerarquia = 1;
            } else {
                if ("^".equals(operador)) {
                    jerarquia = 2;
                } else {
                    jerarquia = -1;//Operador no reconocido
                }
            }
        }
        return jerarquia;
    }

   /**
    * Convierte de notacion infija o normal a notacion postfija
    * o notacion polaca inversa (Reverse Polish Notation)(RPN)
    */
    public void convertir() {//entrada=85+(92*1)
        //lector=
        String lecturado = "";
        boolean necesitaOtraVuelta = false;
        while (lector <= (this.entrada.length() - 1)) {
            if (necesitaOtraVuelta == false) {
                lecturado = leerToken();
            }
            necesitaOtraVuelta = false;
            if (esOperador(lecturado)) {
                //Entra si es operador
                if (!pila1.isEmpty()) {
                    if (esOperador(pila1.peek())) {//El peek lo que hace es 
                        //obtener el dato del tope de la pila(de la cima de la pila)
                        if (jerarquia(lecturado) <= jerarquia(pila1.peek())) {
                            salida.offer(pila1.pop());
                            necesitaOtraVuelta=true;
                            lector--;

                        } else {
                            pila1.push(lecturado);
                        }
                    } else {
                        pila1.push(lecturado);
                    }

                } else {
                    pila1.push(lecturado);
                }

            } else {
                if (!esParentesis(lecturado)) {
                    //entra si es (numero)
                    salida.offer(lecturado);
                } else {
                    //Entra si es Paréntesis
                    if ("(".equals(lecturado)) {
                        //Entra si es paréntesis abierto
                        pila1.push(lecturado);

                    } else {
                        //Entra si es paréntesis cerrado

                        String dato = pila1.peek();//*,(
                        while ((!pila1.isEmpty()) && (!"(".equals(dato))) {
                            salida.offer(pila1.pop());

                            dato = pila1.peek();

                        }
                        if (pila1.isEmpty()) {
                            System.out.println("Error::convertir:Parentesis sin pareja");
                            System.exit(1);
                        } else {
                            pila1.pop();
                        }
                    }
                }
            }
            lector++;
        }

        while (!pila1.isEmpty()) {
            if ("(".equals(pila1.peek())) {
                System.out.println("Error:Convertir:Parentesis sin pareja");
                System.exit(1);
            } else {
                salida.offer(pila1.pop());
            }
        }

    }
    /**
     * Devuelve la RPN o notacion PostFija en una cadena.
     * @return 
     */
    public String toString() {
        String S = "";
        Queue<String> colaAux = new LinkedList<>();
        while (!salida.isEmpty()) {
            colaAux.offer(salida.peek());
            S = S + salida.poll();
        }
        //volviendo a la cola como antes
        salida = colaAux;
        return S;
    }
    /**
     * Devuelve lo que hay en la cola de salida es
     * decir devuelve lo que hemos convertido de notacion normal 
     * o infija a notacion polaca inversa(RPN).
     * Retorna la RPN! 
     * @return 
     */
    public Queue<String> colaDeCadenas() {
        Queue<String> colaAux = new LinkedList<>();
        Queue<String> colaAux2 = new LinkedList<>();
        while (!salida.isEmpty()) {
            colaAux.offer(salida.peek());
            colaAux2.offer(salida.poll());
        }
        salida = colaAux2;
        return colaAux;
    }
}
