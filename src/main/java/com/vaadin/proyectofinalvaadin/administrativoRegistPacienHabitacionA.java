package com.vaadin.proyectofinalvaadin;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("administrativoRegistPacienHabitacionA")
public class administrativoRegistPacienHabitacionA extends VerticalLayout {

    public administrativoRegistPacienHabitacionA() {

        setSizeFull();
        setPadding(false);
        setMargin(false);
        setSpacing(false);

        // HEADER CON COLOR DIFERENTE
        HorizontalLayout headerLayout = new HorizontalLayout();
        headerLayout.setWidthFull();
        headerLayout.setHeight("80px");
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
                .set("transition", "all 0.3s ease");
        btnRegresar.addClickListener(e -> UI.getCurrent().navigate("administrativoRegistPacien"));

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
                .set("flex-direction", "column");

        // CONTENIDO CENTRAL
        VerticalLayout contenidoCentral = new VerticalLayout();
        contenidoCentral.setSizeFull();
        contenidoCentral.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        contenidoCentral.setAlignItems(FlexComponent.Alignment.CENTER);
        contenidoCentral.getStyle()
                .set("padding", "40px")
                .set("flex-grow", "1");

        // Contenedor de las habitaciones (mantiene el diseño original pero centrado)
        Div contenedorHabitaciones = new Div();
        contenedorHabitaciones.getStyle()
                .set("background", "white")
                .set("border-radius", "15px")
                .set("padding", "40px")
                .set("max-width", "1000px")
                .set("width", "100%")
                .set("text-align", "center")
                .set("box-shadow", "0 4px 20px rgba(0,0,0,0.1)");

        // Grid de habitaciones (manteniendo tu código original)
        FormLayout gridHabitaciones = new FormLayout();
        gridHabitaciones.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 2),
                new FormLayout.ResponsiveStep("600px", 3),
                new FormLayout.ResponsiveStep("900px", 5));
        gridHabitaciones.getStyle()
                .set("justify-content", "center")
                .set("gap", "20px");

        // Creación de las 10 habitaciones
        for (int i = 1; i <= 10; i++) {
            Icon bedIcon = new Icon(VaadinIcon.BED);
            bedIcon.setSize("40px");

            Button habitacion = new Button("HABITACIÓN " + (100 + i), bedIcon);
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
                    .set("transition", "all 0.3s ease");

            // Efectos hover mejorados
            habitacion.getElement().getStyle().set("cursor", "pointer");
            habitacion.addClickListener(e -> {
                habitacion.getStyle().set("background", "#d6eaf8");
                // Navegar a la ruta correspondiente
                UI.getCurrent().navigate(""); // TODO: ruta específica de la habitación
            });

            gridHabitaciones.add(habitacion);
        }

        // Pie de página
        Div footer = new Div();
        footer.getStyle()
                .set("margin-top", "40px")
                .set("padding-top", "30px")
                .set("border-top", "2px solid #ecf0f1")
                .set("text-align", "center")
                .set("color", "#7f8c8d")
                .set("font-size", "1.1em");
        footer.setText("HOSPITAL MONTELÍBANO");

        // ENSAMBLAJE FINAL
        contenedorHabitaciones.add(gridHabitaciones, footer);
        contenidoCentral.add(contenedorHabitaciones);
        contenedorPrincipal.add(headerLayout, contenidoCentral);
        
        add(contenedorPrincipal);
    }
}