package edu.fiuba.algo3.modelo.CartaDeDesarrollo;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Recurso.RecursoNulo;
import edu.fiuba.algo3.Excepciones.AccionNoPermitida;
import java.util.ArrayList;
import java.util.List;

public class CartaMonopolio extends Carta {
    public CartaMonopolio(){
        nombre = "Monopolio";
        descripcion = "Los demás jugadores deben entregarle todas las cartas del recurso que elija el jugador";
    }

    private Recurso recursoElegido = new RecursoNulo();
    private final List<Jugador> victimas = new ArrayList<>();

    public void configurar(Recurso recurso, List<Jugador> jugadores) {
        if ( recurso.esNulo() ||jugadores.isEmpty()) {
            throw new AccionNoPermitida("Configuración inválida para carta Monopolio.");
        }
        this.recursoElegido = recurso;
        this.victimas.clear();
        this.victimas.addAll(jugadores);
    }

    @Override
    public void usar(Jugador ladron) {
//        if ( recursoElegido.esNulo() || victimas.isEmpty()) {
//            throw new AccionNoPermitida("Debes configurar la carta Monopolio antes de usarla.");
//        }

        for (Jugador victima : victimas) {
            if (!victima.equals(ladron)) {
                victima.entregarTodo(recursoElegido, ladron);
            }
        }
        this.recursoElegido = new RecursoNulo();
        this.victimas.clear();
    }

    @Override
    public int puntosDeVictoriaOcultos() {
        return 0;
    }

}
