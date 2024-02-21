/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import Controlador.TDA.Listas.ListaEnlazada;
import Controlador.dao.DaoImplements;
import com.thoughtworks.xstream.XStream;
import controlador.TDA.grafos.GrafosEtiquetadosNoDirigidos;
import controlador.TDA.listas.Exception.EmptyException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import modelo.Pozo;

/**
 *
 * @author Usuario
 */
public class PozoControl extends DaoImplements<Pozo> {

    private ListaEnlazada<Pozo> pozos = new ListaEnlazada<>();

    private Pozo pozo;
    private GrafosEtiquetadosNoDirigidos<Pozo> grafo;

    public PozoControl() {
        super(Pozo.class);
        pozos = new ListaEnlazada<>();
    }

    // Getters and Setters
    public GrafosEtiquetadosNoDirigidos<Pozo> getGrafo() throws Exception {
        if (grafo == null) {
            ListaEnlazada<Pozo> list = getPozos();
            if (!list.isEmpty()) {
                grafo = new GrafosEtiquetadosNoDirigidos<>(list.getLength(), Pozo.class);
                for (int i = 0; i < list.getLength(); i++) {
                    grafo.labelVertice((i + 1), list.getInfo(i));
                }
            }
        }
        return grafo;
    }

    public void setGrafo(GrafosEtiquetadosNoDirigidos<Pozo> grafo) {
        this.grafo = grafo;
    }

    public ListaEnlazada<Pozo> getPozos() {
        if (pozos.isEmpty()) {
            pozos = all();
        }
        return pozos;
    }

    public void setPozos(ListaEnlazada<Pozo> pozos) {
        this.pozos = pozos;
    }

    public Pozo getPozo() {
        if (pozo == null) {
            pozo = new Pozo();
        }
        return pozo;
    }

    public void setPozo(Pozo pozo) {
        this.pozo = pozo;
    }

    public Boolean persist() {
        pozo.setId(all().getLength());
        return persist(pozo);
    }

    public void loadGraph() throws FileNotFoundException, Exception {
        grafo = (GrafosEtiquetadosNoDirigidos<Pozo>) getConection()
                .fromXML(new FileReader("files/grafo.json"));
        pozos.reset();
        for (int i = 0; i < grafo.num_vertice(); i++) {
            pozos.add(grafo.getLabelE(i));

        }

    }

    public void guardarGrafo() throws Exception {
        if (grafo != null) {
            this.getConection().alias(grafo.getClass().getName(), GrafosEtiquetadosNoDirigidos.class);
            this.getConection().toXML(grafo, new FileWriter("data/grafo.json"));
        } else {
            new NullPointerException("Grafo Vacío");
        }
    }
    
}
