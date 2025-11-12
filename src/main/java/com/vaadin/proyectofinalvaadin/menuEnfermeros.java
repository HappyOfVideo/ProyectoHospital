package com.vaadin.proyectofinalvaadin;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("menuEnfermeros")
public class menuEnfermeros extends VerticalLayout {

    //Inicialización
    public static VerticalLayout divForm;

    public menuEnfermeros() {

        setSizeFull();
        setPadding(false);
        setMargin(false);
        setSpacing(false);

        //Main layout
        VerticalLayout mainLayout = new VerticalLayout();
        //Ajuste de espacios del main layout
        mainLayout.setSizeFull();
        mainLayout.setSpacing(false);
        mainLayout.setPadding(false);
        mainLayout.setMargin(false);
        mainLayout.getStyle()
            .set("background", "linear-gradient(135deg, #1c4c5dff 0%, #1C4C5D 100%)")
            .set("font-family", "Arial, sans-serif")
            .set("padding-left", "270px")
        ;

        //Hero layout
        FormLayout hero = new FormLayout();
        hero.setResponsiveSteps(
            new FormLayout.ResponsiveStep("0", 1)
        );
        construirHero(hero);

        mainLayout.add(hero);

        add(mainLayout);

    }

    //Construir hero, agregara los componentes del encabezado inicial de la pagina
    public void construirHero(FormLayout hero) {
        try {

            //Contenedor blanco para el contenido
            Div contenedor = new Div();
            contenedor.getStyle()
                .set("background", "white")
                .set("border-radius", "15px")
                .set("padding", "40px")
                .set("margin-top", "110px")
                .set("max-width", "900px")
                .set("box-shadow", "0 10px 30px #0000001a")
            ; 

           //Header - Titulo y cerrar sesión
            HorizontalLayout headerLayout = new HorizontalLayout();
            headerLayout.setWidthFull();
            headerLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
            headerLayout.setAlignItems(FlexComponent.Alignment.CENTER);
            headerLayout.getStyle().set("margin-bottom", "20px");

            //Título principal
            H1 mainTitle = new H1("Menú Enfermeros");
            mainTitle.getStyle()
                .set("color", "#2c3e50")
                .set("margin", "0")
                .set("font-size", "40px")
                .set("font-weight", "bold")
                .set("text-shadow", "1px 1px 2px #0000001a")
            ;

            //Botón de cerrar sesion
            MenuBar menuBar = new MenuBar();
            MenuItem menu = menuBar.addItem("☰");
            SubMenu subMenu = menu.getSubMenu();
            subMenu.addItem("AQUÍ"/*TODO: Aqui sale el nombre de la persona que inicio sesión*/).setEnabled(false); 
            subMenu.addItem("⏻ Cerrar sesión", e -> {
                UI.getCurrent().navigate("");
            }).getStyle().set("color", "#ff0000ff");
            menuBar.getStyle().set("font-weight", "bold");

            headerLayout.add(mainTitle, menuBar);

            //Sección de busqueda
            VerticalLayout seccionBusquedas = new VerticalLayout();
            seccionBusquedas.setWidthFull();
            seccionBusquedas.setSpacing(false);
            seccionBusquedas.setPadding(false);

            //Subtítulo
            H2 subTitle = new H2("Buscar paciente");
            subTitle.getStyle()
                .set("color", "#34495e")
                .set("text-align", "center")
                .set("margin-top", "0")
                .set("margin-bottom", "30px")
                .set("font-size", "24px")
                .set("font-weight", "normal")
            ;

            //Layout horizontal para las barras de búsqueda
            HorizontalLayout searchLayout = new HorizontalLayout();
            searchLayout.setWidthFull();
            searchLayout.setSpacing(true);
            searchLayout.setAlignItems(Alignment.CENTER);

            //Barra de búsqueda por Nombre
            TextField searchByName = campoBusqueda("Buscar por nombre", VaadinIcon.USER, "Escriba el nombre completo del paciente sin tildes o caracteres especiales(@, *, .)");
            searchByName.addKeyPressListener(Key.ENTER, event ->{
                try {
                    if ((searchByName.getValue()).isEmpty()) {
                        Notification.show("El campo no puede estar vacio");
                    }else if ((searchByName.getValue()).matches(".*[áéíóúÁÉÍÓÚüÜ.*].*") || (searchByName.getValue()).matches(".*[@#$%^&*()_+={}\\[\\]:;\"'<>,.?/\\\\|`~].*")) {
                        Notification.show("No se permiten tildes ni caracteres especiales");
                    }else{
                        // TODO: Buscar en el .txt por el nombre
                    }
                } catch (Exception e) {
                    Notification.show("Error de busqueda, comuniquese con soporte");
                }
            });

            //Barra de búsqueda por Documento
            TextField searchByDocument = campoBusqueda("Buscar por documento", VaadinIcon.USER_CARD, "Escriba el número de documento del paciente");
            searchByDocument.addKeyPressListener(Key.ENTER, event ->{
                try {
                    if (!(searchByDocument.getValue()).matches("\\d*")) {
                        Notification.show("Deben ser solo numeros");
                    }else if ((searchByDocument.getValue()).isEmpty()) {
                        Notification.show("El campo no puede estar vacio");
                    }else if ((searchByDocument.getValue()).matches(".*[@#$%^&*()_+={}\\[\\]:;\"'<>,.?/\\\\|`~].*")) {
                        Notification.show("No se permiten caracteres especiales");
                    }else{
                        // TODO: Buscar en el .txt por el documento
                    }
                } catch (Exception e) {
                    Notification.show("Error de busqueda, comuniquese con soporte");
                }
            });

            //Barra de búsqueda por Habitación
            TextField searchByRoom = campoBusqueda("Buscar por habitación",VaadinIcon.BED,"Escriba el número de habitación del paciente");
            searchByRoom.addKeyPressListener(Key.ENTER, event ->{
                try {
                    if (((searchByRoom.getValue()).toUpperCase()).isEmpty()) {
                        Notification.show("El campo no puede estar vacio");
                    }else if ((searchByRoom.getValue()).matches(".*[áéíóúÁÉÍÓÚüÜ.*].*") || (searchByRoom.getValue()).matches(".*[@#$%^&*()_+={}\\[\\]:;\"'<>,.?/\\\\|`~].*")) {
                        Notification.show("No se permiten tildes ni caracteres especiales, exceptuando \"-\"");
                    }else{
                        // TODO: Buscar en el .txt por la habitación
                    }
                } catch (Exception e) {
                    Notification.show("Error de busqueda, comuniquese con soporte");
                }
            });

            //Función en la parte baja del código(Genera un estilo para los campos de busqueda)(IA)
            estiloPredeterminado(searchByName);
            estiloPredeterminado(searchByDocument);
            estiloPredeterminado(searchByRoom);

            //Agregar las barras al layout horizontal
            searchLayout.add(searchByName, searchByDocument, searchByRoom);
            searchLayout.setFlexGrow(1, searchByName, searchByDocument, searchByRoom);

            //Sección de instrucciones
            VerticalLayout instrucciones = new VerticalLayout();
            instrucciones.setWidthFull();
            instrucciones.setSpacing(true);
            instrucciones.setPadding(true);
            instrucciones.getStyle()
                .set("background", "#f8f9fa")
                .set("border-radius", "10px")
                .set("margin-top", "30px")
                .set("border-left", "4px solid #3498db")
            ;

            //Texto de instrucciones
            Paragraph txtInstrunciones = new Paragraph(
                "Escribir en la parte de arriba como quiere buscar al paciente (Nombre, Documento, Habitación). " +
                "Luego de eso le aparecerá en pantalla la información."
            );
            txtInstrunciones.getStyle()
                .set("color", "#2c3e50")
                .set("font-size", "1.1em")
                .set("line-height", "1.6")
                .set("margin", "0")
                .set("text-align", "left")
            ;

            //Pie de página
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
                .set("text-shadow", "1px 1px 3px #0000001a")
            ;

            //Construcción final
            seccionBusquedas.add(subTitle, searchLayout);
            instrucciones.add(txtInstrunciones);
            footer.add(hospital);
            contenedor.add(headerLayout, seccionBusquedas, instrucciones, footer);
            hero.add(contenedor);

        } catch (Exception e) {
            String error = e.getMessage();
            System.err.println("Error en construirHero: " + error);
            e.printStackTrace();
        }
    }

    
    //Método para crear campos de búsqueda (Uso de IA para generar un cuadro de busqueda bien melo)
    public static TextField campoBusqueda(String placeholder, VaadinIcon icon, String tooltip) {
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

    //Método para aplicar estilos a los campos de busqueda (Uso de IA para generar un cuadro de busqueda bien melo)
    public static void estiloPredeterminado(TextField field) {
        field.getElement().getStyle()
            .set("border", "2px solid #98d4fcff")
            .set("border-radius", "8px")
            .set("background", "#f8f9fa")
            .set("transition", "all 0.3s ease")
            .set("padding", "8px")
        ;
        
        //Estilos para el input interno dentro del TextField
        field.getElement().executeJs(
            "this.style.setProperty('--lumo-text-field-background', '#f8f9fa');" +
            "this.style.setProperty('--lumo-text-field-border-color', '#bdc3c7');" +
            "this.style.setProperty('--lumo-text-field-border-radius', '8px');" +
            "this.style.setProperty('--lumo-text-field-border-width', '2px');"
        );
    }

}