/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compraventamvc1.controladores;

import compraventamvc1.vista.VProducto;

/**
 *
 * @author Lion
 */
public class CProveedor {

    private VProducto vProducto;
    public CProveedor() {
       vProducto=new VProducto();
        initComponent();
      
    
    }
    private void initComponent(){
        vProducto.setVisible(true);
    }
    
    
}
