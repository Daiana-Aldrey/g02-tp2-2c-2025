package edu.fiuba.algo3.Excepciones;

public class PiezaNoEncontrada extends RuntimeException{
    public PiezaNoEncontrada(String mensaje) {
        super(mensaje);
    }
}
