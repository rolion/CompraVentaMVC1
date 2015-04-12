/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compraventamvc1.controladores;


import compraventamvc1.modelo.negocio.NCategoria;
import compraventamvc1.vista.VCategoria;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author BDiamond
 */
public class CCategoria {
    
    
    
    private VCategoria VCat; 
    private NCategoria NCat;
    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        new CCategoria();
//    }

    public CCategoria() {
        VCat=new VCategoria();
        NCat=new NCategoria();
        CargarComponentes();
    }
    
    private void CargarComponentes(){
        VCat.btnguardar.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); 
                JOptionPane.showMessageDialog(VCat, "FUNCIONA");
            }
            
        });
        VCat.setVisible(true);
    }
}
