package compraventamvc1.modelo.negocio;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import compraventamvc1.modelo.datos.TiendaSucursal;
import compraventamvc1.modelo.utilidades.DataBaseHelper;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author BDiamond
 */
public class NTienda_Sucursal extends DataBaseHelper{
    private TiendaSucursal TiendaSucursal;
     public TiendaSucursal InsertarTiendaSucursal(TiendaSucursal ts) throws Exception{
        if (ts !=null){
            Object aux;
            aux= saveObjet(ts);
                if(aux !=null){
                    return (TiendaSucursal) aux;
                }else{
                    throw new Exception("Error al insertar la Tienda_Sucursal");
                }
        }
        return null;
    }
    public List<TiendaSucursal> BuscarTiendaSucursal(TiendaSucursal ts) throws Exception{
        List<TiendaSucursal> result=null;
        if(ts!=null){
            try{
            initTransaction();
                result=getSession().createSQLQuery("Select * from Tienda_Sucursal where nombre like '" +ts.getNombre()+ "'  ").addEntity(TiendaSucursal.class).list();
            
            }catch(HibernateException HE){
               throw new Exception("Error durante la busqueda");
            }
            finally{closeTransaction();}
        return result;
    }
        throw new Exception("No se puede Buscar valores Nulos");
    }
    public TiendaSucursal ModificarTiendaSucursal(TiendaSucursal ts) throws Exception{
        if (ts!= null){
            try{
                ts=(TiendaSucursal) updateObject(ts);
            }catch(HibernateException HE){
                HE.printStackTrace();
                throw new Exception("Error al Modificar");
            }           
        }
        return TiendaSucursal;
    }
       public List<TiendaSucursal> ListarTienda(){
       List<TiendaSucursal> result=null;
       
           try {
               initTransaction();
               result=getSession().createSQLQuery("Select * from tienda_sucursal").addEntity(TiendaSucursal.class).list();
           } catch (Exception e) {
           }
           finally{
               closeTransaction();
           }
            
        return result;
   }
}
