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
    	        new Recurso(RecursoTipo.MADERA, 1),
    	        new Recurso(RecursoTipo.LADRILLO, 1),
    	        new Recurso(RecursoTipo.LANA, 1),
    	        new Recurso(RecursoTipo.GRANO, 1)
    	    );
    }
    
    
    public void colocar(List<Integer> vertice) {
    	if (vertice.size() != 1) {
            throw new IllegalArgumentException("Un poblado necesita exactamente 1 vértice");
        }
    	  
          this.ubicacion = vertice.get(0);
          Tablero tablero = Tablero.getInstance();
          tablero.colocarEdificio(ubicacion, this);
          propietario.incorporarPoblado(this);
    }


    public int ubicacion() {
    	return ubicacion;
    }

    public int produccion(){return 1;}

    @Override
    public boolean esPoblado() {return true;}
}
