/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package controlador.TDA.grafos.exception;

/**
 *
 * @author Alejandro
 */
public class VerticeException extends Exception {

    /**
     * Creates a new instance of <code>VerticeException</code> without detail message.
     */
    
    public VerticeException(String msg) {
        super(msg);
    }

    public VerticeException() {
        super("Vertice fuera de rango");
    }
}
