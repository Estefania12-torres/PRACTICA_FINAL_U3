/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista.Listas.Tabla;

import Controlador.utiles.Utiles;
import controlador.TDA.grafos.GrafosEtiquetadosDirigidos;
import javax.swing.table.AbstractTableModel;
import modelo.Pozo;

/**
 *
 * @author Usuario
 */
public class ModeloTablaAdyacenciaPozo extends AbstractTableModel{
    
    private GrafosEtiquetadosDirigidos<Pozo> grafo;

    @Override
    public int getRowCount() {
        return grafo.num_vertice();
    }

    @Override
    public int getColumnCount() {
        return grafo.num_vertice() + 1;
    }

    @Override
    public String getColumnName(int column) {
        if (column == 0) {
            return "ESCUELA";
        } else {
            try {
                return grafo.getLabelE(column).toString();
            } catch (Exception e) {
                return "";
            }
        }
    }

    public GrafosEtiquetadosDirigidos getGrafo() {
        return grafo;
    }

    public void setGrafo(GrafosEtiquetadosDirigidos grafo) {
        this.grafo = grafo;
    }
    @Override
   

    public Object getValueAt(int i, int i1) {
        try {
            if (i1 == 0) {
                return grafo.getLabelE(i + 1).toString();
            } else {
                Pozo p = grafo.getLabelE(i + 1);
                Pozo o = grafo.getLabelE(i1);
                if (grafo.isEdge(o, p)) {
                    return Utiles.redondear(grafo.peso_arista(i + 1, i1)).toString();
                } else {
                    return "--";
                }
            }
        } catch (Exception e) {
            return "";
        }
    }
}

