package edu.fiuba.algo3.Excepciones;

public class AccionNoPermitida extends RuntimeException {
    public AccionNoPermitida(String message) {
        super(message);
    }
}
