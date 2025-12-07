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
    private Button btnCancelar;

    private boolean visibleCamino;
    private boolean visiblePoblado;

    private List<VistaVerticeEdificio> vertices;
    private List<VistaArista> aristas;

    public ControladorPieza(VistaPieza vistaPieza, Button btnCiudad, Button btnCamino, Button btnPoblado, Button btnCancelar) {
        this.vista = vistaPieza;
        this.btnPoblado = btnPoblado;
        this.btnCiudad = btnCiudad;
        this.btnCamino = btnCamino;
        this.btnCancelar = btnCancelar;

        visibleCamino = false;
        visiblePoblado = false;

    }

    public void darComportamiento() {
        btnPoblado.setOnAction(e -> {
            for (VistaVerticeEdificio vista : vertices) {
                vista.actualizar();
            }
            btnCancelar.setVisible(true);
            visiblePoblado = true;
        });
        btnCamino.setOnAction(e -> {
            for (VistaArista vista : aristas) {
                vista.actualizar();
            }
            btnCancelar.setVisible(true);
            visibleCamino = true;
        });

        invisibilicarBotones();
    }

    private void invisibilicarBotones() {
        btnCancelar.setOnAction(e -> {
            if (visibleCamino) {
                for (VistaArista arista : aristas) {
                    arista.setVisible(false);
                }
                visibleCamino = false;
            }
            if (visiblePoblado) {
                for (VistaVerticeEdificio vista : vertices) {
                    vista.setVisible(false);
                }
                visiblePoblado = false;
            }
            btnCancelar.setVisible(false);
        });
    }


    public void setVertices(List<VistaVerticeEdificio> vertices) {
        this.vertices = vertices;
    }

    public void setArista(List<VistaArista> aristas) {
        this.aristas = aristas;
    }
}
