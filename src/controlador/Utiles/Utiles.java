/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.utiles;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author Usuario
 */
public class Utiles {

    public static boolean validadorDeCedula(String cedula) {
        if (cedula.length() != 10) {
            return false;
        }

        for (int i = 0; i < cedula.length(); i++) {
            if (!Character.isDigit(cedula.charAt(i))) {
                return false;
            }
        }

        int suma = 0;
        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(cedula.charAt(i));
            if (i % 2 == 0) {
                digito *= 2;
                if (digito > 9) {
                    digito -= 9;
                }
            }
            suma += digito;
        }

        int modulo = suma % 10;
        int digitoVerificador = (modulo == 0) ? 0 : 10 - modulo;

        return digitoVerificador == Character.getNumericValue(cedula.charAt(9));
    }

    public static Field getField(Class clazz, String attribute) {
        Field field = null;
        //Field[] fields = clazz.getFields();//ver atributos declarados en la clase padre
        for (Field f : clazz.getSuperclass().getDeclaredFields()) {
            System.out.println(f.getName() + " " + f.getType().getName());
            if (f.getName().equalsIgnoreCase(attribute)) {
                field = f;
                break;
            }
        }
        for (Field f : clazz.getDeclaredFields()) {
            System.out.println(f.getName() + " " + f.getType().getName());
            if (f.getName().equalsIgnoreCase(attribute)) {
                field = f;
                break;
            }
        }
        return field;
    }

    public static String getDirPoject() {
        return System.getProperty("user.dir");
    }

    public static String getOS() {
        return System.getProperty("os.name");
    }

    public static void abrirNavegadorPredeterminadorWindows(String url) throws Exception {
        //Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
               try {
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "start", url);
            builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void abrirNavegadorPredeterminadorLinux(String url) throws Exception {
        Runtime.getRuntime().exec("xdg-open " + url);
    }

    public static void abrirNavegadorPredeterminadorMacOsx(String url) throws Exception {
        Runtime.getRuntime().exec("open " + url);
    }
    //PARA GUARDAR ARCHIVO EN UTILES
    public static void copiarArchivo(File origen, File destino) throws Exception {
        Files.copy(origen.toPath(),
                (destino).toPath(),
                StandardCopyOption.REPLACE_EXISTING);
    }

    //EN UTILES EXTENSION
    public static String extension(String fileName) {
        String extension = "";

        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i + 1);
        }
        return extension;
    }

    public static double coordGpsToKm(double lat1, double lon1, double lat2, double lon2) {
        double lat1rad = Math.toRadians(lat1);
        double lon1rad = Math.toRadians(lon1);
        double lat2rad = Math.toRadians(lat2);
        double lon2rad = Math.toRadians(lon2);

        double difLatitud = lat1rad - lat2rad;
        double difLongitud = lon1rad - lon2rad;

        double a = Math.pow(Math.sin(difLatitud / 2), 2)
                + Math.cos(lat1rad)
                * Math.cos(lat2rad)
                * Math.pow(Math.sin(difLongitud / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double radioTierraKm = 6378.0;
        double distancia = radioTierraKm * c;

        return distancia;
    }
    
    public static Double redondear(Double x){
        return Math.round(x * 100.0 ) / 100.0;
    }

   

}
