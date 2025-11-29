package edu.fiuba.algo3.modelo.Recurso;

public class Madera extends Recurso{
    public Madera(){
        super();
    }

    public Madera(int cant){
        super(cant);
    }

    @Override
    public boolean podesIncrementar(Recurso recursoDelJugador, int cantidad) {
        return recursoDelJugador.incrementar(this, cantidad);
    }

    @Override
    protected boolean incrementar(Madera recursoRecibido, int cant) {
        this.incrementar(cant);
        return true;
    }

    @Override
    protected boolean incrementar(Mineral recursoRecibido, int cant) {
        return false;
    }

    @Override
    protected boolean incrementar(Ladrillo recursoRecibido, int cant) {
        return false;
    }

    @Override
    protected boolean incrementar(Lana recursoRecibido, int cant) {
        return false;
    }

    @Override
    protected boolean incrementar(Grano recursoRecibido, int cant) {
        return false;
    }

    @Override
    public boolean podesDecrementar(Recurso recursoDelJugador, int cant) {
        return recursoDelJugador.decrementar(this, cant);
    }

    @Override
    protected boolean decrementar(Madera recursoADecrementar, int cant) {
        this.decrementar(cant);
        return true;
    }

    @Override
    protected boolean decrementar(Ladrillo recursoADecrementar, int cant) {
        return false;
    }

    @Override
    protected boolean decrementar(Grano recursoADecrementar, int cant) {
        return false;
    }

    @Override
    protected boolean decrementar(Lana recursoADecrementar, int cant) {
        return false;
    }

    @Override
    protected boolean decrementar(Mineral recursoADecrementar, int cant) {
        return false;
    }

    @Override
    public Recurso crearCon(int cant) {
        return new Madera(cant);
    }

}
