/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compraventamvc1.controladores;

import compraventamvc1.modelo.datos.Cliente;
import compraventamvc1.modelo.datos.Detalleventa;
import compraventamvc1.modelo.negocio.NVenta;
import compraventamvc1.modelo.utilidades.DataBaseHelper;
import compraventamvc1.vista.VCliente;
import compraventamvc1.vista.VPrincipal;
import compraventamvc1.vista.VProducto;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

/**
 *
 * @author Lion
 */
public class CPrincipal extends DataBaseHelper{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CPrincipal();
            }
        });
        
    }
    private VPrincipal vPrincipal;
    private CProducto cProcducto;
    private CCategoria cCategoria;
    private CProveedor cProveedor;
    private CVenta cVenta;
    public CPrincipal() {
        vPrincipal=new VPrincipal();
        initComponent();
    }
    private void initComponent(){
        vPrincipal.btnproducto. addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); 
                cProcducto=new CProducto();
            }
            
        });
        vPrincipal.btncategoria.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); 
                cCategoria=new CCategoria();
            }
            
        });
        vPrincipal.btnproveedor.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); 
                cProcducto=new CProducto();
            }
            
        });
        vPrincipal.addWindowListener(new WindowAdapter() {

            @Override
            public void windowActivated(WindowEvent e) {
                super.windowActivated(e); 
                getSession();
                 System.out.println("La venta principal esta activa");
            }  
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                closeSession();
                System.out.println("La ventana principal Se esta cerrando");
            }
            
        });
        vPrincipal.btnventa.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cVenta=new CVenta();
            }
           
           
        });
        
        vPrincipal.setVisible(true);
    }
    
}
