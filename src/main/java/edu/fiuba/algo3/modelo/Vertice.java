package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class Vertice {
    private Integer nombre;
    private int numVertice;
    private List<Vertice> adyacetes;
    private boolean disponible;
    private boolean pieza;

    public Vertice(Integer nombre) {
        adyacetes = new ArrayList<>();
        this.nombre = nombre;
        disponible = true;
        pieza = false;
    }
    
    // Post: agrega un vértice adyacente a la lista de "adyacentes".
    public void agregarVerticeAdyacente(Vertice vertice) {
        adyacetes.add(vertice);
    }

    // Post: Compara el nombre que le mandan por el parametro con el nombre que tiene como atributo.
    public boolean tieneNombre(Integer nombre) {
        return this.nombre.equals(nombre);
    }

    // Post: Devuelve su estado de disponibilidad.
    public boolean estaDisponible() {
        return this.disponible;
    }

    // Post: marca al vértice como no disponible y también a sus vertices adyacentes
    public void tienePieza() {
        pieza = true;
        noDisponible();
        for(Vertice vertice: adyacetes){
            vertice.noDisponible();
        }
    }
    // Post: Setea su estado como no disponible
    public void noDisponible() {
        disponible = false;
    }

    // Post: Verifica si tiene cierto vertice como adyacente
    public boolean hayVerticeAdyacente(Vertice vertice) {
        int i = 0;
        boolean encontrado = false;
        while(i < adyacetes.size() &&! encontrado) {
            if(adyacetes.get(i).equals(vertice)) {
                encontrado = true;
            }
            i++;
        }
        return encontrado;
    }

    // Post: Verifica si en este vertice hay una pieza puesta
    public boolean hayPieza() {
        return this.pieza;
    }
    //sacar
    public Integer nombre() {
        return nombre;
    }
    //sacar
    public List<Vertice> adyacentes() {
        return adyacetes;
    }
}

