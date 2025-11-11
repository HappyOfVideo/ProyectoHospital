package com.vaadin.proyectofinalvaadin.Controller;

import java.io.*;

public class Procesos {

    // Globales

    public static final String RUTA = "src\\main\\java\\com\\vaadin\\proyectofinalvaadin\\src\\";

    public static Boolean Validacion(String tipo, Double user, String passw) throws Exception {

        String archivoUsuarios = "Usuarios.txt";
        File ubicaciónArchivo = null;
        FileReader archivoLectura = null;
        BufferedReader datosArchivo = null;

        try {
            ubicaciónArchivo = new File(RUTA + archivoUsuarios);
            archivoLectura = new FileReader(ubicaciónArchivo);
            datosArchivo = new BufferedReader(archivoLectura);

            String linea = "";
            String[] datosSeparados = null;
            String datoTipo = "";
            String datoUser = "";
            String datoPassw = "";
            Boolean credencialesValidas = false;

            datosArchivo.readLine();
            while ((linea = datosArchivo.readLine()) != null) {

                if (linea.trim().isEmpty()) {
                    continue;
                }

                datosSeparados = linea.trim().split(";"); // Separa los datos en los ";"
                datoTipo = datosSeparados[1].trim(); // Lee la columna 2 del archivo (Tipo de usuario)
                datoUser = datosSeparados[2].trim(); // Lee la columna 3 del archivo (Usuario)
                datoPassw = datosSeparados[3].trim(); // Lee la columna 4 del archivo (Contraseña)

                if (datoTipo.equalsIgnoreCase(tipo)
                        && datoUser.equals(String.valueOf(user.longValue()))
                        && datoPassw.equals(passw)) {
                    credencialesValidas = true;
                    break;
                }

            }

            return credencialesValidas;

        } catch (IOException io) {
            throw new Exception("Error en la consulta de archivos" + io.getMessage());
        } catch (Exception e) {
            throw new Exception("Error generico" + e.getMessage());
        } finally {
            datosArchivo.close();
        }

    }

    public static void ListaPacientes(String nombre, String dI, String habitacion){

        try {
            
            

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

}