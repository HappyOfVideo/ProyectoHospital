package com.vaadin.proyectofinalvaadin.Controller;

import java.io.*;

public class Procesos {

    // Globales
    public static final String RUTA = "src\\main\\java\\com\\vaadin\\proyectofinalvaadin\\src\\"; // Ruta carpeta de
                                                                                                  // archivos
    public static final String RUTA_CARPETA_PACIENTES = "src\\main\\java\\com\\vaadin\\proyectofinalvaadin\\src\\pacientes\\";

    public static String nombreUsuarioActual = "";

    public static Boolean Validacion(String tipo, Double user, String passw) throws Exception {

        // contexto de la funcion

        File ubicaciónArchivo = null;
        FileReader archivoLectura = null;
        BufferedReader datosArchivo = null;

        try {

            // Variables para lectura
            String archivoUsuarios = "Usuarios.txt"; // Archivo exacto donde se encuentran los usuarios de ingreso
            ubicaciónArchivo = new File(RUTA + archivoUsuarios);
            archivoLectura = new FileReader(ubicaciónArchivo);
            datosArchivo = new BufferedReader(archivoLectura);

            // DE por archivo
            String datoTipo = "";
            String datoUser = "";
            String datoPassw = "";
            String linea = "";
            String[] datosSeparados = null; // Creacion de vector tipo String para separar datos del .split()

            // DS
            Boolean credencialesValidas = false; // Boolean que se exporta al IndexView.java

            datosArchivo.readLine(); // Saltear encabezado
            while ((linea = datosArchivo.readLine()) != null) {

                if (linea.trim().isEmpty()) {
                    continue;
                }

                datosSeparados = linea.trim().split(";"); // Separa los datos en los ";"
                datoTipo = datosSeparados[1].trim().toLowerCase(); // Lee la columna 2 del archivo (Tipo de usuario)
                datoUser = datosSeparados[2].trim(); // Lee la columna 3 del archivo (Usuario)
                datoPassw = datosSeparados[3].trim(); // Lee la columna 4 del archivo (Contraseña)

                if (datoTipo.equalsIgnoreCase(tipo) && datoUser.equals(String.valueOf(user.longValue()))
                        && datoPassw.equals("passw")) {
                    credencialesValidas = true; // Cambia el valor de credencialesValidas en caso de que el tipo de
                                                // usuario, el usuario y la contraseña sean correctas
                    break;
                }
            }

            return credencialesValidas; // Regresa el valor de credencialesValidas a donde sea invocada la funcion

        } catch (IOException io) {
            throw new Exception("Error en la consulta de archivos" + io.getMessage()); // Posibles errores por consulta
                                                                                       // de archivos (IOException)
        } catch (Exception e) {
            throw new Exception("Error generico" + e.getMessage()); // Posibles errores genericos
        } finally {
            datosArchivo.close(); // Cerrar el archivo para que no consuma memoria adicional
        }
    }

    public static String separarUser(Double user) throws Exception {

        // contexto de la funcion

        File ubicaciónArchivo = null;
        FileReader archivoLectura = null;
        BufferedReader datosArchivo = null;

        try {

            // Variables para lectura
            String archivoUsuarios = "Usuarios.txt"; // Archivo exacto donde se encuentran los usuarios de ingreso
            ubicaciónArchivo = new File(RUTA + archivoUsuarios);
            archivoLectura = new FileReader(ubicaciónArchivo);
            datosArchivo = new BufferedReader(archivoLectura);

            // DE por archivo
            String datoUser = "";
            String nombreUser = "";
            String linea = "";
            String[] datosSeparados = null; // Creacion de vector tipo String para separar datos del .split()

            datosArchivo.readLine(); // Saltear encabezado
            while ((linea = datosArchivo.readLine()) != null) {

                if (linea.trim().isEmpty()) {
                    continue;
                }

                datosSeparados = linea.trim().split(";"); // Separa los datos en los ";"
                nombreUser = datosSeparados[0].trim(); // Lee la columna 1 del archivo (nombre usuario)
                datoUser = datosSeparados[2].trim(); // Lee la columna 3 del archivo (Usuario)

                if (datoUser.equals(String.valueOf(user.longValue()))) {
                    nombreUsuarioActual = nombreUser;
                    break;
                }
            }

            return nombreUsuarioActual; // Regresa el valor de nombreUser a donde sea invocada la funcion

        } catch (IOException io) {
            throw new Exception("Error en la consulta de archivos" + io.getMessage()); // Posibles errores por consulta
                                                                                       // de archivos (IOException)
        } catch (Exception e) {
            throw new Exception("Error generico" + e.getMessage()); // Posibles errores genericos
        } finally {
            datosArchivo.close(); // Cerrar el archivo para que no consuma memoria adicional
        }

    }

    public static String userActual() throws Exception { // Mini-funcion para poder guardar cual es el usuario actual en
                                                         // cualquier momento.

        try {

            return nombreUsuarioActual;

        } catch (Exception e) {
            throw new Exception("Error generico: " + e.getMessage());
        }

    }

    public static String registroPacientesNuevo(String nombre, String dI, String habitacion) throws Exception {

        // Contexto de la funcion
        File ubicacionArchivoListaPacientes = null;
        FileReader archivoLectura = null;
        BufferedReader datosArchivo = null;

        FileWriter archivoEscrituraLista = null;
        PrintWriter escrituraLista = null;

        FileWriter archivoEscrituraPaciente = null;
        PrintWriter escrituraPaciente = null;

        try {

            // Variables para lectura
            String listaPacientes = "listaPacientes.txt"; // archivo al que entraran datos generales de pacientes
            ubicacionArchivoListaPacientes = new File(RUTA + listaPacientes);
            archivoLectura = new FileReader(ubicacionArchivoListaPacientes);
            datosArchivo = new BufferedReader(archivoLectura);

            // DE archivo
            String linea = "";
            String[] datosSeparados = null; // Creacion de vector tipo String para separar datos del .split()
            String datoDI = "";

            // VA
            boolean estaRegistado = false; // Boolean que nos permitira evaluar si ya esta registrado el paciente

            // DS
            String estadoRegistro = "El usuario ya ha sido registrado"; // String que se exportara al Index.View

            datosArchivo.readLine(); // Saltear encabezado
            while ((linea = datosArchivo.readLine()) != null) {

                if (linea.trim().isEmpty()) {
                    continue;
                }

                datosSeparados = linea.trim().split(";");
                datoDI = datosSeparados[1];

                if (dI.equalsIgnoreCase(datoDI)) { // Este if evaluara si el documento de identidad del paciente es
                                                   // igual a alguno ya registrado para poder darle un valor a
                                                   // estaRegistrado
                    estaRegistado = true;
                    break;
                }

            }
            if (!estaRegistado) { // Se pone el ! para darle un valor invertido al boolean y poder entrar en el if
                                  // en caso de que el usuario no este registrado

                archivoEscrituraLista = new FileWriter(RUTA + listaPacientes, true); // Se invocan las variables de
                                                                                     // escritura de lista de paciente,
                                                                                     // añadiendo el append: True para
                                                                                     // no borrar lo que ya estaba en el
                                                                                     // archivo
                escrituraLista = new PrintWriter(archivoEscrituraLista);

                escrituraLista.println(nombre + ";" + dI + ";" + habitacion); // Ingresar en una linea del .txt la
                                                                              // informacion primordial del paciente

                archivoEscrituraPaciente = new FileWriter(RUTA + dI.trim() + ".txt", true); // Se crea un archivo con el
                                                                                            // documento de identidad
                                                                                            // del paciente ya que es el
                                                                                            // identificador unico en un
                                                                                            // .txt
                escrituraPaciente = new PrintWriter(archivoEscrituraPaciente);

                estadoRegistro = "Ha sido registrado exitosamente"; // Cambio de estadoRegistro para indicar el nuevo
                                                                    // registro

            }

            return estadoRegistro; // Regresa el valor de estadoRegistro a donde sea invocada la funcion

        } catch (IOException IO) {
            throw new Exception("Error en la consulta de archivos" + IO.getMessage());// Posibles errores por consulta
                                                                                      // de archivos (IOException)
        } catch (Exception e) {
            throw new Exception("Error generico:" + e.getMessage()); // Posibles errores genericos
        } finally {
            // cerrar los archivos abiertos tanto de escritura como de lectura para guardar
            // cambios y no consumir más memoria
            datosArchivo.close();
            escrituraLista.close();
            archivoEscrituraPaciente.close();
        }

    }

}