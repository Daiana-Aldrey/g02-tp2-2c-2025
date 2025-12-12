package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Ubicacion.Ubicacion;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.modelo.Tablero.*;
import edu.fiuba.algo3.modelo.Intercambio.*;
import edu.fiuba.algo3.modelo.Pieza.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BancoTest {

    private Banco banco;
    private Tablero tablero;

 
    private static final String POBLADO = "poblado";

    @BeforeEach
    public void setUp() {
        banco = Banco.getInstance();
        tablero = Tablero.getInstance();
        tablero.reset();
    }

    private void darMuchosRecursos(Jugador jugador) {
        jugador.recibirRecurso(new Madera(), 20);
        jugador.recibirRecurso(new Ladrillo(), 20);
        jugador.recibirRecurso(new Lana(), 20);
        jugador.recibirRecurso(new Grano(), 20);
        jugador.recibirRecurso(new Mineral(), 20);
    }

    private void construirPobladoEn(Jugador jugador, int numeroVertice) {
        Poblado poblado = new Poblado(jugador);
        poblado.colocarPrimera(List.of(new UbicacionVertice(numeroVertice)));
    }

    /*
     * test01: Jugador sin puertos → tasa bancaria estándar 4:1
     */
    @Test
    public void test01JugadorSinPuertosIntercambiaConTasaBancariaEstandar4a1() {
        Jugador jugador = new Jugador("Ana");
        darMuchosRecursos(jugador);

        int tasa = banco.calcularTasaOptima(jugador, new Madera());
        assertEquals(4, tasa);

        banco.comercializar(jugador, new Madera(0), new Ladrillo(0), 1);

        assertEquals(16, jugador.buscarRecurso(new Madera()).cantidad());
        assertEquals(21, jugador.buscarRecurso(new Ladrillo()).cantidad());
    }

    /*
     * test02: Jugador con puerto genérico → tasa 3:1
     * Puerto genérico real: (4,5)
     */
    @Test
    public void test02JugadorConPuertoGenericoIntercambiaConTasa3a1() {
        Jugador jugador = new Jugador("Ana");
        darMuchosRecursos(jugador);

        construirPobladoEn(jugador, 4); // vértice real del puerto genérico (4,5)

        int tasa = banco.calcularTasaOptima(jugador, new Madera());
        assertEquals(3, tasa);

        banco.comercializar(jugador, new Madera(0), new Ladrillo(0), 1);

        assertEquals(17, jugador.buscarRecurso(new Madera()).cantidad());
        assertEquals(21, jugador.buscarRecurso(new Ladrillo()).cantidad());
    }

    /*
     * test03: Jugador con puerto específico Madera → tasa 2:1
     * Puerto madera real: (1,2)
     */
    @Test
    public void test03JugadorConPuertoEspecificoMaderaObtieneTasa2a1() {
        Jugador jugador = new Jugador("Ana");
        darMuchosRecursos(jugador);

        construirPobladoEn(jugador, 1); // vértice real del puerto madera (1,2)

        int tasa = banco.calcularTasaOptima(jugador, new Madera());
        assertEquals(2, tasa);

        banco.comercializar(jugador, new Madera(0), new Ladrillo(0), 1);

        assertEquals(18, jugador.buscarRecurso(new Madera()).cantidad());
        assertEquals(21, jugador.buscarRecurso(new Ladrillo()).cantidad());
    }

    /*
     * test04: Puerto específico de Lana no da beneficio al vender Madera → sigue 4:1
     * Puerto lana real: (15,16)
     */
    @Test
    public void test04JugadorConPuertoEspecificoOvejaNoObtieneBeneficioAlVenderMadera() {
        Jugador jugador = new Jugador("Ana");
        darMuchosRecursos(jugador);

        construirPobladoEn(jugador, 15); // puerto lana (15,16)

        int tasa = banco.calcularTasaOptima(jugador, new Madera());
        assertEquals(4, tasa);

        banco.comercializar(jugador, new Madera(0), new Ladrillo(0), 1);

        assertEquals(16, jugador.buscarRecurso(new Madera()).cantidad());
        assertEquals(21, jugador.buscarRecurso(new Ladrillo()).cantidad());
    }

    /*
     * test05: Puerto genérico + específico correcto → elige la mejor opción (2:1)
     * Genérico (4,5) y Madera (1,2)
     */
    @Test
    public void test05JugadorConPuertoGenericoYPuertoEspecificoEligeLaMejorOpcion() {
        Jugador jugador = new Jugador("Ana");
        darMuchosRecursos(jugador);

        construirPobladoEn(jugador, 4); // genérico
        construirPobladoEn(jugador, 1); // madera

        int tasa = banco.calcularTasaOptima(jugador, new Madera());
        assertEquals(2, tasa);

        banco.comercializar(jugador, new Madera(0), new Ladrillo(0), 1);

        assertEquals(18, jugador.buscarRecurso(new Madera()).cantidad());
        assertEquals(21, jugador.buscarRecurso(new Ladrillo()).cantidad());
    }

    /*
     * test06: Puerto genérico + específico ajeno (Lana) → usa el genérico (3:1)
     * Genérico (4,5) y Lana (15,16)
     */
    @Test
    public void test06JugadorConPuertoGenericoYEspecificoAjenoUsaElGenerico() {
        Jugador jugador = new Jugador("Ana");
        darMuchosRecursos(jugador);

        construirPobladoEn(jugador, 4);   
        construirPobladoEn(jugador, 15); 

        int tasa = banco.calcularTasaOptima(jugador, new Madera());
        assertEquals(3, tasa);

        banco.comercializar(jugador, new Madera(0), new Ladrillo(0), 1);

        assertEquals(17, jugador.buscarRecurso(new Madera()).cantidad());
        assertEquals(21, jugador.buscarRecurso(new Ladrillo()).cantidad());
    }

    /*
     * test07: Intercambio por mayor cantidad escala correctamente (2:1 * 5 = 10)
     * Puerto madera (1,2)
     */
    @Test
    public void test07IntercambioPorMayorCantidadEscalaCorrectamente() {
        Jugador jugador = new Jugador("Ana");
        darMuchosRecursos(jugador);

        construirPobladoEn(jugador, 1); 

        int tasa = banco.calcularTasaOptima(jugador, new Madera());
        assertEquals(2, tasa);

        banco.comercializar(jugador, new Madera(0), new Ladrillo(0), 5);

        assertEquals(10, jugador.buscarRecurso(new Madera()).cantidad());
        assertEquals(25, jugador.buscarRecurso(new Ladrillo()).cantidad());
    }
}

