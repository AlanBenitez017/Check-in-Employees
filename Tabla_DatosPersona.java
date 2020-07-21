package Consultas;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class Tabla_DatosPersona{

	Metodos met = null;
	JTextField nrolegajo;
	JDateChooser desdeBoton;
	JDateChooser hastaBoton;
	static String bd = "erp_comermex_pruebas";
	static String login = "abenitez";
	static String password = "dtmx.2020";
	static String driver = "org.postgresql.Driver";
	static String url = "jdbc:postgresql://10.99.99.74:5432/"+bd;

	public static void buscarLegajo(JTextField nrolegajo, JLabel lblNombre) {

		try {
			Connection con = DriverManager.getConnection(url, login, password);
			PreparedStatement pst = con.prepareStatement("SELECT nombre, apellido FROM dbo.legajo WHERE nrolegajo =" + nrolegajo.getText() + " ");
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				lblNombre.setText(rs.getString(1) + " " + rs.getString(2));
				System.out.print(rs.getString(1));
				System.out.print(" ");
				System.out.println(rs.getString(2));
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Legajo invalido, intentar de nuevo");
		}

	}

	public void visualizar_DatosPersona(JTable tabla, JTextField nrolegajo, JDateChooser desdeBoton, JDateChooser hastaBoton, JComboBox<String> cbox){

		tabla.setDefaultRenderer(Object.class, new Render());
		@SuppressWarnings("serial")
		DefaultTableModel dt = new DefaultTableModel(){
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};    
		dt.addColumn("Id");
		dt.addColumn("Legajo");
		dt.addColumn("Momento de Marcacion");
		dt.addColumn("Tipo de marca");
		dt.addColumn("Modificar");
		dt.addColumn("Eliminar");

		JButton btn_modificar = new JButton("Modificar");
		btn_modificar.setName("m");
		JButton btn_eliminar = new JButton("Eliminar");
		btn_eliminar.setName("e");

		met = new Metodos();
		DatosPersona dp = new DatosPersona();
		ArrayList<DatosPersona> list = null;
		try {
			list = met.Listar_DatosPersona(nrolegajo, desdeBoton, hastaBoton);
		}catch(Exception e) {
			cbox.removeAllItems();
		}
		if(list.size() > 0){
			for(int i = 0; i < list.size(); i++){
				Object fila[] = new Object[6];
				dp = list.get(i);
				fila[0] = dp.getId();
				fila[1] = dp.getIdLegajo();
				fila[2] = dp.getMomentoMarcacion();
				fila[3] = dp.getIdTipoMarca();
				if(fila[3].equals(1)) {
					fila[3] = "Ingreso";
				}
				if(fila[3].equals(2)) {
					fila[3] = "Egreso";
				}
				if(fila[3].equals(3)) {
					fila[3] = "Salida Almuerzo";
				}
				if(fila[3].equals(4)) {
					fila[3] = "Retorno Almuerzo";
				}
				fila[4] = btn_modificar;
				fila[5] = btn_eliminar;
				dt.addRow(fila);
			}
			
			tabla.setModel(dt);
			tabla.getColumnModel().getColumn(0).setPreferredWidth(50);
			tabla.getColumnModel().getColumn(1).setPreferredWidth(50);
			tabla.getColumnModel().getColumn(2).setPreferredWidth(150);
			tabla.getColumnModel().getColumn(3).setPreferredWidth(50);
			tabla.setRowHeight(20);
		}

		try {
			Connection con = DriverManager.getConnection(url, login, password);
			PreparedStatement pst = con.prepareStatement("select descripciontipomarca from dbo.tipomarca t ");
			ResultSet rs = pst.executeQuery();

			
			cbox.addItem("");
			while(rs.next()){
				cbox.addItem(rs.getString(1));

			}

		} catch(Exception e1) {
			JOptionPane.showMessageDialog(null, "Actualizado con exito");
			cbox.removeAllItems();
		}
	}

}
