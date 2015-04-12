package compraventamvc1.modelo.datos;
// Generated 12-abr-2015 14:39:54 by Hibernate Tools 4.3.1



/**
 * Referenciacliente generated by hbm2java
 */
public class Referenciacliente  implements java.io.Serializable {


     private Integer idReferenciaCliente;
     private Cliente cliente;
     private int telefono;
     private String correo;

    public Referenciacliente() {
    }

	
    public Referenciacliente(int telefono) {
        this.telefono = telefono;
    }
    public Referenciacliente(Cliente cliente, int telefono, String correo) {
       this.cliente = cliente;
       this.telefono = telefono;
       this.correo = correo;
    }
   
    public Integer getIdReferenciaCliente() {
        return this.idReferenciaCliente;
    }
    
    public void setIdReferenciaCliente(Integer idReferenciaCliente) {
        this.idReferenciaCliente = idReferenciaCliente;
    }
    public Cliente getCliente() {
        return this.cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public int getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
    public String getCorreo() {
        return this.correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }




}

