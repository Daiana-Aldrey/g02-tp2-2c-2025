package edu.fiuba.algo3.modelo.Recurso;
import edu.fiuba.algo3.modelo.Jugador;


public class RecursoNulo extends Recurso {
    public RecursoNulo() {
        super(0);
    }

    @Override
    public void podesIncrementar(Recurso recursoDelJugador, int cantidad) {}

    @Override
    public void podesDecrementar(Recurso recursoDelJugador, int cant) {}

    // Métodos de double dispatch: todos NO HACEN NADA
    @Override
    protected void incrementar(Madera recursorecibido, int cant) { }

    @Override
    protected void incrementar(Mineral recursorecibido, int cant) { }

    @Override
    protected void incrementar(Ladrillo recursorecibido, int cant) { }

    @Override
    protected void incrementar(Lana recursorecibido, int cant) { }

    @Override
    protected void incrementar(Grano recursorecibido, int cant) { }

    @Override
    protected void decrementar(Madera recursoADecrementar, int cant) { }

    @Override
    protected void decrementar(Ladrillo recursoADecrementar, int cant) { }

    @Override
    protected void decrementar(Grano recursoADecrementar, int cant) { }

    @Override
    protected void decrementar(Lana recursoADecrementar, int cant) { }

    @Override
    protected void decrementar(Mineral recursoADecrementar, int cant) { }

    @Override
    public void decrementar(int cantidadPedida) {}

    @Override
    public void cobrarDe(Jugador jugador) {}

    @Override
    public void transferirA(Jugador destino, int cantidad) {}

    @Override
    public void cobrarDe(Jugador pagador, Jugador receptor) {}

    @Override
    public Recurso crearCon(int cant) {return new RecursoNulo();}

    @Override
    public String recurso() {return "NULO";}

    @Override
    public boolean esNulo() {
        return true;
    }

}