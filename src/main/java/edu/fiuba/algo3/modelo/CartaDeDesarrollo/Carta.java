package edu.fiuba.algo3.modelo.CartaDeDesarrollo;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Ubicacion.Ubicacion;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;
import java.util.List;

public abstract class Carta {
    protected String nombre;
    protected String descripcion;

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }


	public abstract void usar(Jugador jugador);

    public void configurarRecurso(Recurso recurso) {}
    public void configurarVictimas(List<Jugador> victimas) {}
    public void configurarRecursos(List<Recurso>recursos) {}
    public void configurarLadron(UbicacionVertice destino, Jugador victima) {}
    public void configurarCaminos(List<Ubicacion> camino1, List<Ubicacion> camino2) {}
    public boolean modificaRecursos() {return false;}
    public int puntosDeVictoriaOcultos() {
        return 0;
    }

}
