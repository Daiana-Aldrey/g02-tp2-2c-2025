package edu.fiuba.algo3.modelo.Intercambio;

import edu.fiuba.algo3.modelo.CartaDeDesarrollo.*;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Recurso.RecursoTipo;
import edu.fiuba.algo3.Excepciones.NoTieneCarta;

import java.util.*;

public class Banco {
    private final Deque<Carta> mazoDesarrollo;

    public Banco(FabricaMazoCartasDesarrollo fabrica) {
        this.mazoDesarrollo = new ArrayDeque<>(fabrica.crearMazoDesarrollo());
    }
    public Banco(){
        this (new MazoCartasDesarrollo());}


    public Carta venderCartaDesarrollo(Jugador jugador) {
        if (mazoDesarrollo.isEmpty()) {
            throw new NoTieneCarta("No hay más cartas.");
        }
        //  Precio una carta de desarrollo
        List<Recurso> precio = List.of(
                new Recurso(RecursoTipo.LANA, 1),
                new Recurso(RecursoTipo.GRANO, 1),
                new Recurso(RecursoTipo.MINERAL, 1)
        );
        jugador.pagarRecursos(precio);
        Carta carta = mazoDesarrollo.pop();
        jugador.recibirCartaDesarrollo(carta);

        return carta;
    }

    public void comerciar(Jugador jugador, RecursoTipo recursoDado, RecursoTipo recursoRecibido, int cantidadSolicitada) {
        int tasa = jugador.seleccionarTasaPara(recursoDado);
        int costo = tasa * cantidadSolicitada;
        Recurso recurso = jugador.buscarRecurso(recursoDado);

        if (recurso.esSuficiente(costo)) {
            throw new IllegalArgumentException("No tiene recursos suficientes");
        }

        jugador.descontarRecurso(recursoDado, costo);
        jugador.recibirRecurso(recursoRecibido, cantidadSolicitada);
    }
}
