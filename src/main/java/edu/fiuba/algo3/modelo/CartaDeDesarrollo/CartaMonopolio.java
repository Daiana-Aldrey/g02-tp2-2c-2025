package edu.fiuba.algo3.modelo.CartaDeDesarrollo;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Recurso.RecursoNulo;
import edu.fiuba.algo3.Excepciones.AccionNoPermitida;
import java.util.ArrayList;
import java.util.List;

public class CartaMonopolio extends Carta {

    private Recurso recursoElegido = new RecursoNulo();
    private final List<Jugador> victimas = new ArrayList<>();


    public CartaMonopolio(){
        nombre = "Monopolio";
        descripcion = "Los demás jugadores deben entregarle todas las cartas del recurso que elija el jugador";
    }

    public void configurarRecurso(Recurso recurso) {
        if ( recurso.esNulo() ) {
            throw new AccionNoPermitida("Elegi un recurso valido.");
        }
        this.recursoElegido = recurso;
    }
    @Override
    public void configurarVictimas(List<Jugador> jugadores) {
        if ( jugadores.isEmpty()) {
            throw new AccionNoPermitida("No hay jugadores para aplicar Monopolio.");
        }
        this.victimas.clear();
        this.victimas.addAll(jugadores);
    }

    @Override
    public void usar(Jugador ladron) {
        if ( recursoElegido.esNulo() || victimas.isEmpty()) {
            throw new AccionNoPermitida("Debes configurar la carta Monopolio antes de usarla.");
        }

        for (Jugador victima : victimas) {
            if (!victima.equals(ladron)) {
                victima.entregarTodo(recursoElegido, ladron);
            }
        }
        this.recursoElegido = new RecursoNulo();
        this.victimas.clear();
    }

    @Override
    public int puntosDeVictoriaOcultos() {return 0;}

}
