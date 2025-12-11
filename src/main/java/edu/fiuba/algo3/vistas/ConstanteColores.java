package edu.fiuba.algo3.vistas;

import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;

public class ConstanteColores {
    public static final int TERRACOTA = 0;
    public static final int PARAMO = 1;
    public static final int VERDECLARO = 2;
    public static final int AMARILLOCAMPO = 3;
    public static final int VERDEOSCURO = 4;
    public static final int GRIS = 5;
    public static final int ARENA = 6;

    private static Paint paramo = Color.web("EBDC6F");
    private static Paint terracota = Color.web("F55C4A");
    private static Paint verdePastizal = Color.web("A0EB6E");
    private static Paint amarilloCampo = Color.web("F5EB1A");
    private static Paint verdeBosque = Color.web("008f39");
    private static Paint grisMotania = Color.web("C6D3EB");
    private static Paint arena = Color.web("DEBD76");



    public static final Paint[] coloresTablero = {terracota, paramo,verdePastizal, amarilloCampo,verdeBosque,grisMotania,arena};

}