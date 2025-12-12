package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.Excepciones.ColocacionInvalida;
import edu.fiuba.algo3.Excepciones.SinRecursos;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.NoJugador;
import edu.fiuba.algo3.modelo.Tablero.VerticeEdificio;
import edu.fiuba.algo3.modelo.Ubicacion.Ubicacion;
import edu.fiuba.algo3.vistas.VistaJuego;
import edu.fiuba.algo3.vistas.VistaRecursos;
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
    private VistaJuego vistaJuego;

    public ControladorVertice(VistaVerticeEdificio vista, VerticeEdificio modelo) {
        this.vista = vista;
        this.modelo = modelo;
        ubicacion = modelo.obtenerUbicacion();
        jugador = new NoJugador();
        adyacentes = new ArrayList<>();

        ubicaciones = new ArrayList<>();
        ubicaciones.add(this.ubicacion);
    }

    public void colocarPieza(String tipoPieza, VistaRecursos vistaRecursos) {
        vista.setOnAction(e -> {
            try {
                if (tipoPieza.equals("poblado")) {
                    jugador.construirPieza(tipoPieza, ubicaciones);
                    vista.cambiarFormaYColorPoblado(jugador.obtenerColor());
                    actualizarAdyacentes();
                    vistaRecursos.actualizarRecursos(jugador.recursos());
                }
                if (tipoPieza.equals("ciudad")) {
                    jugador.construirPieza(tipoPieza, ubicaciones);
                    vista.cambiarFormaACiudad(jugador.obtenerColor());
                    actualizarAdyacentes();
                    vistaRecursos.actualizarRecursos(jugador.recursos());
                }
            } catch (ColocacionInvalida e1) {
                vistaJuego.mostrarAviso("No se permite colocar una pieza en ese lugar");
            } catch (SinRecursos e2) {
                vistaJuego.mostrarAviso("No tiene los suficientes recursos");
            } catch (Exception error) {
                System.out.println("Faltan recursos para el camino");
            }
        });
    }

    public void setComportamientoInicial(JuegoObservable modelo) {
        vista.setOnAction(e -> {
            try {
                modelo.colocarPiezaInicialObservable("poblado", ubicaciones);
                vista.cambiarFormaYColorPoblado(modelo.juego().jugadorActual().obtenerColor());
                actualizarAdyacentes();
            } catch (Exception error) {
                System.out.println("No se puede colocar inicial: " + error.getMessage());
            }
        });
    }

    public void setJugadorActual(Jugador jugador) {
        this.jugador = jugador;
    }

    public void agregarAdyacente(VistaVerticeEdificio vista) {
        adyacentes.add(vista);
    }
    
    private void actualizarAdyacentes() {
        for (VistaVerticeEdificio adyacente : adyacentes) {
            adyacente.mostrarVerticeDisponible();
        }
    }

    public void setVistaJuego(VistaJuego vistaJuego) {
        this.vistaJuego = vistaJuego;
    }
}
