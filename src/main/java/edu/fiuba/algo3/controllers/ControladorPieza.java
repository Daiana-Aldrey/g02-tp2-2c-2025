package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Recurso.*;
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
    private Jugador jugador;

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
        jugador = new NoJugador();

        visibleCamino = false;
        visiblePoblado = false;
        visibleCiudad = false;

    }

    public void darComportamiento(Jugador jugador) {
        this.jugador = jugador;
        permitirVerBotonCiudad(jugador);
        permitirVerBotonPoblado(jugador);
        permitirVerBotonCiudad(jugador);
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
                permitirVerBotonPoblado(jugador);
                permitirVerBotonCiudad(jugador);
            }
            if (visiblePoblado) {
                for (VistaVerticeEdificio vista : vertices) {
                    vista.invisibilizarVerticeDisponible();
                }
                permitirVerBotonCiudad(jugador);
                permitirVerBotonCamino(jugador);
            }
            if (visibleCiudad) {
                for (VistaVerticeEdificio vistaVertice : vertices) {
                    vistaVertice.pobladoNoClickeable();
                }
                permitirVerBotonPoblado(jugador);
                permitirVerBotonCamino(jugador);
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

    public void permitirVerBotonPoblado(Jugador jugador) {
        if (sePuedeComprarPoblado(jugador)) {
            vista.disenioBotonActivado(btnPoblado);
            visiblePoblado = true;
        } else  {
            vista.disenioDesactivado(btnPoblado);
            visiblePoblado = false;
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

    public void permitirVerBotonCamino(Jugador jugador) {
        if (sePuedeComprarCamino(jugador)) {
            vista.disenioBotonActivado(btnCamino);
            visibleCamino = true;
        } else {
            vista.disenioDesactivado(btnCamino);
            visibleCamino = false;
        }
    }

    public void comportamientoBotonCiudad(Jugador jugador) {
        btnCancelar.setVisible(true);
        vista.disenioDesactivado(btnPoblado);
        vista.disenioDesactivado(btnCamino);
        for (VistaVerticeEdificio vistaVerticeEdificio : vertices) {
            vistaVerticeEdificio.setJugador(jugador);
            vistaVerticeEdificio.resaltarPoblado(jugador);
            vistaVerticeEdificio.habilitarConstruccion("ciudad");
        }
    }

    public void permitirVerBotonCiudad(Jugador jugador) {
        if (sePuedeComprarCiudad(jugador)) {
            vista.disenioBotonActivado(btnCiudad);
            visibleCiudad = true;
        } else {
            vista.disenioDesactivado(btnCiudad);
            visibleCiudad = false;
        }
    }

    public boolean sePuedeComprarPoblado(Jugador jugador) {
        boolean permitido = false;

        int cantidadGrano = jugador.buscarRecurso(new Grano()).cantidad();
        int cantidadLadrillo = jugador.buscarRecurso(new Ladrillo()).cantidad();
        int cantidadMadera = jugador.buscarRecurso(new Madera()).cantidad();
        int cantidadLana = jugador.buscarRecurso(new Lana()).cantidad();

        if (cantidadGrano > 0 && cantidadLadrillo > 0 && cantidadMadera > 0 && cantidadLana > 0) {
            permitido = true;
        }
        return permitido;
    }

    public boolean sePuedeComprarCiudad(Jugador jugador) {
        boolean permitido = false;

        int cantidadGrano = jugador.buscarRecurso(new Grano()).cantidad();
        int cantidadMineral = jugador.buscarRecurso(new Mineral()).cantidad();

        if ( cantidadGrano > 1 && cantidadMineral > 2 ) {
            permitido = true;
        }
        return permitido;
    }

    public boolean sePuedeComprarCamino(Jugador jugador) {
        boolean permitido = false;

        int cantidadLadrillo = jugador.buscarRecurso(new Ladrillo()).cantidad();
        int cantidadMadera = jugador.buscarRecurso(new Madera()).cantidad();

        if (cantidadLadrillo > 0 && cantidadMadera > 0) {
            permitido = true;
        }
        return permitido;
    }

    public void setVertices(List<VistaVerticeEdificio> vertices) {
        this.vertices = vertices;
    }

    public void setArista(List<VistaArista> aristas) {
        this.aristas = aristas;
    }
    
    public void darComportamientoInicial(JuegoObservable modelo) {
        vista.disenioBotonActivado(btnPoblado);

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

        invisibilizarBotonesIniciales();
    }

    private void invisibilizarBotonesIniciales() {
        btnCancelar.setOnAction(e -> {
            if (visibleCamino) {
                for (VistaArista arista : aristas) {
                    arista.invisibilizarVerticeDisponible();
                }
                vista.disenioDesactivado(btnCamino);
            }
            if (visiblePoblado) {
                for (VistaVerticeEdificio vista : vertices) {
                    vista.invisibilizarVerticeDisponible();
                }
                vista.disenioDesactivado(btnPoblado);
                vista.disenioBotonActivado(btnCamino);
            }
            btnCancelar.setVisible(false);
        });
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
        vista.disenioDesactivado(btnCiudad);

        for (VistaArista vistaArista : aristas) {
            vistaArista.setComportamientoInicial(modelo);
            vistaArista.mostrarAristaDisponible();
        }
        vista.disenioDesactivado(btnPoblado);
    }
}
