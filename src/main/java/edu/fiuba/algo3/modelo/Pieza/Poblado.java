package edu.fiuba.algo3.modelo.Pieza;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Recurso.RecursoTipo;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

import java.util.List;

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

    public int produccion(){return 1;}

    @Override
    public boolean esPoblado() {return true;}

    @Override
    public boolean tenesUbicacion(int ubicacion) {
        return this.ubicacion == ubicacion;
    }
}
