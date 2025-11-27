package edu.fiuba.algo3.modelo.Dados;
import java.util.Random;

public class GeneradorNumerosAleatorios {
    private Random random;
    private static final GeneradorNumerosAleatorios INSTANCE = new GeneradorNumerosAleatorios();

    public GeneradorNumerosAleatorios(){
        random = new Random(42);
    }

    public int generarEnRango(int numero) {
        int numeroAleatorio = random.nextInt(numero) + 1;
        return numeroAleatorio;
    }

    public int generarEnRangoDesdeCero(int numero) {
        int numeroAleatorio = random.nextInt(numero);
        return numeroAleatorio;
    }

    public static GeneradorNumerosAleatorios getInstance() {
        return INSTANCE;
    }
}
