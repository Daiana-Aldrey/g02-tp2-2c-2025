package edu.fiuba.algo3.modelo.Bonificacion;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Pieza.Camino;
import edu.fiuba.algo3.modelo.Ubicacion.Ubicacion;

import java.util.*;

public class RutaMayor {
    private final int PUNTOSVICTORIA = 2;
    private Jugador propietario;
    private List<Camino> caminos;
    private List<Ubicacion> ubicaciones;
    private Map<Ubicacion, List<Ubicacion>> adyacentes;
    private int rutaMayor;

    public RutaMayor(Jugador propietario, List<Camino> caminos){
        this.propietario = propietario;
        this.caminos = caminos;
        this.ubicaciones = new ArrayList<>();
        this.adyacentes = new HashMap<>();
        rutaMayor = 0;
    }

    public void setearAdyacenteYUbicaciones(List<Ubicacion> ubicaciones, Map<Ubicacion, List<Ubicacion>> adyacentes) {
        this.ubicaciones = ubicaciones;
        this.adyacentes = adyacentes;
    }

    public void setearRutaMayor(int rutaMayor) {
        this.rutaMayor = rutaMayor;
    }

    public int calcularRutaMayor() {
        restablecerUbicaciones();

        List<Ubicacion> visitados = new ArrayList<>();
        Map<Ubicacion, Integer> orden = new HashMap<>();
        for (Ubicacion ubicacion : this.ubicaciones) {
            if (!visitados.contains(ubicacion)) {
                orden.put(ubicacion, 0);
                dfs(ubicacion, visitados, orden);
            }
        }
        rutaMayor = Collections.max(orden.values());
        return rutaMayor;
    }

    private void dfs(Ubicacion ubicacion, List<Ubicacion> visitados,  Map<Ubicacion, Integer> orden) {
        visitados.add(ubicacion);
        for (Ubicacion ubicacionAdyacente : adyacentes.get(ubicacion)) {
            if (!visitados.contains(ubicacionAdyacente)) {
                orden.put(ubicacionAdyacente, orden.get(ubicacion) + 1);
                dfs(ubicacionAdyacente, visitados, orden);
            }
        }
    }

    public void restablecerUbicaciones() {
        if (!this.caminos.isEmpty()) {
            Camino caminoNuevo = this.caminos.get(caminos.size() - 1);
            caminoNuevo.guardarUbicacion(ubicaciones);
            caminoNuevo.guardarAdyacentes(adyacentes);
        }
    }

    public boolean pasaCapacidadMinima() {
        boolean supera = false;
        if (rutaMayor > 4) {
            supera = true;
        }
        return supera;
    }

    public boolean otorgarPuntos() {
        boolean capacidadMinima = pasaCapacidadMinima();
        if (capacidadMinima) {
            propietario.otorgarPuntos(PUNTOSVICTORIA);
        }
        return capacidadMinima;
    }

    public void sacarPuntos() {
        propietario.sacarPuntos(PUNTOSVICTORIA);
    }


    public boolean sosMayor(RutaMayor parcialMayor) {
        return !parcialMayor.SosMayorInt(this.rutaMayor);
    }

    private boolean SosMayorInt(int rutaMayor) {
        return rutaMayor < this.rutaMayor;
    }


}
