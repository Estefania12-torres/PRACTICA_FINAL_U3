/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.dao;
import Controlador.TDA.Listas.ListaEnlazada;
import com.thoughtworks.xstream.XStream;
import controlador.TDA.listas.Exception.EmptyException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class DaoImplements <T>implements daoInterface<T>{
    private final Class<T>  clazz;
    private final XStream conection;
    private final String  URL;

    public DaoImplements(Class<T> clazz) {
        this.clazz = clazz;
        conection = Bridge.getConection();
        URL = Bridge.URL+clazz.getSimpleName()+ ".json";
    }

    
    public Boolean persist(T data) {
        ListaEnlazada <T>  Id = all();
        Id.add(data);
        try{
            conection.toXML(Id, new FileWriter(URL));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Boolean merge(T data, Integer index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public ListaEnlazada <T> all(){
        ListaEnlazada<T> dl = new ListaEnlazada<>();
        try{
            dl = (ListaEnlazada<T>) conection.fromXML(new FileReader(URL));
        }catch (Exception e){
            
        }
        return dl;
    }
       
    public T get(Integer id) {
        ListaEnlazada<T> ld = all();
        try {
            return ld.getInfo(id);
        } catch (EmptyException ex) {
            Logger.getLogger(DaoImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     public XStream getConection() {
        return conection;
    }
    
}
