package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Recurso.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;



public class RecursoTest{
    @Test
    public void testIncrementarAumentaLaCantidadDeRecursoCreadoConCAntCero() {
        Recurso mineral = new Mineral();
        mineral.incrementar(3);
        Assertions.assertEquals(3, mineral.cantidad());
    }

    @Test
    public void testIncrementarAumentaLaCantidadDeRecursoCreado() {
        Recurso mineral = new Mineral(2);
        mineral.incrementar(3);
        Assertions.assertEquals(5, mineral.cantidad());
    }

    @Test
    public void testIncrementarVariasVecesAumentaCorrectamente() {
        Recurso mineral = new Mineral();

        mineral.incrementar(3);
        mineral.incrementar((5));
        mineral.incrementar(2);
        mineral.incrementar(5);

        Assertions.assertEquals(15, mineral.cantidad());
    }

    @Test
    public void testDecrementarReduceLaCantidadCuandoHaySuficiente() {
        Recurso ladrillo = new Ladrillo(4);
        ladrillo.decrementar(2);
        Assertions.assertEquals(2, ladrillo.cantidad());
    }

    @Test
    public void testDecrementarLanzaExcepcionCuandoNoHaySuficiente() {
        Recurso lana = new Lana(1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            lana.decrementar(3);
        });
    }

    @Test
    public void testDecrementarVariasVecesReduceLaCantidadCorrectamente() {
        Recurso ladrillo = new Ladrillo(10);

        ladrillo.decrementar(2);
        ladrillo.decrementar(1);
        ladrillo.decrementar(3);
        ladrillo.decrementar(1);
        ladrillo.decrementar(2);

        Assertions.assertEquals(1, ladrillo.cantidad());
    }

    @Test
    public void testIncrementarYDecrementar() {
        Recurso lana = new Lana();
        lana.incrementar((5));
        lana.incrementar((5));
        lana.decrementar(2);
        lana.incrementar(3);
        lana.decrementar(4);

        Assertions.assertEquals(7, lana.cantidad());
    }
    
    @Test
    public void recursoTransfiereUnaCartaEntreJugadores() {
        Recurso madera = new Madera(3);
        Jugador destino = mock(Jugador.class);

        madera.transferirA(destino, 1);

        assertEquals(2, madera.cantidad());
        verify(destino, times(1)).recibirRecurso(new Madera(), 1);
        verifyNoMoreInteractions(destino);
    }
    
    @Test
    public void cobrarDeLlamaEntregarEnPagadorConTipoYCantidadCorrectos() {
        Jugador pagador  = mock(Jugador.class);
        Jugador receptor = mock(Jugador.class);

        Recurso recursoPedido = new Ladrillo(3);
        recursoPedido.cobrarDe(pagador, receptor);

        verify(pagador).entregar(new Ladrillo(3), 3, receptor);
        verifyNoMoreInteractions(pagador, receptor);
    }
}

