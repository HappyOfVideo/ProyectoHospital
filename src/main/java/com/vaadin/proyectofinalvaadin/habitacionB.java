package com.vaadin.proyectofinalvaadin;

import java.io.IOException;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.proyectofinalvaadin.Controller.Procesos;

@Route("registrar/TipoB")
public class habitacionB extends VerticalLayout {
        public final static String REGEX = ".*[@#$%^&*()_+={}\\[\\]:;\"'<>,.?/\\\\|`~].*";

        public habitacionB() {

                try {

                        setSizeFull();
                        setPadding(false);
                        setMargin(false);
                        setSpacing(false);

                        // HEADER
                        HorizontalLayout headerLayout = new HorizontalLayout();
                        headerLayout.setWidthFull();
                        headerLayout.setMinHeight("100px");
                        headerLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
                        headerLayout.setAlignItems(FlexComponent.Alignment.CENTER);
                        headerLayout.getStyle()
                                        .set("position", "fixed")
                                        .set("z-index", "1000")
                                        .set("background", "linear-gradient(135deg, #2c3e50 0%, #34495e 100%)")
                                        .set("padding", "0 40px")
                                        .set("box-shadow", "0 2px 10px rgba(0,0,0,0.1)");

                        // Título en el header
                        H1 headerTitle = new H1("Registro de habitaciones");
                        headerTitle.getStyle()
                                        .set("color", "white")
                                        .set("margin", "0")
                                        .set("font-size", "28px")
                                        .set("font-weight", "bold");

                        // Layout derecho para botones
                        HorizontalLayout rightButtons = new HorizontalLayout();
                        rightButtons.setSpacing(true);
                        rightButtons.setAlignItems(Alignment.CENTER);

                        // Botón Regresar
                        Button btnRegresar = new Button("Regresar", new Icon(VaadinIcon.ARROW_LEFT));
                        btnRegresar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
                        btnRegresar.getStyle()
                                        .set("background", "#3498db")
                                        .set("color", "white")
                                        .set("font-weight", "bold")
                                        .set("border-radius", "8px")
                                        .set("transition", "all 0.3s ease");
                        btnRegresar.addClickListener(e -> UI.getCurrent().navigate("registrar"));

                        // Botón Cerrar Sesión
                        Button btnCerrarSesion = new Button("Cerrar Sesión", new Icon(VaadinIcon.SIGN_OUT));
                        btnCerrarSesion.addThemeVariants(ButtonVariant.LUMO_ERROR);
                        btnCerrarSesion.getStyle()
                                        .set("background", "#e74c3c")
                                        .set("color", "white")
                                        .set("font-weight", "bold")
                                        .set("border-radius", "8px")
                                        .set("transition", "all 0.3s ease");
                        btnCerrarSesion.addClickListener(e -> {
                                UI.getCurrent().navigate(""); // Ruta de login
                        });

                        rightButtons.add(btnRegresar, btnCerrarSesion);
                        headerLayout.add(headerTitle, rightButtons);

                        // CONTENEDOR PRINCIPAL QUE OCUPA TODA LA PANTALLA
                        Div contenedorPrincipal = new Div();
                        contenedorPrincipal.setWidthFull();
                        contenedorPrincipal.getStyle()
                                        .set("background", "white")
                                        .set("display", "flex")
                                        .set("flex-direction", "column");

                        // CONTENIDO CENTRAL
                        VerticalLayout contenidoCentral = new VerticalLayout();
                        contenidoCentral.setSizeFull();
                        contenidoCentral.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
                        contenidoCentral.setAlignItems(FlexComponent.Alignment.CENTER);
                        contenidoCentral.getStyle()
                                        .set("padding", "40px")
                                        .set("flex-grow", "1");

                        // Contenedor de las habitaciones
                        Div contenedorHabitaciones = new Div();
                        contenedorHabitaciones.getStyle()
                                        .set("background", "white")
                                        .set("border-radius", "15px")
                                        .set("padding", "40px")
                                        .set("max-width", "1100px")
                                        .set("width", "100%")
                                        .set("text-align", "center")
                                        .set("box-shadow", "0 4px 20px rgba(0,0,0,0.1)")
                                        .set("margin-top", "90px");

                        // Grid de habitaciones
                        FormLayout gridHabitaciones = new FormLayout();
                        gridHabitaciones.setResponsiveSteps(
                                        new FormLayout.ResponsiveStep("0", 2),
                                        new FormLayout.ResponsiveStep("600px", 3),
                                        new FormLayout.ResponsiveStep("900px", 5));
                        gridHabitaciones.getStyle()
                                        .set("justify-content", "center")
                                        .set("gap", "20px");

                        // FormLayot para habitaciones dobles
                        FormLayout dobles = new FormLayout();
                        dobles.setResponsiveSteps(
                                        new FormLayout.ResponsiveStep("0", 1),
                                        new FormLayout.ResponsiveStep("600px", 2));
                        dobles.getStyle().set("margin-left", "40px");
                        VerticalLayout leftSide = new VerticalLayout();
                        VerticalLayout rightSide = new VerticalLayout();

                        // Grid de detalles derecho
                        VerticalLayout detalles = new VerticalLayout();
                        FormLayout gridDetalles = new FormLayout();
                        gridDetalles.setResponsiveSteps(
                                        new FormLayout.ResponsiveStep("0", 1));
                        gridDetalles.setMaxWidth("400px");
                        gridDetalles.getStyle().set("justify-content", "center");

                        // Grid de detalles izquierdo
                        VerticalLayout detallesIZ = new VerticalLayout();
                        FormLayout gridDetallesIZ = new FormLayout();
                        gridDetallesIZ.setResponsiveSteps(
                                        new FormLayout.ResponsiveStep("0", 1));
                        gridDetallesIZ.setMaxWidth("400px");
                        gridDetallesIZ.getStyle().set("justify-content", "center");

                        // Creación de las 20 habitaciones
                        H2 tituloDetalles = new H2();
                        H2 tituloDetallesIZ = new H2();
                        for (int i = 1; i < 21; i++) {
                                String nombreHabitacion = "B" + i; // Variable creada dentro de ciclo para poder que
                                                                   // cada boton recuerde su número
                                boolean estaLibre = Procesos.estadoHabitacionB(nombreHabitacion);

                                Icon bedIcon = new Icon(VaadinIcon.BED);
                                bedIcon.setSize("40px");

                                Button habitacion = new Button("HABITACIÓN B" + i, bedIcon);

                                habitacion.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
                                habitacion.getStyle()
                                                .set("width", "150px")
                                                .set("height", "150px")
                                                .set("font-size", "1em")
                                                .set("font-weight", "bold")
                                                .set("color", "#2c3e50")
                                                .set("background", "#ecf0f1")
                                                .set("border-radius", "12px")
                                                .set("box-shadow", "0 3px 10px rgba(0,0,0,0.1)")
                                                .set("transition", "all 0.3s ease")
                                                .set("margin-bottom", "20px")
                                                .set("transition", "background-color 1s ease");

                                contenedorHabitaciones.add(habitacion);
                                gridHabitaciones.add(habitacion);
                                habitacion.addClickListener(e -> {
                                        if (estaLibre) {
                                                tituloDetalles.setText("Habitación " + nombreHabitacion + " - C1");
                                                tituloDetallesIZ.setText("Habitación " + nombreHabitacion + " - C2");
                                                gridHabitaciones.setVisible(false);
                                                detalles.setVisible(true);
                                                detallesIZ.setVisible(true);
                                        } else {
                                                habitacion.getElement().executeJs(
                                                                "this.style.backgroundColor = '#fd9999ff';" + // color
                                                                                                              // temporal
                                                                                "setTimeout(() => this.style.backgroundColor = '#ecf0f1', 1500);" // vuelve
                                                                                                                                                  // al
                                                                                                                                                  // original
                                                                                                                                                  // en
                                                                                                                                                  // 1.5
                                                                                                                                                  // seg
                                                );
                                                habitacion.setEnabled(false);
                                                Notification.show("Hábitación llena");
                                                return;
                                        }
                                });
                        }

                        // Campos de paciente derecha
                        tituloDetalles.getStyle()
                                        .set("background", "linear-gradient(135deg, #5e2525ff 0%, #476482ff 100%)")
                                        .set("color", "white")
                                        .set("font-weight", "bold")
                                        .set("padding", "10px 0px 10px 0px")
                                        .set("margin-bottom", "10px")
                                        .set("border-radius", "10px");
                        ;
                        TextField nombrePaciente = new TextField("Ingrese el nombre");
                        nombrePaciente.setPlaceholder("Paciente");
                        IntegerField documentoPaciente = new IntegerField("Ingrese el documento");
                        documentoPaciente.setPlaceholder("Ej: cc");

                        // Contenerdor de botones derecha
                        HorizontalLayout contenedorBotones = new HorizontalLayout();
                        contenedorBotones.getStyle().set("margin-top", "20px");
                        Button salir = new Button(new Icon(VaadinIcon.ARROW_LEFT));
                        salir.setMinWidth("191px");
                        salir.getStyle()
                                        .set("background", "linear-gradient(135deg, #2c3e50 0%, #34495e 100%)")
                                        .set("color", "#ffffff");
                        ;
                        salir.addClickListener(e -> {
                                gridHabitaciones.setVisible(true);
                                detalles.setVisible(false);
                                detallesIZ.setVisible(false);
                        });
                        Button agregar = new Button(new Icon(VaadinIcon.ADD_DOCK));
                        agregar.setMinWidth("191px");
                        agregar.getStyle()
                                        .set("background", "linear-gradient(135deg, #2c3e50 0%, #34495e 100%)")
                                        .set("color", "#ffffff");

                        try {
                                agregar.addClickListener(e -> {
                                        String dp = String.valueOf(documentoPaciente.getValue());
                                        if (dp == null || nombrePaciente.getValue().equals("")) {
                                                Notification.show("Los campos no pueden estar vacío"); // Si algún campo
                                                                                                       // está vacío se
                                                                                                       // muestra una
                                                                                                       // notificación y
                                                                                                       // se detiene la
                                                                                                       // ejecución.
                                                return;
                                        } else if (!dp.matches("\\d*") && (nombrePaciente.getValue()).matches(REGEX)) {
                                                Notification.show("No se permiten tildes ni caracteres especiales"); // dp.matches("\\d*")
                                                                                                                     // →
                                                                                                                     // el
                                                                                                                     // documento
                                                                                                                     // debe
                                                                                                                     // contener
                                                                                                                     // solo
                                                                                                                     // números
                                                return; // REGEX valida que el nombre no tenga caracteres inválidos
                                                        // (tildes, ñ, símbolos)
                                        } else {
                                                gridHabitaciones.setVisible(true);
                                                detalles.setVisible(false);
                                                detallesIZ.setVisible(false); // Esto vuelve a mostrar el grid donde
                                                                              // están las habitaciones, ocultando el
                                                                              // panel de edición.

                                                String[] habitacion = (tituloDetalles.getText()).split(" ");
                                                String nHabitacion = "";
                                                String nCama = "";
                                                nHabitacion = habitacion[1];
                                                nCama = habitacion[3].trim();

                                                try {
                                                        Procesos.registroPacientesHabitacionesB(
                                                                        nombrePaciente.getValue(), dp, nHabitacion, // Este
                                                                                                                    // método
                                                                                                                    // es
                                                                                                                    // el
                                                                                                                    // que
                                                                                                                    // actualiza
                                                                                                                    // la
                                                                                                                    // información
                                                                                                                    // interna:
                                                                        nCama); // Marca la cama como ocupada
                                                        Notification.show(nCama + " - " + " de " + nHabitacion // Asocia
                                                                                                               // el
                                                                                                               // paciente
                                                                                                               // con
                                                                                                               // esa
                                                                                                               // cama
                                                                        + " ocupada correctamente"); // Lanza errores si
                                                                                                     // la cama ya está
                                                                                                     // ocupada o el
                                                                                                     // paciente ya
                                                                                                     // existe
                                                        nombrePaciente.setEnabled(false);
                                                        documentoPaciente.setEnabled(false);
                                                        salir.setEnabled(false);
                                                        agregar.setEnabled(false);
                                                        return;
                                                } catch (Exception E) {
                                                        Notification.show("Paciente ya registrado y/o cama ya ocupada");
                                                }

                                                nombrePaciente.clear();
                                                documentoPaciente.clear();

                                        }

                                });

                        } catch (Exception e) {
                                Notification.show("Error en el botón, contactar con soporte");
                                return;
                        }

                        // Campos de paciente izquierda
                        tituloDetallesIZ.getStyle()
                                        .set("background", "linear-gradient(135deg, #5e2525ff 0%, #476482ff 100%)")
                                        .set("color", "white")
                                        .set("font-weight", "bold")
                                        .set("padding", "10px 0px 10px 0px")
                                        .set("margin-bottom", "10px")
                                        .set("border-radius", "10px");
                        ;
                        TextField nombrePacienteIZ = new TextField("Ingrese el nombre");
                        nombrePacienteIZ.setPlaceholder("Paciente");
                        IntegerField documentoPacienteIZ = new IntegerField("Ingrese el documento");
                        documentoPacienteIZ.setPlaceholder("Ej: cc");

                        // Contenerdor de botones derecha
                        HorizontalLayout contenedorBotonesIZ = new HorizontalLayout();
                        contenedorBotonesIZ.getStyle().set("margin-top", "20px");
                        Button salirIZ = new Button(new Icon(VaadinIcon.ARROW_LEFT));
                        salirIZ.setMinWidth("191px");
                        salirIZ.getStyle()
                                        .set("background", "linear-gradient(135deg, #2c3e50 0%, #34495e 100%)")
                                        .set("color", "#ffffff");
                        ;
                        salirIZ.addClickListener(e -> {
                                gridHabitaciones.setVisible(true);
                                detalles.setVisible(false);
                                detallesIZ.setVisible(false);
                        });
                        Button agregarIZ = new Button(new Icon(VaadinIcon.ADD_DOCK));
                        agregarIZ.setMinWidth("191px");
                        agregarIZ.getStyle()
                                        .set("background", "linear-gradient(135deg, #2c3e50 0%, #34495e 100%)")
                                        .set("color", "#ffffff");
                        ;
                        try {

                                agregarIZ.addClickListener(e -> {
                                        String dp = String.valueOf(documentoPacienteIZ.getValue());
                                        if (dp == null || nombrePacienteIZ.getValue().equals("")) {
                                                Notification.show("Los campos no pueden estar vacío");
                                                return;
                                        } else if (!dp.matches("\\d*")
                                                        && (nombrePacienteIZ.getValue()).matches(REGEX)) {
                                                Notification.show("No se permiten tildes ni caracteres especiales");
                                                return;
                                        } else {
                                                gridHabitaciones.setVisible(true);
                                                detalles.setVisible(false);
                                                detallesIZ.setVisible(false);

                                                String[] habitacionIZ = (tituloDetallesIZ.getText()).split(" ");
                                                String nHabitacionIZ = "";
                                                String nCamaIZ = "";
                                                nHabitacionIZ = habitacionIZ[1];
                                                nCamaIZ = habitacionIZ[3].trim();
                                                try {
                                                        Procesos.registroPacientesHabitacionesB(
                                                                        nombrePacienteIZ.getValue(), dp, nHabitacionIZ,
                                                                        nCamaIZ);

                                                        Notification.show(nCamaIZ + " - " + " de " + nHabitacionIZ
                                                                        + " ocupada correctamente");
                                                        nombrePacienteIZ.setEnabled(false);
                                                        documentoPacienteIZ.setEnabled(false);
                                                        salirIZ.setEnabled(false);
                                                        agregarIZ.setEnabled(false);
                                                        return;

                                                } catch (Exception E) {
                                                        Notification.show("Paciente ya registrado y/o cama ya ocupada");
                                                }

                                                nombrePacienteIZ.clear();
                                                documentoPacienteIZ.clear();

                                        }

                                });
                        } catch (Exception e) {
                                Notification.show("Error en el botón, contactar con soporte");
                                return;
                        }

                        contenedorBotones.add(salir, agregar);
                        contenedorBotonesIZ.add(salirIZ, agregarIZ);

                        gridDetalles.add(tituloDetalles, nombrePaciente, documentoPaciente, contenedorBotones);
                        gridDetallesIZ.add(tituloDetallesIZ, nombrePacienteIZ, documentoPacienteIZ,
                                        contenedorBotonesIZ);

                        detalles.add(gridDetalles);
                        detallesIZ.add(gridDetallesIZ);

                        detalles.setVisible(false);
                        detallesIZ.setVisible(false);

                        leftSide.add(detallesIZ);
                        rightSide.add(detalles);

                        dobles.add(rightSide, leftSide);

                        // Pie de página
                        Div footer = new Div();
                        footer.getStyle()
                                        .set("margin-top", "40px")
                                        .set("margin-bottom", "10px")
                                        .set("padding-top", "30px")
                                        .set("border-top", "2px solid #ecf0f1")
                                        .set("text-align", "center")
                                        .set("color", "#676f6fff")
                                        .set("font-size", "20px");
                        footer.setText("HOSPITAL MONTELÍBANO");

                        // ENSAMBLAJE FINAL
                        contenedorHabitaciones.add(gridHabitaciones, dobles);
                        contenidoCentral.add(contenedorHabitaciones, footer);
                        contenedorPrincipal.add(headerLayout, contenidoCentral);

                        add(contenedorPrincipal);
                } catch (IOException io) {
                        Notification.show("Error al consultar las habitaciones disponibles");
                } catch (Exception e) {
                        Notification.show("Error al agendar habitacion");
                }
        }
}