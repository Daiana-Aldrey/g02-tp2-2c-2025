package edu.fiuba.algo3.entrega_1.testUnitarios;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.RecursoTipo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;



public class RecursoTest{

    @Test
    public void sosTipodevuelveTrueAlCoincidirRecurso(){
        Recurso madera = new Recurso(RecursoTipo.MADERA);
        boolean esMadera = madera.sosTipo(RecursoTipo.MADERA);
        Assertions.assertTrue(esMadera);
    }

    @Test
    public void sosTipodevuelveFalseAlNoCoincidirRecurso(){
        Recurso madera = new Recurso(RecursoTipo.MADERA);
        boolean esMadera = madera.sosTipo(RecursoTipo.LADRILLO);
        Assertions.assertFalse(esMadera);
    }

    @Test
    public void testIncrementarAumentaLaCantidadDeRecursoCreadoConCAntCero() {
        Recurso mineral = new Recurso(RecursoTipo.MINERAL);
        mineral.incrementar(3);
        Assertions.assertEquals(3, mineral.cantidad());
    }

    @Test
    public void testIncrementarAumentaLaCantidadDeRecursoCreado() {
        Recurso mineral = new Recurso(RecursoTipo.MINERAL, 2);
        mineral.incrementar(3);
        Assertions.assertEquals(5, mineral.cantidad());
    }

    @Test
    public void testIncrementarVariasVecesAumentaCorrectamente() {
        Recurso mineral = new Recurso(RecursoTipo.MINERAL);

        mineral.incrementar(3);
        mineral.incrementar((5));
        mineral.incrementar(2);
        mineral.incrementar(5);

        Assertions.assertEquals(15, mineral.cantidad());
    }

    @Test
    public void testDecrementarReduceLaCantidadCuandoHaySuficiente() {
        Recurso ladrillo = new Recurso(RecursoTipo.LADRILLO, 4);
        ladrillo.decrementar(2);
        Assertions.assertEquals(2, ladrillo.cantidad());
    }

    @Test
    public void testDecrementarLanzaExcepcionCuandoNoHaySuficiente() {
        Recurso lana = new Recurso(RecursoTipo.LANA, 1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            lana.decrementar(3);
        });
    }

    @Test
    public void testDecrementarVariasVecesReduceLaCantidadCorrectamente() {
        Recurso ladrillo = new Recurso(RecursoTipo.LADRILLO, 10);

        ladrillo.decrementar(2);
        ladrillo.decrementar(1);
        ladrillo.decrementar(3);
        ladrillo.decrementar(1);
        ladrillo.decrementar(2);

        Assertions.assertEquals(1, ladrillo.cantidad());
    }

    @Test
    public void testIncrementarYDecrementar() {
        Recurso lana = new Recurso(RecursoTipo.LANA);
        lana.incrementar((5));
        lana.incrementar((5));
        lana.decrementar(2);
        lana.incrementar(3);
        lana.decrementar(4);

        Assertions.assertEquals(7, lana.cantidad());
    }
    
    @Test
    public void recursoTransfiereUnaCartaEntreJugadores() {
        Recurso madera = new Recurso(RecursoTipo.MADERA, 3);
        Jugador destino = mock(Jugador.class);

        madera.transferirA(destino, 1);

        assertEquals(2, madera.cantidad());
        verify(destino, times(1)).recibirRecurso(RecursoTipo.MADERA, 1);
        verifyNoMoreInteractions(destino);
    }
}

