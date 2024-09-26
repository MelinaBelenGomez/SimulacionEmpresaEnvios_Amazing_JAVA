package amazing;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class EmpresaAmazing {

	// datos
	private String cuit;
	private String nombre;
	private Map<Integer, Pedido> pedidosMap; //si habia que eliminar esto, nos quedamos solo con arraylist 		 	 	
	private Map<String, Transporte> transportesMap;
	private double totalPedidosCerrados;
	
	

	// constructor

	public EmpresaAmazing(String cuit) {
		this.cuit = cuit;
		this.nombre = "Amazing";
		this.pedidosMap = new HashMap<Integer, Pedido>();  								
		this.transportesMap= new HashMap <String,Transporte>();
		this.totalPedidosCerrados = 0.0;
		
	}

	
	
	public int registrarPedido(String nombre, String domicilio, int dni) {
		Pedido pedido = new Pedido(nombre, domicilio, dni);
		
			//el tamanio del map de pedido +1 nos garantiza un numero de pedido actualizado
			pedido.setNumPedido(pedidosMap.size()+1);
			pedidosMap.put(pedido.getNumPedido(), pedido);
		
		return pedido.getNumPedido(); // devuelve el numero de pedido
	}


	
	
	public void registrarUtilitario(String patente, int volumenMaximoCarga, double precioBase, double valorExtraPorViaje) {

		if (patenteYaRegistrada(patente)) {
			throw new RuntimeException("El utilitario ya se encuentra registrado");
		} else {
			Utilitario utilitario = new Utilitario(patente, volumenMaximoCarga, precioBase, valorExtraPorViaje);		
			transportesMap.put(patente, utilitario);

		}
	}


	public void registrarCamion(String patente, int volumenMaximoCarga, double precioBase, double valorAdicionalPorPaquete) {

		if (patenteYaRegistrada(patente)) {
			throw new RuntimeException("El camión ya se encuentra registrado");
		} else {
			Camion camion = new Camion(patente, volumenMaximoCarga, precioBase, valorAdicionalPorPaquete);
			transportesMap.put(patente, camion);
		}

	}

	public void registrarAutomovil(String patente, int volumenMaximoCarga, double precioBase, int limiteMaxPaquetes) {

		if (patenteYaRegistrada(patente)) {
			throw new RuntimeException("El automovil ya se encuentra registrado");
		} else {
			Automovil automovil = new Automovil(patente, volumenMaximoCarga, precioBase, limiteMaxPaquetes);
			transportesMap.put(patente, automovil);
				
		}	
	} 
	

	public boolean patenteYaRegistrada(String patente) {
		for (Transporte transporte : transportesMap.values()) {
			if (patente.equals(transporte.getPatente())) {
				return true;
			}
		}
		return false;
	}


	public ArrayList<Paquete> paquetesCerrados() {
	    ArrayList<Paquete> paqCerrados = new ArrayList<>();
	    for (Pedido ped : pedidosMap.values()) {
	        if (ped.esPedidoCerrado()) {
	            paqCerrados.addAll(ped.listaPaquetesPorPedido());
	        }
	    }
	    return paqCerrados;
	}
	
	
	public String cargarTransporte(String patente) {
	    Transporte transporte = obtenerTransportePorPatente(patente);

	    if (!transportesMap.containsKey(patente)) {
	    	throw new RuntimeException("Patente no registrada en sistema");
	    }
	    
	    ArrayList<Paquete> paquetesCerrados = paquetesCerrados(); // Obtener paquetes cerrados	    	        	      
	       return transporte.cargarEsteTransporte(paquetesCerrados);	        	    	    	    	  	   
	}
	
	
	public Transporte obtenerTransportePorPatente(String patente) {
	   if (!transportesMap.containsKey(patente)) {
		   throw new RuntimeException("No se encontró el transporte con la patente: " + patente);
	   }
	   return transportesMap.get(patente);
	    
	}

	
	//metodo en O (1)
	public double costoEntrega(String patente) {
		if (transportesMap.size()<0) {
			throw new RuntimeException("No hay transporte para verificar");
		}
	    Transporte transporte = obtenerTransportePorPatente(patente);
	    return transporte.calcularPrecioPorEsteTransporte();
	}
	

	// este es agregar paquete a pedidos
	public int agregarPaquete(int numPedido, int volumen, double precio, int costoEnvio) {

		
		Pedido pedido = obtenerPedido(numPedido);
		
		// el tamanio del map de paquetes de un pedido +1 nos garantiza un numero de paquete unico 
		int numeroPaquete = pedido.lenPaquetes()+1;
		if (!(pedido == null) && !(pedido.esPedidoCerrado())) {

			pedido.agregarPaquete(numeroPaquete, volumen, precio, costoEnvio);
		
			return numeroPaquete;// tiene que devolver un numero de paquete unico
		}

		else {
			throw new RuntimeException(
					"El pedido al cual quieres agregar el producto se encuentra cerrado o no existe");
		}

	}

	public int agregarPaquete(int numPedido, int volumen, double precio, int porcentaje, int adicional) {
		
		Pedido pedido = obtenerPedido(numPedido);
		int numeroPaquete = pedido.lenPaquetes()+1;
		
		if (!(pedido == null) && !(pedido.esPedidoCerrado())) {

			pedido.agregarPaquete(pedido.lenPaquetes()+1, volumen, precio, porcentaje, adicional);
			
			return numeroPaquete; // tiene que devolver un numero de paquete unico
		} else {
			throw new RuntimeException(
					"El pedido al cual quieres agregar el producto se encuentra cerrado o no existe");
		}
	}

	
	// metodo con complejidad O(n^2)
	public boolean quitarPaquete(int codPaquete) {
		for (Pedido pedido : pedidosMap.values()) {
			if (pedido != null && !pedido.esPedidoCerrado() && pedido.tienePaquete(codPaquete)) {
				pedido.quitarPaquete(codPaquete);
				return true;
			}
		}

		throw new RuntimeException("No se encontró el paquete en ningún pedido");
	}

	
	public double cerrarPedido(int codPedido) {
		for (Pedido pedido : pedidosMap.values()) {
			if (pedido.getNumPedido() == codPedido) {
				if (!pedido.esPedidoCerrado()) {
					double totalPedidoCerrado = pedido.cerrarPedido();
					
					// cada vez que se cierra un pedido se agrega el valor del mismo a total de pedidos cerrados
					this.totalPedidosCerrados += totalPedidoCerrado;
					return totalPedidoCerrado;
				} else {
					throw new RuntimeException("El pedido ya está cerrado");
				}
			}
		}

		throw new RuntimeException("El pedido no existe");
	}

	
	// metodo en O(1)
	
	public double facturacionTotalPedidosCerrados() {
		return this.totalPedidosCerrados;

	}

	
	
	public boolean hayTransportesIdenticos() {
	    
	    List<Transporte> transportes = obtenerListaDeTransportes();

	    // Verifica si hay al menos dos transportes para comparar
	    if (transportes.size() < 2) {
	        return false;
	    }

	    // Recorre la lista de transportes 
	    for (Transporte transporteActual : transportes) {
	        // Verifica si hay otro transporte identico al actual
	        for (Transporte otroTransporte : transportes) {
	            if (transporteActual != otroTransporte && transporteActual.sonTransportesIdenticos(otroTransporte)) {
	                return true; // Se encontro al menos un par de transportes identicos
	            }
	        }
	    }

	    // No se encontraron transportes iguales
	    return false;
	}

	
		
	public List<Transporte> obtenerListaDeTransportes() {
	    ArrayList <Transporte> lista = new  ArrayList <Transporte>();
	    for( Transporte transporte: transportesMap.values()) {
	    	lista.add(transporte);
	    }
	    return lista;
	}
	
	
	
	public Map<Integer, String> pedidosNoEntregados() {
        Map<Integer, String> pedidosNoEntregadosMap = new HashMap<>();
       
        for(Pedido ped: pedidosMap.values()) {
        	if(ped.esPedidoCerrado() && !ped.tienePaquetesParaEntregar()) {
        		pedidosNoEntregadosMap.put(ped.getNumPedido(), ped.nombreCliente());
        	}
        	
        }
        return pedidosNoEntregadosMap;
	}
        
     


	public double obtenerImportePorDni(int dni) {
		Cliente cliente = buscarCliente(dni);

		if (cliente != null) {
			double valorTotal = 0.0;

			for (Pedido pedido : pedidosMap.values()) {
				if (pedido.getCliente() == cliente) {
					// Sumar el valor de cada pedido
					valorTotal += pedido.valorTotalDeEstePedido();
				}
			}

			return valorTotal;
		} else {
		
			throw new RuntimeException("El cliente no esta registrado"); 
		}
	}

	public String toString() {
		StringBuilder st = new StringBuilder();
		st.append("Nombre empresa: ");
		st.append(nombre);
		st.append(" cuit: ");
		st.append(cuit);
		st.append("\n");
		for (Pedido pedido : pedidosMap.values()) {
			st.append(pedido);
		}

		return st.toString();
	}

	public Pedido obtenerPedido(int numeroPedido) {
		
		for (Pedido pedido : pedidosMap.values()) {
			if (pedido.getNumPedido() == numeroPedido) {
				
				return pedido;
			}
		}

		
		return null;
	}

	public Cliente buscarCliente(int dni) {
		// Recorre todos los pedidos en el HashMap de pedidos
		for (Pedido pedido : pedidosMap.values()) {
			Cliente cliente = pedido.getCliente();
			if (cliente != null && cliente.getDni() == dni) {
				
				return cliente;
			}
		}
		
		return null;
	}
}

