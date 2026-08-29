package com.vaadin.proyectofinalvaadin;

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
import com.vaadin.flow.router.Route;
import com.vaadin.proyectofinalvaadin.Controller.Procesos;

@Route("imprimirFactura")
public class imprimirFactura extends VerticalLayout {
        public final static String RUTA_PACIENTES = "src/main/java/com/vaadin/proyectofinalvaadin/src/listaPacientes.txt";
        public final static String REGEX = ".*[@#$%^&*()_+={}\\[\\]:;\"'<>,.?/\\\\|`~].*";

        public imprimirFactura() {
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
                        btnRegresar.addClickListener(e -> UI.getCurrent().navigate("menuAdministrativos"));

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
                                UI.getCurrent().navigate("");
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

                        // Contenedor de facturacion
                        Div contenedorHabitaciones = new Div();
                        contenedorHabitaciones.getStyle()
                                        .set("background", "white")
                                        .set("border-radius", "15px")
                                        .set("padding", "40px")
                                        .set("max-width", "500px")
                                        .set("width", "100%")
                                        .set("text-align", "center")
                                        .set("box-shadow", "0 4px 20px rgba(0,0,0,0.1)")
                                        .set("margin-top", "110px")
                                        .set("padding-left", "110px");
                        ;

                        // Grid de detalles
                        VerticalLayout detalles = new VerticalLayout();
                        FormLayout gridDetalles = new FormLayout();
                        gridDetalles.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));
                        gridDetalles.setMaxWidth("400px");
                        gridDetalles.getStyle().set("justify-content", "center");

                        // Campos de facturación
                        H2 tituloDetalles = new H2();
                        tituloDetalles.setText("Imprimir factura ");
                        tituloDetalles.getStyle()
                                        .set("background", "linear-gradient(135deg, #5e2525ff 0%, #476482ff 100%)")
                                        .set("color", "white")
                                        .set("font-weight", "bold")
                                        .set("padding", "10px 0px 10px 0px")
                                        .set("margin-bottom", "10px")
                                        .set("border-radius", "10px");
                        ;
                        IntegerField documentoPaciente = new IntegerField("Ingrese el documento");
                        documentoPaciente.setPlaceholder("Ej: cc");
                        IntegerField diasActivo = new IntegerField("Ingrese dias activo");
                        diasActivo.setPlaceholder("Días");

                        // Contenerdor de botones
                        HorizontalLayout contenedorBotones = new HorizontalLayout();
                        contenedorBotones.getStyle().set("margin-top", "20px");
                        Button agregar = new Button(new Icon(VaadinIcon.PRINT));
                        agregar.setSizeFull();
                        agregar.getStyle()
                                        .set("font-size", "30px")
                                        .set("background", "linear-gradient(135deg, #2c3e50 0%, #34495e 100%)")
                                        .set("color", "#ffffff");
                        ;

                        //evento al darle click, valida errores y ejecuta si todo esta bien
                        try {
                                agregar.addClickListener(ev -> {
                                        if (documentoPaciente.isEmpty() || diasActivo.isEmpty()) {
                                                Notification.show("Los campos no pueden estar vacíos");
                                                return;
                                        }

                                        String dp = String.valueOf(documentoPaciente.getValue());
                                        String da = String.valueOf(diasActivo.getValue());
                                        Boolean tieneAlta = false;

                                        if (!dp.matches("\\d*") || !da.matches("\\d*")) {
                                                Notification.show("No se permiten tildes ni caracteres especiales");
                                                return;
                                        } else if (!new java.io.File(Procesos.RUTA_CARPETA_PACIENTES + dp + ".txt").exists()) {
                                                Notification.show("Usuario no encontrado");
                                                return;
                                        } else {
                                                try {
                                                        tieneAlta = Procesos.tieneAltaMedica(String.valueOf(dp));
                                                        if (tieneAlta) {
                                                                Procesos.facturar(dp, da);
                                                                Notification.show(
                                                                                "Factura generada corrrectamente, feliz día");
                                                                return;
                                                        } else {
                                                                Notification.show("El paciente no tiene alta medica");
                                                        }

                                                } catch (Exception e) {
                                                        Notification.show("Error al facturar, contactar soporte ");
                                                }
                                                documentoPaciente.clear();
                                                diasActivo.clear();

                                        }

                                });
                        } catch (Exception e) {
                                Notification.show("Error en el botón, contactar con soporte");
                                return;
                        }

                        contenedorBotones.add(agregar);

                        gridDetalles.add(tituloDetalles, documentoPaciente, diasActivo, contenedorBotones);

                        detalles.add(gridDetalles);
                        contenedorHabitaciones.add(detalles);

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
                        contenidoCentral.add(contenedorHabitaciones, footer);
                        contenedorPrincipal.add(headerLayout, contenidoCentral);

                        add(contenedorPrincipal);
                } catch (Exception e) {
                        Notification.show("Error al agendar habitacion");
                }
        }
}