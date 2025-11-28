package edu.fiuba.algo3.modelo.Pieza;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Recurso.RecursoTipo;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Ubicacion.Ubicacion;
import edu.fiuba.algo3.modelo.Ubicacion.NoUbicacion;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;

import java.util.List;

public class Poblado extends Pieza {
	private Ubicacion ubicacion;
	
	public Poblado(Jugador propietario) {
	    this.propietario = propietario; 
	    this.ubicacion = new NoUbicacion();
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
    
    @Override
    public void colocarPrimera(List<UbicacionVertice> ubicacion) {
    	if (ubicacion.size() != 1) {
            throw new IllegalArgumentException("Un poblado necesita exactamente 1 ubicación");
        }
    	  
          UbicacionVertice ubicacionIntento = ubicacion.get(0);
          Tablero tablero = Tablero.getInstance();
          tablero.colocarEdificio(ubicacionIntento, this);
          propietario.incorporarPoblado(this);
    }

    @Override
    public void colocar(List<UbicacionVertice> ubicacion) {
        if (ubicacion.size() != 1) {
            throw new IllegalArgumentException("Un poblado necesita exactamente 1 ubicación");
        }

        UbicacionVertice ubicacionIntento = ubicacion.get(0);
        if (!propietario.tenesPiezaEnUbicacion(ubicacionIntento)) {
            throw new IllegalArgumentException("Tiene que tener un camino que lo conecte");
        }

        Tablero tablero = Tablero.getInstance();
        tablero.colocarEdificio(ubicacionIntento, this);
        propietario.incorporarPoblado(this);
    }

    public int produccion(){return 1;}

    @Override
    public boolean tenesUbicacion(UbicacionVertice ubicacion) {
        return ubicacion.equals(this.ubicacion);
    }

    @Override
    public boolean usable() {
        boolean usable = true;
        return usable;
    }

    @Override
    public void setearUbicacion(UbicacionVertice ubicacion) {
        this.ubicacion = ubicacion;
    }
}
