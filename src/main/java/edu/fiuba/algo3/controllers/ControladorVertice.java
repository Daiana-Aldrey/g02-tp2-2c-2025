package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.NoJugador;
import edu.fiuba.algo3.modelo.Tablero.VerticeEdificio;
import edu.fiuba.algo3.modelo.Ubicacion.Ubicacion;
import edu.fiuba.algo3.vistas.VistaVerticeEdificio;

import java.util.ArrayList;
import java.util.List;

public class ControladorVertice {
    private VistaVerticeEdificio vista;
    private VerticeEdificio modelo;
    private List<VistaVerticeEdificio> adyacentes;
    private Jugador jugador;
    Ubicacion ubicacion;
    List<Ubicacion> ubicaciones;

    public ControladorVertice(VistaVerticeEdificio vista, VerticeEdificio modelo) {
        this.vista = vista;
        this.modelo = modelo;
        ubicacion = modelo.obtenerUbicacion();
        jugador = new NoJugador();
        adyacentes = new ArrayList<>();

        ubicaciones = new ArrayList<>();
        ubicaciones.add(this.ubicacion);
    }

    public void colocarPiezaPrimerTurno(String tipoPieza) {
        vista.setOnAction(e -> {
            jugador.colocarPiezaInicial(tipoPieza, ubicaciones);
            vista.cambiarFormaYColorPoblado(jugador.obtenerColor());

            for (VistaVerticeEdificio adyacente : adyacentes) {
                adyacente.mostrarVerticeDisponible();
            }
        });
    }

    public void construirPieza(String tipoPieza) {
        vista.setOnAction(e -> {
            jugador.construirPieza(tipoPieza, ubicaciones);
            vista.cambiarFormaYColorPoblado(jugador.obtenerColor());

            for (VistaVerticeEdificio adyacente : adyacentes) {
                adyacente.mostrarVerticeDisponible();
            }
        });
    }

    public void setJugadorActual(Jugador jugador) {
        this.jugador = jugador;
    }

    public void agregarAdyacente(VistaVerticeEdificio vista) {
        adyacentes.add(vista);
    }
    
    public void setComportamientoInicial(JuegoObservable modelo) {
        vista.setOnAction(e -> {
        modelo.colocarPiezaInicialObservable("poblado", ubicaciones);
        vista.cambiarFormaYColorPoblado(modelo.juego().jugadorActual().obtenerColor());
        });
    }
}
