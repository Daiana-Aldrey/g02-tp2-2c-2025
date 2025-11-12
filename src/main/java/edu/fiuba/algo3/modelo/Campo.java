package edu.fiuba.algo3.modelo;

public class Campo extends Terreno{
    public Campo(int ficha){
        super(ficha);
    }

    @Override
    public String tipoRecurso(){
        return "grano";
    }
}