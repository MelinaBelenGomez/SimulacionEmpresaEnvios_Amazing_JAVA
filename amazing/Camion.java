package amazing;

import java.util.ArrayList;

public class Camion extends Transporte {
	private double valorAdicionalPorPaquete;
	
	

	public Camion(String patente, double volumenMaximoCarga, double precioBase, double valorAdicionalPorPaquete) {
		super(patente, volumenMaximoCarga, precioBase);
		this.valorAdicionalPorPaquete = valorAdicionalPorPaquete;	
		this.tipo= "camion";
	}
	

	@Override
	public String cargarEsteTransporte(ArrayList<Paquete> paquetes) {
	    StringBuilder st = new StringBuilder();
	    
	    for (Paquete paq : paquetes) {
	        if (paq instanceof PaqueteEspecial && paq.getVolumen() > 2000 && !paq.isEntregado() && volumenMaximoCarga >= paq.getVolumen()) {
	            // es un paquete especial con un volumen mayor a 2000 y hay espacio en el camion
	            st.append(" + [ " + paq.getNumPedido() + " - " + paq.getNumPaquete() + " ] "+paq.getDireccion()+"\n");
	            System.out.println(st.toString());
	            paquetesEnTransporte.add(paq);
	            paq.setEntregado(true); 

	            volumenMaximoCarga -= paq.getVolumen(); // descuento el volumen del paquete
	        }
	    }

	    if (volumenMaximoCarga < 0) {
	        // limite completo
	        throw new RuntimeException("Este camión no puede cargar más paquetes");
	    }

	    
	    return st.toString();
	}
	

	@Override
	public double calcularPrecioPorEsteTransporte() {
		
		return getPrecioBase() + (paquetesEnTransporte.size()* this.valorAdicionalPorPaquete);
			
	}
	
	
}

