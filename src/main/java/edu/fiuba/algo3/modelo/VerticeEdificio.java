package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class VerticeEdificio extends EnlazadorVertices {
    private Integer ubicacion;
    private List<VerticeTerreno> terrenos; //ver si se queda o no
    private boolean disponible;
    private Pieza pieza;
    private List<Camino> caminos;
    private Jugador jugadorPerteneciente;

    public VerticeEdificio(Integer ubicacion) {
        terrenos = new ArrayList<>();
        this.ubicacion = ubicacion;
        disponible = true;
        pieza = null;
        jugadorPerteneciente = null;
        caminos = new ArrayList<>();
    }

    // Post: Compara el nombre que le mandan por el parametro con el nombre que tiene como atributo.
    public boolean tieneUbicacion(Integer ubicacion) {
        return ubicacion == this.ubicacion;
    }

    // Post: Devuelve su estado de disponibilidad.
    public boolean estaDisponible() {
        return this.disponible;
    }

    // Post: marca al vértice como no disponible y también a sus vertices adyacentes
    public void colocarPieza(Pieza edificio) {
        this.pieza = edificio;
        noDisponible();
        agregarEdificioATerrenos(edificio);

        for (VerticeEdificio vertice : adyacentes) {
            vertice.noDisponible();
        }
    }

    public void colocarPiezaFija(Pieza edificio) {
        if ((jugadorPerteneciente ==  null) || (jugadorPerteneciente.obtenerNombre() == edificio.obtenerNombreJugador())) {
            noDisponible();
            this.pieza = edificio;
            jugadorPerteneciente = edificio.obtenerJugador();
        } else {
            throw new IllegalArgumentException("Casillero inválido");
        }
        noDisponible();
        agregarEdificioATerrenos(edificio);
        for (VerticeEdificio vertice : adyacentes) {
            vertice.asignarJugador(jugadorPerteneciente);
        }
    }

    public void colocarPiezaCamino(Camino camino) {
        if ((jugadorPerteneciente ==  null) || (jugadorPerteneciente.obtenerNombre() == camino.obtenerNombreJugador())) {
            caminos.add(camino);
            jugadorPerteneciente = camino.obtenerJugador();
        } else {
            throw new IllegalArgumentException("Casillero inválido");
        }
        for (VerticeEdificio vertice : adyacentes) {
            vertice.asignarJugador(jugadorPerteneciente);
        }
    }

    // Post: Setea su estado como no disponible
    private void noDisponible() {
        disponible = false;
    }

    // Post: Verifica si tiene cierto vertice como adyacente
    public boolean hayVerticeAdyacente(VerticeEdificio verticeEdificio) {
        int i = 0;
        boolean encontrado = false;
        while (i < adyacentes.size() && !encontrado) {
            if (adyacentes.get(i).equals(verticeEdificio)) {
                encontrado = true;
            }
            i++;
        }
        return encontrado;
    }

    public void asignarJugador(Jugador jugador) {
        this.jugadorPerteneciente = jugador;
    }

    public void agregarVerticeAdyacente (VerticeTerreno vertice){
        terrenos.add(vertice);
    }

    public boolean hayTerrenoAdyacente(char letra) {
        int i = 0;
        boolean encontrado = false;
        while (i < terrenos.size() && !encontrado) {
            if (terrenos.get(i).tieneUbicacion(letra)) {
                encontrado = true;
            }
            i++;
        }
        return encontrado;
    }

    public void agregarEdificioATerrenos (Pieza pieza) {
        for (VerticeTerreno terreno : terrenos) {
            terreno.agregarEdificio(pieza);
        }
    }

    //sacar
    public Integer nombre() {
        return ubicacion;
    }

    //sacar
    public List<VerticeEdificio> adyacentes() {
        return adyacentes;
    }

}

