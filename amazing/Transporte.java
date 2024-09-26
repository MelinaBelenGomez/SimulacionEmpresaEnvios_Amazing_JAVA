package amazing;

import java.util.ArrayList;

public abstract class Transporte {
    // datos
    private String patente;
    protected double volumenMaximoCarga;
    private double precioBase;
    protected String tipo;
    protected ArrayList<Paquete> paquetesEnTransporte = new ArrayList<>();

    // constructor
    public Transporte(String patente, double volumenMaximoCarga, double precioBase) {
        this.patente = patente;
        this.volumenMaximoCarga = volumenMaximoCarga;
        this.precioBase = precioBase;
        this.tipo = "";
    }

    
    //metodos para verificar si los trasnportes son iguales
    public boolean sonTransportesIdenticos(Transporte otro) {
        return distintaPatente(otro) && mismoTipo(otro) && mismaCarga(otro);
    }
    
    
    public boolean distintaPatente( Transporte otroTransporte) {
    	return !this.patente.equals(otroTransporte.getPatente());
    }


    public boolean mismoTipo( Transporte otroTransporte) {
    	return this.tipo.equals(otroTransporte.getTipo());
    }
    
    
    public boolean mismaCarga(Transporte otro) {
        ArrayList<Paquete> paquetes1 = this.paquetesEnTransporte;
        ArrayList<Paquete> paquetes2 = otro.paquetesEnTransporte;

	 if ((paquetes1==null || paquetes2==null) || paquetes1.size() != paquetes2.size()) {
	    	return false;
	    }

      
	 boolean paqueteCoincide = false;
        for (Paquete paquete1 : paquetes1) {
        	paqueteCoincide = false;

            for (Paquete paquete2 : paquetes2) {
            	
            	//acumulamos el valor de paqueteCoincide para retornarlo al final
            	//almenos debe coincidir una vez con el paquete1
                paqueteCoincide|= paquete1.sonPaquetesIdenticos(paquete2);
                }
                    
        }

        return paqueteCoincide;
    }
    
 
    

	public abstract double calcularPrecioPorEsteTransporte();

    public abstract String cargarEsteTransporte(ArrayList<Paquete> paquetes);

    public double getVolumenMaximoCarga() {
        return volumenMaximoCarga;
    }

    public void setVolumenMaximoCarga(double volumenMaximoCarga) {
        this.volumenMaximoCarga = volumenMaximoCarga;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public String getPatente() {
        return patente;
    }
    public String getTipo() {
		return tipo;
	}
   	

}



