package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class TableroCatan extends Tablero {

    //PATRON DE DISEÑO SINGLETON

    // unica instancia creada al cargar la clase
    private static final TableroCatan INSTANCE = new TableroCatan();

    // nadie puede hacer "new Tablero()"
    private TableroCatan() {
        cantidadVertices = 54;
        cantidadHexagonos = 19;
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

    public List<Integer> establecerInicioDiagonales() {
        List<Integer> inicioDiagonales = new ArrayList<>();
        inicioDiagonales.add(0);
        inicioDiagonales.add(0);
        inicioDiagonales.add(0);
        inicioDiagonales.add(1);
        inicioDiagonales.add(1);
        return inicioDiagonales;
    }

    public Integer cantidadVerticesUltimaFila() {
        return 7;
    }


}
