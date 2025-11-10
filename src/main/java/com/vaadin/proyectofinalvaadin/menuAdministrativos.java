package com.vaadin.proyectofinalvaadin;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("menuAdministrativos")
public class menuAdministrativos extends VerticalLayout {

    public static void main(String[] args) {
        try {
            // Layout principal con fondo gradiente
            VerticalLayout mainLayout = new VerticalLayout();
            mainLayout.setWidthFull();
            mainLayout.setSpacing(false);
            mainLayout.setPadding(false);
            mainLayout.getStyle()
                .set("background", "linear-gradient(135deg, #667eea 0%, #764ba2 100%)")
                .set("min-height", "100vh")
                .set("margin", "0")
                .set("font-family", "Arial, sans-serif");

            // Contenedor principal blanco
            Div contentContainer = new Div();
            contentContainer.getStyle()
                .set("background", "white")
                .set("border-radius", "20px")
                .set("padding", "50px")
                .set("margin", "20px")
                .set("max-width", "700px")
                .set("width", "90%")
                .set("box-shadow", "0 15px 35px rgba(0,0,0,0.2)")
                .set("text-align", "center");

            // Título principal
            H1 mainTitle = new H1("Bienvenido al sistema MonteLibano");
            mainTitle.getStyle()
                .set("color", "#2c3e50")
                .set("margin-bottom", "10px")
                .set("font-size", "2.8em")
                .set("font-weight", "bold")
                .set("text-shadow", "1px 1px 2px rgba(0,0,0,0.1)");

            // Subtítulo
            H2 subtitle = new H2("¿Qué deseas hacer el día de hoy?");
            subtitle.getStyle()
                .set("color", "#34495e")
                .set("margin-top", "0")
                .set("margin-bottom", "40px")
                .set("font-size", "1.8em")
                .set("font-weight", "normal")
                .set("font-style", "italic");

            // Contenedor para los botones
            VerticalLayout buttonsContainer = new VerticalLayout();
            buttonsContainer.setWidth("100%");
            buttonsContainer.setSpacing(true);
            buttonsContainer.setPadding(false);
            buttonsContainer.setAlignItems(com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment.CENTER);

            // Botón 1: Registrar paciente
            Button registerButton = new Button("Registrar paciente", new Icon(VaadinIcon.USER_CARD));
            registerButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_LARGE);
            registerButton.getStyle()
                .set("width", "350px")
                .set("height", "60px")
                .set("font-size", "1.2em")
                .set("font-weight", "bold")
                .set("margin", "10px 0")
                .set("border-radius", "12px")
                .set("background", "linear-gradient(135deg, #3498db 0%, #2980b9 100%)")
                .set("color", "white")
                .set("border", "none")
                .set("box-shadow", "0 4px 15px rgba(52, 152, 219, 0.3)")
                .set("transition", "all 0.3s ease");

            // Acción del botón Registrar
            registerButton.addClickListener(e -> {
                registerButton.getStyle().set("transform", "scale(0.98)");
                UI.getCurrent().access(() -> {
                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    registerButton.getStyle().set("transform", "scale(1)");
                    UI.getCurrent().navigate("registrar-paciente");
                });
            });

            // Botón 2: Imprimir factura
            Button invoiceButton = new Button("Imprimir factura de un paciente", new Icon(VaadinIcon.PRINT));
            invoiceButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_LARGE);
            invoiceButton.getStyle()
                .set("width", "350px")
                .set("height", "60px")
                .set("font-size", "1.2em")
                .set("font-weight", "bold")
                .set("margin", "10px 0")
                .set("border-radius", "12px")
                .set("background", "linear-gradient(135deg, #27ae60 0%, #229954 100%)")
                .set("color", "white")
                .set("border", "none")
                .set("box-shadow", "0 4px 15px rgba(39, 174, 96, 0.3)")
                .set("transition", "all 0.3s ease");

            // Acción del botón Imprimir factura
            invoiceButton.addClickListener(e -> {
                invoiceButton.getStyle().set("transform", "scale(0.98)");
                UI.getCurrent().access(() -> {
                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    invoiceButton.getStyle().set("transform", "scale(1)");
                    UI.getCurrent().navigate("imprimir-factura");
                });
            });

            // Pie de página
            Div footer = new Div();
            footer.getStyle()
                .set("margin-top", "60px")
                .set("padding-top", "20px")
                .set("border-top", "2px solid #ecf0f1")
                .set("text-align", "center");

            H2 hospitalName = new H2("HOSPITAL MONTELÍBANO");
            hospitalName.getStyle()
                .set("color", "#2c3e50")
                .set("font-size", "2em")
                .set("font-weight", "bold")
                .set("margin", "0")
                .set("letter-spacing", "2px")
                .set("text-shadow", "1px 1px 3px rgba(0,0,0,0.1)");

            // CONSTRUCCIÓN FINAL
            
            // Agregar elementos a sus contenedores
            buttonsContainer.add(registerButton, invoiceButton);
            footer.add(hospitalName);
            
            // Agregar todo al contentContainer
            contentContainer.add(mainTitle, subtitle, buttonsContainer, footer);
            
            // Centrar el contentContainer usando el estilo del mainLayout
            mainLayout.setJustifyContentMode(com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode.CENTER);
            mainLayout.setAlignItems(com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment.CENTER);
            mainLayout.add(contentContainer);
            
            // Agregar el mainLayout a esta página - CORREGIDO
            //add(mainLayout);
            

        } catch (Exception e) {
            System.err.println("Error en menuAdministrativos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}