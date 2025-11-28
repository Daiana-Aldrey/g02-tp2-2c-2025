package edu.fiuba.algo3.modelo.Pieza;

import edu.fiuba.algo3.Excepciones.CantidadUbicacionesInvalida;
import edu.fiuba.algo3.Excepciones.ColocacionInvalida;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Grano;
import edu.fiuba.algo3.modelo.Recurso.Mineral;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Ubicacion.Ubicacion;
import edu.fiuba.algo3.modelo.Ubicacion.NoUbicacion;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;

import java.util.List;

public class Ciudad extends Pieza {
	private Ubicacion ubicacion;
	
	public Ciudad(Jugador propietario) {
	    this.propietario = propietario; 
	    this.ubicacion = new NoUbicacion();
	}


    @Override
    public List<Recurso> costoDeConstruccion(){
        return List.of(
                new Grano(2),
                new Mineral(3)
        );
    }

    @Override
    public void colocarPrimera(List<Ubicacion> ubicaciones) {
        throw new ColocacionInvalida("No se puede colocar una ciudad en los primeros turnos");
    }

    @Override
    public void colocar(List<Ubicacion> ubicacion) {
        if (ubicacion.size() != 1) {
            throw new CantidadUbicacionesInvalida("Se necesita de solamanete una ubicación");
        }

        UbicacionVertice ubicacionIntento = (UbicacionVertice) ubicacion.get(0);

        if (!propietario.tenesPobladoEnUbicacion(ubicacionIntento)) {
            throw new ColocacionInvalida("Tenes que tener un poblado en la misma ubicacion para poder construir una ciudad");
        }

        Tablero tablero = Tablero.getInstance();
        tablero.removerPoblado(ubicacionIntento);

        this.propietario.removerPoblado(ubicacionIntento);
        tablero.colocarEdificio(ubicacionIntento, this);

        propietario.incorporarCiudad(this);

    }

    @Override
    public int produccion(){return 2;}

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
}
