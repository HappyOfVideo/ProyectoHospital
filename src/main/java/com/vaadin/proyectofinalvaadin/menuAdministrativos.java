package com.vaadin.proyectofinalvaadin;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("menuAdministrativos")
public class menuAdministrativos extends VerticalLayout {

    public menuAdministrativos() {
        try {
            // Layout principal con fondo gradiente
            VerticalLayout mainLayout = new VerticalLayout();
            mainLayout.setWidthFull();
            mainLayout.setSpacing(false);
            mainLayout.setPadding(false);
            mainLayout.getStyle()
                .set("background", "linear-gradient(135deg, #1C4C5D 0%, #1C4C5D 100%)")
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

            // 🔹 Layout superior con el título y el botón regresar
            H1 mainTitle = new H1("Bienvenido al sistema MonteLibano");
            mainTitle.getStyle()
                .set("color", "#2c3e50")
                .set("margin", "0")
                .set("font-size", "2.4em")
                .set("font-weight", "bold")
                .set("text-shadow", "1px 1px 2px rgba(0,0,0,0.1)");

            Button backButton = new Button("Regresar", new Icon(VaadinIcon.ARROW_LEFT));
            backButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
            backButton.getStyle()
                .set("background-color", "#f0f0f0")
                .set("color", "#3498db")
                .set("font-weight", "bold")
                .set("border-radius", "10px")
                .set("padding", "8px 16px")
                .set("box-shadow", "0 2px 6px rgba(0,0,0,0.15)")
                .set("cursor", "pointer")
                .set("transition", "all 0.2s ease");

            // Acción del botón regresar 
            backButton.addClickListener(e -> UI.getCurrent().navigate("")); //TODO

            // Efecto hover
            backButton.getElement().getStyle().set("transition", "background-color 0.3s ease");
            backButton.getElement().addEventListener("mouseover",
                    e -> backButton.getStyle().set("background-color", "#e0e0e0"));
            backButton.getElement().addEventListener("mouseout",
                    e -> backButton.getStyle().set("background-color", "#f0f0f0"));

            // Layout horizontal (título izquierda, botón derecha)
            HorizontalLayout headerLayout = new HorizontalLayout(mainTitle, backButton);
            headerLayout.setWidthFull();
            headerLayout.setJustifyContentMode(JustifyContentMode.BETWEEN);
            headerLayout.setAlignItems(Alignment.CENTER);
            headerLayout.getStyle().set("margin-bottom", "30px");

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
            buttonsContainer.setAlignItems(Alignment.CENTER);

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

            registerButton.addClickListener(e -> UI.getCurrent().navigate("registrar-paciente"));

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

            invoiceButton.addClickListener(e -> UI.getCurrent().navigate("imprimir-factura"));

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

            // Construcción final
            buttonsContainer.add(registerButton, invoiceButton);
            footer.add(hospitalName);

            contentContainer.add(headerLayout, subtitle, buttonsContainer, footer);

            mainLayout.setJustifyContentMode(JustifyContentMode.CENTER);
            mainLayout.setAlignItems(Alignment.CENTER);
            mainLayout.add(contentContainer);

            add(mainLayout);

        } catch (Exception e) {
            System.err.println("Error en menuAdministrativos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
