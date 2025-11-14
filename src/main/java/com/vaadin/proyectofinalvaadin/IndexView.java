package com.vaadin.proyectofinalvaadin;

import java.io.IOException;

import com.vaadin.proyectofinalvaadin.Controller.Procesos; // Poder hacer uso de las funciones que estan dentro de Procesos.java
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
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
public class IndexView extends VerticalLayout {

        // Inicialización
        public static VerticalLayout divForm;
        public static Select<String> tipoUserSelect;
        public static NumberField userField;
        public static TextField passwordField;
        public static Button ingresarButton;

        // (Uso de IA para apoyo en ubicacion de margenes y alineamientos)
        public IndexView() {

                // Hace que entre los layouts del @Route se ocupe todo el espacio disponible
                // (IA)
                setSizeFull();
                setPadding(false);
                setMargin(false);
                setSpacing(false);

                // Main layout
                VerticalLayout main = new VerticalLayout();
                // Ajuste de espacios del main layout (IA)
                main.setSizeFull();
                main.setPadding(false);
                main.setMargin(false);
                main.setSpacing(false);

                // Hero layout
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

                        VerticalLayout leftSide = new VerticalLayout();
                        VerticalLayout rightSide = new VerticalLayout();

                        // Ajustes de VerticalLayouts
                        leftSide.setAlignItems(Alignment.CENTER);
                        leftSide.getStyle()
                                        .set("align-self", "flex-start") // (IA)
                                        .set("background-color", "#B0B0B0");
                        leftSide.setHeight("729px");

                        rightSide.setAlignItems(Alignment.CENTER);
                        rightSide.getStyle().set("align-self", "flex-start"); // (IA)
                        rightSide.setHeight("729px");

                        // LeftSide -> Marca (logo)
                        Image logo = new Image("https://i.imgur.com/IX0v7k1.png", "Logo del hospital");

                        // Ajustes de logo
                        logo.setWidth("400px");
                        logo.setHeight("auto");
                        logo.getStyle().set("margin-top", "120px");

                        // RightSide -> panel de inicio de sesion
                        // Titulo y subtitulo
                        H1 inicioSesion = new H1("Inicio de sesión");
                        H4 instrucciones = new H4("Bienvenido a Montelíbano, porfavor ingrese sus datos");

                        // Ajuste de textos
                        inicioSesion.getStyle()
                                        .set("text-align", "center")
                                        .set("margin", "0")
                                        .set("width", "100%")
                                        .set("margin-top", "50px");

                        instrucciones.getStyle()
                                        .set("text-align", "center")
                                        .set("margin", "0 0 1rem 0")
                                        .set("width", "100%")
                                        .set("color", "#a3a3a3ff");

                        // Formulario inicio de sesión
                        FormLayout formLayout = new FormLayout();
                        formLayout.setWidth("100%");
                        formLayout.getStyle()
                                        .set("margin-top", "50px")
                                        .set("margin-left", "-10px");

                        // Configurar pasos responsivos
                        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));

                        // Campos del formulario
                        tipoUserSelect = new Select<>();
                        tipoUserSelect.setLabel("Tipo usuario");
                        tipoUserSelect.setItems("Administrativo", "Médico", "Enfermero(a)");
                        tipoUserSelect.setPlaceholder("Seleccione tipo");
                        tipoUserSelect.setMinWidth("300px");
                        userField = new NumberField("Usuario");
                        userField.setPlaceholder("ID del hospital");
                        userField.setMinWidth("300px");
                        passwordField = new TextField("Ingrese la contraseña");
                        passwordField.setPlaceholder("•••••••••••••");
                        passwordField.setMinWidth("300px");

                        // Botón de ingreso (agregado para completar el formulario)
                        ingresarButton = new Button("Ingresar");
                        // Ajustes botón
                        ingresarButton.setMinWidth("180px");
                        ingresarButton.getStyle()
                                        .set("margin-top", "20px")
                                        .set("margin-bottom", "50px")
                                        .set("background-color", "#1C4C5D")
                                        .set("color", "#ffffffff");

                        // Acción del botón --> Verifica "base de datos" y redirije a su repectiva
                        // Página
                        ingresarButton.addClickListener(event -> {
                                try {
                                        String tipoU = tipoUserSelect.getValue();
                                        Double userN = userField.getValue();
                                        String password = passwordField.getValue();

                                        if (tipoU == null || userN == null || password.isEmpty()) {
                                                Notification.show("Porfavor complete todos los campos");
                                                return;
                                        } else if (userN <= 0) {
                                                Notification.show("Usuario invalido");
                                                return;
                                        }

                                        try {
                                                boolean acceso = Procesos.Validacion(tipoU, userN, password); // Invocar
                                                                                                              // la
                                                                                                              // funcion
                                                                                                              // de
                                                                                                              // Procesos.java
                                                String nombreUser = Procesos.separarUser(userN);
                                                
                                                //dependiendo de la opción escogida y la validacion de los campos, redirije a la vista correcta
                                                if (acceso) {
                                                        if (tipoU.equals("Administrativo")) {
                                                                Notification.show("Acceso válido");
                                                                UI.getCurrent().navigate("menuAdministrativos");
                                                        } else if (tipoU.equals("Médico")) {
                                                                Notification.show("Acceso válido");
                                                                UI.getCurrent().navigate("menuMedicos");
                                                        } else if (tipoU.equals("Enfermero(a)")) {
                                                                Notification.show("Acceso válido");
                                                                UI.getCurrent().navigate("menuEnfermeros");
                                                        }
                                                } else {
                                                        Notification.show("Datos incorrectos, intente nuevamente");
                                                }

                                        } catch (IOException e) {
                                                Notification.show("Error al leer el archivo de usuarios");
                                        }

                                } catch (Exception e) {
                                        Notification.show("Error en el botón, porfavor comunicarse con soporte");
                                }

                        });

                        // VerticalLayout para alinear objetos al centro y crear un cubo de formulario
                        divForm = new VerticalLayout();
                        divForm.setWidthFull();
                        divForm.getStyle().set("padding", "20px");
                        divForm.setAlignItems(Alignment.CENTER);

                        divForm.add(inicioSesion, instrucciones, tipoUserSelect, userField, passwordField,
                                        ingresarButton);

                        // Agregar campos al formulario
                        formLayout.add(divForm);

                        // Agregar componentes de leftSide
                        leftSide.add(logo);

                        // Agregar componentes de rightSide
                        rightSide.add(formLayout);

                        // Agregar componentes de hero
                        hero.add(leftSide, rightSide);

                } catch (Exception e) {
                        String error = e.getMessage();
                        System.err.println("Error en construirHero: " + error);
                        e.printStackTrace();
                }
        }

}