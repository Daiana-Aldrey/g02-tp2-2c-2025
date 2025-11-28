package edu.fiuba.algo3.modelo.Recurso;

public class Mineral extends Recurso{
    public Mineral(){
        super();
    }

    public Mineral(int cant){
        super(cant);
    }

    @Override
    public boolean podesIncrementar(Recurso recursoDelJugador, int cantidad) {
        return recursoDelJugador.incrementarMineral(this, cantidad);
    }

    @Override
    protected boolean incrementarMadera(Recurso recursorecibido, int cant) {
        return false;
    }

    @Override
    protected boolean incrementarMineral(Recurso recursorecibido, int cant) {
        this.incrementar(cant);
        return true;
    }

    @Override
    protected boolean incrementarLadrillo(Recurso recursorecibido, int cant) {
        return false;
    }

    @Override
    protected boolean incrementarLana(Recurso recursorecibido, int cant) {
        return false;
    }

    @Override
    protected boolean incrementarGrano(Recurso recursorecibido, int cant) {
        return false;
    }

    @Override
    public boolean podesDecrementar(Recurso recursoDelJugador, int cant) {
        return recursoDelJugador.decrementarMineral(this, cant);
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
        return false;
    }

    @Override
    protected boolean decrementarMineral(Recurso recursoADecrementar, int cant) {
        this.decrementar(cant);
        return true;
    }

    @Override
    public Recurso crearCon(int cant) {
        return new Mineral(cant);
    }
}
