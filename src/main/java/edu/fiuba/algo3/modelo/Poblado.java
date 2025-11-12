package edu.fiuba.algo3.modelo;

import java.util.List;

public class Poblado extends Pieza {
	private Jugador propietario;
	
	public Poblado(Jugador popietario) {
		this.propietario = propietario;
	}

    @Override
    public List<Recurso> costoDeConstruccion() {
        return List.of();
    }
}
