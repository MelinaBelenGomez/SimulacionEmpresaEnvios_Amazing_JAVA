package amazing;
import java.util.ArrayList;


public class Automovil extends Transporte{
	//datos adicionales
		private int limiteMaxPaquetes;
		
	
		//constructor
		public Automovil (String patente, double volumenMaximoCarga, double precioBase, int limiteMaxPaquetes2) {
			super(patente,volumenMaximoCarga, precioBase);
			this.limiteMaxPaquetes = limiteMaxPaquetes2;
			this.tipo= "automovil";
			
			
		}
		


		@Override
		public String cargarEsteTransporte(ArrayList<Paquete> paquetes) {
			
		    StringBuilder st = new StringBuilder();
		    
		    for (Paquete paq : paquetes) {
		        if (paq instanceof PaqueteOrdinario && paq.getVolumen() < 2000 && !paq.isEntregado()) {
		            // Es un paquete ordinario con volumen menor a la capacidad
		        	System.out.println(paq.getDireccion());
		            st.append(" + [ " + paq.getNumPedido() + " - " + paq.getNumPaquete() + " ] "+paq.getDireccion()+"\n");
		             System.out.println(st.toString());
		             paquetesEnTransporte.add(paq);
		             paq.setEntregado(true); //si el pedido esta cerrado y se carga en el auto, el paquete se considera entregado
		             
		             this.volumenMaximoCarga -= paq.getVolumen(); // le descuento el volumen del paquete

		        }
		            if (paquetesEnTransporte.size() >= limiteMaxPaquetes || volumenMaximoCarga < 0) {
		                // limite completo
		                throw new RuntimeException("Este automovil no puede cargar más paquetes");
		            }   
		        } 
		    return st.toString();
		    }
		   
		
		@Override
		public double calcularPrecioPorEsteTransporte() {
			if(paquetesEnTransporte.size()==0) {
				throw new RuntimeException ("El transporte no tiene paquetes para cargar");
			}
			return getPrecioBase();
		}
		
}	
		


		

