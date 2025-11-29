package edu.fiuba.algo3.modelo.Recurso;

import edu.fiuba.algo3.Excepciones.RecursoIncorrecto;

public class Madera extends Recurso{
    public Madera(){
        super();
    }

    public Madera(int cant){
        super(cant);
    }

    @Override
    public void podesIncrementar(Recurso recursoDelJugador, int cantidad) {
        recursoDelJugador.incrementar(this, cantidad);
    }

    @Override
    protected void incrementar(Madera recursoRecibido, int cant) {
        this.incrementar(cant);
    }

    @Override
    protected void incrementar(Mineral recursoRecibido, int cant) {
        throw new RecursoIncorrecto();
    }

    @Override
    protected void incrementar(Ladrillo recursoRecibido, int cant) {
        throw new RecursoIncorrecto();
    }

    @Override
    protected void incrementar(Lana recursoRecibido, int cant) {
        throw new RecursoIncorrecto();
    }

    @Override
    protected void incrementar(Grano recursoRecibido, int cant) {
        throw new RecursoIncorrecto();
    }

    @Override
    public void podesDecrementar(Recurso recursoDelJugador, int cant) {
        recursoDelJugador.decrementar(this, cant);
    }

    @Override
    protected void decrementar(Madera recursoADecrementar, int cant) {
        this.decrementar(cant);
    }

    @Override
    protected void decrementar(Ladrillo recursoADecrementar, int cant) {
        throw new RecursoIncorrecto();
    }

    @Override
    protected void decrementar(Grano recursoADecrementar, int cant) {
        throw new RecursoIncorrecto();
    }

    @Override
    protected void decrementar(Lana recursoADecrementar, int cant) {
        throw new RecursoIncorrecto();
    }

    @Override
    protected void decrementar(Mineral recursoADecrementar, int cant) {
        throw new RecursoIncorrecto();
    }

    @Override
    public Recurso crearCon(int cant) {
        return new Madera(cant);
    }

}
