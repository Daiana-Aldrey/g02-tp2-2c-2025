package edu.fiuba.algo3.modelo;

import java.util.List;

public class Ciudad extends Pieza{
	private int ubicacion;
	
	public Ciudad(Jugador propietario) {
	    this.propietario = propietario; 
	    this.ubicacion = -1;
	}


    @Override
    public List<Recurso> costoDeConstruccion() {
        return List.of();
    }
}
