package amazing;



public class PaqueteOrdinario extends Paquete {
	
	//dato adicional
	private int costoEnvio;
	
	
	//constructor
	public PaqueteOrdinario( int numPaquete, int volumen, double precio, int costoEnvio) {
		super(numPaquete, volumen, precio);
		this.costoEnvio= costoEnvio;
		this.direccion= "";
		this.clase= "ordinario";
		
	}

	 @Override //sobreescribe el metodo calcular precio
	    public double calcularPrecioPorEstePaquete() {
		 	double precioDelPaquete = super.getPrecio() + costoEnvio;
		 	setPrecio(precioDelPaquete);
	        return precioDelPaquete;
	    }
	 
	 @Override
	 public boolean sonAtributosIguales(Paquete otroPaquete) {
	     if (otroPaquete.getClase().equals(this.clase)) {
	         PaqueteOrdinario otroPaqueteOrdinario = (PaqueteOrdinario) otroPaquete;
	         
	         return this.costoEnvio == otroPaqueteOrdinario.costoEnvio;
	     }
	     
	     return false;
	 }
	
	 public String getDireccion() {
		 return super.getDireccion();
	 }

	public int getCostoEnvio() {
		return costoEnvio;
	}
	 
	 
	 
}
