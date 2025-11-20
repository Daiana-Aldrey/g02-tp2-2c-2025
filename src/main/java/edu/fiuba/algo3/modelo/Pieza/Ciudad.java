package edu.fiuba.algo3.modelo.Pieza;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Recurso.RecursoTipo;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

import java.util.List;

public class Ciudad extends Pieza {
	private int ubicacion;
	
	public Ciudad(Jugador propietario) {
	    this.propietario = propietario; 
	    this.ubicacion = -1;
	}


    @Override
    public List<Recurso> costoDeConstruccion(){
        return List.of(
                new Recurso(RecursoTipo.GRANO,2),
                new Recurso(RecursoTipo.MINERAL, 3));
    }

    @Override
    public void colocar(List<Integer> vertice) {
        if (vertice.size() != 1) {
            throw new IllegalArgumentException("Una ciudad necesita exactamente 1 vértice");
        }

        int ubicacion = vertice.get(0);

        if (!propietario.tenesPobladoEnUbicacion(ubicacion)) {
            throw new IllegalArgumentException("Tenes que tener un poblado en la ubicacion para poder construir una ciudad");
        }

        Tablero tablero = Tablero.getInstance();
        tablero.removerPoblado(ubicacion);

        this.propietario.removerPoblado(ubicacion);
        tablero.colocarEdificio(ubicacion, this);
        this.ubicacion = ubicacion;

        propietario.incorporarCiudad(this);
    }

    @Override
    public int produccion(){return 2;}

    @Override
    public boolean tenesUbicacion(int ubicacion) {
        return this.ubicacion == ubicacion;
    }

    public boolean esCiudad(){return true;}
}
