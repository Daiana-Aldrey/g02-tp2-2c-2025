package edu.fiuba.algo3.modelo.Recurso;

public class Lana extends Recurso{
    public Lana(){
        super();
    }

    public Lana(int cant){
        super(cant);
    }

    @Override
    public boolean podesIncrementar(Recurso recursoDelJugador, int cantidad) {
        return recursoDelJugador.incrementar(this, cantidad);
    }

    @Override
    protected boolean incrementar(Madera recursorecibido, int cant) {
        return false;
    }

    @Override
    protected boolean incrementar(Mineral recursorecibido, int cant) {
        return false;
    }

    @Override
    protected boolean incrementar(Ladrillo recursorecibido, int cant) {
        return false;
    }

    @Override
    protected boolean incrementar(Lana recursorecibido, int cant) {
        this.incrementar(cant);
        return true;
    }

    @Override
    protected boolean incrementar(Grano recursorecibido, int cant) {
        return false;
    }

    @Override
    public boolean podesDecrementar(Recurso recursoDelJugador, int cant) {
        return recursoDelJugador.decrementar(this, cant);
    }

    @Override
    protected boolean decrementar(Madera recursoADecrementar, int cant) {
        return false;
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
        this.decrementar(cant);
        return true;
    }

    @Override
    protected boolean decrementar(Mineral recursoADecrementar, int cant) {
        return false;
    }

    @Override
    public Recurso crearCon(int cant) {
        return new Lana(cant);
    }
}
