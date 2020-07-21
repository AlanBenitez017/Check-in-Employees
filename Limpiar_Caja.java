package Consultas;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class Limpiar_Caja {
    
    public void limpiar_texto(JPanel panel, JDateChooser desdeBoton, JDateChooser hastaBoton, JComboBox<String> cbox, JTextField nrolegajo, JLabel lblNombre, JTable tabla){
        for(int i = 0; panel.getComponents().length > i; i++){
            if(panel.getComponents()[i] instanceof JTextField){
                ((JTextField)panel.getComponents()[i]).setText("");
            }
            else if(panel.getComponents()[i] instanceof JPasswordField){
                ((JPasswordField)panel.getComponents()[i]).setText("");
            }
            /*else if(panel.getComponents()[i] instanceof JComboBox){
                ((JComboBox) panel.getComponents()[i]).setSelectedItem("");
                
            }*/
            /*else if(panel.getComponents()[i] instanceof JDateChooser) {
        		((JDateChooser)panel.getComponents()[i]).setDate(null);
        	}*/
        }
        lblNombre.setText("");
        cbox.removeAllItems();
        desdeBoton.setDate(null);
        hastaBoton.setDate(null);
        nrolegajo.setText("");
        tabla.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Id", "Legajo", "Momento de Marcacion", "Tipo de Marca", "Modificar", "Eliminar"
				}
				));
    	tabla.getColumnModel().getColumn(0).setPreferredWidth(50);
		tabla.getColumnModel().getColumn(1).setPreferredWidth(50);
		tabla.getColumnModel().getColumn(2).setPreferredWidth(150);
		tabla.getColumnModel().getColumn(3).setPreferredWidth(50);
		tabla.setRowHeight(20);
        /*for(int i = 0; i < desdeBoton.getComponents().length; i++) {
        	if(desdeBoton.getComponents()[i] instanceof JDateChooser) {
        		((JDateChooser)desdeBoton.getComponents()[i]).setDate(null);
        	} 
        }*/
    }
    
}