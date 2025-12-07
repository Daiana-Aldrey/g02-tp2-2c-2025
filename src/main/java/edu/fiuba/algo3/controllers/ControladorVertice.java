package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Pieza.Pieza;
import edu.fiuba.algo3.modelo.Pieza.Poblado;
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

    public ControladorVertice(VistaVerticeEdificio vista, VerticeEdificio modelo) {
        this.vista = vista;
        this.modelo = modelo;
        ubicacion = modelo.obtenerUbicacion();

        adyacentes = new ArrayList<>();
    }

    public void colocarPieza(Jugador jugador, String tipoPieza) {
        List<Ubicacion> ubicacion = new ArrayList<>();
        ubicacion.add(this.ubicacion);

        vista.setOnAction(e -> {
            jugador.construirPieza(tipoPieza, ubicacion);
            for (VistaVerticeEdificio adyacente : adyacentes) {
                adyacente.actualizar();
            }
            vista.actualizar();
        });
    }

    public void agregarAdyacente(VistaVerticeEdificio vista) {
        adyacentes.add(vista);
    }
}
