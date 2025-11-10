package com.vaadin.proyectofinalvaadin;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

@Route("menuMedicosEnfermeros")
public class menuMedicosEnfermeros extends Div {

    public menuMedicosEnfermeros() {
        try {
            // Layout principal
            VerticalLayout mainLayout = new VerticalLayout();
            mainLayout.setWidthFull();
            mainLayout.setSpacing(false);
            mainLayout.setPadding(true);
            mainLayout.getStyle()
                .set("background", "linear-gradient(135deg, #1C4C5D 0%, #1C4C5D 100%)")
                .set("min-height", "100vh")
                .set("margin", "0")
                .set("font-family", "Arial, sans-serif");

            // Contenedor blanco para el contenido
            Div contentContainer = new Div();
            contentContainer.getStyle()
                .set("background", "white")
                .set("border-radius", "15px")
                .set("padding", "40px")
                .set("margin", "20px auto")
                .set("margin-top", "180px")
                .set("max-width", "900px")
                .set("box-shadow", "0 10px 30px rgba(0,0,0,0.1)")
                .set("position", "relative");

           // HEADER CON TÍTULO Y BOTÓN DE REGRESAR
            HorizontalLayout headerLayout = new HorizontalLayout();
            headerLayout.setWidthFull();
            headerLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
            headerLayout.setAlignItems(FlexComponent.Alignment.CENTER);
            headerLayout.getStyle()
                .set("margin-bottom", "20px");

            // Título principal
            H1 mainTitle = new H1("Menú Medicos-Enfermeros");
            mainTitle.getStyle()
                .set("color", "#2c3e50")
                .set("margin", "0")
                .set("font-size", "2.5em")
                .set("font-weight", "bold")
                .set("text-shadow", "1px 1px 2px rgba(0,0,0,0.1)");

            // Botón de regresar
            Button backButton = new Button("Regresar", new Icon(VaadinIcon.ARROW_LEFT));
            backButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
            backButton.getStyle()
                .set("margin", "0")
                .set("font-weight", "bold")
                .set("color", "#3498db")
                .set("border", "1px solid #3498db");
            
            // Acción del botón regresar - redirige a la página anterior o específica
            backButton.addClickListener(e -> {
                // Redirige a la página anterior o a una específica
                backButton.getUI().ifPresent(ui -> ui.navigate("hospital\\src\\main\\java\\com\\vaadin\\proyectofinalvaadin\\IndexView.java"));
            });

            headerLayout.add(mainTitle, backButton);

            // SECCIÓN DE BÚSQUEDA
            VerticalLayout searchSection = new VerticalLayout();
            searchSection.setWidthFull();
            searchSection.setSpacing(true);
            searchSection.setPadding(false);

            // Subtítulo
            H2 subTitle = new H2("Buscar paciente");
            subTitle.getStyle()
                .set("color", "#34495e")
                .set("text-align", "center")
                .set("margin-top", "0")
                .set("margin-bottom", "30px")
                .set("font-size", "1.5em")
                .set("font-weight", "normal");

            // Layout horizontal para las barras de búsqueda
            HorizontalLayout searchLayout = new HorizontalLayout();
            searchLayout.setWidthFull();
            searchLayout.setSpacing(true);
            searchLayout.setAlignItems(Alignment.CENTER);

            // Barra de búsqueda por Nombre
            TextField searchByName = createSearchField(
                "Buscar por nombre", 
                VaadinIcon.USER, 
                "Escriba el nombre completo del paciente"
            );

            // Barra de búsqueda por Documento
            TextField searchByDocument = createSearchField(
                "Buscar por documento", 
                VaadinIcon.CREDIT_CARD, 
                "Escriba el número de documento del paciente"
            );

            // Barra de búsqueda por Habitación
            TextField searchByRoom = createSearchField(
                "Buscar por habitación", 
                VaadinIcon.BED, 
                "Escriba el número de habitación del paciente"
            );

            // Aplicar estilos básicos a los campos
            applyBasicFieldStyle(searchByName);
            applyBasicFieldStyle(searchByDocument);
            applyBasicFieldStyle(searchByRoom);

            // Agregar las barras al layout horizontal
            searchLayout.add(searchByName, searchByDocument, searchByRoom);
            searchLayout.setFlexGrow(1, searchByName, searchByDocument, searchByRoom);

            // SECCIÓN DE INSTRUCCIONES
            VerticalLayout instructionSection = new VerticalLayout();
            instructionSection.setWidthFull();
            instructionSection.setSpacing(true);
            instructionSection.setPadding(true);
            instructionSection.getStyle()
                .set("background", "#f8f9fa")
                .set("border-radius", "10px")
                .set("margin-top", "30px")
                .set("border-left", "4px solid #3498db");

            // Texto de instrucciones
            Paragraph instructionText = new Paragraph(
                "Escribir en la parte de arriba como quiere buscar al paciente (Nombre, Documento, Habitación). " +
                "Luego de eso le aparecerá en pantalla la información."
            );
            instructionText.getStyle()
                .set("color", "#2c3e50")
                .set("font-size", "1.1em")
                .set("line-height", "1.6")
                .set("margin", "0")
                .set("text-align", "left");


            // CONSTRUCCIÓN FINAL
            searchSection.add(subTitle, searchLayout);
            instructionSection.add(instructionText);
            
            contentContainer.add(headerLayout, searchSection, instructionSection);
            mainLayout.add(contentContainer);
            add(mainLayout);

        } catch (Exception e) {
            System.err.println("Error en MenuMedicosEnfermeros: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método auxiliar para crear campos de búsqueda
    private TextField createSearchField(String placeholder, VaadinIcon icon, String tooltip) {
        TextField field = new TextField();
        field.setPlaceholder(placeholder);
        field.setPrefixComponent(new Icon(icon));
        field.setWidth("100%");

        Button infoButton = new Button(new Icon(VaadinIcon.INFO_CIRCLE));
        infoButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE, ButtonVariant.LUMO_ICON);
        field.setSuffixComponent(infoButton);
        field.setTooltipText(tooltip);

        return field;
    }

    // Método simplificado para aplicar estilos básicos a los campos
    private void applyBasicFieldStyle(TextField field) {
        field.getElement().getStyle()
            .set("border", "2px solid #bdc3c7")
            .set("border-radius", "8px")
            .set("background", "#f8f9fa")
            .set("transition", "all 0.3s ease")
            .set("padding", "8px");
        
        // Estilos para el input interno dentro del TextField
        field.getElement().executeJs(
            "this.style.setProperty('--lumo-text-field-background', '#f8f9fa');" +
            "this.style.setProperty('--lumo-text-field-border-color', '#bdc3c7');" +
            "this.style.setProperty('--lumo-text-field-border-radius', '8px');" +
            "this.style.setProperty('--lumo-text-field-border-width', '2px');"
        );
    }
}