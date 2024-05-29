package MotoGp;

public class Piloto {
	public class PilotoMotoGP {
	    private int idPiloto;
	    private String nombre;
	    private String pais;
	    private int edad;
	    private int dorsal;
	    private int idEquipo;
	    
	    // Constructor
	    public PilotoMotoGP(int idPiloto, String nombre, String pais, int edad, int dorsal, int idEquipo) {
	        this.idPiloto = idPiloto;
	        this.nombre = nombre;
	        this.pais = pais;
	        this.edad = edad;
	        this.dorsal = dorsal;
	        this.idEquipo = idEquipo;
	    }
	    

	    public void mostrarInformacion() {
	        System.out.println("ID del piloto: " + idPiloto);
	        System.out.println("Nombre: " + nombre);
	        System.out.println("País: " + pais);
	        System.out.println("Edad: " + edad);
	        System.out.println("Dorsal: " + dorsal);
	        System.out.println("ID del equipo: " + idEquipo);
	    }
	    

	}
}
