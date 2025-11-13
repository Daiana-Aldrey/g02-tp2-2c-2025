package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class TableroCatan extends Tablero {
    private int cantidadVertices;
    private List<Integer> aristasHorizontales;
    private List<Integer> aristasDiagonales;

    //PATRON DE DISEÑO SINGLETON

    // unica instancia creada al cargar la clase
    private static final TableroCatan INSTANCE = new TableroCatan();

    // nadie puede hacer "new Tablero()"
    private TableroCatan() {
        cantidadVertices = 54;
        aristasHorizontales = new ArrayList<>();
        aristasDiagonales = new ArrayList<>();
    }

    // metodo de acceso global
    public static Tablero getInstance() {
        return INSTANCE;
    }


    public void establecerAristasHorizontales() {
        aristasHorizontales.add(6);
        aristasHorizontales.add(8);
        aristasHorizontales.add(10);
        aristasHorizontales.add(10);
        aristasHorizontales.add(8);
        aristasHorizontales.add(6);
    }

    public void establecerAristasDiagonales() {
        aristasDiagonales.add(8);
        aristasDiagonales.add(10);
        aristasDiagonales.add(11);
        aristasDiagonales.add(10);
        aristasDiagonales.add(8);
    }


}
