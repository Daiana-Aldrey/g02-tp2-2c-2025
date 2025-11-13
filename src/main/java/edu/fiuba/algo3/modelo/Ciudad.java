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

    @Override
    public void colocar() {
        System.out.println("modificar metodo, implementado unicamente para que proyecto pueda ser ejecutado");
    }

    @Override
    public void colocarPiezaFija(int ubicacion) {
        System.out.println("modificar metodo, implementado unicamente para que proyecto pueda ser ejecutado");
    }

    @Override
    public void colocarPiezaCamino(int ubicacion1, int ubicacion2) {
        System.out.println("modificar metodo, implementado unicamente para que proyecto pueda ser ejecutado");
    }

    @Override
    public int ubicacion() {
        return 0; //modificar metodo, implementado unicamente para que proyecto pueda ser ejecutado
    }

    @Override
    public String obtenerNombreJugador() {
        return "modificar metodo, implementado unicamente para que proyecto pueda ser ejecutado";
    }

    @Override
    public Jugador obtenerJugador() {
        return null; //modificar metodo, implementado unicamente para que proyecto pueda ser ejecutado
    }
}
