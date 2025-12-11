package edu.fiuba.algo3.modelo.Pieza;

import edu.fiuba.algo3.Excepciones.PiezaNoEncontrada;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Ubicacion.Ubicacion;

import java.util.*;

public abstract class Pieza {
    protected Jugador propietario;
    
    public static Pieza crear(String tipo, Jugador propietario) {
        tipo = tipo.toLowerCase(Locale.ROOT).trim();

        switch (tipo) {
            case "poblado":
                return new Poblado(propietario);

            case "camino":
                return new Camino(propietario);

            case "ciudad":
                return new Ciudad(propietario);

            default:
                throw new PiezaNoEncontrada(
                    "Tipo de pieza no válido: " + tipo + ". Debe ser 'poblado', 'camino' o 'ciudad'.");
        }
    }
    
    public abstract List<Recurso> costoDeConstruccion();

    public void agregarRecursos(Recurso recurso, int cantidad) {
        propietario.recibirRecurso(recurso, cantidad);
    }

    public abstract void colocarPrimera(List<Ubicacion> ubicaciones);

    public abstract void colocar(List<Ubicacion> ubicaciones);

    public boolean esDe(Jugador jugador) {
        return this.propietario == jugador;
    }

    public Jugador obtenerJugador(){
        return propietario;
    }

    public void afectarPorLadron(Jugador jugadorQueMueve) {
        jugadorQueMueve.robarCartaAleatoriaA(propietario);
    }

    public int produccion(){return 0;}

    public abstract boolean tenesUbicacion(Ubicacion ubicacion);

    public abstract boolean usable();

    public abstract void setearUbicacion(Ubicacion ubicacion);
    public void cobrarRecursosIniciales() {
      
    }
}
    