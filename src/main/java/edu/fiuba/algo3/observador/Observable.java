package edu.fiuba.algo3.observador;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {

    private final List<Observador> observadores = new ArrayList<>();

    public void agregarObservador(Observador observador) {
        observadores.add(observador);
    }

    public void quitarObservador(Observador observador) {
        observadores.remove(observador);
    }

    protected void notificarObservadores(Object evento) {
        for (Observador observador : observadores) {
            observador.actualizar(this, evento);
        }
    }
}
