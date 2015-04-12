/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compraventamvc1.controladores;

import compraventamvc1.vista.VCliente;
import compraventamvc1.vista.VPrincipal;
import compraventamvc1.vista.VProducto;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Lion
 */
public class CPrincipal {

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
                 System.out.println("La venta esta activa");
            }

            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e); //To change body of generated methods, choose Tools | Templates.
                 System.out.println("La ventana se esta abriendo");
            }
            

            
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("La ventana Se esta cerrando");
            }
            
        });
        
        vPrincipal.setVisible(true);
    }
    
}
