/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista.Listas.Tabla;

import Controlador.TDA.Listas.ListaEnlazada;
import javax.swing.table.AbstractTableModel;
import modelo.Pozo;

/**
 *
 * @author Usuario
 */
public class ModeloTablaPozo extends AbstractTableModel {

    private ListaEnlazada<Pozo> pozos = new ListaEnlazada<>();

    public ListaEnlazada<Pozo> getPozos() {
        return pozos;
    }

    public void setPozos(ListaEnlazada<Pozo> escuelas) {
        this.pozos = escuelas;
    }

    @Override
    public int getRowCount() {
        return pozos.getLength();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Pozo p = null;
        try {
            p = pozos.getInfo(i);
        } catch (Exception ex) {
        }
        switch (i1) {
            case 0:
                return (p != null) ? p.getId() : "";

            case 1:
                return (p != null) ? p.getDescripcion() : "";
            case 2:
                return (p != null) ? p.getCoordenada().getLatitud(): "";
            case 3:
                return (p != null) ? p.getCoordenada().getLongitud(): "";
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "DESCRIPCION";
            case 2:
                return "LATITUD";
            case 3:
                return "LONGITUD";
            default:
                return null;
        }
    }
}
