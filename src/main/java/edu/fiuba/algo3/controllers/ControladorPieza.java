package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.*;
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
    private boolean visibleCiudad;

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
        visibleCiudad = false;

    }

    public void darComportamiento(Jugador jugador) {
        btnPoblado.setOnAction(e -> {
            comportamientoBotonPoblado(jugador);
        });
        btnCamino.setOnAction(e -> {
            comportamientoBotonCamino(jugador);
        });
        btnCiudad.setOnAction(e -> {
            comportamientoBotonCiudad(jugador);
        });

        invisibilizarBotones();
    }

    private void invisibilizarBotones() {
        btnCancelar.setOnAction(e -> {
            if (visibleCamino) {
                for (VistaArista arista : aristas) {
                    arista.invisibilizarVerticeDisponible();
                }
                visibleCamino = false;
                vista.disenioBotonActivado(btnPoblado);
                vista.disenioBotonActivado(btnCiudad);
            }
            if (visiblePoblado) {
                for (VistaVerticeEdificio vista : vertices) {
                    vista.invisibilizarVerticeDisponible();
                }
                visiblePoblado = false;
                vista.disenioBotonActivado(btnCamino);
                vista.disenioBotonActivado(btnCiudad);
            }
            if (visibleCiudad) {
                visibleCiudad = false;
                vista.disenioBotonActivado(btnPoblado);
                vista.disenioBotonActivado(btnCamino);
                for (VistaVerticeEdificio vistaVertice : vertices) {
                    vistaVertice.pobladoNoClickeable();
                }
            }
            btnCancelar.setVisible(false);
        });
    }

    public void comportamientoBotonPoblado(Jugador jugador) {
        btnCancelar.setVisible(true);
        visiblePoblado = true;
        vista.disenioDesactivado(btnCamino);
        vista.disenioDesactivado(btnCiudad);
        for (VistaVerticeEdificio vistaVertice : vertices) {
            vistaVertice.setJugador(jugador);
            vistaVertice.habilitarConstruccion("poblado");
            vistaVertice.mostrarVerticeDisponible();
        }
    }

    public void comportamientoBotonCamino(Jugador jugador) {
        btnCancelar.setVisible(true);
        visibleCamino = true;
        vista.disenioDesactivado(btnPoblado);
        vista.disenioDesactivado(btnCiudad);
        for (VistaArista vistaArista : aristas) {
            vistaArista.setJugador(jugador);
            vistaArista.habilitarConstruccion("camino");
            vistaArista.mostrarAristaDisponible();
        }
    }

    public void comportamientoBotonCiudad(Jugador jugador) {
        btnCancelar.setVisible(true);
        visibleCiudad = true;
        vista.disenioDesactivado(btnPoblado);
        vista.disenioDesactivado(btnCamino);
        for (VistaVerticeEdificio vistaVerticeEdificio : vertices) {
            vistaVerticeEdificio.setJugador(jugador);
            vistaVerticeEdificio.habilitarConstruccion("ciudad");
            vistaVerticeEdificio.resaltarPoblado(jugador);
        }
    }

    public void setVertices(List<VistaVerticeEdificio> vertices) {
        this.vertices = vertices;
    }

    public void setArista(List<VistaArista> aristas) {
        this.aristas = aristas;
    }
    
    public void darComportamientoInicial(JuegoObservable modelo) {
    	btnPoblado.setOnAction(null);
        btnCamino.setOnAction(null);
        btnCiudad.setOnAction(null);
        
        btnPoblado.setOnAction(e -> {
            if (!modelo.yaPusoPobladoInicial()) {
                comportamientoBotonPobladoInicial(modelo);
            }
        });

        btnCamino.setOnAction(e -> {
            if (!modelo.yaPusoCaminoInicial()) {
                comportamientoBotonCaminoInicial(modelo);
            }
        });

        vista.disenioDesactivado(btnCiudad);
        btnCiudad.setDisable(true);         
        invisibilizarBotones();
    }

    public void comportamientoBotonPobladoInicial(JuegoObservable modelo) {
        btnCancelar.setVisible(true);
        visiblePoblado = true;
        vista.disenioDesactivado(btnCamino);
        vista.disenioDesactivado(btnCiudad);

        for (VistaVerticeEdificio vistaVertice : vertices) {
            vistaVertice.setComportamientoInicial(modelo); 
            vistaVertice.mostrarVerticeDisponible();
        }
    }
    

    public void comportamientoBotonCaminoInicial(JuegoObservable modelo) {
        btnCancelar.setVisible(true);
        visibleCamino = true;
        vista.disenioDesactivado(btnPoblado);
        vista.disenioDesactivado(btnCiudad);

        for (VistaArista vistaArista : aristas) {
            vistaArista.setComportamientoInicial(modelo);
            vistaArista.mostrarAristaDisponible();
        }
    }
}
