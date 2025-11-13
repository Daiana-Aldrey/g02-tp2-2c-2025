package edu.fiuba.algo3.modelo;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Jugador {
	private String nombre;

	private List<Recurso> recursos;
    //ELIMINAR DESPUES las dejo par que compile PREGUNTAR si no las vamos a usar mas
    private List<Poblado> poblados = new ArrayList<>();
    private List<Ciudad>  ciudades = new ArrayList<>();
    private List<Camino>  caminos  = new ArrayList<>();
    //private List<Recurso> recursos = new ArrayList<>();


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
        String t = tipo.trim().toUpperCase();
        for (Recurso recurso : recursos) {
            if (recurso.sosTipo(t)) {
                recurso.incrementar(cantidad);
                return;
            }
        }

        Recurso nuevo = new Recurso(t);
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

    //suma de cantidades en la lista de recursos
    public int totalRecursos() {
        int total = 0;
        for (Recurso r : recursos) {
            total += r.cantidad();
        }
        return total;
    }

    //descarta la mitad empezando por las prime cartas
    public void descartarMitad() {
        int total = totalRecursos();
        if (total <= 7) return;

        int aDescartar = total / 2; // floor

        // Recorremos la lista de recursos descontando cantidades
        int i = 0;
        while (aDescartar > 0 && i < recursos.size()) {
            Recurso r = recursos.get(i);
            int disponible = r.cantidad();
            int tomar = Math.min(disponible, aDescartar);

            if (tomar > 0) {
                r.decrementar(tomar);
                aDescartar -= tomar;
            }

            if (r.cantidad() == 0) {
                recursos.remove(i);
            } else {
                i++;
            }
        }
    }


//	public void descartarMitad() {
    //ya no sirve por que no tengo una lista de todas  cartas
//		int total = recursos.size();
//		if (total <= 7) return;
//
//		int aDescartar = total / 2;
//		for (int i = 0; i < aDescartar; i++) {
//			//descarto las ultimas
//			recursos.remove(recursos.size() - 1);
//		}
//	}

    // Para verif en los tests
    public int cantidadDeCartas() {
        return totalRecursos();
    }

    public String getNombre() {
        return nombre;
    }

}
