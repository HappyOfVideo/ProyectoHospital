package com.vaadin.proyectofinalvaadin;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class IndexView extends VerticalLayout{
    
    public IndexView (){
    
        H1 titulo = new H1("Hola Mundo");
        
        add(titulo);
        
    }
    
}
