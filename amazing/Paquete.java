package amazing;


public abstract class Paquete {
	
	//datos
	private int numPaquete;
	private int volumen;
	private double precio;
	protected boolean entregado; // estado del paquete
	protected int numPedido;
	protected String direccion;
	protected String clase;
	

	
	//constructor
	
	public Paquete( int numPaquete, int volumen, double precio) {
		this.numPaquete = numPaquete;
		this.volumen= volumen;
		this.precio= precio;
		this.entregado= false; //el estado comienza en false porque aun no se entrego
		this.numPedido = 0;
		this.direccion= "";
		this.clase="";
		
		
		
	}
	 public boolean isEntregado() {
		return entregado;
	}
	public void setEntregado(boolean entregado) {
		this.entregado = entregado;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getNumPedido() {
		return numPedido;
	}
	public void setNumPedido(int numPedido) {
		this.numPedido = numPedido;
	}
	
	
	//metodos abstractos
	public abstract double calcularPrecioPorEstePaquete();
	public abstract boolean sonAtributosIguales(Paquete otroPaquete);
	
	
	//metodos para verificar si son paquetes iguales
	public boolean sonPaquetesIdenticos(Paquete otroPaquete) {
		return mismoPrecio(otroPaquete) && mismoVolumen(otroPaquete) && mismaClase(otroPaquete) && sonAtributosIguales(otroPaquete);
	}
	 
	public boolean mismoVolumen(Paquete otroPaquete) {
		return this.volumen == otroPaquete.getVolumen();
	}
	
	public boolean mismaClase(Paquete otroPaquete) {
		return this.clase.equals(otroPaquete.getClase());
	}
	
	public boolean mismoPrecio(Paquete otroPaquete) {
		return this.precio == otroPaquete.getPrecio();
	}
	 	 		 
		
	  public double getPrecio() {
	        return precio;
	    }

	    public void setPrecio(double precio) {
	        this.precio = precio;
	    }
	    public int getVolumen() {
	        return volumen;
	    }
	    
	 
	    public String getClase() {
			return clase;
		}
		public void setVolumen(int volumen) {
	        this.volumen = volumen;
	    }
		public int getNumPaquete() {
			return numPaquete;
		}
		
		
		public void setNumPaquete(int numPaquete) {
			this.numPaquete = numPaquete;
		}
		public String toString() {
		       StringBuilder st= new StringBuilder();
		       st.append("Codigo Paquete: ");
		       st.append(numPaquete);
		       st.append(" volumen: ");
		       st.append(volumen);
		       st.append(" precio: $");
		       st.append(precio);
		       st.append(" \n");
		       return st.toString();
		    }
		public String getDireccion() {
			
			return this.direccion;
		}
		
	
		
	
}