/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Usuario
 */
public class Pozo {

    private Integer id;
    private String descripcion;
    private String foto1;
    private String foto2;
    private Coordenada coordenada;

    public Pozo() {
    }

    public Pozo(Integer id, String Descripcion, String foto1, String foto2, Coordenada coordenada) {
        this.id = id;
        this.descripcion = Descripcion;
        this.foto1 = foto1;
        this.foto2 = foto2;
        this.coordenada = coordenada;
    }

    public Coordenada getCoordenada() {
        if (coordenada == null) {
            coordenada = new Coordenada();
        }
        return coordenada;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }

    public String getFoto1() {
        return foto1;
    }

    public void setFoto1(String foto1) {
        this.foto1 = foto1;
    }

    public String getFoto2() {
        return foto2;
    }

    public void setFoto2(String foto2) {
        this.foto2 = foto2;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.descripcion = Descripcion;
    }

}
