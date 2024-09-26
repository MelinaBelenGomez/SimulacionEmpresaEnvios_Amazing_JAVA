package amazing;

public class Cliente {
	//datos
	private int dni;
	private String nombreCompleto;
	private String direccion;
	
	
	//constructor
	public Cliente (String nombreCompleto, String direccion, int dni) {
		
		this.nombreCompleto= nombreCompleto;
		this.direccion= direccion;
		this.dni= dni;
	}
	 public String toString() {
	       StringBuilder st= new StringBuilder();
	       st.append("Nombre Cliente: ");
	       st.append(nombreCompleto);
	       st.append(" \n");
	       return st.toString();
	              
	    }
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	
	public String getDireccion() {
		return this.direccion;
	}
	
	public String getNombre(){
	return this.nombreCompleto;	
	}
}
