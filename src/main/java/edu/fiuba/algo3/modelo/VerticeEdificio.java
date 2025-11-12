package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class VerticeEdificio extends EnlazadorVertices {
    private Integer ubicacion;
    private List<VerticeTerreno> terrenos; //ver si se queda o no
    private boolean disponible;
    private Pieza pieza;

    public VerticeEdificio(Integer ubicacion) {
        terrenos = new ArrayList<>();
        this.ubicacion = ubicacion;
        disponible = true;
        pieza = null;
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

