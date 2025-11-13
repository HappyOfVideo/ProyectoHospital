package com.vaadin.proyectofinalvaadin;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("administrativoRegistPacien")
public class administrativoRegistPacien extends VerticalLayout {  //ESTA PESTAÑA SE DERIVA DE menuAdministrativos

    public static VerticalLayout divForm;

    public administrativoRegistPacien() {

        setSizeFull();
        setPadding(false);
        setMargin(false);
        setSpacing(false);

        // main layout
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setWidthFull();
        mainLayout.setSpacing(false);
        mainLayout.setPadding(false);
        mainLayout.getStyle()
            .set("background", "linear-gradient(135deg, #1C4C5D 0%, #1C4C5D 100%)")
            .set("min-height", "100vh")
            .set("font-family", "Arial, sans-serif")
            .set("padding-left", "370px");

        // hero layout
        FormLayout hero = new FormLayout();
        hero.setResponsiveSteps(
            new FormLayout.ResponsiveStep("0", 1)
        );
        construirHero(hero);

        mainLayout.add(hero);
        mainLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        mainLayout.setAlignItems(Alignment.CENTER);

        add(mainLayout);
    }

    // Construir hero
    public void construirHero(FormLayout hero) {
        try {
            // Contenedor blanco
            Div contenedor = new Div();
            contenedor.getStyle()
                .set("background", "white")
                .set("border-radius", "20px")
                .set("padding", "50px")
                .set("max-width", "700px")
                .set("text-align", "center");

            // Header layout
            HorizontalLayout headerLayout = new HorizontalLayout();
            headerLayout.setWidthFull();
            headerLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
            headerLayout.setAlignItems(FlexComponent.Alignment.CENTER);
            headerLayout.getStyle().set("margin-bottom", "40px");

            // Título principal
            H1 mainTitle = new H1("Bienvenido");
            mainTitle.getStyle()
                .set("color", "#2c3e50")
                .set("margin", "0")
                .set("font-size", "40px")
                .set("font-weight", "bold")
                .set("text-shadow", "1px 1px 2px #0000001a");

            // Layout derecho con los botones
            HorizontalLayout rightButtons = new HorizontalLayout();
            rightButtons.setSpacing(true);
            rightButtons.setAlignItems(Alignment.CENTER);

            // Botón "Regresar"
            Button btnRegresar = new Button("Regresar", new Icon(VaadinIcon.ARROW_LEFT));
            btnRegresar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            btnRegresar.getStyle()
                .set("background", "linear-gradient(135deg, #e74c3c 0%, #c0392b 100%)")
                .set("color", "white")
                .set("font-weight", "bold")
                .set("border-radius", "10px")
                .set("box-shadow", "0 3px 10px #c0392b4d")
                .set("transition", "all 0.3s ease");
            btnRegresar.addClickListener(e -> UI.getCurrent().navigate("menuAdministrativos"));

            // Menú de cerrar sesión
            MenuBar menuBar = new MenuBar();
            MenuItem menu = menuBar.addItem("☰");
            SubMenu subMenu = menu.getSubMenu();
            subMenu.addItem("Usuario actual").setEnabled(false);
            subMenu.addItem("⏻ Cerrar sesión", e -> {
                UI.getCurrent().navigate(""); // TODO: ruta a login 
            }).getStyle().set("color", "#ff0000ff");
            menuBar.getStyle().set("font-weight", "bold").set("border-radius", "20px"); //TO DO

            // Efecto hover
            menuBar.getElement().getStyle().set("transition", "background-color 0.3s ease");
            menuBar.getElement().addEventListener("mouseover", e -> menuBar.getStyle().set("background-color", "#e0e0e0"));
            menuBar.getElement().addEventListener("mouseout", e -> menuBar.getStyle().set("background-color", "#f0f0f0"));

            rightButtons.add(btnRegresar, menuBar);

            headerLayout.add(mainTitle, rightButtons);

            // Subtítulo
            H2 subtitle = new H2("Selecciona el tipo de habitación");
            subtitle.getStyle()
                .set("color", "#34495e")
                .set("margin-top", "0")
                .set("margin-bottom", "40px")
                .set("font-size", "1.8em")
                .set("font-weight", "normal")
                .set("font-style", "italic");

            // Contenedor de botones
            VerticalLayout buttonsContainer = new VerticalLayout();
            buttonsContainer.setWidth("100%");
            buttonsContainer.setSpacing(true);
            buttonsContainer.setPadding(false);
            buttonsContainer.setAlignItems(Alignment.CENTER);

            // Botón 1 → Habitación Tipo A
            Button habitacionA = new Button("Habitación Tipo A (Individual)", new Icon(VaadinIcon.HOME_O));
            habitacionA.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_LARGE);
            habitacionA.getStyle()
                .set("width", "350px")
                .set("height", "60px")
                .set("font-size", "1.2em")
                .set("font-weight", "bold")
                .set("margin", "10px 0")
                .set("border-radius", "12px")
                .set("background", "linear-gradient(135deg, #3498db 0%, #2980b9 100%)")
                .set("color", "white")
                .set("border", "none")
                .set("box-shadow", "0 4px 15px #3498db4d")
                .set("transition", "all 0.3s ease");
            habitacionA.addClickListener(e -> UI.getCurrent().navigate("administrativoRegistPacienHabitacionA"));

            // Botón 2 → Habitación Tipo B
            Button habitacionB = new Button("Habitación Tipo B (2 camas)", new Icon(VaadinIcon.HOME));
            habitacionB.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_LARGE);
            habitacionB.getStyle()
                .set("width", "350px")
                .set("height", "60px")
                .set("font-size", "1.2em")
                .set("font-weight", "bold")
                .set("margin", "10px 0")
                .set("border-radius", "12px")
                .set("background", "linear-gradient(135deg, #27ae60 0%, #229954 100%)")
                .set("color", "white")
                .set("border", "none")
                .set("box-shadow", "0 4px 15px #27ae604d")
                .set("transition", "all 0.3s ease");
            habitacionB.addClickListener(e -> UI.getCurrent().navigate(""/* TODO: ruta de habitación tipo B */));

            // Pie de página
            Div footer = new Div();
            footer.getStyle()
                .set("margin-top", "40px")
                .set("padding-top", "40px")
                .set("border-top", "2px solid #ecf0f1")
                .set("text-align", "center");

            H2 hospital = new H2("HOSPITAL MONTELÍBANO");
            hospital.getStyle()
                .set("color", "#2c3e50")
                .set("font-size", "2em")
                .set("font-weight", "bold")
                .set("letter-spacing", "2px")
                .set("text-shadow", "1px 1px 3px #0000001a");

            // Construcción final
            buttonsContainer.add(habitacionA, habitacionB);
            footer.add(hospital);
            contenedor.add(headerLayout, subtitle, buttonsContainer, footer);
            hero.add(contenedor);

        } catch (Exception e) {
            System.err.println("Error en construirHero: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
