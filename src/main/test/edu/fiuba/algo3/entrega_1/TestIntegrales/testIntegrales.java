package edu.fiuba.algo3.entrega_1.TestIntegrales;

import edu.fiuba.algo3.controllers.Juego;
import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class testIntegrales {

    public void colocacionCorrectaDeHexagonosAleatorios(){

    }

    @Test
    public void validacionReglaDeLaDistanciaAlcolocarPobladosIniciales() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();
        tablero.crearGrafo();

        Jugador luis = new Jugador("Luis");
        Jugador federico = new Jugador("Federico");
        Jugador lucas = new Jugador("Lucas");
        Jugador ricardo = new Jugador("Ricardo");
        Jugador fabiano = new Jugador("Fabiano");

        luis.colocarPiezaInicial("poblado", new ArrayList<>(List.of(1)));
        Assertions.assertTrue(tablero.hayPieza(List.of(1)));

        luis.colocarPiezaInicial("camino", new ArrayList<>(List.of(1, 2)));

        lucas.colocarPiezaInicial("poblado", new ArrayList<>(List.of(5)));
        Assertions.assertTrue(tablero.hayPieza(List.of(5)));

        lucas.colocarPiezaInicial("camino", new ArrayList<>(List.of(5,6)));

        assertThrows(IllegalArgumentException.class, () -> {
            federico.colocarPiezaInicial("poblado", new ArrayList<>(List.of(6)));;
        });
        assertThrows(IllegalArgumentException.class, () -> {
            ricardo.colocarPiezaInicial("poblado", new ArrayList<>(List.of(2)));;
        });
        assertThrows(IllegalArgumentException.class, () -> {
            fabiano.colocarPiezaInicial("poblado", new ArrayList<>(List.of(1)));;
        });
    }

    @Test
    public void jugadoresRecibenRecursosPorSusSegundosPoblados() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();
        tablero.crearGrafo();
        GeneradorDeDados dado = () -> 8;

        Juego juego = new Juego(3, List.of("Luis", "Ana", "Marcos"), dado);
        Jugador j1 = juego.jugadores().get(0);
        Jugador j2 = juego.jugadores().get(1);
        Jugador j3 = juego.jugadores().get(2);

        for (Jugador j : juego.jugadores()) {
            j.recibirRecurso(RecursoTipo.MADERA,   10);
            j.recibirRecurso(RecursoTipo.LADRILLO, 10);
            j.recibirRecurso(RecursoTipo.LANA,     10);
            j.recibirRecurso(RecursoTipo.GRANO,    10);
        }

        Terreno montaniaA = new Montania(8);
        Terreno montaniaB = new Montania(8);

        tablero.registrarTerreno('A', montaniaA, List.of(10));
        tablero.registrarTerreno('B', montaniaB, List.of(40));


        j1.colocarPiezaInicial("poblado", List.of(1));
        j1.colocarPiezaInicial("camino",  List.of(1, 2));
        j1.construirPieza("poblado", List.of(10));

        j2.colocarPiezaInicial("poblado", List.of(54));
        j2.colocarPiezaInicial("camino",  List.of(54, 53));
        j2.construirPieza("poblado", List.of(40));


        j3.colocarPiezaInicial("poblado", List.of(6));
        j3.colocarPiezaInicial("camino",  List.of(6, 7));
        j3.construirPieza("poblado", List.of(36));

        int tirada = juego.tirarDado();
        juego.manejarTirada(tirada);

        Recurso mineralJ1 = j1.buscarRecurso(RecursoTipo.MINERAL);
        Recurso mineralJ2 = j2.buscarRecurso(RecursoTipo.MINERAL);

        assertEquals(1, mineralJ1.cantidad());
        assertEquals(1, mineralJ2.cantidad());


        Recurso mineralJ3 = j3.buscarRecurso(RecursoTipo.MINERAL);
        assertTrue(mineralJ3.cantidad() == 0);
    }


    @Test
    void cuandoDa12_esValidoYDevuelve12() {
        var juego = new Juego(3, List.of("A","B","C"),()-> 12);
        int n = juego.tirarDado();
        assertEquals(12, n);
    }
    @Test
    public void jugadorRecibeUnRecursoPorPobladoCuandoCorresponde() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();
        tablero.crearGrafo();
        GeneradorDeDados dado = () -> 8;

        Juego juego = new Juego(3, List.of("Luis", "Ana", "Marcos"), dado);
        Jugador jugador = juego.jugadores().get(0);

        Terreno montania = new Montania(8);
        tablero.registrarTerreno('A',montania, List.of(10));

        jugador.colocarPiezaInicial("poblado", List.of(10));

        int tirada = juego.tirarDado();
        juego.manejarTirada(tirada);

        Recurso mineral = jugador.buscarRecurso(RecursoTipo.MINERAL);
        assertEquals(1, mineral.cantidad());
    }

    @Test
    public void jugadorRecibeDosRecursosPorCiudadCuandoCorresponde() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();
        tablero.crearGrafo();
        GeneradorDeDados dado = () -> 8;

        Juego juego = new Juego(3, List.of("Luis", "Ana", "Marcos"), dado);
        Jugador jugador = juego.jugadores().get(0);

        Terreno montania = new Montania(8);
        tablero.registrarTerreno('A',montania, List.of(10));

        jugador.colocarPiezaInicial("poblado", List.of(10));
        jugador.construirPieza("ciudad", List.of(10));

        int tirada = juego.tirarDado();
        juego.manejarTirada(tirada);

        Recurso mineral = jugador.buscarRecurso(RecursoTipo.MINERAL);
        assertEquals(2, mineral.cantidad());
    }
    @Test
    public void terrenoConLadronNoProduceRecursos() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();
        tablero.crearGrafo();

        Terreno bosque = new Bosque(8);
        tablero.registrarTerreno('X', bosque, List.of(1,2,3,4,5,6));
        VerticeTerreno vt = tablero.buscarVerticeTerreno('X');

        Jugador jugador = new Jugador("Jugador");
        Pieza poblado = new Poblado(jugador);
        vt.agregarEdificio(poblado);

        tablero.moverLadronA('X', jugador);

        int antes = jugador.cantidadDeCartas();
        tablero.cosechar(6);
        int despues = jugador.cantidadDeCartas();

        assertEquals(antes, despues,
                "Un terreno con el Ladrón NO debe producir recursos");
    }

    @Test
    public void jugadorDescartaLaMitadDeCartasSiSale7yTieneMasDe7Cartas() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();
        tablero.crearGrafo();

        GeneradorDeDados dado = () -> 7;

        Juego juego = new Juego(3, List.of("lu", "gia", "da"), dado);
        Jugador jugador = juego.jugadores().get(0);

        jugador.recibirRecurso(RecursoTipo.MADERA, 5);
        jugador.recibirRecurso(RecursoTipo.LADRILLO, 4);

        assertEquals(9, jugador.cantidadDeCartas(), "Precondición: debe tener 9 cartas");

        int tirada = juego.tirarDado();
        juego.manejarTirada(tirada);

        assertEquals(5, jugador.cantidadDeCartas(),
                "Después de tirar 7, descarta la mitad y queda con 5 cartas");
    }

    @Test
    public void jugadorActivoMueveAlLadronYRobaCartaAJugadorAdyacenteANuevoTerreno() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();
        GeneradorDeDados generador = () -> 7;
        Juego juego = new Juego(3, List.of("Luis", "Ana", "Marcos"), generador);

        Jugador jugadorActivo = juego.jugadores().get(0);
        jugadorActivo.recibirRecurso(RecursoTipo.MADERA, 10);
        jugadorActivo.recibirRecurso(RecursoTipo.LADRILLO, 10);
        jugadorActivo.recibirRecurso(RecursoTipo.LANA, 10);
        jugadorActivo.recibirRecurso(RecursoTipo.GRANO, 10);

        Jugador jugadorVictima = juego.jugadores().get(1);

        jugadorVictima.recibirRecurso(RecursoTipo.MADERA, 1);
        jugadorVictima.recibirRecurso(RecursoTipo.LADRILLO, 1);
        jugadorVictima.recibirRecurso(RecursoTipo.LANA, 1);
        jugadorVictima.recibirRecurso(RecursoTipo.GRANO, 1);

        jugadorVictima.recibirRecurso(RecursoTipo.MADERA, 1);
        jugadorVictima.construirPieza("poblado", List.of(4));

        int cartasAntesVictima = jugadorVictima.cantidadDeCartas();
        int cartasAntesActivo = jugadorActivo.cantidadDeCartas();

        jugadorActivo.moverLadron('B');

        assertEquals(cartasAntesVictima - 1, jugadorVictima.cantidadDeCartas(),
                "La víctima debería tener una carta menos después del robo");
        assertEquals(cartasAntesActivo + 1, jugadorActivo.cantidadDeCartas(),
                "El jugador activo debería tener una carta más después del robo");
    }






}
