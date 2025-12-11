package edu.fiuba.algo3.modelo.Pieza;

import edu.fiuba.algo3.Excepciones.CantidadUbicacionesInvalida;
import edu.fiuba.algo3.Excepciones.ColocacionInvalida;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Ubicacion.Ubicacion;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;
import edu.fiuba.algo3.modelo.Ubicacion.NoUbicacion;

import java.util.*;

public class Camino extends Pieza {
    private Ubicacion ubicacion1;
    private Ubicacion ubicacion2;

    public Camino(Jugador propietario) {
        this.propietario = propietario;
        this.ubicacion1 = new NoUbicacion();
        this.ubicacion2 = new NoUbicacion();

    }

    @Override
    public List<Recurso> costoDeConstruccion() {
        return List.of(
                new Madera(1),
                new Ladrillo(1)
        );
    }

    @Override
    public void colocarPrimera(List<Ubicacion> ubicaciones) {
        if (ubicaciones.size() != 2) {
            throw new CantidadUbicacionesInvalida("Un camino necesita exactamente 2 ubicaciones");
        }
        UbicacionVertice ubicacion1 = (UbicacionVertice) ubicaciones.get(0);
        UbicacionVertice ubicacion2 = (UbicacionVertice) ubicaciones.get(1);

        if(propietario.tenesPobladoEnUbicacion(ubicacion1) || propietario.tenesPobladoEnUbicacion(ubicacion2)) {
            Tablero tablero = Tablero.getInstance();
            tablero.colocarCamino(ubicacion1, ubicacion2 ,this);

            propietario.incorporarCamino(this);
        } else {
            throw new ColocacionInvalida("No se encuentra poblado para empezar camino");
        }
    }

    @Override
    public void colocar(List<Ubicacion> ubicaciones) {
        if (ubicaciones.size() != 2) {
            throw new CantidadUbicacionesInvalida("Un camino necesita exactamente 2 vértices");
        }

        UbicacionVertice ubicacion1 = (UbicacionVertice)ubicaciones.get(0);
        UbicacionVertice ubicacion2 = (UbicacionVertice) ubicaciones.get(1);

        if (propietario.tenesPiezaEnUbicacion(ubicacion1) || propietario.tenesPiezaEnUbicacion(ubicacion2)) {
            Tablero tablero = Tablero.getInstance();
            tablero.colocarCamino(ubicacion1, ubicacion2 ,this);

            propietario.incorporarCamino(this);
        } else {
            throw new ColocacionInvalida("No se encuentra poblado/camino para colocar un camino");
        }
    }

    @Override
    public boolean tenesUbicacion(Ubicacion ubicacion) {
        return (this.ubicacion1.equals(ubicacion) || this.ubicacion2.equals(ubicacion));
    }

    @Override
    public boolean usable() {
        boolean usable = true;
        return usable;
    }

    @Override
    public void setearUbicacion(Ubicacion ubicacion) {
        this.ubicacion1 = ubicacion;
    }

    public void setearSegundaUbicacion(Ubicacion ubicacion) {
        this.ubicacion2 = ubicacion;
    }

    public void guardarUbicacion(List<Ubicacion> ubicaciones) {
        if(!ubicaciones.contains(this.ubicacion1)) {
            ubicaciones.add(this.ubicacion1);
        }
        if(!ubicaciones.contains(this.ubicacion2)) {
            ubicaciones.add(this.ubicacion2);
        }
    }

    public void guardarAdyacentes(Map<Ubicacion, List<Ubicacion>> adyacentes) {
        if (!adyacentes.containsKey(this.ubicacion1)) {
            adyacentes.put(this.ubicacion1, new ArrayList<>());
            List<Ubicacion> valor1 = adyacentes.get(this.ubicacion1);
            valor1.add(this.ubicacion2);
        } else {
            List<Ubicacion> valor1 = adyacentes.get(this.ubicacion1);
            valor1.add(this.ubicacion2);
        }
        if (!adyacentes.containsKey(this.ubicacion2)) {
            adyacentes.put(this.ubicacion2, new ArrayList<>());
            List<Ubicacion> valor2 = adyacentes.get(this.ubicacion2);
            valor2.add(this.ubicacion1);
        }  else {
            List<Ubicacion> valor2 = adyacentes.get(this.ubicacion2);
            valor2.add(this.ubicacion1);
        }
    }
    
    public void cobrarRecursosIniciales() {
    }
}