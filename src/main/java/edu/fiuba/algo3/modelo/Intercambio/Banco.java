package edu.fiuba.algo3.modelo.Intercambio;

import edu.fiuba.algo3.modelo.CartaDeDesarrollo.*;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.Excepciones.NoTieneCarta;
import edu.fiuba.algo3.modelo.Jugador.*;
import edu.fiuba.algo3.Excepciones.*;

import java.util.*;

public class Banco implements Comercializar{
    private final Deque<Carta> mazoDesarrollo;
    private int tasaBancaria;
    
    private static final Banco INSTANCE = new Banco();

    public Banco(FabricaMazoCartasDesarrollo fabrica) {
        this.mazoDesarrollo = new ArrayDeque<>(fabrica.crearMazoDesarrollo());
        this.tasaBancaria = 4;
    }
    
    public Banco(){
        this (new MazoCartasDesarrollo());
    }

    public static Banco getInstance() {
    	return INSTANCE;
    }

    public Carta venderCartaDesarrollo(Jugador jugador) {
        if (mazoDesarrollo.isEmpty()) {
            throw new NoTieneCarta("No hay más cartas.");
        }
      
        List<Recurso> precio = List.of(
                new Lana(1),
                new Grano(1),
                new Mineral(1)
        );
        jugador.pagarRecursos(precio);
        Carta carta = mazoDesarrollo.pop();
        jugador.recibirCartaDesarrollo(carta);

        return carta;
    }

    @Override
    public void comercializar(Jugador jugador, Recurso recursoOferta, Recurso recursoPedido, int cantPedida) {
        int tasa = calcularTasaOptima(jugador, recursoOferta);
        int costo = tasa * cantPedida;
        intercambiar(jugador, recursoOferta, recursoPedido, cantPedida, costo);
    }

    public void intercambiar(Jugador jugador,Recurso recursoOferta, Recurso recursoPedido, int cantPedida, int costo) {
    	Recurso recursoADescontar = recursoOferta.crearCon(costo);
        Recurso recursoAEntregar = recursoPedido.crearCon(cantPedida);
        jugador.descontarRecurso(recursoADescontar, costo);
        jugador.recibirRecurso(recursoAEntregar, cantPedida);
    }
    

    public int calcularTasaOptima(Jugador jugador, Recurso recursoOferta) {
        int mejorTasa = this.tasaBancaria; 

        for (Puerto puerto : jugador.getPuertos()) {
            try {
                int tasaDelPuerto = puerto.tasaDeCambioPara(recursoOferta);
                if (tasaDelPuerto < mejorTasa) 
                    mejorTasa = tasaDelPuerto;
                  
            } catch (RecursoIncorrecto e) {
            }
        }
        return mejorTasa;
    }
}