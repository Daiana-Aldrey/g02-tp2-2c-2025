package edu.fiuba.algo3.modelo;

public class Pastizal extends Terreno{
    public Pastizal(int ficha){
        super(ficha);
    }

    @Override
    public String tipoRecurso(){
        return "lana";
    }
}
