package edu.fiuba.algo3.modelo.Dados;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dados {

    private final Random random = new Random();
    private int cantDados;
    private List<Integer> valoresTirada;

    public Dados(int cantidad) {
        this.cantDados = cantidad;
        this.valoresTirada = new ArrayList<>(cantidad);
        for (int i = 0; i < cantidad; i++) {
            valoresTirada.add(0);
        }
    }

    public int[]  tirar() {
        for (int i = 0; i < cantDados; i++) {
            valoresTirada.set(i, random.nextInt(6) + 1); 
        }

        int[] resultado = new int[cantDados];
        for (int i = 0; i < cantDados; i++) {
            resultado[i] = valoresTirada.get(i);
        }
        
        return resultado;
    }

    public int sumarTirada() {
        int suma = 0;
        for (int i = 0; i < cantDados; i++) {
            suma += valoresTirada.get(i);
        }
        return suma;
    }
    
    public int tiradaFalsa(int valorObtenido) {
    	return valorObtenido;
    }

}
