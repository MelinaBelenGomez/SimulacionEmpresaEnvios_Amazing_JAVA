package amazing;
import java.util.ArrayList;

public class Utilitario extends Transporte{
	private double valorExtraPorViaje; 
	private double precioBase;
	
	
	public Utilitario (String patente, double volumenMaximoCarga, double precioBase, double valorExtraPorViaje) {
		super(patente, volumenMaximoCarga, precioBase);		
		this.valorExtraPorViaje= valorExtraPorViaje;
		this.tipo= "utilitario";
	}
	
	
	
	@Override
	public double calcularPrecioPorEsteTransporte() {
		if(paquetesEnTransporte.size()==0) {
			throw new RuntimeException ("El transporte no tiene paquetes para cargar");
		}
		double suma=0;
		suma += precioBase; 
		if(paquetesEnTransporte.size()>=3) {		
				suma+= valorExtraPorViaje;
		}

		return suma;
	}

	
	@Override
	public String cargarEsteTransporte(ArrayList<Paquete> paquetes) {

		 StringBuilder st = new StringBuilder();
		    //primero cargamos los especiales del array
		    for (Paquete paq : paquetes) {
		        if (paq instanceof PaqueteEspecial && !paq.isEntregado()) {
		           
		            st.append(" + [ " + paq.getNumPedido() + " - " + paq.getNumPaquete() + " ] "+paq.getDireccion()+"\n");
		            paquetesEnTransporte.add(paq);
		            paq.setEntregado(true);
		            this.volumenMaximoCarga -= paq.getVolumen();
		        }
		    }

		    // cargar paquetes ordinarios
		    for (Paquete paq : paquetes) {
		        if (paq instanceof PaqueteOrdinario && !paq.isEntregado()) {
		            
		            st.append(" + [ " + paq.getNumPedido() + " - " + paq.getNumPaquete() + " ] "+paq.getDireccion()+"\n");
		            paquetesEnTransporte.add(paq);
		            paq.setEntregado(true);
		            this.volumenMaximoCarga -= paq.getVolumen();
		        }
		    } 		             
		        
		            if (volumenMaximoCarga < 0) {
		                // limite completo
		                throw new RuntimeException("Este automovil no puede cargar más paquetes");
		            }    
		    return st.toString();
		    }
		   
		
}
