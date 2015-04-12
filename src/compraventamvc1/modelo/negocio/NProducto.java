/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compraventamvc1.modelo.negocio;

import compraventamvc1.modelo.datos.Producto;
import compraventamvc1.modelo.utilidades.DataBaseHelper;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author BDiamond
 */
public class NProducto extends DataBaseHelper{
    
    public Producto InsertarProducto(Producto producto) throws Exception{
        if (producto !=null){
            Object aux;
            aux= saveObjet(producto);
                if(aux !=null){
                    return (Producto) aux;
                }else{
                    throw new Exception("Error al insertar el producto");
                }
        }
        return null;
    }
    public List<Producto> BuscarProductoPorNombre(Producto producto) throws Exception{
        List<Producto> result=null;
        if(producto!=null){
            try{
            initTransaction();
                result=getSession().createSQLQuery("Select * from producto where Nombre like '" +producto.getNombre()+ "%' ;").addEntity(Producto.class).list();
            
            }catch(HibernateException HE){
               throw new Exception("Error durante la busqueda");
            }
            finally{closeTransaction();}
        return result;
    }
        throw new Exception("No se puede Buscar valores Nulos");
    }
    public Producto ModificarProducto(Producto producto) throws Exception{
        if (producto!= null){
            try{
                producto=(Producto) updateObject(producto);
            }catch(HibernateException HE){
                HE.printStackTrace();
                throw new Exception("Error al Modificar");
            }           
        }
        return producto;
    }
       public List<Producto> ListarProducto(){
       List<Producto> result=null;
       
           try {
               initTransaction();
               result=getSession().createSQLQuery("Select * from Producto").addEntity(Producto.class).list();
           } catch (Exception e) {
           }
           finally{closeTransaction();}
        return result;
   }
}
