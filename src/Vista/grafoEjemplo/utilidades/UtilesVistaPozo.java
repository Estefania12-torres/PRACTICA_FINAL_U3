/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.grafoEjemplo.utilidades;

import Controlador.TDA.Listas.ListaEnlazada;
import Controlador.utiles.Utiles;
import controlador.PozoControl;
import controlador.TDA.grafos.GrafosEtiquetadosDirigidos;
import java.io.FileWriter;
import javax.swing.JComboBox;
import modelo.Pozo;

/**
 *
 * @author Santiago
 */
public class UtilesVistaPozo{

    public static void crearMapaEscuela(GrafosEtiquetadosDirigidos<Pozo> ge) throws Exception {
        String maps = "var osmUrl = 'https://tile.openstreetmap.org/{z}/{x}/{y}.png',\n"
                + "                    osmAttrib = '&copy; <a href=\"https://www.openstreetmap.org/copyright\">OpenStreetMap</a> contributors',\n"
                + "                    osm = L.tileLayer(osmUrl, {maxZoom: 15, attribution: osmAttrib});\n"
                + "\n"
                + "            var map = L.map('map').setView([-4.036, -79.201], 15);\n"
                + "\n"
                + "            L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {\n"
                + "                attribution: '&copy; <a href=\"https://www.openstreetmap.org/copyright\">OpenStreetMap</a> contributors'\n"
                + "            }).addTo(map); + \n";

        for (int i = 1; i <= ge.num_vertice(); i++) {
            Pozo ec = ge.getLabelE(i);
            maps += "L.marker([" + ec.getCoordenada().getLongitud()+ ", " + ec.getCoordenada().getLatitud()+ "]).addTo(map)\n";
            maps += ".bindPopup('" + ec.toString() + "')\n";
            maps += ".openPopup();";
        }
        FileWriter file = new FileWriter("mapas/mapa.js");
        file.write(maps);
        file.close();

//        L.marker([-4.045, -79.2015]).addTo(map)
//                    .bindPopup('1.')
//                    .openPopup();
    }

    public static void cargarComboPozo(JComboBox cbx) throws Exception {
        cbx.removeAllItems();
        ListaEnlazada<Pozo> list = new PozoControl().getPozos();
        for (int i = 0; i < list.getLength(); i++) {
            cbx.addItem(list.getInfo(i));
        }
    }

    public static Double calcularDistanciasPozo(Pozo o, Pozo d) {
        Double dist = Utiles.coordGpsToKm(o.getCoordenada().getLatitud(), o.getCoordenada().getLongitud(),
                                          d.getCoordenada().getLatitud(), d.getCoordenada().getLongitud());
        return redondear(dist);
    }
    
    public static Double redondear(Double x){
        Double d = Math.round(x *100.0)/100.0;
        return d;
    }
}
