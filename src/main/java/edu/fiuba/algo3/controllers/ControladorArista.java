package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.JuegoObservable;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.NoJugador;
import edu.fiuba.algo3.modelo.Tablero.Arista;
import edu.fiuba.algo3.modelo.Ubicacion.Ubicacion;
import edu.fiuba.algo3.vistas.VistaArista;
import javafx.scene.control.Button;
import edu.fiuba.algo3.Excepciones.*;

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


    public void construirPieza(String tipoPieza) {
        boton.setOnAction(e -> {
            try {
                jugador.construirPieza(tipoPieza, ubicaciones);
                vista.cambiarFormaYColor(jugador.obtenerColor());
            } catch (RecursoIncorrecto | SinRecursos error) {
                System.out.println("Faltan recursos para el camino");
            } catch (Exception error) {
                 System.out.println("Error: " + error.getMessage());
            }
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
