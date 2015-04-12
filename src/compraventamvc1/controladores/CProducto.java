/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compraventamvc1.controladores;


import compraventamvc1.modelo.datos.Producto;
import compraventamvc1.modelo.negocio.NCategoria;
import compraventamvc1.modelo.negocio.NProducto;
import compraventamvc1.modelo.utilidades.jTableModel;
import compraventamvc1.vista.VProducto;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


/**
 *
 * @author BDiamond
 */
public class CProducto {

    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//       new CProducto();
//
//    }
    private VProducto vistaP;
    private NProducto negoP;
    private jTableModel tableModel;
    private DefaultComboBoxModel comboBoxModel;
    private int selectedRowIndex;
    private NCategoria cat;
    private Producto selectedItem;
    public CProducto() {
        cat=new NCategoria();
        vistaP=new VProducto();
        vistaP.setVisible(true);
        negoP = new NProducto();
        CargarComponentes();
                
    }
    
    private void CargarComponentes(){
        comboBoxModel=new DefaultComboBoxModel(cat.ListarCategoria().toArray());
        vistaP.comboproducto.setModel(comboBoxModel);
        vistaP.btnInsertar.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Producto p = new Producto();
                p.setNombre(vistaP.txtnombre.getText());
                p.setPrecio(Float.valueOf(vistaP.txtprecio.getText()));
                try {
                    negoP.InsertarProducto(p);
                    vistaP.txtid.setText(p.getIdProducto().toString());
                    JOptionPane.showMessageDialog(vistaP, "Producto Insertado Correctamente", "Exito",JOptionPane.INFORMATION_MESSAGE);
                    
                } catch (Exception ex) {
                    Logger.getLogger(CProducto.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(vistaP, ex.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
                }
            }
           });
        vistaP.btnBuscar.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Producto p = new Producto();
                p.setNombre(vistaP.txtnombre.getText());
                try {
                   List<Producto> listita=negoP.BuscarProductoPorNombre(p);
                   Object vector[]={"IdProdcuto","Nombre","Precio"} ;
                   Object o [][]=new Object[listita.size()][3];
                   if(listita!=null && !listita.isEmpty()){ 
                    for (Producto objects : listita) {
                         int i=listita.indexOf(objects);
                         o[i][0]=objects.getIdProducto();
                         o[i][1]=objects.getNombre();
                         o[i][2]=objects.getPrecio();
                    }
                    tableModel= new jTableModel(vector, o);
                    vistaP.tablaproducto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    vistaP.tablaproducto.setModel(tableModel);
                    
                   }else
                    JOptionPane.showMessageDialog(vistaP, "No se encontro ningun producto","Mensaje de Error", JOptionPane.WARNING_MESSAGE);
                } catch (Exception er) { 
                    er.printStackTrace();
                    JOptionPane.showMessageDialog(vistaP, er.getMessage());
                }
            
            }
    });
    
    vistaP.btnModificar.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if(selectedItem!=null){
                        if(vistaP.txtnombre.getText().trim().length()>0
                                && vistaP.txtnombre.getText().trim().length()>0
                                && vistaP.txtprecio.getText().trim().length()>0){
                            selectedItem.setNombre(vistaP.txtnombre.getText());
                            selectedItem.setPrecio(Float.valueOf(vistaP.txtprecio.getText()));
                            try {
                                selectedItem=negoP.ModificarProducto(selectedItem);
                                tableModel.setValueAt(selectedItem.getNombre(), selectedRowIndex, 1);
                                tableModel.setValueAt(selectedItem.getPrecio(), selectedRowIndex, 2);
                                vistaP.tablaproducto.updateUI();
                                if(selectedItem!=null){
                                    JOptionPane.showMessageDialog(vistaP, "Producto Actualizado");
                                }
                            } catch (Exception ex) {
                                Logger.getLogger(CProducto.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                }
                
            }
    });
    vistaP.tablaproducto.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(tableModel!=null){
                    selectedRowIndex=vistaP.tablaproducto.getSelectedRow();
                        if(selectedRowIndex>-1 && selectedRowIndex<tableModel.getRowCount()){
                        selectedItem=new Producto();
                        selectedItem.setIdProducto((Integer) tableModel.getValueAt(selectedRowIndex, 0));
                        vistaP.txtid.setText(String.valueOf(selectedItem.getIdProducto()));
                        selectedItem.setNombre((String) tableModel.getValueAt(selectedRowIndex, 1));
                        vistaP.txtnombre.setText(selectedItem.getNombre());
                        selectedItem.setPrecio((float) tableModel.getValueAt(selectedRowIndex, 2));
                        vistaP.txtprecio.setText(String.valueOf(selectedItem.getPrecio()));
                    }
                }
            }
        });
    }
}
