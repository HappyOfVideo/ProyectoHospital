package com.vaadin.proyectofinalvaadin.Controller;

import java.io.*;

public class Procesos {

    // Globales

    public static final String ARCHIVO = "src\\main\\java\\com\\vaadin\\proyectofinalvaadin\\src\\";
    public static final String USUARIOS =  "Usuarios.txt";

    public static String Validacion() throws Exception{

        try {
            String tipoMenu = "";
            
            String linea = "";

            String [] lineaVoluntarioSeparada = null;

            String idVoluntario = "";

            String nombreVoluntario = "";

            String proyectoRelacionados = "";

            // Procesos

            /*datosVoluntario.readLine(); // Lee la linea del encabezado

            while ( (linea = datosVoluntario.readLine()) != null ) {
                
                if (linea.trim().isEmpty()){
                    continue;
                }

                lineaVoluntarioSeparada = linea.trim().split(";");

                idVoluntario = lineaVoluntarioSeparada [ 0 ].trim();

                nombreVoluntario = lineaVoluntarioSeparada [ 1 ].trim();

                archivoEscritura = new FileWriter(RUTA_ARCHIVO_ESCRITURA + nombreVoluntario + ".txt");

                escritura = new PrintWriter(archivoEscritura);

                escritura.println(idVoluntario + " - " + nombreVoluntario + "\n\n");

                proyectoRelacionados = buscarProyecto(idVoluntario);

                escritura.println(proyectoRelacionados + "\n\n");

                escritura.close();

            }*/

            return tipoMenu;

        } //catch (IOException io) {

            //throw new Exception("Error en la consulta de archivos" + io.getMessage());

        //}
        catch (Exception e) {

            throw new Exception("Error generico" + e.getMessage());
        }

    }

}