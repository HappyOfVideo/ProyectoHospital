package com.vaadin.proyectofinalvaadin;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

// Este es el menú principal para médicos y enfermeros
@Route("menuMedicosEnfermeros")
public class menuMedicosEnfermeros extends Div {

    public menuMedicosEnfermeros() {
        try {
            VerticalLayout layout = new VerticalLayout();

            // mensaje de bienvenida y explica cómo buscar los pacientes
            H1 titulo = new H1("Menú principal - Médicos y Enfermeros");
            H2 subtitulo = new H2("Escribir en la parte de arriba como desea buscar al paciente (Nombre, Documento, habitación)");
            H2 subtitulo2 = new H2("Feliz día!");

            // Crear layout horizontal para las 3 barras de búsqueda
            HorizontalLayout searchLayout = new HorizontalLayout();
            searchLayout.setWidthFull();
            searchLayout.setSpacing(true); // Espacio entre las barras
            searchLayout.setAlignItems(Alignment.CENTER); // Centrar verticalmente

            // Primera barra de búsqueda - busqueda por Nombre
            TextField searchByName = new TextField();
            searchByName.setPlaceholder("Buscar por nombre");
            searchByName.setPrefixComponent(new Icon(VaadinIcon.USER));
            searchByName.setWidth("100%"); 

            Button infoButton1 = new Button(new Icon(VaadinIcon.INFO_CIRCLE));
            infoButton1.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE, ButtonVariant.LUMO_ICON);
            searchByName.setSuffixComponent(infoButton1);
            searchByName.setTooltipText("Escriba el nombre completo del paciente");

            // Segunda barra de búsqueda - busqueda por Documento
            TextField searchByDocument = new TextField();
            searchByDocument.setPlaceholder("Buscar por documento");
            searchByDocument.setPrefixComponent(new Icon(VaadinIcon.CREDIT_CARD));
            searchByDocument.setWidth("100%");

            Button infoButton2 = new Button(new Icon(VaadinIcon.INFO_CIRCLE));
            infoButton2.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE, ButtonVariant.LUMO_ICON);
            searchByDocument.setSuffixComponent(infoButton2);
            searchByDocument.setTooltipText("Escriba el número de documento del paciente");

            // Tercera barra de búsqueda - busqueda por Habitación
            TextField searchByRoom = new TextField();
            searchByRoom.setPlaceholder("Buscar por habitación");
            searchByRoom.setPrefixComponent(new Icon(VaadinIcon.BED));
            searchByRoom.setWidth("100%");

            Button infoButton3 = new Button(new Icon(VaadinIcon.INFO_CIRCLE));
            infoButton3.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE, ButtonVariant.LUMO_ICON);
            searchByRoom.setSuffixComponent(infoButton3);
            searchByRoom.setTooltipText("Escriba el número de habitación del paciente");

            // Agregar las 3 barras al layout horizontal
            searchLayout.add(searchByName, searchByDocument, searchByRoom);
            
            // Hacer que cada barra ocupe el mismo espacio
            searchLayout.setFlexGrow(1, searchByName, searchByDocument, searchByRoom);

            layout.add(titulo, subtitulo, subtitulo2, searchLayout);
            add(layout);

        } catch (Exception e) {
            System.err.println("Error en MenuMedicosEnfermeros: " + e.getMessage());
            e.printStackTrace();
        }
    }
}