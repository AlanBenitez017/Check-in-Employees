package Consultas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;


/*Metodo listar*/
public class Metodos{

	static String bd = "erp_comermex_pruebas";
	static String login = "abenitez";
	static String password = "dtmx.2020";
	static String driver = "org.postgresql.Driver";
	static String url = "jdbc:postgresql://10.99.99.74:5432/"+bd;

	public ArrayList<DatosPersona> Listar_DatosPersona(JTextField nrolegajo, JDateChooser desdeBoton, JDateChooser hastaBoton){

		ArrayList<DatosPersona> list = new ArrayList<DatosPersona>();
		JComboBox<String> combo = null;
		Conectar conec = new Conectar();
		SimpleDateFormat dateFormat;
		String startDateString = null;
		String endDateString = null;
		String sql = null;
		try {
			dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			startDateString = dateFormat.format(desdeBoton.getDate());
			endDateString = dateFormat.format(hastaBoton.getDate());

			sql = "SELECT id, idlegajo, momentomarcacion, idtipomarca FROM dbo.registromarcacionlegajo WHERE idlegajo = " + nrolegajo.getText() + 
					" AND momentomarcacion between '" + startDateString.toString() + "' AND '" + endDateString.toString() + "'" + "AND activo = " + true;
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Fecha Invalida. Favor introducir la fecha en formato MMM DD, AAAA");
			combo.removeAllItems();
		}
		ResultSet rs = null;
		PreparedStatement ps = null;

		try{
			ps = conec.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				DatosPersona dp = new DatosPersona();
				dp.setId(rs.getInt(1));
				dp.setIdLegajo(rs.getInt(2)); 
				dp.setMomentoMarcacion(rs.getString(3));
				dp.setIdTipoMarca(rs.getInt(4));
				list.add(dp);
			}
		}catch(NullPointerException | SQLException ex){
			//JOptionPane.showMessageDialog(null, "Fecha Invalida. Favor introducir la fecha en formato MMM DD, AAAA");
		}finally{
			try{
				ps.close();
				rs.close();
				conec.desconectar();
			}catch(NullPointerException | SQLException ex){
				//JOptionPane.showMessageDialog(null, "Fecha Invalida. Favor introducir la fecha en formato MMM DD, AAAA");
			}
		}
		return list;
	}


	/*Metodo agregar*/
	public void Agregar_DatosPersona(DatosPersona dp, JComboBox<String> combo, JLabel datosEmpresa){
		Conectar conec = new Conectar();
		String sql = "INSERT INTO dbo.registromarcacionlegajo (idlegajo, momentomarcacion, idtipomarca, iddispositivomarcacion, idempresa, idlegajoregistro, idlocal, activo, ultmodificacion, username) VALUES(?,?::timestamp,?::bigint, ?, ?::bigint, ?::bigint, ?::bigint, ?, ?::timestamp ,?);";
		PreparedStatement ps = null;
		try{
			ps = conec.getConnection().prepareStatement(sql);
			ps.setInt(1, dp.getIdLegajo());
			ps.setString(2, dp.getMomentoMarcacion());
			if(combo.getSelectedItem().toString().equals("RETORNO ALMUERZO")) {
				ps.setInt(3, 4);
			}

			else if(combo.getSelectedItem().toString().equals("INGRESO")) {
				ps.setInt(3, 1);
			}

			else if(combo.getSelectedItem().toString().equals("EGRESO")) {
				ps.setInt(3, 2);
			}

			else if(combo.getSelectedItem().toString().equals("SALIDA ALMUERZO")) {
				ps.setInt(3, 3);
			}
			ps.setInt(4, 1);
			ps.setString(5, datosEmpresa.getText());
			ps.setInt(6, 7);
			ps.setInt(7, 1);
			ps.setBoolean(8, true);
			ps.setString(9, dp.getMomentoMarcacion());
			ps.setString(10, "Admin");
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Registro agregado con exito");
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, "Error. Formato de fecha (AAAA-MM-DD HH:MM:SS.MS) o tipo de marca invalido");
		}finally{
			try{
				ps.close();
				conec.desconectar();
			}catch(Exception ex){}
		}
	}


	/*Metodo Modificar*/
	public void Modificar_DatosPersona(DatosPersona dp, JComboBox<String> combo){
		Conectar conec = new Conectar();
		String sql = "UPDATE dbo.registromarcacionlegajo SET idLegajo = ?, momentomarcacion = ?::timestamp, idtipomarca = ?::bigint WHERE id = ?;";
		PreparedStatement ps = null;
		try{
			ps = conec.getConnection().prepareStatement(sql);
			ps.setInt(1, dp.getIdLegajo());
			ps.setString(2, dp.getMomentoMarcacion());
			//ps.setString(3, (String) combo.getSelectedItem());
			if(combo.getSelectedItem().toString().equals("RETORNO ALMUERZO")) {
				ps.setInt(3, 4);
			}

			else if(combo.getSelectedItem().toString().equals("INGRESO")) {
				ps.setInt(3, 1);
			}

			else if(combo.getSelectedItem().toString().equals("EGRESO")) {
				ps.setInt(3, 2);
			}

			else if(combo.getSelectedItem().toString().equals("SALIDA ALMUERZO")) {
				ps.setInt(3, 3);
			}
			ps.setInt(4,dp.getId());
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Actualizado con exito");
		}catch(Throwable ex){
			JOptionPane.showMessageDialog(null, "Error. Favor introducir la fecha en formato AAAA-MM-DD HH:MM:SS.MS");
		}finally{
			try{
				ps.close();
				conec.desconectar();
			}catch(Exception ex){}
		}
	}

	/*Metodo Eliminar*/
	public void Eliminar_DatosPersona(DatosPersona dp){
		Conectar conec = new Conectar();
		String sql = "UPDATE dbo.registromarcacionlegajo SET activo = FALSE WHERE id = ?;";
		//String sql = "DELETE FROM dbo.registromarcacionlegajo WHERE id = ?;";
		PreparedStatement ps = null;
		try{
			ps = conec.getConnection().prepareStatement(sql);
			ps.setInt(1, dp.getId());
			ps.executeUpdate();
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}finally{
			try{
				ps.close();
				conec.desconectar();
			}catch(Exception ex){}
		}
	}

	public static void datosEmpresa(JLabel datosEmpresa, JTextField nrolegajo) {
		try {
			Connection con = DriverManager.getConnection(url, login, password);
			PreparedStatement pst = con.prepareStatement("SELECT idempresa FROM dbo.legajo WHERE nrolegajo =" + nrolegajo.getText() + " ");
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				datosEmpresa.setText(rs.getString(1));
				System.out.print(rs.getString(1));
			}


		} catch (SQLException e) {
			//JOptionPane.showMessageDialog(null, "Legajo invalido, intentar de nuevo");
		}
	}

}