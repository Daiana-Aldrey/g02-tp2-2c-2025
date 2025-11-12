package edu.fiuba.algo3.modelo;

import java.util.*;

public class Camino extends Pieza {
	private List<Integer> ubicacion;
	
	public Camino(Jugador propietario) {
	    this.propietario = propietario; 
	    this.ubicacion = new ArrayList();
	}

    @Override
    public List<Recurso> costoDeConstruccion() {
        return List.of();
    }
}
