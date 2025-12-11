package edu.fiuba.algo3.modelo.Pieza;

import edu.fiuba.algo3.Excepciones.CantidadUbicacionesInvalida;
import edu.fiuba.algo3.Excepciones.ColocacionInvalida;
import edu.fiuba.algo3.Excepciones.SinPiezas;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recurso.*;
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
    	        new Madera(1),
    	        new Ladrillo(1),
    	        new Lana(1),
    	        new Grano(1)
    	    );
    }
    
    @Override
    public void colocarPrimera(List<Ubicacion> ubicacion) {
    	if (ubicacion.size() != 1) {
            throw new CantidadUbicacionesInvalida("Un poblado necesita exactamente 1 ubicación");
        }
    	UbicacionVertice ubicacionIntento = (UbicacionVertice) ubicacion.get(0);
        this.ubicacion = ubicacionIntento;
          Tablero tablero = Tablero.getInstance();
          tablero.colocarEdificio(ubicacionIntento, this);
          propietario.incorporarPoblado(this);
    }

    @Override
    public void colocar(List<Ubicacion> ubicacion) {
        if (ubicacion.size() != 1) {
            throw new CantidadUbicacionesInvalida("Un poblado necesita exactamente 1 ubicación");
        }

        UbicacionVertice ubicacionIntento = (UbicacionVertice) ubicacion.get(0);
        if (!propietario.tenesPiezaEnUbicacion(ubicacionIntento)) {
            throw new ColocacionInvalida("Poblado tiene que tener un camino que lo conecte");
        }

        Tablero tablero = Tablero.getInstance();
        tablero.colocarEdificio(ubicacionIntento, this);
        propietario.incorporarPoblado(this);
    }

    public int produccion(){return 1;}

    @Override
    public boolean tenesUbicacion(Ubicacion ubicacion) {
        return ubicacion.equals(this.ubicacion);
    }

    @Override
    public boolean usable() {
        boolean usable = true;
        return usable;
    }

    @Override
    public void setearUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    @Override
    public void cobrarRecursosIniciales() {
        if (this.propietario.pobladosInicialesColocados()) {
        	Tablero.getInstance().entregarRecursosIniciales(ubicacion, this);
        }
    }
}
