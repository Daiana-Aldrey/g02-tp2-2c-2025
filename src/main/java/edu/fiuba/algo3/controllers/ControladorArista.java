package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.NoJugador;
import edu.fiuba.algo3.modelo.Pieza.Camino;
import edu.fiuba.algo3.modelo.Tablero.Arista;
import edu.fiuba.algo3.modelo.Ubicacion.Ubicacion;
import edu.fiuba.algo3.vistas.VistaArista;

import java.util.ArrayList;
import java.util.List;


public class ControladorArista {
    private VistaArista vista;
    private Arista modelo;
    private Jugador jugador;
    private Ubicacion ubicacion1;
    private Ubicacion ubicacion2;
    List<Ubicacion> ubicaciones;

    public ControladorArista(VistaArista vista, Arista modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.jugador = new NoJugador();

         ubicacion1 = modelo.obtenerPrimeraUbicacion();
         ubicacion2 = modelo.obtenerSegundaUbicacion();

         ubicaciones = new ArrayList<>();
        ubicaciones.add(this.ubicacion1);
        ubicaciones.add(this.ubicacion2);

        colocarCamino(new Camino(new Jugador("jaime")));
    }

    public void colocarPiezaPrimerTurno(String tipoPieza) {
        vista.setOnAction(e -> {
            jugador.colocarPiezaInicial(tipoPieza, ubicaciones);
            vista.cambiarColor(jugador.obtenerColor());
        });
    }

    public void construirPieza(String tipoPieza) {
        vista.setOnAction(e -> {
            jugador.construirPieza(tipoPieza, ubicaciones);
            vista.cambiarColor(jugador.obtenerColor());
        });
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void colocarCamino(Camino pieza) {
        vista.setOnAction(e -> {
            modelo.colocarCamino(pieza);
            vista.invisibilizarVerticeDisponible();
        });
    }
}
