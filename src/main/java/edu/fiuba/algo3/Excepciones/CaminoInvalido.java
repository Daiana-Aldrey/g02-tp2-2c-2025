package edu.fiuba.algo3.Excepciones;

public class CaminoInvalido extends RuntimeException{
    public CaminoInvalido(String mensaje){
        super(mensaje);
    }
}
