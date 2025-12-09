package edu.fiuba.algo3.modelo.CartaDeDesarrollo;

import edu.fiuba.algo3.Excepciones.AccionNoPermitida;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Ubicacion.Ubicacion;

import java.util.ArrayList;
import java.util.List;

public class CartaConstruccionCarreteras extends Carta {
    List<Ubicacion> ubicaciones1;
    List<Ubicacion> ubicaciones2;

    public CartaConstruccionCarreteras() {
        nombre = "Construccion de Carreteras";
        descripcion = "Permite construir 2 Carreteras gratuitamente";
        ubicaciones1 = new ArrayList<>();
        ubicaciones2 = new ArrayList<>();
    }
    @Override
    public void configurarCaminos(List<Ubicacion> camino1, List<Ubicacion> camino2) {
        if (camino1.size() != 2 || camino2.size() != 2) {
            throw new AccionNoPermitida("Debés elegir 2 caminos para usar esta carta.");
        }

        this.ubicaciones1.clear();
        this.ubicaciones1.addAll(camino1);

        this.ubicaciones2.clear();
        this.ubicaciones2.addAll(camino2);
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
