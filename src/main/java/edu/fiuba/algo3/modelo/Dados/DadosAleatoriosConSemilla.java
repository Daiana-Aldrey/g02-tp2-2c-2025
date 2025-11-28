package edu.fiuba.algo3.modelo.Dados;
import edu.fiuba.algo3.Excepciones.*;
import edu.fiuba.algo3.Excepciones.TiradaFueraDeRango;

import java.util.Random;

public class DadosAleatoriosConSemilla implements GeneradorDeDados {
    final int  MINIMO = 2 ;
    final int  MAXIMO = 12 ;
    private final Random random;

    public DadosAleatoriosConSemilla() {
        this.random = new Random();
    }

    public DadosAleatoriosConSemilla(long semilla) {
        this.random = new Random(semilla);
    }


    @Override
    public int tirar() {
        int d1 = random.nextInt(6) + 1;
        int d2 = random.nextInt(6) + 1;
        int tiradaSumaDados = d1 + d2;

        if (tiradaSumaDados < MINIMO || tiradaSumaDados > MAXIMO) {
            throw new TiradaFueraDeRango("Tirada fuera de rango: " + tiradaSumaDados);
        }

        return tiradaSumaDados;
    }
}
