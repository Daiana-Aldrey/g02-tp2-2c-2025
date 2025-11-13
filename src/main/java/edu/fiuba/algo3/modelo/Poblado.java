package edu.fiuba.algo3.modelo;

import java.util.List;
import java.util.Scanner;

public class Poblado extends Pieza {
	private int ubicacion;
	
	public Poblado(Jugador propietario) {
	    this.propietario = propietario; 
	    this.ubicacion = -1;
	}


    @Override
    public List<Recurso> costoDeConstruccion() {
    	return List.of(
    	        new Recurso("MADERA", 1),
    	        new Recurso("LADRILLO", 1),
    	        new Recurso("LANA", 1),
    	        new Recurso("GRANO", 1)
    	    );
    }
    
    private void preguntarPosicion() {
    	Scanner scanner = new Scanner(System.in);
        System.out.println("Elegi la posicion donde quieres colocar el poblado");
        int numVertice = scanner.nextInt();
        ubicacion =  numVertice;
    }
    
    public void colocar() {
    	preguntarPosicion();
    	Tablero tablero = Tablero.getInstance();
    	tablero.colocarEdificio(ubicacion, this);
    }

    public void colocarPiezaFija(int ubicacion) {
        this.ubicacion = ubicacion;
        preguntarPosicion();
        Tablero tablero = Tablero.getInstance();
        tablero.colocarPieza(ubicacion, this);
    }

    public int ubicacion() {
    	return ubicacion;
    }

    public String obtenerNombreJugador() {
        return propietario.obtenerNombre();
    }
}
