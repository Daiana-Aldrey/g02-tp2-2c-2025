package edu.fiuba.algo3.modelo;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Jugador {
	private String nombre;
	private List<Recurso> recursos;

	
	public Jugador(String nombre) {
		this.nombre = nombre;
		this.recursos = new ArrayList<Recurso>();
		
		inicializarRecursos(List.of(" MADERA", "LADRILLO", "LANA", "GRANO", "MINERAL"));
	}
	

    public void colocarPiezaFija(String tipo, int ubicacion) {
        Pieza pieza = Pieza.crear(tipo, this);
        pieza.colocarPiezaFija(ubicacion);
        switch (tipo) {
            case "poblado":
                this.poblados.add((Poblado) pieza);
            case "ciudad":
                this.ciudades.add((Ciudad) pieza);
            default:
                throw new IllegalArgumentException(
                        "Tipo de pieza no válido: " + tipo + ". Debe ser 'poblado' o 'ciudad'.");
        }
    }

    public String obtenerNombre() {
        return nombre;
    }

    public void colocarPiezaCamino(String tipo, int ubicacion1, int ubicacion2) {
        Pieza pieza = Pieza.crear(tipo, this);
        pieza.colocarPiezaCamino(ubicacion1, ubicacion2);
        switch (tipo) {
            case "Camino":
                this.caminos.add((Camino) pieza);
            default:
                throw new IllegalArgumentException(
                        "Tipo de pieza no válido: " + tipo + ". Debe ser 'camino'.");
        }
    }
	
	public void elegirColocazionPieza(String tipo) {
        Pieza pieza = Pieza.crear(tipo, this);
		pieza.colocar();
	}	
	 
	private void inicializarRecursos(List<String> tiposRecursos) {
		for(int i = 0; i < tiposRecursos.size(); i ++) {
			Recurso recurso = new Recurso(tiposRecursos.get(i));
			recursos.add(recurso);
		}
	}
	
	
	public void elegirColocacionInicial(String tipo) {
        Pieza pieza = Pieza.crear(tipo, this);
		pieza.colocar();
	}

    public void recibirRecurso(String tipo, int cantidad) {
        for (Recurso recurso : recursos) {
            if (recurso.sosTipo(tipo)) {
                recurso.incrementar(cantidad);
                return;
            }
        }

        Recurso nuevo = new Recurso(tipo);
        nuevo.incrementar(cantidad);
        recursos.add(nuevo);
    }
    
    public Recurso buscarRecurso(String tipo) {
        for (Recurso r : recursos) {
            if (r.sosTipo(tipo)) {
                return r;
            }
        }
        return null;
    }

    private boolean recursosSuficientes(List<Recurso> precio) {
        for (Recurso costo : precio) {
            Recurso recursoJugador = buscarRecurso(costo.nombre());
        
            if (!recursoJugador.puedeCubrir(costo))
                return false;  
        }
        return true;
    }
    
    public void construirPieza() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿Querés construir una pieza? (si/no)");
        String respuesta = scanner.nextLine().trim().toLowerCase();

        if (!respuesta.equals("si")) {
            return;
        }

        System.out.println("¿Qué pieza querés construir? (poblado / camino / ciudad)");
        String tipo = scanner.nextLine().trim().toLowerCase();

       Pieza piezaElegida= Pieza.crear(tipo, this);
       List<Recurso> precio= piezaElegida.costoDeConstruccion();
       
       Boolean puedoConstruirla = recursosSuficientes(precio);
       
       if(puedoConstruirla) {
    	   piezaElegida.colocar();
       }
        
    }
    
    public void moverLadron() {
    	
    }
    
	public void turno() {
		construirPieza(); 	
	}
    
	
	
	//Para los test, despeus hay que usar mocks
	public boolean puedeConstruir(Pieza pieza) {
	    return recursosSuficientes(pieza.costoDeConstruccion());
	}

	public void construirPiezaDePrueba(Pieza pieza) {
	    if (!puedeConstruir(pieza)) throw new IllegalStateException("No alcanza");
	}
}
