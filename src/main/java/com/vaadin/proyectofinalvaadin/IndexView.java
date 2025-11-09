package com.vaadin.proyectofinalvaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
public class IndexView extends VerticalLayout {

    //(Uso de IA para apoyo en ubicacion de margenes y aliniamientos)
    public IndexView() {

        //hace que entre los layouts del @Route se ocupe todo el espacio disponible     (IA)
        setSizeFull();
        setPadding(false);
        setMargin(false);
        setSpacing(false);

        //main layout
        VerticalLayout main = new VerticalLayout();
        //ajuste de espacios del main layout    (IA)
        main.setSizeFull();
        main.setPadding(false);
        main.setMargin(false);
        main.setSpacing(false);

        //hero layout
        FormLayout hero = new FormLayout();
        hero.setResponsiveSteps(
            new FormLayout.ResponsiveStep("0", 1),
            new FormLayout.ResponsiveStep("600px", 2)
        );

        construirHero(hero);

        main.add(hero);

        add(main);

    }

    // Construir hero agregara los componentes del encabezado inicial de la pagina 
    public static void construirHero(FormLayout hero) {
        try {

            VerticalLayout leftSide = new VerticalLayout(); 
            VerticalLayout rightSide = new VerticalLayout();

            //Ajustes de VerticalLayouts
            leftSide.setAlignItems(Alignment.CENTER);
            leftSide.getStyle()
            .set("align-self", "flex-start")    //(IA)
            .set("background-color","#B0B0B0");
            leftSide.setHeight("729px");

            rightSide.setAlignItems(Alignment.CENTER);
            rightSide.getStyle().set("align-self", "flex-start");    //(IA)
            rightSide.setHeight("729px");

            // leftSide -> Marca (logo)
            Image logo = new Image("https://i.imgur.com/IX0v7k1.png", "Logo del hospital");

            // Ajustes de logo
            logo.setWidth("400px"); 
            logo.setHeight("auto");
            logo.getStyle().set("margin-top", "120px");
            
            // rightSide -> panel de inicio de sesion
            // Titulo y subtitulo
            H1 inicioSesion = new H1("Inicio de sesión");
            H4 instrucciones = new H4("Bienvenido a Montelíbano, porfavor ingrese sus datos");
            
            // Ajuste de textos
            inicioSesion.getStyle()
            .set("text-align", "center")
            .set("margin", "0")
            .set("width", "100%")
            .set("margin-top","50px");
            
            instrucciones.getStyle()
            .set("text-align", "center")
            .set("margin", "0 0 1rem 0")
            .set("width", "100%")
            .set("color", "#a3a3a3ff");

            //Formulario inicio de sesión
            FormLayout formLayout = new FormLayout();
            formLayout.setWidth("100%");
            formLayout.getStyle()
            .set("margin-top", "50px")
            .set("margin-left", "-10px");

            // Configurar pasos responsivos
            formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1)
            );
            
            // Campos del formulario
            Select<String> tipoUser = new Select<>();
            tipoUser.setLabel("Tipo usuario");
            tipoUser.setItems("Administrativo","Médico","Enfermero(a)");
            tipoUser.setPlaceholder("Seleccione tipo");
            tipoUser.setMinWidth("300px");
            NumberField user = new NumberField("Usuario");
            user.setPlaceholder("ID del hospital");
            user.setMinWidth("300px");
            TextField password = new TextField("Contraseña");
            password.setPlaceholder("•••••••••••••");
            password.setMinWidth("300px");
            
            //Botón de ingreso (agregado para completar el formulario)
            Button ingresarButton = new Button("Ingresar");
            //Ajustes botón
            ingresarButton.setMinWidth("180px");
            ingresarButton.getStyle()
            .set("margin-top", "20px")
            .set("margin-bottom", "50px")
            .set("background-color", "#1C4C5D")
            .set("color", "#ffffffff");

            //VerticalLayout para alinear objetos al centro y crear un cubo de formulario
            VerticalLayout divForm = new VerticalLayout();
            divForm.setWidthFull();
            divForm.getStyle()
            .set("padding", "20px")
            .set("border-radius", "50px");
            divForm.setAlignItems(Alignment.CENTER);

            divForm.add(inicioSesion, instrucciones, tipoUser, user,password,ingresarButton);
            
            //Agregar campos al formulario
            formLayout.add(divForm);

            //Agregar componentes de leftSide
            leftSide.add(logo);

            //Agregar componentes de rightSide
            rightSide.add(formLayout);

            //Agregar componentes de hero   
            hero.add(leftSide, rightSide);
                
        }catch (Exception e) {
            String error = e.getMessage();
            System.err.println("Error en construirHero: " + error);
            e.printStackTrace();
        }
    }
}