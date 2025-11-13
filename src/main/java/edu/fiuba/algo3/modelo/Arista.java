package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Arista {
    private Camino camino;
    private boolean disponible;
    private List<Integer> adyacentes;

    public Arista(Integer v1, Integer v2) {
        camino = null;
        disponible = true;

        adyacentes = new ArrayList<>();
        adyacentes.add(v1);
        adyacentes.add(v2);
    }

    public boolean sonMisAdyacentes(List<Integer> posiblesAdyacentes) {
        List<Integer> revertida = listaReversionada();

        return posiblesAdyacentes.equals(adyacentes) || posiblesAdyacentes.equals(revertida);
    }

    private List<Integer> listaReversionada() {
        List<Integer> revertida = new ArrayList<>(adyacentes);
        Collections.reverse(revertida);
        return revertida;
    }

    public void colocarCamino(Camino camino) {
        this.camino = camino;
        noDisponible();
    }

    public boolean estaDisponible() {
        return disponible;
    }

    private void noDisponible() {
        disponible = false;
    }

}
