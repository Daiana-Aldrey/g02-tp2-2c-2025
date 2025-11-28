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
        return recursoDelJugador.incrementarLana(this, cantidad);
    }

    @Override
    protected boolean incrementarMadera(Recurso recursorecibido, int cant) {
        return false;
    }

    @Override
    protected boolean incrementarMineral(Recurso recursorecibido, int cant) {
        return false;
    }

    @Override
    protected boolean incrementarLadrillo(Recurso recursorecibido, int cant) {
        return false;
    }

    @Override
    protected boolean incrementarLana(Recurso recursorecibido, int cant) {
        this.incrementar(cant);
        return true;
    }

    @Override
    protected boolean incrementarGrano(Recurso recursorecibido, int cant) {
        return false;
    }

    @Override
    public boolean podesDecrementar(Recurso recursoDelJugador, int cant) {
        return recursoDelJugador.decrementarLana(this, cant);
    }

    @Override
    protected boolean decrementarMadera(Recurso recursoADecrementar, int cant) {
        return false;
    }

    @Override
    protected boolean decrementarLadrillo(Recurso recursoADecrementar, int cant) {
        return false;
    }

    @Override
    protected boolean decrementarGrano(Recurso recursoADecrementar, int cant) {
        return false;
    }

    @Override
    protected boolean decrementarLana(Recurso recursoADecrementar, int cant) {
        this.decrementar(cant);
        return true;
    }

    @Override
    protected boolean decrementarMineral(Recurso recursoADecrementar, int cant) {
        return false;
    }

    @Override
    public Recurso crearCon(int cant) {
        return new Lana(cant);
    }
}
