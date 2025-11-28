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
        return recursoDelJugador.incrementarMadera(this, cantidad);
    }

    @Override
    protected boolean incrementarMadera(Recurso recursoRecibido, int cant) {
        this.incrementar(cant);
        return true;
    }

    @Override
    protected boolean incrementarMineral(Recurso recursoRecibido, int cant) {
        return false;
    }

    @Override
    protected boolean incrementarLadrillo(Recurso recursoRecibido, int cant) {
        return false;
    }

    @Override
    protected boolean incrementarLana(Recurso recursoRecibido, int cant) {
        return false;
    }

    @Override
    protected boolean incrementarGrano(Recurso recursoRecibido, int cant) {
        return false;
    }

    @Override
    public boolean podesDecrementar(Recurso recursoDelJugador, int cant) {
        return recursoDelJugador.decrementarMadera(this, cant);
    }

    @Override
    protected boolean decrementarMadera(Recurso recursoADecrementar, int cant) {
        this.decrementar(cant);
        return true;
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
        return false;
    }

    @Override
    public Recurso crearCon(int cant) {
        return new Madera(cant);
    }

}
