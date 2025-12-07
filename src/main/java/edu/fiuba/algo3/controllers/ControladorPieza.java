package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.vistas.VistaArista;
import edu.fiuba.algo3.vistas.VistaPieza;
import edu.fiuba.algo3.vistas.VistaVerticeEdificio;
import javafx.scene.control.Button;

import java.util.List;

public class ControladorPieza {
    private VistaPieza vista;
    private Button btnCiudad;
    private Button btnCamino;
    private Button btnPoblado;

    private List<VistaVerticeEdificio> vertices;
    private List<VistaArista> aristas;

    public ControladorPieza(VistaPieza vistaPieza, Button btnCiudad, Button btnCamino, Button btnPoblado) {
        this.vista = vistaPieza;
        this.btnPoblado = btnPoblado;
        this.btnCiudad = btnCiudad;
        this.btnCamino = btnCamino;
    }

     public void darComportamiento(){
        btnPoblado.setOnAction(e -> {
            for(VistaVerticeEdificio vista: vertices){
                vista.actualizar();
            }
        });
        btnCamino.setOnAction(e -> {
            for(VistaArista vista: aristas){
                vista.actualizar();
            }
        });
     }

    public void setVertices(List<VistaVerticeEdificio> vertices) {
        this.vertices = vertices;
    }

    public void setArista(List<VistaArista> aristas) {
        this.aristas = aristas;
    }
}
