package edu.fiuba.algo3.testUnitarios;


import edu.fiuba.algo3.Excepciones.RecursoIncorrecto;
import edu.fiuba.algo3.modelo.Intercambio.*;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;
import edu.fiuba.algo3.modelo.Jugador;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class BancoTest {

	package edu.fiuba.algo3.testUnitarios;

	import edu.fiuba.algo3.modelo.Banco;
	import edu.fiuba.algo3.modelo.Intercambio.Puerto;
	import edu.fiuba.algo3.modelo.Intercambio.PuertoEspecifico;
	import edu.fiuba.algo3.modelo.Intercambio.PuertoGenerico;
	import edu.fiuba.algo3.modelo.Jugador;
	import edu.fiuba.algo3.modelo.Pieza.Poblado;
	import edu.fiuba.algo3.modelo.Recurso.*;
	import edu.fiuba.algo3.modelo.Tablero.Tablero;
	import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;
	import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.Test;

	import static org.junit.jupiter.api.Assertions.assertEquals;

	package edu.fiuba.algo3.testUnitarios;

	import edu.fiuba.algo3.modelo.Banco;
	import edu.fiuba.algo3.modelo.Intercambio.Puerto;
	import edu.fiuba.algo3.modelo.Intercambio.PuertoEspecifico;
	import edu.fiuba.algo3.modelo.Intercambio.PuertoGenerico;
	import edu.fiuba.algo3.modelo.Jugador;
	import edu.fiuba.algo3.modelo.Pieza.Poblado;
	import edu.fiuba.algo3.modelo.Recurso.*;
	import edu.fiuba.algo3.modelo.Tablero.Tablero;
	import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;
	import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.Test;

	import static org.junit.jupiter.api.Assertions.assertEquals;

	public class BancoTest {

	    private Banco banco;
	    private Tablero tablero;

	    @BeforeEach
	    public void setUp() {
	        banco = Banco.getInstance();
	        tablero = Tablero.getInstance();
	        tablero.reset();
	        tablero.crearGrafo();
	    }

	    private void darMuchosRecursos(Jugador jugador) {
	        jugador.recibirRecurso("MADERA", 20);
	        jugador.recibirRecurso("LADRILLO", 20);
	        jugador.recibirRecurso("LANA", 20);
	        jugador.recibirRecurso("GRANO", 20);
	        jugador.recibirRecurso("MINERAL", 20);
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

	        Recurso madera = jugador.buscarRecurso(new Madera());
	        Recurso ladrillo = jugador.buscarRecurso(new Ladrillo());
	        assertEquals(16, madera.cantidad());
	        assertEquals(21, ladrillo.cantidad());
	    }

	    /*
	     * test02: Jugador con puerto genérico → tasa 3:1
	     */
	    @Test
	    public void test02JugadorConPuertoGenericoIntercambiaConTasa3a1() {
	        Jugador jugador = new Jugador("Ana");
	        darMuchosRecursos(jugador);

	        UbicacionVertice v1 = new UbicacionVertice(1);
	        UbicacionVertice v2 = new UbicacionVertice(2);

	        Puerto puerto3a1 = new PuertoGenerico(v1, v2);
	        tablero.registrarPuerto(puerto3a1);

	        
	        Poblado poblado = new Poblado(jugador);
	        puerto3a1.notificarConstruccion(v1, poblado);

	        int tasa = banco.calcularTasaOptima(jugador, new Madera());
	        assertEquals(3, tasa);

	        banco.comercializar(jugador, new Madera(0), new Ladrillo(0), 1);

	        Recurso madera = jugador.buscarRecurso(new Madera());
	        Recurso ladrillo = jugador.buscarRecurso(new Ladrillo());

	        
	        assertEquals(17, madera.cantidad());
	        assertEquals(21, ladrillo.cantidad());
	    }

	    /*
	     * test03: Jugador con puerto específico Madera → tasa 2:1
	     */
	    @Test
	    public void test03JugadorConPuertoEspecificoMaderaObtieneTasa2a1() {
	        Jugador jugador = new Jugador("Ana");
	        darMuchosRecursos(jugador);

	        UbicacionVertice v1 = new UbicacionVertice(1);
	        UbicacionVertice v2 = new UbicacionVertice(2);

	        Puerto puertoMadera = new PuertoEspecifico(new Madera(), v1, v2);
	        tablero.registrarPuerto(puertoMadera);

	        Poblado poblado = new Poblado(jugador);
	        puertoMadera.notificarConstruccion(v1, poblado);

	        int tasa = banco.calcularTasaOptima(jugador, new Madera());
	        assertEquals(2, tasa);

	        banco.comercializar(jugador, new Madera(0), new Ladrillo(0), 1);

	        Recurso madera = jugador.buscarRecurso(new Madera());
	        Recurso ladrillo = jugador.buscarRecurso(new Ladrillo());

	       
	        assertEquals(18, madera.cantidad());
	        assertEquals(21, ladrillo.cantidad());
	    }

	    /*
	     * test04: Puerto específico de Lana no da beneficio al vender Madera → sigue 4:1
	     */
	    @Test
	    public void test04JugadorConPuertoEspecificoOvejaNoObtieneBeneficioAlVenderMadera() {
	        Jugador jugador = new Jugador("Ana");
	        darMuchosRecursos(jugador);

	        UbicacionVertice v1 = new UbicacionVertice(1);
	        UbicacionVertice v2 = new UbicacionVertice(2);

	        Puerto puertoLana = new PuertoEspecifico(new Lana(), v1, v2);
	        tablero.registrarPuerto(puertoLana);

	        Poblado poblado = new Poblado(jugador);
	        puertoLana.notificarConstruccion(v1, poblado);

	        int tasa = banco.calcularTasaOptima(jugador, new Madera());
	        assertEquals(4, tasa);

	        banco.comercializar(jugador, new Madera(0), new Ladrillo(0), 1);

	        Recurso madera = jugador.buscarRecurso(new Madera());
	        Recurso ladrillo = jugador.buscarRecurso(new Ladrillo());

	        assertEquals(16, madera.cantidad());
	        assertEquals(21, ladrillo.cantidad());
	    }

	    /*
	     * test05: Puerto genérico + específico correcto → elige la mejor opción (2:1)
	     */
	    @Test
	    public void test05JugadorConPuertoGenericoYPuertoEspecificoEligeLaMejorOpcion() {
	        Jugador jugador = new Jugador("Ana");
	        darMuchosRecursos(jugador);

	        UbicacionVertice v1 = new UbicacionVertice(1);
	        UbicacionVertice v2 = new UbicacionVertice(2);
	        UbicacionVertice v3 = new UbicacionVertice(3);
	        UbicacionVertice v4 = new UbicacionVertice(4);

	        Puerto puertoGenerico = new PuertoGenerico(v1, v2);
	        Puerto puertoMadera = new PuertoEspecifico(new Madera(), v3, v4);

	        tablero.registrarPuerto(puertoGenerico);
	        tablero.registrarPuerto(puertoMadera);

	        Poblado poblado = new Poblado(jugador);
	        puertoGenerico.notificarConstruccion(v1, poblado);
	        puertoMadera.notificarConstruccion(v3, poblado);

	        int tasa = banco.calcularTasaOptima(jugador, new Madera());
	        assertEquals(2, tasa);

	        banco.comercializar(jugador, new Madera(0), new Ladrillo(0), 1);

	        Recurso madera = jugador.buscarRecurso(new Madera());
	        Recurso ladrillo = jugador.buscarRecurso(new Ladrillo());

	    
	        assertEquals(18, madera.cantidad());
	        assertEquals(21, ladrillo.cantidad());
	    }

	    /*
	     * test06: Puerto genérico + específico ajeno (Lana) → usa el genérico (3:1)
	     */
	    @Test
	    public void test06JugadorConPuertoGenericoYEspecificoAjenoUsaElGenerico() {
	        Jugador jugador = new Jugador("Ana");
	        darMuchosRecursos(jugador);

	        UbicacionVertice v1 = new UbicacionVertice(1);
	        UbicacionVertice v2 = new UbicacionVertice(2);
	        UbicacionVertice v3 = new UbicacionVertice(3);
	        UbicacionVertice v4 = new UbicacionVertice(4);

	        Puerto puertoGenerico = new PuertoGenerico(v1, v2);
	        Puerto puertoLana = new PuertoEspecifico(new Lana(), v3, v4);

	        tablero.registrarPuerto(puertoGenerico);
	        tablero.registrarPuerto(puertoLana);

	        Poblado poblado = new Poblado(jugador);
	        puertoGenerico.notificarConstruccion(v1, poblado);
	        puertoLana.notificarConstruccion(v3, poblado);

	        int tasa = banco.calcularTasaOptima(jugador, new Madera());
	        assertEquals(3, tasa);

	        banco.comercializar(jugador, new Madera(0), new Ladrillo(0), 1);

	        Recurso madera = jugador.buscarRecurso(new Madera());
	        Recurso ladrillo = jugador.buscarRecurso(new Ladrillo());

	        assertEquals(17, madera.cantidad());
	        assertEquals(21, ladrillo.cantidad());
	    }

	    /*
	     * test07: Intercambio por mayor cantidad escala correctamente (2:1 * 5 = 10)
	     */
	    @Test
	    public void test07IntercambioPorMayorCantidadEscalaCorrectamente() {
	        Jugador jugador = new Jugador("Ana");
	        darMuchosRecursos(jugador);

	        UbicacionVertice v1 = new UbicacionVertice(1);
	        UbicacionVertice v2 = new UbicacionVertice(2);

	        Puerto puertoMadera = new PuertoEspecifico(new Madera(), v1, v2);
	        tablero.registrarPuerto(puertoMadera);

	        Poblado poblado = new Poblado(jugador);
	        puertoMadera.notificarConstruccion(v1, poblado);

	        int tasa = banco.calcularTasaOptima(jugador, new Madera());
	        assertEquals(2, tasa);

	        banco.comercializar(jugador, new Madera(0), new Ladrillo(0), 5);

	        Recurso madera = jugador.buscarRecurso(new Madera());
	        Recurso ladrillo = jugador.buscarRecurso(new Ladrillo());

	        assertEquals(10, madera.cantidad());
	        assertEquals(25, ladrillo.cantidad());
	    }
	}
