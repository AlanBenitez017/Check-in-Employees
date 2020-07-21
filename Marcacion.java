package Marcacion;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Consultas.Visual;
import Login.Login;
import Main.Principal;

import java.awt.Color;

@SuppressWarnings("serial")
public class Marcacion extends JFrame {

	static String driver = "org.postgresql.Driver";
	static String ruta = "jdbc:postgresql://10.99.99.74:5432/erp_comermex_pruebas";
	static String user = "abenitez";
	static String password = "dtmx.2020";
	static JLabel lClock = new JLabel();

	JFrame frame = new JFrame("Sistema de Marcacion");
	JPanel panel = new JPanel();

	public Marcacion() {

		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/Marcacion/logodatamex.jpg")));
		clock();
		placeComponents(panel);

		frame.getContentPane().add(panel);

		frame.setSize(1382, 683);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(lClock, BorderLayout.SOUTH);
		frame.setVisible(true);

		frame.setExtendedState(MAXIMIZED_BOTH);

	}

	public void buscarLegajo(JTextField nrolegajo, JLabel datos) {

		try {
			Connection con = DriverManager.getConnection(ruta, user, password);
			PreparedStatement pst = con.prepareStatement("SELECT nombre, apellido FROM dbo.legajo WHERE nrolegajo =" + nrolegajo.getText() + " ");
			ResultSet rs = pst.executeQuery();


			while (rs.next()) {
				datos.setText(rs.getString(1) + " " + rs.getString(2));
				System.out.print(rs.getString(1));
				System.out.print(" ");
				System.out.println(rs.getString(2));
			}


		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Legajo invalido, intentar de nuevo");
		}

	}

	public void datosEmpresa(JLabel datosEmpresa, JTextField nrolegajo) {
		try {
			Connection con = DriverManager.getConnection(ruta, user, password);
			PreparedStatement pst = con.prepareStatement("SELECT idempresa FROM dbo.legajo WHERE nrolegajo =" + nrolegajo.getText() + " ");
			ResultSet rs = pst.executeQuery();


			while (rs.next()) {
				datosEmpresa.setText(rs.getString(1));
				System.out.print(rs.getString(1));
			}


		} catch (SQLException e) {

		}
	}

	public void botonIngreso(JButton ingreso, JTextField nrolegajo, JLabel datosEmpresa, Timestamp mm, Timestamp um){
		try {
			Class.forName(driver);
			Connection conne = DriverManager.getConnection(ruta, user, password);
			Statement consulta = conne.createStatement();
			Date date = new Date();  
			mm = new Timestamp(date.getTime());  
			um = new Timestamp(date.getTime());


			consulta.executeUpdate("insert into dbo.registromarcacionlegajo(idempresa, idlegajo, iddispositivomarcacion, idlocal, idtipomarca, activo, momentomarcacion, idlegajoregistro, ultmodificacion) VALUES('" 
					+ datosEmpresa.getText() + "','" + nrolegajo.getText() + "','" + 1 + "','" + 1 + "','" 
					+ 1 + "','" + "TRUE" + "','" + mm.toString() + "','" + 7 + "','" + um.toString() + "')");
			JOptionPane.showMessageDialog(null, "Registro Marcado. Buenos dias!");
		} catch (SQLException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Legajo Invalido");
		}

	}

	public void botonEgreso(JButton egreso, JTextField nrolegajo, JLabel datosEmpresa, Timestamp mm, Timestamp um) {
		try {
			Class.forName(driver);
			Connection conne = DriverManager.getConnection(ruta, user, password);
			Statement consulta = conne.createStatement();
			Date date = new Date();  
			mm = new Timestamp(date.getTime());  
			um = new Timestamp(date.getTime());
			consulta.executeUpdate("insert into dbo.registromarcacionlegajo(idempresa, idlegajo, iddispositivomarcacion, idlocal, idtipomarca, activo, momentomarcacion, idlegajoregistro, ultmodificacion) VALUES("
					+ datosEmpresa.getText() + ",'" + nrolegajo.getText() + "','" + 1 + "','" + 1 + "',' " + 2 + "','" + "TRUE" + "','" + mm.toString() + "','" + 7 + "','" + um.toString() + "')");
			JOptionPane.showMessageDialog(null, "Registro Marcado. Gracias por tu esfuerzo de hoy!");

		} catch (SQLException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Legajo Invalido");
		}

	}

	public void botonSalidaAlmuerzo(JButton salidaAlmuerzo, JTextField nrolegajo, JLabel datosEmpresa, Timestamp mm, Timestamp um) {
		try {
			Class.forName(driver);
			Connection conne = DriverManager.getConnection(ruta, user, password);
			Statement consulta = conne.createStatement();
			Date date = new Date();  
			mm = new Timestamp(date.getTime());  
			um = new Timestamp(date.getTime());
			consulta.executeUpdate("insert into dbo.registromarcacionlegajo(idempresa, idlegajo, iddispositivomarcacion, idlocal, idtipomarca, activo, momentomarcacion, idlegajoregistro, ultmodificacion) VALUES("
					+ datosEmpresa.getText() + ",'" + nrolegajo.getText() + "','" + 1 + "','" + 1 + "',' " + 3 + "','" + "TRUE" + "','" + mm.toString() + "','" + 7 + "','" + um.toString() + "')");
			JOptionPane.showMessageDialog(null, "Registro Marcado. Buen provecho!");
		} catch (SQLException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Legajo Invalido");
		}

	}

	public void botonRetornoAlmuerzo(JButton retornoAlmuerzo, JTextField nrolegajo, JLabel datosEmpresa, Timestamp mm, Timestamp um) {
		try {
			Class.forName(driver);
			Connection conne = DriverManager.getConnection(ruta, user, password);
			Statement consulta = conne.createStatement();
			Date date = new Date();  
			mm = new Timestamp(date.getTime());  
			um = new Timestamp(date.getTime());
			consulta.executeUpdate("insert into dbo.registromarcacionlegajo(idempresa, idlegajo, iddispositivomarcacion, idlocal, idtipomarca, activo, momentomarcacion, idlegajoregistro, ultmodificacion, username) VALUES("
					+ datosEmpresa.getText() + ",'" + nrolegajo.getText() + "','" + 1 + "','" + 1 + "',' " + 4 + "','" + "TRUE" + "','" + mm.toString() + "','" + 7 + "','" + um.toString() + "','" + "Admin" + "')");
			JOptionPane.showMessageDialog(null, "Registro Marcado. Buenas Tardes!");
		} catch (SQLException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Legajo Invalido");
		}

	}

	public void clock() {
		Thread clock = new Thread() {
			public void run() {
				try {
					while (true) {
						Calendar cal = new GregorianCalendar();
						Date probando = cal.getTime();
						lClock.setText(" " + probando);
						sleep(1000);
					}
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		};

		clock.start();
	}

	public void placeComponents(JPanel panel) {

		panel.setLayout(null);

		JLabel lLegajo = new JLabel("LEGAJO");
		lLegajo.setForeground(new Color(255, 255, 255));
		lLegajo.setBounds(480, 50, 86, 25);
		lLegajo.setFont(new Font("arial",Font.PLAIN,20));
		panel.add(lLegajo);

		JTextField legajo = new JTextField(10);
		legajo.setBounds(570, 50, 165, 25);
		panel.add(legajo);

		JButton aceptar = new JButton("ACEPTAR");
		aceptar.setBounds(750, 50, 100, 25);
		panel.add(aceptar);

		JLabel datosLegajo = new JLabel();
		datosLegajo.setForeground(new Color(255, 255, 255));
		datosLegajo.setBounds(520, 110, 572, 25);
		datosLegajo.setFont(new Font("arial",Font.PLAIN,20));
		panel.add(datosLegajo);

		JButton ingreso = new JButton("INGRESO");
		//ingreso.setIcon(new javax.swing.ImageIcon(Marcacion.class.getResource("/Marcacion/oficina.png")));
		ingreso.setBounds(570, 170, 165, 50);
		panel.add(ingreso);

		JButton egreso = new JButton("EGRESO");
		//egreso.setIcon(new javax.swing.ImageIcon(Marcacion.class.getResource("/Marcacion/salida.png")));
		egreso.setBounds(570, 250, 165, 50);
		panel.add(egreso);

		JButton salidaAlmuerzo = new JButton("SALIDA ALMUERZO");
		//salidaAlmuerzo.setIcon(new javax.swing.ImageIcon(Marcacion.class.getResource("/Marcacion/almuerzo.png")));
		salidaAlmuerzo.setBounds(570, 330,  165, 50);
		panel.add(salidaAlmuerzo);

		JButton retornoAlmuerzo = new JButton("RETORNO ALMUERZO");
		//retornoAlmuerzo.setIcon(new javax.swing.ImageIcon(Marcacion.class.getResource("/Marcacion/caritafeliz.png")));
		retornoAlmuerzo.setBounds(570, 410, 165, 50);
		panel.add(retornoAlmuerzo);

		/*JButton login = new JButton("LOGIN");
		login.setBounds(147, 184, 113, 23);
		panel.add(login);

		JLabel lIrA = new JLabel("IR A:");
		lIrA.setFont(new Font("Tahoma", Font.BOLD, 16));
		lIrA.setForeground(new Color(255, 255, 255));
		lIrA.setBounds(147, 150, 76, 23);
		panel.add(lIrA);

		JButton consultas = new JButton("CONSULTAS");
		consultas.setBounds(147, 223, 113, 23);
		panel.add(consultas);

		JButton principal = new JButton("PRINCIPAL");
		principal.setBounds(147, 264, 113, 23);
		panel.add(principal);*/

		JButton limpiarTexto = new JButton("Limpiar Texto");
		limpiarTexto.setBounds(582, 491, 140, 23);
		panel.add(limpiarTexto);

		JLabel datosEmpresa = new JLabel();

		JLabel foto = new JLabel();
		foto.setBounds(0, 0, 1366, 689);
		panel.add(foto);
		ImageIcon data = new ImageIcon(panel.getClass().getResource("/Marcacion/palmeras.jpg"));
		ImageIcon icono = new ImageIcon(data.getImage().getScaledInstance(foto.getWidth(), foto.getHeight(), Image.SCALE_DEFAULT));
		foto.setIcon(icono);

		Date date = new Date();
		Timestamp um = new Timestamp(date.getTime());  
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		lClock.setText(formatter.format(date));
		Timestamp mm = new Timestamp(date.getTime());  

		aceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				buscarLegajo(legajo, datosLegajo);
				datosEmpresa(datosEmpresa, legajo);
				panel.add(ingreso);
				panel.add(egreso);
				panel.add(salidaAlmuerzo);
				panel.add(retornoAlmuerzo);
			}

		});

		aceptar.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				char cTeclaPresionada = e.getKeyChar();
				if(cTeclaPresionada == KeyEvent.VK_ENTER) {
					aceptar.doClick();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {


			}

			@Override
			public void keyReleased(KeyEvent e) {


			}

		});

		ingreso.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				botonIngreso(ingreso, legajo, datosEmpresa, mm, um);			
			}


		});
		ingreso.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				char cTeclaPresionada = e.getKeyChar();
				if(cTeclaPresionada == KeyEvent.VK_ENTER) {
					ingreso.doClick();
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {


			}

			@Override
			public void keyReleased(KeyEvent e) {


			}
		});

		egreso.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				botonEgreso(egreso, legajo, datosEmpresa, mm, um);
			}


		});

		egreso.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char cTeclaPresionada = e.getKeyChar();
				if(cTeclaPresionada == KeyEvent.VK_ENTER) {
					egreso.doClick();
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {


			}

			@Override
			public void keyReleased(KeyEvent e) {


			}
		});

		salidaAlmuerzo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				botonSalidaAlmuerzo(salidaAlmuerzo, legajo, datosEmpresa, mm, um);
			}


		});

		salidaAlmuerzo.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char cTeclaPresionada = e.getKeyChar();
				if(cTeclaPresionada == KeyEvent.VK_ENTER) {
					salidaAlmuerzo.doClick();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {


			}

			@Override
			public void keyReleased(KeyEvent e) {


			}
		});

		retornoAlmuerzo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				botonRetornoAlmuerzo(retornoAlmuerzo, legajo, datosEmpresa, mm, um);
			}


		});

		retornoAlmuerzo.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char cTeclaPresionada = e.getKeyChar();
				if(cTeclaPresionada == KeyEvent.VK_ENTER) {
					retornoAlmuerzo.doClick();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {


			}

			@Override
			public void keyReleased(KeyEvent e) {


			}
		});

		limpiarTexto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				legajo.setText("");
				datosLegajo.setText("");
			}

		});

		limpiarTexto.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char cTeclaPresionada = e.getKeyChar();
				if(cTeclaPresionada == KeyEvent.VK_ENTER) {
					limpiarTexto.doClick();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {


			}

			@Override
			public void keyReleased(KeyEvent e) {


			}
		});

		/*login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//@SuppressWarnings("unused")

				Thread t1 = new Thread(new Runnable() {
					@Override
					public void run() {
						Login login = new Login();
						frame.dispose();
					}
				});
				t1.start();
				frame.dispose();
				Login login = new Login();
				//frame.setVisible(false);
				frame.dispose();
			}
		});

		login.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char cTeclaPresionada = e.getKeyChar();
				if(cTeclaPresionada == KeyEvent.VK_ENTER) {
					login.doClick();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {


			}

			@Override
			public void keyReleased(KeyEvent e) {


			}
		});

		consultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
						if ("Windows".equals(info.getName())) {
							javax.swing.UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}

				} catch (ClassNotFoundException ex) {
					java.util.logging.Logger.getLogger(Visual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
				} catch (InstantiationException ex) {
					java.util.logging.Logger.getLogger(Visual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
				} catch (IllegalAccessException ex) {
					java.util.logging.Logger.getLogger(Visual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
				} catch (javax.swing.UnsupportedLookAndFeelException ex) {
					java.util.logging.Logger.getLogger(Visual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
				}
				//</editor-fold>

				//Create and display the form 
				java.awt.EventQueue.invokeLater(new Runnable() {

					public void run() {
						frame.dispose();
						new Visual().setVisible(true);
						//frame.setVisible(false);
						frame.dispose();
					}
				});
			}
		});

		consultas.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char cTeclaPresionada = e.getKeyChar();
				if(cTeclaPresionada == KeyEvent.VK_ENTER) {
					consultas.doClick();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {


			}

			@Override
			public void keyReleased(KeyEvent e) {


			}
		});

		principal.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char cTeclaPresionada = e.getKeyChar();
				if(cTeclaPresionada == KeyEvent.VK_ENTER) {
					principal.doClick();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {


			}

			@Override
			public void keyReleased(KeyEvent e) {


			}
		});

		principal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//@SuppressWarnings("unused")
				frame.dispose();
				Principal principal = new Principal();
				frame.dispose();
				//frame.setVisible(false);
			}
		});*/


	}
	public static void main(String[] args) {

		@SuppressWarnings("unused")
		Marcacion frame = new Marcacion();
		/*try {

			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}

		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Visual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Visual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Visual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Visual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		//Create and display the form 
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				new Marcacion().setVisible(true);
			}
		});*/

	}

}