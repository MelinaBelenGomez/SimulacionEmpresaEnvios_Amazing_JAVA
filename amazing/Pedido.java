package amazing;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class Pedido {
	//datos
	private int numPedido;
	private Cliente cliente;
	private Map<Integer, Paquete> paquetesMap; 		
	private double valor;
	private boolean pedidoCerrado;

	
	//constructor
	public Pedido(String nombre, String direccion, int dni) {
		Cliente cliente = new Cliente(nombre, direccion, dni);
		
		this.numPedido = 0;
		this.cliente = cliente;
		this.paquetesMap = new HashMap<Integer, Paquete>();		
		this.valor = 0.0;
		this.pedidoCerrado = false;

	}

	
	public boolean esPedidoCerrado() {
		return pedidoCerrado;
	}

	
	public ArrayList<Paquete> listaPaquetesPorPedido() {
	    return new ArrayList<>(paquetesMap.values());
	}
	
	public int lenPaquetes() {
		return paquetesMap.size();
	}
	
	public double cerrarPedido() {
		if (this.pedidoCerrado) {
			throw new RuntimeException("El pedido ya está cerrado"); 
		}
		pedidoCerrado = true;
		
		return this.valor;
	}

	

	public void agregarPaquete(int numPaquete, int volumen, double precio, int costoEnvio) {
	    Paquete paquete = new PaqueteOrdinario(numPaquete, volumen, precio, costoEnvio);
	    paquetesMap.put(paquete.getNumPaquete(), paquete);
	    this.valor += paquete.calcularPrecioPorEstePaquete();
	    paquete.setNumPedido(this.numPedido);	        
	    paquete.setDireccion(this.cliente.getDireccion());
	   
	}

	public void agregarPaquete(int numPaquete, int volumen, double precio, int porcentaje, int adicional) {
		Paquete paquete = new PaqueteEspecial(numPaquete, volumen, precio, porcentaje, adicional);
		paquetesMap.put(paquete.getNumPaquete(), paquete);
		this.valor += paquete.calcularPrecioPorEstePaquete();
		paquete.setNumPedido(this.numPedido);
		paquete.setDireccion(this.cliente.getDireccion());
	}

	public int getNumPedido() {
		return numPedido;
	}

	public void setNumPedido(int numPedido) {
		this.numPedido = numPedido;
	}

	public boolean tienePaquete(int codPaquete) {
		return paquetesMap.containsKey(codPaquete); // da verdadero cuando alguno de los paquetes contiene el codigo que
													// se le pasa
	}

	public void quitarPaquete(int codPaquete) {
		Iterator<Paquete> iterador = paquetesMap.values().iterator();
		while (iterador.hasNext()) {
			Paquete paquete = iterador.next();
			if (paquete.getNumPaquete() == codPaquete) { 
				this.valor -= paquete.getPrecio(); // restamos el precio del paquete a eliminar al valor total del
													// pedido
				iterador.remove();
			}
		}
	}

	public double valorTotalDeEstePedido() {
		return valor;
	}

	
	public String toString() {
		StringBuilder st = new StringBuilder();
		st.append("numero de pedido >>");
		st.append(numPedido);
		st.append("<< \n");
		st.append(cliente.toString());
		st.append("Pedido cerrado: ");
		st.append(pedidoCerrado);
		st.append("\n");
		st.append("valor total del pedido: $");
		st.append(valor);
		st.append("\nPaquetes: \n");
		for (Paquete paquete : paquetesMap.values()) {

			st.append(paquete);
		}
		st.append("\n");
		return st.toString();

	}


	public Cliente getCliente() {
		return cliente;
	}

	public String nombreCliente() {
		return this.cliente.getNombre();
	}
	

	public Map<Integer, Paquete> getPaquetes() {
		return paquetesMap;
	}

	public boolean tienePaquetesParaEntregar() {
		for(Paquete Paquete: paquetesMap.values()) {
			if (!Paquete.entregado) {
				return false;
			}
		
		}
		return true;
	}
}