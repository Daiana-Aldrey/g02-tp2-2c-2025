package edu.fiuba.algo3.modelo;

import java.util.*;

public class Camino extends Pieza {
	private List<Integer> ubicacion;
	
	public Camino(Jugador propietario) {
	    this.propietario = propietario; 
	    this.ubicacion = new ArrayList();
	}

    public void colocarPiezaCamino(int ubicacion1, int ubicacion2) {
        this.ubicacion.add(ubicacion1);
        this.ubicacion.add(ubicacion2);
        Tablero tablero = Tablero.getInstance();
        tablero.colocarPiezaCamino(ubicacion, this);
    }

    public String obtenerNombreJugador() {
        return propietario.obtenerNombre();
    }

    @Override
    public List<Recurso> costoDeConstruccion() {
        return List.of();
    }
}
