package edu.fiuba.algo3.modelo.CartaDeDesarrollo;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MazoCartasDesarrollo implements FabricaMazoCartasDesarrollo {

    @Override
    public List<Carta> crearMazoDesarrollo() {

        List<Carta> cartas = new ArrayList<>();

        for (int i = 0; i < 14; i++) {cartas.add(new CartaCaballero());}
        for (int i = 0; i < 5; i++) {cartas.add(new CartaPuntoVictoria());}
        for (int i = 0; i < 2; i++) {cartas.add(new CartaMonopolio());}
        for (int i = 0; i < 2; i++) {cartas.add(new CartaConstruccionCarreteras());}
        for (int i = 0; i < 2; i++) {cartas.add(new CartaDescubrimiento());}

        Collections.shuffle(cartas);
        return cartas;
    }
}
