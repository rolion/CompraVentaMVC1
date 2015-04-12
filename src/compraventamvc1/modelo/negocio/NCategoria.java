/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compraventamvc1.modelo.negocio;

import compraventamvc1.modelo.datos.Categoria;
import compraventamvc1.modelo.utilidades.DataBaseHelper;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author BDiamond
 */
public class NCategoria extends DataBaseHelper {

    public NCategoria() {
    }
    
    
    public Categoria InsertarCategoria(Categoria categoria) throws Exception{
        if(categoria!=null){
            Object aux;
            aux= saveObjet(categoria);
                if(aux!=null){
                    return (Categoria) aux;
                }else{
                    throw new Exception("Error al insertar la categoria");
                }
        }
        return null;
    }
    
   public List<Categoria> BuscarCategoriaPorNombre(Categoria categoria) throws Exception{
        List<Categoria> result=null;
        if(categoria!=null){
            try{
            initTransaction();
                result=getSession().createSQLQuery("Select * from Categoria where nombre like '"+categoria.getNombre()+"' ").addEntity(Categoria.class).list();
            }catch(HibernateException HE){
                throw new Exception("Error durante la busqueda");
            }
            finally{closeTransaction();}
        return result;
      }
        throw new Exception("No se puede Buscar valores Nulos");
    }
   
   public Categoria ModificarCategoria(Categoria categoria) throws Exception{
        if (categoria!= null){
            try{
                categoria=(Categoria) updateObject(categoria);
            }catch(HibernateException HE){
                HE.printStackTrace();
                throw new Exception("Error al Modificar");
            }           
        }
        return categoria;
    }
   public List<Categoria> ListarCategoria(){
       List<Categoria> result=null;
       
           try {
               initTransaction();
               result=getSession().createSQLQuery("Select * from categoria").addEntity(Categoria.class).list();
           } catch (Exception e) {
           }
           finally{closeTransaction();}
         
        return result;
   }
}

