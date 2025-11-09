package com.vaadin.proyectofinalvaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
public class IndexView extends VerticalLayout {

    public IndexView() {

        VerticalLayout main = new VerticalLayout();

        FormLayout hero = new FormLayout();
        hero.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("600px", 2)) ;

        construirHero(hero);

        main.add(hero);

        add(main);

    }

    // Construir hero agregara los componentes del encabezado inicial de la pagina (Uso de IA para apoyo)
    public static void construirHero(FormLayout hero) {
        try {
            VerticalLayout leftSide = new VerticalLayout(); 
            VerticalLayout rightSide = new VerticalLayout();

            leftSide.setAlignItems(Alignment.CENTER);
            leftSide.setJustifyContentMode(JustifyContentMode.CENTER);
        
            rightSide.setAlignItems(Alignment.CENTER);
            rightSide.setJustifyContentMode(JustifyContentMode.START);

            // leftSide -> Marca (logo)
            Image logo = new Image("https://i.imgur.com/IX0v7k1.png", "Logo de la empresa");

            // Cambio de tamaño
            logo.setWidth("600px"); 
            logo.setHeight("auto");

            // Centrar el logo dentro de leftSide
            logo.getStyle().set("margin", "center");

            // Agregar componentes de leftSide
            leftSide.add(logo);

            // rightSide -> panel de inicio de sesion y de registro
            // Subtitulos
            H2 inicioSesion = new H2("Inicio de sesión");
            H4 instrucciones = new H4("Por favor ingrese su primer nombre con su primer apellido y su D.I.");

            // Centrar los textos
            inicioSesion.getStyle()
                    .set("text-align", "center")
                    .set("margin", "0")
                    .set("width", "100%");

            instrucciones.getStyle()
                    .set("text-align", "center")
                    .set("margin", "0 0 1rem 0")
                    .set("width", "100%")
                    .set("color", "#666");

            // Campos del formulario
            TextField nombre = new TextField("Primer Nombre");
            TextField apellido = new TextField("Primer Apellido");
            NumberField dI = new NumberField("Documento de identidad");

            FormLayout formLayout = new FormLayout();
            formLayout.setWidth("100%");

            // Configurar pasos responsivos
            formLayout.setResponsiveSteps(
                    new FormLayout.ResponsiveStep("0", 1),
                    new FormLayout.ResponsiveStep("600px", 2));

            // Agregar campos al formulario (forma correcta)
            formLayout.add(nombre, apellido);
            formLayout.add(dI, 2); // Ocupa 2 columnas

            // Botón de ingreso (agregado para completar el formulario)
            Button ingresarButton = new Button("Ingresar");
            ingresarButton.setWidthFull();
            formLayout.add(ingresarButton, 2);

            // Agregar todo al rightSide
            rightSide.add(inicioSesion, instrucciones, formLayout);

            hero.add(leftSide, rightSide);

        } catch (Exception e) {
            String error = e.getMessage();
            System.err.println("Error en construirHero: " + error);
        }
    }
}