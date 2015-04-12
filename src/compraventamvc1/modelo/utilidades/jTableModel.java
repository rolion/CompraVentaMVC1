/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compraventamvc1.modelo.utilidades;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author BDiamond
 */
    public class jTableModel extends AbstractTableModel {

    private Object[] colum;
    private Object[][] dataRow;
    private boolean edithable;

    public jTableModel() {
        edithable=false;
    }

    public void setEdithable(boolean edithable) {
        this.edithable = edithable;
    }

    
    public jTableModel(Object[] colum, Object[][] dataRow) {
        this.colum = colum;
        this.dataRow = dataRow;
        this.edithable=false;
    }
    
    @Override
    public int getRowCount() {
        return dataRow.length;
    }

    @Override
    public int getColumnCount() {
       return colum.length;
    }

    @Override
    public Object getValueAt(int r, int c) {
//      if(r>0 && r<this.dataRow.length &&
//              c>0 && c<this.colum.length){
          return dataRow[r][c];
//      }
//        return null;
    }

    @Override
    public String getColumnName(int column) {
        return colum[column].toString();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(this.edithable)
        {
            return columnIndex>0;
        }else
            
            return false;
    }

//    @Override
//    public Class<?> getColumnClass(int columnIndex) {
//        return getValueAt(0, columnIndex).getClass();
//        
//    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //super.setValueAt(aValue, rowIndex, columnIndex);
        this.dataRow[rowIndex][columnIndex]=aValue;
//        //fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public void fireTableCellUpdated(int row, int column) {
        super.fireTableCellUpdated(row, column); //To change body of generated methods, choose Tools | Templates.
    }
    

    @Override
    public void fireTableChanged(TableModelEvent e) {
        super.fireTableChanged(e);
        
    }
    
 }

