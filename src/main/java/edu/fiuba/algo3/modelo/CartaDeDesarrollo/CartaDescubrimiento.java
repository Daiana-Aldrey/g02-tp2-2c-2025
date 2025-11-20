package edu.fiuba.algo3.modelo.CartaDeDesarrollo;

import edu.fiuba.algo3.modelo.Jugador;

public class CartaDescubrimiento extends Carta {

    @Override
    public void usar(Jugador jugador) {
        System.out.println("Usando Carta de Descubrimiento (lógica futura).");
    }
}
