package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.CartaDeBonificacion.BonificadorRutaMayor;
import edu.fiuba.algo3.modelo.JuegoObservable;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.NoJugador;
import edu.fiuba.algo3.modelo.Tablero.Arista;
import edu.fiuba.algo3.modelo.Ubicacion.Ubicacion;
import edu.fiuba.algo3.vistas.VistaArista;
import edu.fiuba.algo3.vistas.VistaJuego;
import edu.fiuba.algo3.vistas.VistaRecursos;
import javafx.scene.control.Button;
import edu.fiuba.algo3.Excepciones.*;

import java.util.ArrayList;
import java.util.List;


public class ControladorArista {
    private VistaArista vista;
    private VistaJuego vistaJuego;
    private Arista modelo;
    private Jugador jugador;
    private Ubicacion ubicacion1;
    private Ubicacion ubicacion2;
    List<Ubicacion> ubicaciones;
    private Button boton;
    private JuegoObservable modeloObservable;

    BonificadorRutaMayor bonificador;

    public ControladorArista(VistaArista vista, Arista modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.jugador = new NoJugador();

        ubicacion1 = modelo.obtenerPrimeraUbicacion();
        ubicacion2 = modelo.obtenerSegundaUbicacion();
        ubicaciones = new ArrayList<>();
        ubicaciones.add(this.ubicacion1);
        ubicaciones.add(this.ubicacion2);

        bonificador = null;
        boton = null;
    }
    public void setBoton(Button boton) {
        this.boton = boton;
    }

    public void setModeloObservable(JuegoObservable modeloObservable) {
        this.modeloObservable = modeloObservable;
    }


    public void construirPieza(String tipoPieza, VistaRecursos vistaRecursos) {
        boton.setOnAction(e -> {
            try {//PARA CARTA CONTRUCCION CARRETERA
                if (modeloObservable.estaEnModoConstruccionCarreteras()) {
                    modeloObservable.registrarCaminoParaCarta(ubicaciones);
                    vista.cambiarFormaYColor(modeloObservable.juego().jugadorActual().obtenerColor());
                    return;
                }
                jugador.construirPieza(tipoPieza, ubicaciones);
                vista.cambiarFormaYColor(jugador.obtenerColor());
                bonificador.bonificarPorRutaMayor();
            } catch (RecursoIncorrecto | SinRecursos error) {
                vistaJuego.mostrarAviso("Faltan recursos para el camino");
            } catch (ColocacionInvalida error) {
                vistaJuego.mostrarAviso("No puede colocar un camino independiente");
            } catch (Exception error) {
                 System.out.println("Error: " + error.getMessage());
            }
            vistaRecursos.actualizarRecursos(jugador.recursos());
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

    public void setBonificador(BonificadorRutaMayor bonificador) {
        this.bonificador = bonificador;

    }

    public void setVistaJuego(VistaJuego vistaJuego) {
        this.vistaJuego = vistaJuego;
    }
}
