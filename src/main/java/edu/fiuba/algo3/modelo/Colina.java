package edu.fiuba.algo3.modelo;

public class Colina extends Terreno{
    public Colina (int ficha){
        super(ficha);
    }

    @Override
    public String tipoRecurso(){
        return "ladrillo";
    }
}
