/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.TDA.Listas.Exception;

/**
 *
 * @author Usuario
 */
public class VerticeException  extends Exception {
        public VerticeException(String msg) {
        super(msg);
    }

    public VerticeException() {
        super("Vertice fuera de rango");
    }
    
}
