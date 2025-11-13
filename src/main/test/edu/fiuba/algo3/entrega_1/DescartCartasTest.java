package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.controllers.Juego;
import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DescartCartasTest {

    @Test
    void conDosDeCadaRecurso_alSalir7_descartaLaMitad() {
        // (fuerzo evento de descarte 7)
        Juego juego = new Juego(3, List.of("Lu", "Bren", "Caro"), new DadosS7(7));
        Jugador jugador = juego.jugadores().get(0);

        // Terrenos -> tipoRecurso()
        Bosque bosque = new Bosque(5);       // "madera"
        Colina colina = new Colina(4);       // "ladrillo"
        Pastizal pastizal = new Pastizal(10);// "lana"
        Campo campo = new Campo(9);          // "grano"
        Montania montania = new Montania(3);    // "mineral"

        jugador.recibirRecurso(bosque.tipoRecurso(), 2);    // madera
        jugador.recibirRecurso(colina.tipoRecurso(), 2);    // ladrillo
        jugador.recibirRecurso(pastizal.tipoRecurso(), 2);  // lana
        jugador.recibirRecurso(campo.tipoRecurso(), 2);     // grano
        jugador.recibirRecurso(montania.tipoRecurso(), 2);  // mineral

        // Tirada forzada = 7
        int n = juego.tirarDado();
        juego.manejarTirada(n);

        // Tenía 10 quedan 5
        assertEquals(5, jugador.cantidadDeCartas());
    }
}
