package edu.fiuba.algo3.modelo.Dados;

import edu.fiuba.algo3.modelo.GeneradorNumerosAleatorios;

import java.util.concurrent.ThreadLocalRandom;

public class DadosAleatorios  implements GeneradorDeDados {
    @Override
    public int tirar() {
        GeneradorNumerosAleatorios generador = GeneradorNumerosAleatorios.getInstance();
        int dado1 = generador.generarEnRango(6);
        int dado2 = generador.generarEnRango(6);
        return dado1 + dado2;
    }
}