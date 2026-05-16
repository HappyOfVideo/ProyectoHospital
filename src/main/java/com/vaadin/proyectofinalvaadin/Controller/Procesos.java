package com.vaadin.proyectofinalvaadin.Controller;

import java.io.*;
import java.time.LocalDateTime;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.server.VaadinSession;

public class Procesos {

    // Globales
    public static final String RUTA = "src\\main\\java\\com\\vaadin\\proyectofinalvaadin\\src\\"; // Ruta carpeta de
                                                                                                  // archivos
    public static final String RUTA_CARPETA_PACIENTES = "src\\main\\java\\com\\vaadin\\proyectofinalvaadin\\src\\pacientes\\";
    public static final String RUTA_CARPETA_FACTURA = "src\\main\\java\\com\\vaadin\\proyectofinalvaadin\\src\\facturas\\";
    public static final String RUTA_CARPETA_INDICACIONES = "src\\main\\java\\com\\vaadin\\proyectofinalvaadin\\src\\Indicaciones\\";

    public static String NOMBRE_USER_ACTUAL = "";
    public static String NOMBRE_PACIENTE_ACTUAL = "";
    public static String ID_PACIENTE = "";

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
            throw new IOException("Error en la consulta de archivos" + io.getMessage()); // Posibles errores por
                                                                                         // consulta
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
                    NOMBRE_USER_ACTUAL = nombreUser;
                    // Guardar en sesión para que persista al navegar entre páginas
                    VaadinSession.getCurrent().setAttribute("NOMBRE_USER_ACTUAL", nombreUser);
                    break;
                }
            }

            return NOMBRE_USER_ACTUAL; // Regresa el valor de nombreUser a donde sea invocada la funcion

        } catch (IOException io) {
            throw new IOException("Error en la consulta de archivos" + io.getMessage()); // Posibles errores por
                                                                                         // consulta
                                                                                         // de archivos (IOException)
        } catch (Exception e) {
            throw new Exception("Error generico" + e.getMessage()); // Posibles errores genericos
        } finally {
            datosArchivo.close(); // Cerrar el archivo para que no consuma memoria adicional
        }

    }

    // Devuelve el nombre del usuario actual, primero desde la sesión y como
    // respaldo desde la variable estática
    public static String getNombreUser() {
        try {
            String nombre = (String) VaadinSession.getCurrent().getAttribute("NOMBRE_USER_ACTUAL");
            return nombre != null ? nombre : NOMBRE_USER_ACTUAL;
        } catch (Exception e) {
            return NOMBRE_USER_ACTUAL;
        }
    }

    public static void registroPacientesHabitacionesA(String nombre, String dI, String habitacion) throws Exception {

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
            String datoHabitacion = "";

            // VA
            boolean estaRegistado = true; // Boolean que nos permitira evaluar si ya esta registrado el paciente

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
                    estaRegistado = false;
                    break;
                }
            }
            if (estaRegistado) { // Este if en caso de que el usuario no este registrado registrara al usuario

                archivoEscrituraLista = new FileWriter(RUTA + listaPacientes, true); // Se invocan las variables de
                                                                                     // escritura de lista de paciente,
                                                                                     // añadiendo el append: True
                                                                                     // para no borrar lo que ya estaba
                                                                                     // en el archivo
                escrituraLista = new PrintWriter(archivoEscrituraLista);

                escrituraLista.println(nombre + ";" + dI + ";" + habitacion); // Ingresar en una linea del .txt la
                                                                              // informacion primordial del paciente

                archivoEscrituraPaciente = new FileWriter(RUTA_CARPETA_PACIENTES + dI.trim() + ".txt", true); // Se crea
                                                                                                              // un
                                                                                                              // archivo
                                                                                                              // con el
                                                                                                              // documento
                                                                                                              // de
                                                                                                              // identidad
                                                                                                              // del
                                                                                                              // paciente
                                                                                                              // ya que
                                                                                                              // es el
                                                                                                              // identificador
                                                                                                              // unico
                                                                                                              // en
                                                                                                              // un.txt
                escrituraPaciente = new PrintWriter(archivoEscrituraPaciente);

                escrituraPaciente.println(nombre + ";" + dI + ";" + habitacion);

            }

        } catch (IOException io) {
            throw new IOException("Error en la consulta de archivos" + io.getMessage());// Posibles errores por consulta
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

    public static void registroPacientesHabitacionesB(String nombre, String dI, String habitacion, String cama)
            throws Exception {

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
            String datoHabitacion = "";
            String datoCama = "";

            // VA
            boolean estaRegistado = true; // Boolean que nos permitira evaluar si ya esta registrado el paciente

            datosArchivo.readLine(); // Saltear encabezado
            while ((linea = datosArchivo.readLine()) != null) {

                if (linea.trim().isEmpty()) {
                    continue;
                }

                datosSeparados = linea.trim().split(";");
                datoDI = datosSeparados[1];
                datoHabitacion = datosSeparados[2];

                if (dI.equalsIgnoreCase(datoDI) || datoHabitacion.equalsIgnoreCase(habitacion + " - " + cama)) { // Este
                                                                                                                 // if
                                                                                                                 // evaluara
                                                                                                                 // si
                                                                                                                 // el
                                                                                                                 // documento
                                                                                                                 // de
                                                                                                                 // identidad
                                                                                                                 // del
                                                                                                                 // paciente
                                                                                                                 // es
                    // igual a alguno ya registrado para poder darle un valor a
                    // estaRegistrado
                    estaRegistado = false;
                    break;
                }
            }
            if (estaRegistado) { // Este if en caso de que el usuario no este registrado registrara al usuario

                archivoEscrituraLista = new FileWriter(RUTA + listaPacientes, true); // Se invocan las variables de
                                                                                     // escritura de lista de paciente,
                                                                                     // añadiendo el append: True
                                                                                     // para no borrar lo que ya estaba
                                                                                     // en el archivo
                escrituraLista = new PrintWriter(archivoEscrituraLista);

                escrituraLista.println(nombre + ";" + dI + ";" + habitacion + " - " + cama); // Ingresar en una linea
                                                                                             // del .txt la
                // informacion primordial del paciente

                archivoEscrituraPaciente = new FileWriter(RUTA_CARPETA_PACIENTES + dI.trim() + ".txt", true); // Se crea
                                                                                                              // un
                                                                                                              // archivo
                                                                                                              // con el
                                                                                                              // documento
                                                                                                              // de
                                                                                                              // identidad
                                                                                                              // del
                                                                                                              // paciente
                                                                                                              // ya que
                                                                                                              // es el
                                                                                                              // identificador
                                                                                                              // unico
                                                                                                              // en
                                                                                                              // un.txt
                escrituraPaciente = new PrintWriter(archivoEscrituraPaciente);

                escrituraPaciente.println(nombre + ";" + dI + ";" + habitacion + " - " + cama);

            }

        } catch (IOException io) {
            throw new IOException("Error en la consulta de archivos" + io.getMessage());// Posibles errores por consulta
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

    // Apartado de buscadores

    public static String busquedaXNombre(String nombre) throws Exception {

        File ubicacionArchivoListaPacientes = null;
        FileReader archivoLectura = null;
        BufferedReader datosArchivo = null;

        try {

            // Variables para lectura
            String listaPacientes = "listaPacientes.txt"; // archivo al que entraran datos generales de pacientes
            ubicacionArchivoListaPacientes = new File(RUTA + listaPacientes);
            archivoLectura = new FileReader(ubicacionArchivoListaPacientes);
            datosArchivo = new BufferedReader(archivoLectura);

            // DE Archivo
            String linea = "";
            String[] datosSeparados = null; // Creacion de vector tipo String para separar datos del .split()
            String datoNombre = "";

            // DS
            String dIPaciente = "";

            datosArchivo.readLine(); // Saltear encabezado
            while ((linea = datosArchivo.readLine()) != null) {

                if (linea.trim().isEmpty()) {
                    continue;
                }

                datosSeparados = linea.trim().split(";");
                datoNombre = datosSeparados[0].trim();

                if (datoNombre.equalsIgnoreCase(nombre.trim())) { // Este if evaluara si el nombre del paciente del
                                                                  // archivo es igual al que se esta buscando
                    dIPaciente = datosSeparados[1];
                    ID_PACIENTE = dIPaciente;
                    break;
                }

            }

            NOMBRE_PACIENTE_ACTUAL = datoNombre;

            return dIPaciente; // retorna un valor a donde sea invocada la funcion

        } catch (IOException io) {
            throw new IOException("Error en la consulta de archivos: " + io.getMessage()); // Posibles errores por
                                                                                           // consulta
                                                                                           // de archivos (IOException)
        } catch (Exception e) {
            throw new Exception("Error generico:" + e.getMessage()); // Posibles errores genericos
        } finally {
            datosArchivo.close(); // Cerrar el archivo para que no consuma memoria adicional
        }

    }

    public static String busquedaXHabitacion(String habitacion) throws Exception {

        File ubicacionArchivoListaPacientes = null;
        FileReader archivoLectura = null;
        BufferedReader datosArchivo = null;

        try {

            // Variables para lectura
            String listaPacientes = "listaPacientes.txt"; // archivo al que entraran datos generales de pacientes
            ubicacionArchivoListaPacientes = new File(RUTA + listaPacientes);
            archivoLectura = new FileReader(ubicacionArchivoListaPacientes);
            datosArchivo = new BufferedReader(archivoLectura);

            // DE Archivo
            String linea = "";
            String[] datosSeparados = null; // Creacion de vector tipo String para separar datos del .split()
            String datoHabitacion = "";
            String datoNombre = "";

            // DS
            String dIPaciente = "";

            datosArchivo.readLine(); // Saltear encabezado
            while ((linea = datosArchivo.readLine()) != null) {

                if (linea.trim().isEmpty()) {
                    continue;
                }

                datosSeparados = linea.trim().split(";");
                datoNombre = datosSeparados[0].trim();
                datoHabitacion = datosSeparados[2].trim();

                if (datoHabitacion.equalsIgnoreCase(habitacion.trim())) { // Este if evaluara si la habitacion del
                                                                          // paciente
                                                                          // del archivo es igual al que se esta
                                                                          // buscando
                    dIPaciente = datosSeparados[1];
                    ID_PACIENTE = dIPaciente;
                    break;
                }

            }

            NOMBRE_PACIENTE_ACTUAL = datoNombre;

            return dIPaciente; // retorna un valor a donde sea invocada la funcion

        } catch (IOException io) {
            throw new IOException("Error en la consulta de archivos: " + io.getMessage()); // Posibles errores por
                                                                                           // consulta
                                                                                           // de archivos (IOException)
        } catch (Exception e) {
            throw new Exception("Error generico:" + e.getMessage()); // Posibles errores genericos
        } finally {
            datosArchivo.close(); // Cerrar el archivo para que no consuma memoria adicional
        }

    }

    public static String busquedaXID(String dI) throws Exception {

        File ubicacionArchivoListaPacientes = null;
        FileReader archivoLectura = null;
        BufferedReader datosArchivo = null;

        try {

            // Variables para lectura
            String listaPacientes = "listaPacientes.txt"; // archivo al que entraran datos generales de pacientes
            ubicacionArchivoListaPacientes = new File(RUTA + listaPacientes);
            archivoLectura = new FileReader(ubicacionArchivoListaPacientes);
            datosArchivo = new BufferedReader(archivoLectura);

            // DE Archivo
            String linea = "";
            String[] datosSeparados = null; // Creacion de vector tipo String para separar datos del .split()
            String datodI = "";
            String datoNombre = "";

            // DS
            String dIPaciente = "";

            datosArchivo.readLine(); // Saltear encabezado
            while ((linea = datosArchivo.readLine()) != null) {

                if (linea.trim().isEmpty()) {
                    continue;
                }

                datosSeparados = linea.trim().split(";");
                datoNombre = datosSeparados[0].trim();
                datodI = datosSeparados[1].trim();

                if (datodI.equalsIgnoreCase(dI.trim())) { // Este if evaluara si el nombre del paciente
                                                          // del archivo es igual al que se esta
                                                          // buscando
                    dIPaciente = datosSeparados[1];
                    ID_PACIENTE = dIPaciente;
                    break;
                }

            }

            NOMBRE_PACIENTE_ACTUAL = datoNombre;

            return dIPaciente; // retorna un valor a donde sea invocada la funcion

        } catch (IOException io) {
            throw new IOException("Error en la consulta de archivos: " + io.getMessage()); // Posibles errores por
                                                                                           // consulta
                                                                                           // de archivos (IOException)
        } catch (Exception e) {
            throw new Exception("Error generico:" + e.getMessage()); // Posibles errores genericos
        } finally {
            datosArchivo.close(); // Cerrar el archivo para que no consuma memoria adicional
        }

    }

    public static boolean estadoHabitacionA(String numHabitacion) throws Exception {

        File ubicacionArchivoListaPacientes = null;
        FileReader archivoLectura = null;
        BufferedReader datosArchivo = null;

        try {

            // Variables para lectura
            String listaPacientes = "listaPacientes.txt"; // archivo al que entraran datos generales de pacientes
            ubicacionArchivoListaPacientes = new File(RUTA + listaPacientes);
            archivoLectura = new FileReader(ubicacionArchivoListaPacientes);
            datosArchivo = new BufferedReader(archivoLectura);

            // DE Archivo
            String linea = "";
            String[] datosSeparados = null; // Creacion de vector tipo String para separar datos del .split()
            String datoHabitacion = "";

            // DS
            Boolean estaLibre = true;

            datosArchivo.readLine(); // Saltear encabezado
            while ((linea = datosArchivo.readLine()) != null) {

                if (linea.trim().isEmpty()) {
                    continue;
                }

                datosSeparados = linea.trim().split(";");
                datoHabitacion = datosSeparados[2].trim();

                if (datoHabitacion.equalsIgnoreCase(numHabitacion.trim())) { // Este if evaluara si la habitacion esta
                                                                             // libre
                    estaLibre = false;
                    break;
                }

            }

            return estaLibre; // retorna un boolean a donde sea invocada la funcion

        } catch (IOException io) {
            throw new IOException("Error en la consulta de archivos: " + io.getMessage()); // Posibles errores por
                                                                                           // consulta
                                                                                           // de archivos (IOException)
        } catch (Exception e) {
            throw new Exception("Error generico:" + e.getMessage()); // Posibles errores genericos
        } finally {
            datosArchivo.close(); // Cerrar el archivo para que no consuma memoria adicional
        }

    }

    public static boolean estadoHabitacionB(String numHabitacion) throws Exception {

        File ubicacionArchivoListaPacientes = null;
        FileReader archivoLectura = null;
        BufferedReader datosArchivo = null;

        try {

            // Variables para lectura
            String listaPacientes = "listaPacientes.txt"; // archivo al que entraran datos generales de pacientes
            ubicacionArchivoListaPacientes = new File(RUTA + listaPacientes);
            archivoLectura = new FileReader(ubicacionArchivoListaPacientes);
            datosArchivo = new BufferedReader(archivoLectura);

            // DE Archivo
            String linea = "";
            String[] datosSeparados = null; // Creacion de vector tipo String para separar datos del .split()
            String datoHabitacion = "";

            // DE
            String cama1 = numHabitacion + " - C1";
            String cama2 = numHabitacion + " - C2";

            // VA
            int sumVerify = 0;

            // DS
            Boolean estaLibre = true;

            datosArchivo.readLine(); // Saltear encabezado
            while ((linea = datosArchivo.readLine()) != null) {

                if (linea.trim().isEmpty()) {
                    continue;
                }

                datosSeparados = linea.trim().split(";");
                datoHabitacion = datosSeparados[2].trim();

                if (datoHabitacion.equalsIgnoreCase(cama1)) { // Este if evaluara que camas estan libres y ocupadas para
                                                              // poder bloquear una habitacion en caso de que ambas
                                                              // esten ocupadas
                    sumVerify += 1;
                } else if (datoHabitacion.equalsIgnoreCase(cama2)) {
                    sumVerify += 2;
                }

            }

            if (sumVerify == 3) {
                estaLibre = false;
            }

            return estaLibre; // retorna un boolean a donde sea invocada la funcion

        } catch (IOException io) {
            throw new IOException("Error en la consulta de archivos: " + io.getMessage()); // Posibles errores por
                                                                                           // consulta
                                                                                           // de archivos (IOException)
        } catch (Exception e) {
            throw new Exception("Error generico:" + e.getMessage()); // Posibles errores genericos
        } finally {
            datosArchivo.close(); // Cerrar el archivo para que no consuma memoria adicional
        }

    }

    public static void facturar(String dIPaciente, String dias) throws Exception {

        // Contexto de la funcion

        File ubicacionArchivoPaciente = null;
        FileReader archivoLectura = null;
        BufferedReader datosArchivo = null;

        File archivoOriginal = null;
        File archivoTemporal = null;
        FileReader archivoOriginalLectura = null;
        FileWriter archivoTemporalEscritua = null;
        BufferedReader lector = null;
        BufferedWriter escritor = null;

        FileWriter facturaWriter = null;
        PrintWriter facturaPrint = null;

        try {
            // Variables para lectura
            String listaPacientes = "listaPacientes.txt";
            ubicacionArchivoPaciente = new File((RUTA_CARPETA_PACIENTES + dIPaciente.trim() + ".txt"));
            archivoLectura = new FileReader(ubicacionArchivoPaciente);
            datosArchivo = new BufferedReader(archivoLectura);

            // DE archivo
            String linea = "";
            String[] datosSeparados = null;
            String nombrePaciente = null;
            String datoHabitacion = "";
            String datoDI = "";

            // DS
            int diasInt = Integer.parseInt(dias);
            int pago = 0;

            // Buscar en archivo del paciente
            while ((linea = datosArchivo.readLine()) != null) {     // El while recorre todo el archivo para buscar el id que se ingreso 

                if (linea.trim().isEmpty()) {
                    continue;
                }

                datosSeparados = linea.trim().split(";");
                nombrePaciente = datosSeparados[0];
                datoDI = datosSeparados[1];
                datoHabitacion = datosSeparados[2];

                if (datoDI.equalsIgnoreCase(dIPaciente)) {
                    break;
                }
            }

            // Archivo listaPacientes --> limpiar línea
            archivoOriginal = new File(RUTA + listaPacientes);          
            archivoTemporal = new File(RUTA + "temp.txt");          
            archivoOriginalLectura = new FileReader(archivoOriginal);
            archivoTemporalEscritua = new FileWriter(archivoTemporal);
            lector = new BufferedReader(archivoOriginalLectura);
            escritor = new BufferedWriter(archivoTemporalEscritua);

            while ((linea = lector.readLine()) != null) {

                if (!linea.contains(dIPaciente)) {
                    escritor.write(linea);
                    escritor.newLine();
                }
            }

            lector.close();
            escritor.close();

            archivoOriginal.delete();
            archivoTemporal.renameTo(archivoOriginal);

            // Crear archivo factura
            File archivoFactura = new File(RUTA_CARPETA_FACTURA + datoDI + "Factura.txt");
            facturaWriter = new FileWriter(archivoFactura, true);
            facturaPrint = new PrintWriter(facturaWriter);

            facturaPrint.println("\n\n//////////////////////////- FACTURA -//////////////////////////\n\n");
            facturaPrint.println("Fecha y hora: " + LocalDateTime.now() + "\n\n");
            facturaPrint.println(
                    "El usuario: " + nombrePaciente + " se ha hospedado [ " + dias + " ] día(s) en el hospital\n\n");

            if (datoHabitacion.contains("A")) {
                pago = diasInt * 350000;
            } else if (datoHabitacion.contains("B")) {
                pago = diasInt * 175000;
            }

            facturaPrint.println("El total de pago es: $" + pago);
            facturaPrint.println("\n\n//////////////////////////- BUEN DÍA -//////////////////////////\n");

        } catch (IOException io) {
            Notification.show("Error en io" + io.getMessage());
            throw new IOException("Error en la consulta de archivos " + io.getMessage());
        } catch (Exception e) {
            Notification.show("Error generico" + e.getMessage());
            throw new Exception("Error generico: " + e.getMessage());
        } finally {
            // Ayuda de (IA) porque habian errores inencontrables y ejecuciones que no se
            // hacian
            /*
             * Se tienen que hacer una verificación de que no sean nulos para evitar:
             * Que el programa explote
             * No se pierdan excepciones importantes
             * No se corrompan archivos temporales
             * No se interrumpa la lógica del rename/delete
             */
            // (IA)
            if (datosArchivo != null)
                datosArchivo.close();
            if (facturaPrint != null)
                facturaPrint.close();
            if (facturaWriter != null)
                facturaWriter.close();
            if (archivoLectura != null)
                archivoLectura.close();
            if (archivoOriginalLectura != null)
                archivoOriginalLectura.close();
            if (archivoTemporalEscritua != null)
                archivoTemporalEscritua.close();
        }
    }

    public static String historialMedico() throws Exception {

        File ubicacionArchivoListaPacientes = null;
        FileReader archivoLectura = null;
        BufferedReader datosArchivo = null;

        try {

            // Variables para lectura
            String historialPaciente = ID_PACIENTE + ".txt"; // archivo al que entraran datos generales de pacientes
            ubicacionArchivoListaPacientes = new File(RUTA_CARPETA_PACIENTES + historialPaciente);
            archivoLectura = new FileReader(ubicacionArchivoListaPacientes);
            datosArchivo = new BufferedReader(archivoLectura);

            // DE Archivo
            String linea = "";

            // DS 
            String acumuladoMensaje = "";

            datosArchivo.readLine(); // Saltear primera linea la cual no nos importa que imprima
            while ((linea = datosArchivo.readLine()) != null) {

                acumuladoMensaje += linea + "\n";

                

            }

            return acumuladoMensaje; // retorna un valor a donde sea invocada la funcion

        } catch (IOException io) {
            throw new IOException("Error en la consulta de archivos: " + io.getMessage()); // Posibles errores por
                                                                                           // consulta
                                                                                           // de archivos (IOException)
        } catch (Exception e) {
            throw new Exception("Error generico:" + e.getMessage()); // Posibles errores genericos
        } finally {
            datosArchivo.close(); // Cerrar el archivo para que no consuma memoria adicional
        }

    }

    public static void escribirIndicaciones(String textoAñadir) throws Exception {

        // Contexto de la funcion
        File ubicacionArchivoIndicacionesPaciente = null;
        FileWriter archivoEscritura = null;
        PrintWriter escritura = null;

        try {

            // Variables para lectura
            String indicacionesArchivo = ID_PACIENTE + "Indicaciones.txt"; // archivo al que entraran datos generales de
                                                                           // pacientes
            ubicacionArchivoIndicacionesPaciente = new File(RUTA_CARPETA_INDICACIONES + indicacionesArchivo);
            archivoEscritura = new FileWriter(ubicacionArchivoIndicacionesPaciente, true);
            escritura = new PrintWriter(archivoEscritura);


            // Añadir un mensaje al id.txt
            escritura.println(textoAñadir
                    + "\n\n/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////\n");

        } catch (IOException io) {
            throw new IOException("Error en la consulta de archivos" + io.getMessage());// Posibles errores por consulta
                                                                                        // de archivos (IOException)
        } catch (Exception e) {
            throw new Exception("Error generico:" + e.getMessage()); // Posibles errores genericos
        } finally {
            // cerrar los archivos abiertos tanto de escritura como de lectura para guardar
            // cambios y no consumir más memoria
            escritura.close();
        }

    }

    public static String leerIndicaciones() throws Exception {

        File ubicacionArchivoIndicacionesPaciente = null;
        FileReader archivoLectura = null;
        BufferedReader datosArchivo = null;

        try {

            // Variables para lectura
            String indicacionesArchivo = ID_PACIENTE + "Indicaciones.txt"; // archivo al que entraran datos generales de
                                                                           // pacientes
            ubicacionArchivoIndicacionesPaciente = new File(RUTA_CARPETA_INDICACIONES + indicacionesArchivo);
            archivoLectura = new FileReader(ubicacionArchivoIndicacionesPaciente);
            datosArchivo = new BufferedReader(archivoLectura);

            // DE Archivo
            String linea = "";

            // DS
            String acumuladoMensaje = "";

            while ((linea = datosArchivo.readLine()) != null) {

                acumuladoMensaje += linea + "\n";

            }

            return acumuladoMensaje; // retorna un valor a donde sea invocada la funcion

        } catch (IOException io) {
            throw new IOException("Error en la consulta de archivos: " + io.getMessage()); // Posibles errores por
                                                                                           // consulta
                                                                                           // de archivos (IOException)
        } catch (Exception e) {
            throw new Exception("Error generico:" + e.getMessage()); // Posibles errores genericos
        } finally {
            datosArchivo.close(); // Cerrar el archivo para que no consuma memoria adicional
        }

    }

    public static void escribirEvolucion(String textoAñadir) throws Exception {

        // Contexto de la funcion
        File ubicacionArchivoPaciente = null;
        FileWriter archivoEscritura = null;
        PrintWriter escritura = null;

        try {

            // Variables para lectura
            String archivoPaciente = ID_PACIENTE + ".txt"; // archivo al que entraran datos generales de pacientes
            ubicacionArchivoPaciente = new File(RUTA_CARPETA_PACIENTES + archivoPaciente);
            archivoEscritura = new FileWriter(ubicacionArchivoPaciente, true);
            escritura = new PrintWriter(archivoEscritura);


            // Añadir mensaje a Id.txt
            escritura.println(textoAñadir
                    + "\n\n/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////\n");

        } catch (IOException io) {
            throw new IOException("Error en la consulta de archivos" + io.getMessage());// Posibles errores por consulta
                                                                                        // de archivos (IOException)
        } catch (Exception e) {
            throw new Exception("Error generico:" + e.getMessage()); // Posibles errores genericos
        } finally {
            // cerrar los archivos abiertos tanto de escritura como de lectura para guardar
            // cambios y no consumir más memoria
            escritura.close();
        }

    }

    public static void escribirMedicamento(String textoAñadir) throws Exception {

        // Contexto de la funcion
        File ubicacionArchivoPaciente = null;
        FileWriter archivoEscritura = null;
        PrintWriter escritura = null;

        try {

            // Variables para lectura
            String archivoPaciente = ID_PACIENTE + ".txt"; // archivo al que entraran datos generales de pacientes
            ubicacionArchivoPaciente = new File(RUTA_CARPETA_PACIENTES + archivoPaciente);
            archivoEscritura = new FileWriter(ubicacionArchivoPaciente, true);
            escritura = new PrintWriter(archivoEscritura);

                // Añadir mensaje a Id.txt
            escritura.println(textoAñadir
                    + "\n\n/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////\n");

        } catch (IOException io) {
            throw new IOException("Error en la consulta de archivos" + io.getMessage());// Posibles errores por consulta
                                                                                        // de archivos (IOException)
        } catch (Exception e) {
            throw new Exception("Error generico:" + e.getMessage()); // Posibles errores genericos
        } finally {
            // cerrar los archivos abiertos tanto de escritura como de lectura para guardar
            // cambios y no consumir más memoria
            escritura.close();
        }

    }

    public static void altaMedica(String textoAñadir) throws Exception {

        // Contexto de la funcion
        File ubicacionArchivoPaciente = null;
        FileWriter archivoEscritura = null;
        PrintWriter escritura = null;

        try {

            // Variables para lectura
            String archivoPaciente = ID_PACIENTE + ".txt"; // archivo al que entraran datos generales de pacientes
            ubicacionArchivoPaciente = new File(RUTA_CARPETA_PACIENTES + archivoPaciente);
            archivoEscritura = new FileWriter(ubicacionArchivoPaciente, true);
            escritura = new PrintWriter(archivoEscritura);

            escritura.println(textoAñadir
                    + "\n\nFecha y hora de la alta medica: " + LocalDateTime.now()
                    + "\n\n////////////-Alta Medica Generada para: " + ID_PACIENTE + "-////////////\n");

        } catch (IOException io) {
            throw new IOException("Error en la consulta de archivos" + io.getMessage());// Posibles errores por consulta
                                                                                        // de archivos (IOException)
        } catch (Exception e) {
            throw new Exception("Error generico:" + e.getMessage()); // Posibles errores genericos
        } finally {
            // cerrar los archivos abiertos tanto de escritura como de lectura para guardar
            // cambios y no consumir más memoria
            escritura.close();
        }

    }

    public static Boolean tieneAltaMedica(String idPaciente) throws Exception {

        File ubicacionArchivoPaciente = null;
        FileReader archivoLectura = null;
        BufferedReader datosArchivo = null;

        try {

            String archivo = idPaciente + ".txt";
            ubicacionArchivoPaciente = new File(RUTA_CARPETA_PACIENTES + archivo);
            archivoLectura = new FileReader(ubicacionArchivoPaciente);
            datosArchivo = new BufferedReader(archivoLectura);

            String linea;
            String nombrePaciente = "";
            boolean tieneAlta = false;

            // Primera línea: datos del paciente

            if ((linea = datosArchivo.readLine()) != null) {
                String[] datos = linea.split(";");
                nombrePaciente = datos[0].trim();
            }

            while ((linea = datosArchivo.readLine()) != null) {

                if (linea.trim().isEmpty())
                    continue;

                if (linea.contains("Alta Medica Generada para:")) {
                    tieneAlta = true;
                }
                // el paciente reingresó
                else if (linea.trim().equals(nombrePaciente)) {
                    tieneAlta = false;
                }
            }

            return tieneAlta;

        } catch (IOException io) {
            throw new IOException("Error en la consulta de archivos" + io.getMessage());// Posibles errores por consulta
                                                                                        // de archivos (IOException)
        } catch (Exception e) {
            throw new Exception("Error generico:" + e.getMessage()); // Posibles errores genericos
        }

        finally {
            if (datosArchivo != null)
                datosArchivo.close();
        }

    }

}