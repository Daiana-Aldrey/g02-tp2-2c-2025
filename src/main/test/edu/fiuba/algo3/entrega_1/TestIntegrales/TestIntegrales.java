package edu.fiuba.algo3.entrega_1.TestIntegrales;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Dados.*;
import edu.fiuba.algo3.modelo.Pieza.*;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.*;
import edu.fiuba.algo3.modelo.Terreno.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class TestIntegrales {

    @Test
    public void colocacionCorrectaDeHexagonosAleatorios(){
        GeneradorNumerosAleatorios generadorMock = mock(GeneradorNumerosAleatorios.class);
        Grafo grafo = new Grafo();
        GeneradorDeTablero genTablero = new GeneradorDeTablero(generadorMock);

        when(generadorMock.generarEnRangoDesdeCero(anyInt())).thenReturn(11,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0);
        genTablero.generarEstructura(grafo);
        genTablero.generarTerrenos(grafo);

        VerticeTerreno v1 = grafo.buscarVertice('A');
        VerticeTerreno v2 = grafo.buscarVertice('B');
        VerticeTerreno v3 = grafo.buscarVertice('C');
        VerticeTerreno v4 = grafo.buscarVertice('D');
        VerticeTerreno v5 = grafo.buscarVertice('E');
        VerticeTerreno v6 = grafo.buscarVertice('F');
        VerticeTerreno v7 = grafo.buscarVertice('G');
        VerticeTerreno v8 = grafo.buscarVertice('H');
        VerticeTerreno v9 = grafo.buscarVertice('I');
        VerticeTerreno v10 = grafo.buscarVertice('J');
        VerticeTerreno v11 = grafo.buscarVertice('K');
        VerticeTerreno v12 = grafo.buscarVertice('L');
        VerticeTerreno v13 = grafo.buscarVertice('M');
        VerticeTerreno v14 = grafo.buscarVertice('N');
        VerticeTerreno v15 = grafo.buscarVertice('O');
        VerticeTerreno v16 = grafo.buscarVertice('P');
        VerticeTerreno v17 = grafo.buscarVertice('Q');
        VerticeTerreno v18 = grafo.buscarVertice('R');
        VerticeTerreno v19 = grafo.buscarVertice('S');

        assertTrue(v1.tieneTerreno("Montania"));
        assertTrue(v1.tieneFichaDeNumero(2));

        assertTrue(v2.tieneTerreno("Colina"));
        assertTrue(v2.tieneFichaDeNumero(6));

        assertTrue(v3.tieneTerreno("Colina"));
        assertTrue(v3.tieneFichaDeNumero(3));

        assertTrue(v4.tieneTerreno("Bosque"));
        assertTrue(v4.tieneFichaDeNumero(8));

        assertTrue(v5.tieneTerreno("Bosque"));
        assertTrue(v5.tieneFichaDeNumero(10));

        assertTrue(v6.tieneTerreno("Bosque"));
        assertTrue(v6.tieneFichaDeNumero(9));

        assertTrue(v7.tieneTerreno("Bosque"));
        assertTrue(v7.tieneFichaDeNumero(12));

        assertTrue(v8.tieneTerreno("Campo"));
        assertTrue(v8.tieneFichaDeNumero(11));

        assertTrue(v9.tieneTerreno("Desierto"));
        assertTrue(v9.tieneFichaDeNumero(0));

        assertTrue(v10.tieneTerreno("Campo"));
        assertTrue(v10.tieneFichaDeNumero(4));

        assertTrue(v11.tieneTerreno("Campo"));
        assertTrue(v11.tieneFichaDeNumero(5));

        assertTrue(v12.tieneTerreno("Campo"));
        assertTrue(v12.tieneFichaDeNumero(10));

        assertTrue(v13.tieneTerreno("Montania"));
        assertTrue(v13.tieneFichaDeNumero(9));

        assertTrue(v14.tieneTerreno("Montania"));
        assertTrue(v14.tieneFichaDeNumero(4));

        assertTrue(v15.tieneTerreno("Pastizal"));
        assertTrue(v15.tieneFichaDeNumero(5));

        assertTrue(v16.tieneTerreno("Pastizal"));
        assertTrue(v16.tieneFichaDeNumero(6));

        assertTrue(v17.tieneTerreno("Pastizal"));
        assertTrue(v17.tieneFichaDeNumero(3));

        assertTrue(v18.tieneTerreno("Pastizal"));
        assertTrue(v18.tieneFichaDeNumero(11));

        assertTrue(v19.tieneTerreno("Colina"));
        assertTrue(v19.tieneFichaDeNumero(8));
    }

    @Test
    public void validacionReglaDeLaDistanciaAlcolocarPobladosIniciales() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();

        Jugador luis = new Jugador("Luis");
        Jugador federico = new Jugador("Federico");
        Jugador lucas = new Jugador("Lucas");
        Jugador ricardo = new Jugador("Ricardo");
        Jugador fabiano = new Jugador("Fabiano");

        luis.colocarPiezaInicial("poblado", new ArrayList<>(List.of(1)));
        luis.colocarPiezaInicial("camino", new ArrayList<>(List.of(1, 2)));

        lucas.colocarPiezaInicial("poblado", new ArrayList<>(List.of(5)));
        lucas.colocarPiezaInicial("camino", new ArrayList<>(List.of(5,6)));

        Assertions.assertTrue(tablero.hayPieza(List.of(1)));
        Assertions.assertTrue(tablero.hayPieza(List.of(5)));

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
        Grafo grafo = new Grafo();

        GeneradorDeTablero generador = new GeneradorDeTablero(new GeneradorNumerosAleatorios());
        generador.generarEstructura(grafo);
        grafo.agregarVertice('A', new Montania(), 8);
        grafo.agregarVertice('B', new Montania(), 8);
        grafo.agregarArista(10,'A');
        grafo.agregarArista(40,'B');

        tablero.setearGrafo(grafo);

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
        Grafo grafo = new Grafo();

        GeneradorDeTablero generador = new GeneradorDeTablero(new GeneradorNumerosAleatorios());
        generador.generarEstructura(grafo);
        grafo.agregarVertice('A', new Montania(), 8);
        grafo.agregarArista(10,'A');

        tablero.setearGrafo(grafo);

        GeneradorDeDados dado = () -> 8;

        Juego juego = new Juego(3, List.of("Luis", "Ana", "Marcos"), dado);
        Jugador jugador = juego.jugadores().get(0);

        jugador.colocarPiezaInicial("poblado", List.of(10));

        int tirada = juego.tirarDado();
        juego.manejarTirada(tirada);

        Recurso mineral = jugador.buscarRecurso(RecursoTipo.MINERAL);
        assertEquals(1, mineral.cantidad());
    }

    @Test
    public void jugadorRecibeDosRecursosPorCiudadCuandoCorresponde() {
        Tablero tablero = Tablero.getInstance();
        Grafo grafo = new Grafo();

        GeneradorDeTablero generador = new GeneradorDeTablero(new GeneradorNumerosAleatorios());
        generador.generarEstructura(grafo);
        grafo.agregarVertice('A', new Bosque(), 8);
        grafo.agregarArista(10,'A');

        tablero.setearGrafo(grafo);

        GeneradorDeDados dado = () -> 8;

        Juego juego = new Juego(3, List.of("Luis", "Ana", "Marcos"), dado);
        Jugador jugador = juego.jugadores().get(0);

        jugador.colocarPiezaInicial("poblado", List.of(10));
        jugador.recibirRecurso(RecursoTipo.GRANO, 2);
        jugador.recibirRecurso(RecursoTipo.MINERAL, 3);
        jugador.construirPieza("ciudad", List.of(10));

        int tirada = juego.tirarDado();
        juego.manejarTirada(tirada);

        Recurso madera = jugador.buscarRecurso(RecursoTipo.MADERA);
        assertEquals(2, madera.cantidad());
    }

    @Test
    public void terrenoConLadronNoProduceRecursos() {
        Tablero tablero = Tablero.getInstance();
        Grafo grafo = new Grafo();

        GeneradorDeTablero generador = new GeneradorDeTablero(new GeneradorNumerosAleatorios());
        generador.generarEstructura(grafo);
        grafo.agregarVertice('X', new Bosque(), 8);
        grafo.agregarArista(1,'X');
        grafo.agregarArista(2,'X');
        grafo.agregarArista(3,'X');
        grafo.agregarArista(9,'X');
        grafo.agregarArista(10,'X');
        grafo.agregarArista(11,'X');

        tablero.setearGrafo(grafo);

        Jugador jugador = new Jugador("Jugador");

        Pieza poblado = new Poblado(jugador);

        tablero.colocarEdificio(1, poblado);

        tablero.moverLadronA('X', jugador);

        int antes = jugador.cantidadDeCartas();
        tablero.cosechar(8);
        int despues = jugador.cantidadDeCartas();

        assertEquals(antes, despues,
                "Un terreno con el Ladrón NO debe producir recursos");
    }

    @Test
    public void jugadorDescartaLaMitadDeCartasSiSale7yTieneMasDe7Cartas() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();

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
