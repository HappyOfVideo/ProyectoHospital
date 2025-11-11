package com.vaadin.proyectofinalvaadin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.vaadin.proyectofinalvaadin.Controller.Procesos;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
public class menuMedicos extends Div {

    // Variables Globales

    // Inicio sesion

    // Inicialización
    public static VerticalLayout divForm;
    public static Select<String> tipoUserSelect;
    public static NumberField userField;
    public static TextField passwordField;
    public static Button ingresarButton;

    // (Uso de IA para apoyo en ubicacion de margenes y alineamientos)
    public menuMedicos() {

        // main layout
        VerticalLayout main = new VerticalLayout();
        // ajuste de espacios del main layout (IA)
        main.setWidthFull();
        main.setSpacing(false);
        main.setPadding(true);
        main.getStyle()
            .set("background", "linear-gradient(135deg, #1c4c5dff 0%, #1C4C5D 100%)")
            .set("min-height", "100vh")
            .set("margin", "0")
            .set("font-family", "Arial, sans-serif")
        ;

        // hero layout
        FormLayout hero = new FormLayout();
        hero.setResponsiveSteps(
            new FormLayout.ResponsiveStep("0", 1),
            new FormLayout.ResponsiveStep("600px", 2));

        construirHero(hero);

        main.add(hero);

        add(main);

    }

    // Construir hero, agregara los componentes del encabezado inicial de la pagina
    public void construirHero(FormLayout hero) {
        try {

        } catch (Exception e) {
            String error = e.getMessage();
            System.err.println("Error en construirHero: " + error);
            e.printStackTrace();
        }
    }

}