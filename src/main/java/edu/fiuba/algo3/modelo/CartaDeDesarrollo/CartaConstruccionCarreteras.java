package edu.fiuba.algo3.modelo.CartaDeDesarrollo;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Ubicacion.Ubicacion;

import java.util.ArrayList;
import java.util.List;

public class CartaConstruccionCarreteras extends Carta {
    List<Ubicacion> ubicaciones1;
    List<Ubicacion> ubicaciones2;

    public CartaConstruccionCarreteras() {
        ubicaciones1 = new ArrayList<>();
        ubicaciones2 = new ArrayList<>();
    }

    @Override
    public void usar(Jugador jugador) {
        jugador.colocarCaminoPorCarta(ubicaciones1);
        jugador.colocarCaminoPorCarta(ubicaciones2);
    }

    public void setearUbicacionesPrimerCamino(Ubicacion ubicacion1, Ubicacion ubicacion2) {
        ubicaciones1.add(ubicacion1);
        ubicaciones1.add(ubicacion2);
    }

    public void setearUbicacionesSegundaCamino(Ubicacion ubicacion1, Ubicacion ubicacion2) {
        ubicaciones2.add(ubicacion1);
        ubicaciones2.add(ubicacion2);
    }
}
