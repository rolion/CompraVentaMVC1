/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compraventamvc1.modelo.utilidades;


import compraventamvc1.modelo.datos.NewHibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author BDiamond
 */
public abstract class DataBaseHelper {
    
    
    private  Session session;

    public Session getSession() {
        return session;
    }
    
    public Object saveObjet(Object object){
        this.session=NewHibernateUtil.getSessionFactory().getCurrentSession();
        try{
            this.session.beginTransaction();
            this.session.save(object);
            this.session.getTransaction().commit();
        }catch(HibernateException he)
        {
            object=null;
            System.out.println("Error al guardar Object /n"+he.getMessage());
            System.out.println(he.getLocalizedMessage());
            this.session.getTransaction().rollback();
        }
        return object;
    }
    public Object updateObject(Object object) {
        this.session=NewHibernateUtil.getSessionFactory().getCurrentSession();
        try{
            this.session.beginTransaction();
            this.session.update(object);
            this.session.getTransaction().commit();
        }catch(HibernateException he){
            object=null;
            System.out.println("Error al actualizar Object /n"+he.getMessage());
            System.out.println(he.getLocalizedMessage());
            this.session.getTransaction().rollback();
           
        }
        return object;
    }
    public Object delteObject(Object object){
        this.session=NewHibernateUtil.getSessionFactory().getCurrentSession();
        try{
            this.session.beginTransaction();
            this.session.delete(object);
            this.session.getTransaction().commit();
        }
        catch(HibernateException he)
        {
            object=null;
            System.out.println("Error al Eliminar Object /n"+he.getMessage());
            System.out.println(he.getLocalizedMessage());
            this.session.getTransaction().rollback();
           
        }
        return object;
    }
    public void initTransaction(){
        this.session=NewHibernateUtil.getSessionFactory().getCurrentSession();
        this.session.beginTransaction();
    }
    public void closeTransaction(){
        this.session.getTransaction().commit();
    }
    public void rollBackTransaction(){
        this.session.getTransaction().rollback();
    }
    
}

