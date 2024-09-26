package amazing;

public class PaqueteEspecial extends Paquete {
	
	//datos adicionales
	private int porcentajeEntregaRapida;
	private int valorAdicional;
	
	
	//constructor
	public PaqueteEspecial(int numPaquete, int volumen, double precio, int porcentajeEntregaRapida, int valorAdicional) {
		super(numPaquete, volumen, precio);
		this.porcentajeEntregaRapida= porcentajeEntregaRapida;
		this.valorAdicional = valorAdicional;
		this.numPedido= 0;
		this.direccion= "";
		this.clase= "especial";
		
	}
	
	@Override //sobreescribe el metodo calcular precio
    public double calcularPrecioPorEstePaquete() {
		if (super.getVolumen()< 3000) {
			double precioDelPaquete = super.getPrecio() + ((super.getPrecio() * porcentajeEntregaRapida) /100 );
			setPrecio(precioDelPaquete);
			return precioDelPaquete;
		}
		else if (super.getVolumen() < 5000) {
			double precioDelPaquete2 =super.getPrecio() + ((super.getPrecio() * porcentajeEntregaRapida) /100 ) + valorAdicional;
			setPrecio(precioDelPaquete2);
			return  precioDelPaquete2;
		}
		else {
			double precioDelPaquete3 = super.getPrecio() + ((super.getPrecio() * porcentajeEntregaRapida) /100 ) + (valorAdicional * 2);
			setPrecio(precioDelPaquete3);
			return precioDelPaquete3;
		}
	}
	
	@Override
	public boolean sonAtributosIguales(Paquete otroPaquete) {
	    if (otroPaquete.getClase().equals(this.clase) ) {
	        PaqueteEspecial otroPaqueteEspecial = (PaqueteEspecial) otroPaquete;
	        
	        return this.porcentajeEntregaRapida == otroPaqueteEspecial.porcentajeEntregaRapida &&
	               this.valorAdicional == otroPaqueteEspecial.valorAdicional;
	    }
	    
	    return false;
	}

	public int getPorcentajeEntregaRapida() {
		return porcentajeEntregaRapida;
	}

	public int getValorAdicional() {
		return valorAdicional;
	}
	


}

