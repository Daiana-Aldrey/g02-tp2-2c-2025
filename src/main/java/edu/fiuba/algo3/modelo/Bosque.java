package edu.fiuba.algo3.modelo;

public class Bosque extends Terreno{
    public Bosque(int ficha){
        super(ficha);
    }

    @Override
    public String tipoRecurso(){
        return "madera";
    }
}
