/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compraventamvc1.controladores;

import compraventamvc1.modelo.datos.Cliente;
import compraventamvc1.modelo.datos.Detalleventa;
import compraventamvc1.modelo.datos.Notaventa;
import compraventamvc1.modelo.datos.Producto;
import compraventamvc1.modelo.datos.TiendaSucursal;
import compraventamvc1.modelo.negocio.NCliente;
import compraventamvc1.modelo.negocio.NProducto;
import compraventamvc1.modelo.negocio.NTienda_Sucursal;
import compraventamvc1.modelo.negocio.NVenta;
import compraventamvc1.modelo.utilidades.DataBaseHelper;
import compraventamvc1.modelo.utilidades.jTableModel;
import compraventamvc1.vista.VVenta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.hibernate.HibernateException;

/**
 *
 * @author Lion
 */
public class CVenta {
    private VVenta vVenta;
    private NVenta nVenta;
    private NCliente nCliente;
    private NProducto nProducto;
    private NTienda_Sucursal nTiendaSucursal;
    private DefaultComboBoxModel modelCliente;
    private DefaultComboBoxModel modelTienda;
    private DefaultComboBoxModel modelProducto;
    private jTableModel modelTabla;
    private Cliente selecteCliente;
    private TiendaSucursal selectedTienda;
    private List<Detalleventa> listDetalle;
    private Detalleventa selectedDetalleVenta;
    private String colum[]={"IdProducto","Nombre","Precio"};
    private Object row[][];
    private int selecteRowTable;
    private Notaventa notaVenta;
    //private Detalleventa detalleVenta;
    private Date toDayDate;
    public CVenta() {
        nVenta=new NVenta();
        nCliente=new NCliente();
        nProducto=new NProducto();
        vVenta=new VVenta();
        nTiendaSucursal=new NTienda_Sucursal();
        initComponent();
    }
    private void initComponent(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        toDayDate = new Date();
        vVenta.txtFecha.setText(dateFormat.format(toDayDate));
        vVenta.addWindowListener(new WindowAdapter() {

            @Override
            public void windowActivated(WindowEvent e) {
                super.windowActivated(e); 
                System.out.println("Ventana venta activada");
            }

            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);
                System.out.println("Ventana venta abierta");
                listDetalle=new ArrayList<>();
                row=new Object[listDetalle.size()][3];
                modelTabla=new jTableModel(colum,row);
                vVenta.Tablita.setModel(modelTabla);
                List<Cliente> cliente=nCliente.ListarCliente();
                if(cliente!=null){
                    modelCliente=new DefaultComboBoxModel(cliente.toArray());
                    vVenta.ComboBoxCliente.setModel(modelCliente);
                }
                List<TiendaSucursal> tiendas=nTiendaSucursal.ListarTienda();
                if(tiendas!=null){
                    modelTienda=new DefaultComboBoxModel(tiendas.toArray());
                    vVenta.ComboBoxTienda.setModel(modelTienda);
                }
                List<Producto> lp=nProducto.ListarProducto();
                if(lp!=null){
                    modelProducto=new DefaultComboBoxModel(lp.toArray());
                    vVenta.ComboBoxProducto.setModel(modelProducto);
                }
            }
            
            
        });  
        vVenta.btnBuscar.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); 
                if(!vVenta.txtIdVenta.isEnabled()){
                    vVenta.txtIdVenta.setEnabled(true);
                }else if(vVenta.txtIdVenta.getText().trim().length()>0){
                    Notaventa bNotaVenta=new Notaventa();
                    bNotaVenta.setIdNotaVenta(Integer.valueOf(vVenta.txtIdVenta.getText()));
                    List<Detalleventa> dVenta=nVenta.buscarVenta(bNotaVenta);
                    if(dVenta!=null && dVenta.size()>0){
                        selecteCliente=nVenta.buscarClienteNota(bNotaVenta);
                        for(int i=0;i<modelCliente.getSize();i++){
                            Cliente c= (Cliente) modelCliente.getElementAt(i);
                            if(c.getNitCi()==selecteCliente.getNitCi()){
                                vVenta.ComboBoxCliente.setSelectedIndex(i);
                                i=modelCliente.getSize()+2;
                            }
                        }
                        vVenta.ComboBoxCliente.setSelectedItem(selecteCliente);
                    }
                    //vVenta.ComboBoxCliente.setSelectedItem(dVenta.get(0).getNotaventa().getCliente());
                    //vVenta.ComboBoxTienda.setSelectedItem(dVenta.get(0).getProducto().getTiendaSucursals());
                    listDetalle=dVenta;
                    initTablaBase();
                }
            }  
        });
        vVenta.ComboBoxTienda.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                 JComboBox comboBox = (JComboBox) e.getSource();
                 selectedTienda=(TiendaSucursal) comboBox.getSelectedItem();
            }
        });  
        vVenta.ComboBoxCliente.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();
                selecteCliente=(Cliente) comboBox.getSelectedItem();
            }
        });
        vVenta.ComboBoxProducto.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();
                Producto p=(Producto) comboBox.getSelectedItem();
                if(listDetalle!=null){
                    int i=0;
                    for (i=0;i<listDetalle.size();i++) {
                        Detalleventa deVenta=listDetalle.get(i);
                        if(deVenta.getProducto().equals(p)){
                            deVenta.setPrecio(deVenta.getPrecio()+p.getPrecio());
                            i=listDetalle.size()+3;
                        }
                    }
                    if(i<listDetalle.size()+2){
                        Detalleventa deVenta=new Detalleventa();
                        deVenta.setProducto(p);
                        deVenta.setPrecio(p.getPrecio());
                        listDetalle.add(deVenta);
                    }
                }
                initTabla();
                
            }
        });
        vVenta.btnNuevo.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); 
                vVenta.txtFecha.setText("");
                vVenta.txtIdVenta.setText("");
                vVenta.txtNitCi.setText("");
                vVenta.txtTotal.setText("");
                row=new Object[1][3];
                modelTabla=new jTableModel(colum, row);
                vVenta.Tablita.setModel(modelTabla);
                listDetalle=new ArrayList<>();
                        
            }
            
        });
        vVenta.btnGuardar.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); 
               if(selecteCliente!=null && selectedTienda!=null && listDetalle!=null 
                       && listDetalle.size()>0){
                   notaVenta=new Notaventa();
                   notaVenta.setCliente(selecteCliente);
                   notaVenta.setFechaHora(toDayDate);
                   
                   try{
                      nVenta.insertVenta(notaVenta, listDetalle);
                      vVenta.txtIdVenta.setText(String.valueOf(notaVenta.getIdNotaVenta()));
                       JOptionPane.showMessageDialog(vVenta, "Nota Guardada");
                   }catch(HibernateException he){
                       
                       System.out.println("-------------Error al guardar la nota----");
                       System.out.println(he.getMessage());
                       he.printStackTrace();
                   }
               }else{
                   JOptionPane.showMessageDialog(vVenta, "Debe seleccionar: Cliente, "
                           + "Tienda y los productos a vender");
               }
            }
            
        });
        vVenta.btnQuitarProducto.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); 
                if(selectedDetalleVenta!=null){
                    listDetalle.remove(selectedDetalleVenta);
                    initTabla();
                    selectedDetalleVenta=null;
                }
            }
            
        });
        vVenta.Tablita.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(modelTabla!=null){
                    selecteRowTable=vVenta.Tablita.getSelectedRow();
                        if(selecteRowTable>-1 && selecteRowTable<modelTabla.getRowCount()){
                        selectedDetalleVenta=new Detalleventa();
                        selectedDetalleVenta=listDetalle.get(selecteRowTable);
                    }
                }
            }
        });
        vVenta.setVisible(true);
    }
    
    private void initTabla(){
        row=new Object[listDetalle.size()][3];
        int count=0;
        float acumPrecio=0;
            for (Detalleventa selectProducto1 : listDetalle) {
                Producto p;
                    p=selectProducto1.getProducto();
                row[count][0]=p.getIdProducto();
                row[count][1]=p.getNombre();
                row[count][2]=selectProducto1.getPrecio();
                acumPrecio=acumPrecio+selectProducto1.getPrecio();
                count++;
            }
            vVenta.txtTotal.setText(String.valueOf(acumPrecio));
            modelTabla=new jTableModel(colum, row);
            vVenta.Tablita.setModel(modelTabla);
    }
    private void initTablaBase(){
        row=new Object[listDetalle.size()][3];
        int count=0;
        float acumPrecio=0;
            for (Detalleventa selectProducto1 : listDetalle) {
                Producto p;
                
                   p= nVenta.getProductoDetalle(selectProducto1);
                
                row[count][0]=p.getIdProducto();
                row[count][1]=p.getNombre();
                row[count][2]=selectProducto1.getPrecio();
                acumPrecio=acumPrecio+selectProducto1.getPrecio();
                count++;
            }
            vVenta.txtTotal.setText(String.valueOf(acumPrecio));
            modelTabla=new jTableModel(colum, row);
            vVenta.Tablita.setModel(modelTabla);
    }
}
