package edu.fiuba.algo3.modelo.Pieza;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Recurso.RecursoTipo;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

import java.util.*;

public class Camino extends Pieza {
    private List<Integer> ubicacion;

    public Camino(Jugador propietario) {
        this.propietario = propietario;
        this.ubicacion = new ArrayList<>();
    }

    @Override
    public List<Recurso> costoDeConstruccion() {
        return List.of(
                new Recurso(RecursoTipo.MADERA, 1),
                new Recurso(RecursoTipo.LADRILLO, 1)
        );
    }

    @Override
    public void colocar(List<Integer> vertices) {
        if (vertices.size() != 2) {
            throw new IllegalArgumentException("Un camino necesita exactamente 2 vértices");
        }

        ubicacion = vertices;

        Tablero tablero = Tablero.getInstance();
        tablero.colocarCamino(ubicacion, this);

        propietario.incorporarCamino(this);
    }

    @Override
    public boolean tenesUbicacion(int ubicacion) {
        return this.ubicacion.contains(ubicacion);
    }
}