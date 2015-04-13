/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compraventamvc1.modelo.negocio;

import compraventamvc1.modelo.datos.Cliente;
import compraventamvc1.modelo.datos.Detalleventa;
import compraventamvc1.modelo.datos.Notaventa;
import compraventamvc1.modelo.datos.Producto;
import compraventamvc1.modelo.utilidades.DataBaseHelper;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author Lion
 */
public class NVenta extends DataBaseHelper{

    public NVenta() {
    }
    public boolean modificarDetalleNota(List<Object> dv){
        boolean rslt=false;
        if(dv!=null && dv.size()>0){
            for (Object dv1 : dv) {
                try{
                    updateObject(dv1);
                }catch(HibernateException he){
                    System.out.println("Error al actualizar el detalle ");
                    System.out.println("Id nota venta "+((Detalleventa)dv1).getNotaventa().getIdNotaVenta());
                    System.out.println("nombre Cliente "+((Detalleventa)dv1).getNotaventa().getCliente().getNombre());
                    System.out.println("Nombre Producto "+((Detalleventa)dv1).getProducto().getNombre());
                    he.getMessage();
                    he.printStackTrace();
                }
            }
            rslt=true;
        }
        return rslt;
    }
    public Notaventa insertVenta(Notaventa notaVenta, List<Detalleventa> detalleProducto){
        if(notaVenta!=null && detalleProducto!=null){
            try{
                
                initTransaction();
                getSession().save(notaVenta);
                for(int i=0;i<detalleProducto.size();i++){
                    Detalleventa dventa=detalleProducto.get(i);
                    dventa.setNotaventa(notaVenta);
                    getSession().save(dventa);
                }
                getSession().getTransaction().commit();
                //detalleVenta.setNotaventa(notaVenta);
            }catch(HibernateException he){
                getSession().getTransaction().rollback();
                System.out.println("---------Error al insertar la venta-------");
                System.out.println(he.getMessage());
                he.printStackTrace(); 
            }    
        }
        return notaVenta;
    }
    public List<Detalleventa> buscarVenta(Notaventa notaVenta){
        if(notaVenta!=null  && notaVenta.getIdNotaVenta()>0){
            initTransaction();
            List<Detalleventa> ldv=getSession().createSQLQuery("select * " +
            "from detalleventa dv, cliente c, producto p, notaventa nv " +
            "where dv.`IdNotaVenta`=nv.`IdNotaVenta` " +
            "and dv.`IdProducto`=p.`IdProducto` and nv.`idCliente`=c.`IdCliente` " +
            "and  nv.`IdNotaVenta`="+notaVenta.getIdNotaVenta()).addEntity(Detalleventa.class).list();
            closeTransaction();
            return ldv;
        }
        
        return null;
    }
     public Cliente buscarClienteNota(Notaventa notaventa){
        Cliente cliente=null;
        if(notaventa!=null){
            initTransaction();
            cliente=(Cliente) getSession().createSQLQuery("select * from cliente c, notaventa nv "
                    + "WHERE nv.`idCliente`=c.`IdCliente` and " +
                    "nv.`IdNotaVenta`="+notaventa.getIdNotaVenta()).addEntity(Cliente.class).uniqueResult();
            closeTransaction();
        }
        return cliente;
    }
     public Producto getProductoDetalle(Detalleventa dv){
         Producto p=null;
         if(dv!=null){
              initTransaction();
             p=(Producto) getSession().createSQLQuery("select * from detalleventa dv, producto "
                     + "p where dv.`IdProducto`=p.`IdProducto` and dv.`IdDetalleV`="+dv.getIdDetalleV()).addEntity(Producto.class).uniqueResult();
             closeTransaction();
         }
         return p;
     }
    
}
