package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.vistas.*;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.util.List;

public class ControladorPieza {
    private VistaPieza vista;
    private VistaRecursos vistaRecursos;
    private Button btnCiudad;
    private Button btnCamino;
    private Button btnPoblado;
    private Button btnCancelar;
    private Jugador jugador;

    private boolean visibleCamino;
    private boolean visiblePoblado;
    private boolean visibleCiudad;

    private boolean visibleBotonesCiudad;
    private boolean visibleBotonesPoblados;
    private boolean visibleBotonesCaminos;

    private List<VistaVerticeEdificio> vertices;
    private List<VistaArista> aristas;
    private VistaJuego vistaJuego;

    public ControladorPieza(VistaPieza vistaPieza, Button btnCiudad, Button btnCamino, Button btnPoblado, Button btnCancelar, VistaRecursos vistaRecursos) {
        this.vista = vistaPieza;
        this.btnPoblado = btnPoblado;
        this.btnCiudad = btnCiudad;
        this.btnCamino = btnCamino;
        this.btnCancelar = btnCancelar;

        this.vistaRecursos = vistaRecursos;
        jugador = new NoJugador();

        visibleCamino = false;
        visiblePoblado = false;
        visibleCiudad = false;
        visibleBotonesCiudad = false;
        visibleBotonesPoblados = false;
        visibleBotonesCaminos = false;

    }

    public void darComportamiento(Jugador jugador) {
        this.jugador = jugador;
        permitirVerBotonCiudad(jugador);
        permitirVerBotonPoblado(jugador);
        permitirVerBotonCamino(jugador);
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
                if (visibleBotonesCaminos) {
                    for (VistaArista arista : aristas) {
                        arista.invisibilizarVerticeDisponible();
                    }
                    visibleBotonesCaminos = false;
                }
                visibleCamino = false;
            }
            if (visiblePoblado) {
                if  (visibleBotonesPoblados) {
                    for (VistaVerticeEdificio vistaVertice : vertices) {
                        vistaVertice.invisibilizarVerticeDisponible();
                    }
                    visibleBotonesPoblados = false;
                }
                visiblePoblado = false;
            }
            if (visibleCiudad) {
                if (visibleBotonesCiudad) {
                    for (VistaVerticeEdificio vistaVertice : vertices) {
                        vistaVertice.pobladoNoClickeable();
                    }
                    visibleBotonesCiudad = false;
                }
                visibleCiudad = false;
            }
            permitirVerBotonPoblado(jugador);
            permitirVerBotonCiudad(jugador);
            permitirVerBotonCamino(jugador);
            vistaJuego.habilitarBotonPasarTurno();
            btnCancelar.setVisible(false);
        });
    }

    public void comportamientoBotonPoblado(Jugador jugador) {
        btnCancelar.setVisible(true);
        visibleBotonesPoblados = true;
        visiblePoblado = true;
        vista.disenioDesactivado(btnCamino);
        vista.disenioDesactivado(btnCiudad);

        for (VistaVerticeEdificio vistaVertice : vertices) {
            vistaVertice.setJugador(jugador);
            vistaVertice.habilitarConstruccion("poblado", vistaRecursos);
            vistaVertice.mostrarVerticeDisponible();
        }

        vistaJuego.deshabilitarBotonPasarTurno();
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
        visibleBotonesCaminos = true;
        visibleCamino = true;
        vista.disenioDesactivado(btnPoblado);
        vista.disenioDesactivado(btnCiudad);
        for (VistaArista vistaArista : aristas) {
            vistaArista.setJugador(jugador);
            vistaArista.habilitarConstruccion("camino", vistaRecursos);
            vistaArista.mostrarAristaDisponible();
        }
        vistaJuego.deshabilitarBotonPasarTurno();
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
        visibleBotonesCiudad = true;
        visibleCiudad = true;
        vista.disenioDesactivado(btnPoblado);
        vista.disenioDesactivado(btnCamino);
        for (VistaVerticeEdificio vistaVerticeEdificio : vertices) {
            vistaVerticeEdificio.setJugador(jugador);
            vistaVerticeEdificio.resaltarPoblado(jugador);
            vistaVerticeEdificio.habilitarConstruccion("ciudad", vistaRecursos);
            //vistaVerticeEdificio.mostrarVerticeDisponible();
        }
        vistaJuego.deshabilitarBotonPasarTurno();
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

        Recurso[] recursos = jugador.getRecursosVector();
        int cantidadGrano = recursos[jugador.GRANO].cantidad();
        int cantidadLadrillo = recursos[jugador.LADRILLO].cantidad();
        int cantidadMadera = recursos[jugador.MADERA].cantidad();
        int cantidadLana =  recursos[jugador.LANA].cantidad();

        if (cantidadGrano > 0 && cantidadLadrillo > 0 && cantidadMadera > 0 && cantidadLana > 0) {
            permitido = true;
        }
        return permitido;
    }

    public boolean sePuedeComprarCiudad(Jugador jugador) {
        boolean permitido = false;

        Recurso[] recursos = jugador.getRecursosVector();
        int cantidadGrano = recursos[jugador.GRANO].cantidad();
        int cantidadMineral = recursos[jugador.MINERAL].cantidad();

        if ( cantidadGrano > 1 && cantidadMineral > 2 ) {
            permitido = true;
        }
        return permitido;
    }

    public boolean sePuedeComprarCamino(Jugador jugador) {
        boolean permitido = false;

        Recurso[] recursos = jugador.getRecursosVector();
        int cantidadLadrillo = recursos[jugador.LADRILLO].cantidad();
        int cantidadMadera = recursos[jugador.MADERA].cantidad();

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

    public void setVistaJuego(VistaJuego vistaJuego) {
        this.vistaJuego = vistaJuego;
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
                visibleCamino = false;
            }
            if (visiblePoblado) {
                for (VistaVerticeEdificio vista : vertices) {
                    vista.invisibilizarVerticeDisponible();
                }
                vista.disenioDesactivado(btnPoblado);
                vista.disenioBotonActivado(btnCamino);
                visiblePoblado = false;
            }
            btnCancelar.setVisible(false);
            vistaJuego.habilitarBotonPasarTurno();
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
        vistaJuego.deshabilitarBotonPasarTurno();
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
        vistaJuego.deshabilitarBotonPasarTurno();
    }

}
