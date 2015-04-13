package compraventamvc1.modelo.negocio;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import compraventamvc1.modelo.datos.Cliente;
import compraventamvc1.modelo.utilidades.DataBaseHelper;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author BDiamond
 */
public class NCliente extends DataBaseHelper{
       
    public Cliente InsertarCliente(Cliente cliente) throws Exception{
        if (cliente !=null){
            Object aux;
            aux= saveObjet(cliente);
                if(aux !=null){
                    return (Cliente) aux;
                }else{
                    throw new Exception("Error al insertar el producto");
                }
        }
        return null;
    }
    public List<Cliente> BuscarClientePorNombre(Cliente cliente) throws Exception{
        List<Cliente> result=null;
        if(cliente!=null){
            try{
            initTransaction();
                result=getSession().createSQLQuery("Select * from Cliente where nombre like '" +cliente.getNombre()+ "'  ").addEntity(Cliente.class).list();
            
            }catch(HibernateException HE){
               throw new Exception("Error durante la busqueda");
            }
            finally{closeTransaction();}
        return result;
    }
        throw new Exception("No se puede Buscar valores Nulos");
    }
    public Cliente ModificarCliente(Cliente cliente) throws Exception{
        if (cliente!= null){
            try{
                cliente=(Cliente) updateObject(cliente);
            }catch(HibernateException HE){
                throw new Exception("Error al Modificar");
            }           
        }
        return cliente;
    }
       public List<Cliente> ListarCliente(){
       List<Cliente> result=null;
           try {
               initTransaction();
               result=getSession().createSQLQuery("Select * from cliente").addEntity(Cliente.class).list();
           } catch (Exception e) {
               System.out.println("Error al listar el proyecto");
               e.printStackTrace();
           }
           finally{
               closeTransaction();
           }
        return result;
   }
}
