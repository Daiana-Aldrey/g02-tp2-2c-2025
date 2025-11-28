package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.Excepciones.ColocacionInvalida;
import edu.fiuba.algo3.modelo.Pieza.Camino;
import edu.fiuba.algo3.modelo.Pieza.NoPieza;
import edu.fiuba.algo3.modelo.Pieza.Pieza;
import edu.fiuba.algo3.modelo.Ubicacion.Ubicacion;

public class Arista {
    private boolean disponible;
    private Pieza camino;
    private Ubicacion ubicacion1;
    private Ubicacion ubicacion2;

    public Arista(Ubicacion ubicacion1, Ubicacion ubicacion2) {
        disponible = true;
        this.ubicacion1 = ubicacion1;
        this.ubicacion2 = ubicacion2;

        this.camino = new NoPieza(ubicacion2);
    }

    public boolean tieneUbicacion(Ubicacion ubicacion1, Ubicacion ubicacion2) {
        return ((this.ubicacion1.equals(ubicacion1) && this.ubicacion2.equals(ubicacion2)) || (this.ubicacion1.equals(ubicacion2) && this.ubicacion2.equals(ubicacion1)));

    }

    public void colocarCamino(Camino camino) {
        if (disponible) {
            noDisponible();
            this.camino = camino;
            camino.setearUbicacion(ubicacion1);
            camino.setearSegundaUbicacion(ubicacion2);
        } else {
            throw new ColocacionInvalida("Esta arista ya esta ocupada");
        }
    }

    public boolean estaDisponible() {
        return disponible;
    }

    private void noDisponible() {
        disponible = false;
    }

    public boolean hayCamino() {
        boolean hayCamino = false;
        if (camino.usable()) {
            hayCamino = true;
        }
        return hayCamino;
    }
}
