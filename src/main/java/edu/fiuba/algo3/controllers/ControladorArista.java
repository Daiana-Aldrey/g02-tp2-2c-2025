package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.NoJugador;
import edu.fiuba.algo3.modelo.Tablero.Arista;
import edu.fiuba.algo3.modelo.Ubicacion.Ubicacion;
import edu.fiuba.algo3.vistas.VistaArista;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;


public class ControladorArista {
    private VistaArista vista;
    private Arista modelo;
    private Jugador jugador;
    private Ubicacion ubicacion1;
    private Ubicacion ubicacion2;
    List<Ubicacion> ubicaciones;
    private Button boton;

    public ControladorArista(VistaArista vista, Arista modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.jugador = new NoJugador();

        ubicacion1 = modelo.obtenerPrimeraUbicacion();
        ubicacion2 = modelo.obtenerSegundaUbicacion();
        ubicaciones = new ArrayList<>();
        ubicaciones.add(this.ubicacion1);
        ubicaciones.add(this.ubicacion2);

    }
    public void setBoton(Button boton) {
        this.boton = boton;
    }

    public void colocarPiezaPrimerTurno(String tipoPieza) {
        boton.setOnAction(e -> {
            jugador.colocarPiezaInicial(tipoPieza, ubicaciones);
            vista.cambiarFormaYColor(jugador.obtenerColor());
        });
    }

    public void construirPieza(String tipoPieza) {
        boton.setOnAction(e -> {
            jugador.construirPieza(tipoPieza, ubicaciones);
            vista.cambiarFormaYColor(jugador.obtenerColor());
        });
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
    
    public void setComportamientoInicial(JuegoObservable modelo) {
        boton.setOnAction(e -> {
	    modelo.colocarPiezaInicialObservable("camino", ubicaciones);
	    vista.cambiarFormaYColor(modelo.juego().jugadorActual().obtenerColor());
        });
    }
}
