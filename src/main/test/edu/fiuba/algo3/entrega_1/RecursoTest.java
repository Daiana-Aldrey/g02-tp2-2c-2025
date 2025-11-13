package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Recurso;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class RecursoTest{

    @Test
    public void sosTipodevuelveTrueAlCoincidirRecurso(){
        Recurso madera = new Recurso("MADERA");
        boolean esMadera = madera.sosTipo("MADERA");
        Assertions.assertTrue(esMadera);
    }

    @Test
    public void sosTipodevuelveFalseAlNoCoincidirRecurso(){
        Recurso madera = new Recurso("MADERA");
        boolean esMadera = madera.sosTipo("LADRILLO");
        Assertions.assertTrue(esMadera);
    }

    @Test
    public void testIncrementarAumentaLaCantidadDeRecursoCreadoConCAntCero() {
        Recurso mineral = new Recurso("MINERAL");
        mineral.incrementar(3);
        Assertions.assertEquals(3, mineral.cantidad());
    }

    @Test
    public void testIncrementarAumentaLaCantidadDeRecursoCreado() {
        Recurso mineral = new Recurso("MINERAL", 2);
        mineral.incrementar(3);
        Assertions.assertEquals(5, mineral.cantidad());
    }

    @Test
    public void testIncrementarVariasVecesAumentaCorrectamente() {
        Recurso mineral = new Recurso("MINERAL");

        mineral.incrementar(3);
        mineral.incrementar((5));
        mineral.incrementar(2);
        mineral.incrementar(5);

        Assertions.assertEquals(15, mineral.cantidad());
    }

    @Test
    public void testDecrementarReduceLaCantidadCuandoHaySuficiente() {
        Recurso ladrillo = new Recurso("LADRILLO", 4);
        ladrillo.decrementar(2);
        Assertions.assertEquals(2, ladrillo.cantidad());
    }

    @Test
    public void testDecrementarLanzaExcepcionCuandoNoHaySuficiente() {
        Recurso lana = new Recurso("LANA", 1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            lana.decrementar(3);
        });
    }

    @Test
    public void testDecrementarVariasVecesReduceLaCantidadCorrectamente() {
        Recurso ladrillo = new Recurso("LADRILLO", 10);

        ladrillo.decrementar(2);
        ladrillo.decrementar(1);
        ladrillo.decrementar(3);
        ladrillo.decrementar(1);
        ladrillo.decrementar(2);

        Assertions.assertEquals(1, ladrillo.cantidad());
    }

    @Test
    public void testIncrementarYDecrementar() {
        Recurso lana = new Recurso("LANA");
        lana.incrementar((5));
        lana.incrementar((5));
        lana.decrementar(2);
        lana.incrementar(3);
        lana.decrementar(4);

        Assertions.assertEquals(7, lana.cantidad());
    }
}
