package edu.fiuba.algo3.modelo;

public class DadosS7 implements GeneradorDeDados {

    private final int fijo;

    public DadosS7(int fijo) {
        this.fijo = fijo;
    }

    @Override public int tirar() {
        return fijo;
    }
}
