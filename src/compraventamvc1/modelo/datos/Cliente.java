package compraventamvc1.modelo.datos;
// Generated 12-abr-2015 14:39:54 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Cliente generated by hbm2java
 */
public class Cliente  implements java.io.Serializable {


     private Integer idCliente;
     private int nitCi;
     private String nombre;
     private String direccion;
     private Set notaventas = new HashSet(0);
     private Set referenciaclientes = new HashSet(0);

    public Cliente() {
    }

	
    public Cliente(int nitCi, String nombre, String direccion) {
        this.nitCi = nitCi;
        this.nombre = nombre;
        this.direccion = direccion;
    }
    public Cliente(int nitCi, String nombre, String direccion, Set notaventas, Set referenciaclientes) {
       this.nitCi = nitCi;
       this.nombre = nombre;
       this.direccion = direccion;
       this.notaventas = notaventas;
       this.referenciaclientes = referenciaclientes;
    }
   
    public Integer getIdCliente() {
        return this.idCliente;
    }
    
    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
    public int getNitCi() {
        return this.nitCi;
    }
    
    public void setNitCi(int nitCi) {
        this.nitCi = nitCi;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public Set getNotaventas() {
        return this.notaventas;
    }
    
    public void setNotaventas(Set notaventas) {
        this.notaventas = notaventas;
    }
    public Set getReferenciaclientes() {
        return this.referenciaclientes;
    }
    
    public void setReferenciaclientes(Set referenciaclientes) {
        this.referenciaclientes = referenciaclientes;
    }

    @Override
    public String toString() {
        return String.valueOf(this.nitCi);
    }




}


