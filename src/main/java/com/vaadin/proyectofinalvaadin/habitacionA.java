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
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("registrar/TipoA")
public class habitacionA extends VerticalLayout {
        public final static String REGEX = ".*[@#$%^&*()_+={}\\[\\]:;\"'<>,.?/\\\\|`~].*";

        public habitacionA() {

                setSizeFull();
                setPadding(false);
                setMargin(false);
                setSpacing(false);

                // HEADER CON COLOR DIFERENTE
                HorizontalLayout headerLayout = new HorizontalLayout();
                headerLayout.setWidthFull();
                headerLayout.setHeight("100px");
                headerLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
                headerLayout.setAlignItems(FlexComponent.Alignment.CENTER);
                headerLayout.getStyle()
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
                        .set("transition", "all 0.3s ease")
                ;
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
                UI.getCurrent().navigate(""); // TODO: ruta de login
                });

                rightButtons.add(btnRegresar, btnCerrarSesion);
                headerLayout.add(headerTitle, rightButtons);

                // CONTENEDOR PRINCIPAL QUE OCUPA TODA LA PANTALLA
                Div contenedorPrincipal = new Div();
                contenedorPrincipal.setSizeFull();
                contenedorPrincipal.getStyle()
                        .set("background", "white")
                        .set("display", "flex")
                        .set("flex-direction", "column")
                ;

                // CONTENIDO CENTRAL
                VerticalLayout contenidoCentral = new VerticalLayout();
                contenidoCentral.setSizeFull();
                contenidoCentral.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
                contenidoCentral.setAlignItems(FlexComponent.Alignment.CENTER);
                contenidoCentral.getStyle()
                        .set("padding", "40px")
                        .set("flex-grow", "1")
                ;

                // Contenedor de las habitaciones (mantiene el diseño original pero centrado)
                Div contenedorHabitaciones = new Div();
                contenedorHabitaciones.getStyle()
                        .set("background", "white")
                        .set("border-radius", "15px")
                        .set("padding", "40px")
                        .set("max-width", "1100px")
                        .set("width", "100%")
                        .set("text-align", "center")
                        .set("box-shadow", "0 4px 20px rgba(0,0,0,0.1)")
                ;

                // Grid de habitaciones
                FormLayout gridHabitaciones = new FormLayout();
                gridHabitaciones.setResponsiveSteps(
                        new FormLayout.ResponsiveStep("0", 2),
                        new FormLayout.ResponsiveStep("600px", 3),
                        new FormLayout.ResponsiveStep("900px", 5)
                );
                gridHabitaciones.getStyle()
                        .set("justify-content", "center")
                        .set("gap", "20px")
                ;

                //Grid de detalles
                VerticalLayout detalles = new VerticalLayout();
                FormLayout gridDetalles = new FormLayout();
                gridDetalles.setResponsiveSteps(
                        new FormLayout.ResponsiveStep("0", 1)
                );
                gridDetalles.setMaxWidth("400px");
                gridDetalles.getStyle().set("justify-content", "center");
                 
                // Creación de las 10 habitaciones
                
                H2 tituloDetalles = new H2();
                for (int i = 1; i < 11; i++) {
                        String nombreHabitacion = "A"+i;        //Variable creada dentro de ciclo para poder que cada boton recuerde su número

                        Icon bedIcon = new Icon(VaadinIcon.BED);
                        bedIcon.setSize("40px");
                        
                        Button habitacion = new Button("HABITACIÓN A"+i, bedIcon);
                        //int num = i;
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
                        ;

                        contenedorHabitaciones.add(habitacion);
                        gridHabitaciones.add(habitacion);
                        habitacion.addClickListener(e -> {
                                if (true /*TODO: Revisar que no esta ocupada en archivos */) {
                                        tituloDetalles.setText("Habitación " + nombreHabitacion);
                                        gridHabitaciones.setVisible(false);
                                        detalles.setVisible(true);
                                }else{
                                        habitacion.getStyle().set("background-color", "#f44444ff");
                                        habitacion.setEnabled(false);
                                        Notification.show("Hábitación ocupada");
                                        return;
                                }
                        });
                }

                //Campos de paciente
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
                NumberField documentoPaciente = new NumberField("Ingrese el documento");
                documentoPaciente.setPlaceholder("Ej: cc");

                //Contenerdor de botones
                HorizontalLayout contenedorBotones = new HorizontalLayout();
                contenedorBotones.getStyle().set("margin-top", "20px");
                Button salir = new Button(new Icon(VaadinIcon.ARROW_LEFT));
                salir.setMinWidth("191px");
                salir.getStyle()
                        .set("background", "linear-gradient(135deg, #2c3e50 0%, #34495e 100%)")
                        .set("color", "#ffffff");
                ;
                salir.addClickListener(e ->{
                        gridHabitaciones.setVisible(true);
                        detalles.setVisible(false);
                });
                Button agregar = new Button(new Icon(VaadinIcon.ADD_DOCK));
                agregar.setMinWidth("191px");
                agregar.getStyle()
                        .set("background", "linear-gradient(135deg, #2c3e50 0%, #34495e 100%)")
                        .set("color", "#ffffff");
                ;
                try {
                        agregar.addClickListener(e ->{
                                String dp = String.valueOf(documentoPaciente.getValue());
                                if (dp == null || nombrePaciente.getValue().equals("")){
                                        Notification.show("Los campos no pueden estar vacío");
                                        return; 
                                }else if (!dp.matches("\\d*")&&(nombrePaciente.getValue()).matches(REGEX)){
                                        Notification.show("No se permiten tildes ni caracteres especiales");
                                        return;
                                }else{
                                        gridHabitaciones.setVisible(true);
                                        detalles.setVisible(false);
                                        nombrePaciente.clear();
                                        documentoPaciente.clear();
                                        
                                        String[] habitacion = (tituloDetalles.getText()).split(" ");
                                        String nHabitacion = "";
                                        nHabitacion = habitacion[1];
                                        //TODO: crear paciente nuevo recibes: nombrePaciente, documentoPaciente y nHabitacion
                                        Notification.show("Habitación "+nHabitacion+" ocupada correctamente");
                                        return;
                                }
                        });
                } catch (Exception e) {
                        Notification.show("Error en el botón, contactar con soporte");
                        return;
                }

                contenedorBotones.add(salir,agregar);
                gridDetalles.add(tituloDetalles, nombrePaciente,documentoPaciente,contenedorBotones);
                detalles.add(gridDetalles);

                detalles.setVisible(false);
                
                
                // Pie de página
                Div footer = new Div();
                footer.getStyle()
                        .set("margin-top", "40px")
                        .set("padding-top", "30px")
                        .set("border-top", "2px solid #ecf0f1")
                        .set("text-align", "center")
                        .set("color", "#676f6fff")
                        .set("font-size", "20px");
                footer.setText("HOSPITAL MONTELÍBANO");

                // ENSAMBLAJE FINAL
                contenedorHabitaciones.add(gridHabitaciones,detalles);
                contenidoCentral.add(contenedorHabitaciones, footer);
                contenedorPrincipal.add(headerLayout, contenidoCentral);
                
                add(contenedorPrincipal);
        }
}