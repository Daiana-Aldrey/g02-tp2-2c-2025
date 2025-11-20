package edu.fiuba.algo3.modelo.Dados;

import java.util.concurrent.ThreadLocalRandom;

public class DadosAleatorios  implements GeneradorDeDados {
    @Override
    public int tirar() {
        int dado1 = ThreadLocalRandom.current().nextInt(1, 7);
        int dado2 = ThreadLocalRandom.current().nextInt(1, 7);
        return dado1 + dado2;
    }
}