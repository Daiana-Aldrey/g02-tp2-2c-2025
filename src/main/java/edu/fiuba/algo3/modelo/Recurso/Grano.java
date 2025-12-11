package edu.fiuba.algo3.modelo.Recurso;

import edu.fiuba.algo3.Excepciones.RecursoIncorrecto;

public class Grano extends Recurso{
    public Grano(){
        super();
    }

    public Grano(int cant){
        super(cant);
    }

    @Override
    public void podesIncrementar(Recurso recursoDelJugador, int cantidad) {
        recursoDelJugador.incrementar(this, cantidad);
    }

    @Override
    protected void incrementar(Madera recursorecibido, int cant) {
        throw new RecursoIncorrecto();
    }

    @Override
    protected void incrementar(Mineral recursorecibido, int cant) {
        throw new RecursoIncorrecto();
    }

    @Override
    protected void incrementar(Ladrillo recursorecibido, int cant) {
        throw new RecursoIncorrecto();
    }

    @Override
    protected void incrementar(Lana recursorecibido, int cant) {
        throw new RecursoIncorrecto();
    }

    @Override
    protected void incrementar(Grano recursorecibido, int cant) {
        this.incrementar(cant);
    }

    @Override
    public void podesDecrementar(Recurso recursoDelJugador, int cant) {
        recursoDelJugador.decrementar(this,cant);
    }

    @Override
    protected void decrementar(Madera recursoADecrementar, int cant) {
        throw new RecursoIncorrecto();
    }

    @Override
    protected void decrementar(Ladrillo recursoADecrementar, int cant) {
        throw new RecursoIncorrecto();
    }

    @Override
    protected void decrementar(Grano recursoADecrementar, int cant) {
        this.decrementar(cant);
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
        return new Grano(cant);
    }

    @Override
    public String recurso() {
        return "grano";
    }
}
