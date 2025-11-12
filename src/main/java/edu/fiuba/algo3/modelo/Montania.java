package edu.fiuba.algo3.modelo;

public class Montania extends Terreno{
    public Montania(int ficha){
        super(ficha);
    }

    @Override
    public String tipoRecurso(){
        return "mineral";
    }
}
