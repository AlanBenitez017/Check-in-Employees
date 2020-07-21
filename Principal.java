package Main;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Consultas.Visual;
import Login.Login;
import Marcacion.Marcacion;

@SuppressWarnings("serial")
public class Principal extends JFrame{

	
	JButton consultas = new JButton("CONSULTAS");
	JButton marcaciones = new JButton("MARCACIONES");
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	
	public Principal() {
		
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/Marcacion/logodatamex.jpg")));
		frame.setTitle("Principal");
		frame.setBounds(500, 120, 370, 400);
		placeComponents(panel);
		frame.getContentPane().add(panel);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void placeComponents(JPanel panel) {
		
		panel.setLayout(null);

		consultas.setBounds(20, 100, 150, 30);
		panel.add(consultas);

		marcaciones.setBounds(190, 100, 150, 30);
		panel.add(marcaciones);
		
		JButton login = new JButton("VOLVER AL LOGIN");
		login.setBounds(106, 185, 150, 30);
		panel.add(login);
		
		JLabel foto = new JLabel();
		foto.setBounds(0, 0, 354, 361);
		panel.add(foto);
		ImageIcon data = new ImageIcon(panel.getClass().getResource("/Main/mipatio.jpg"));
        ImageIcon icono = new ImageIcon(data.getImage().getScaledInstance(foto.getWidth(), foto.getHeight(),
        		Image.SCALE_DEFAULT));
        foto.setIcon(icono);

		consultas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
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
						frame.dispose();
						//frame.setVisible(false);
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

		marcaciones.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//@SuppressWarnings("unused")
				frame.dispose();
				Marcacion marcacion = new Marcacion();
				frame.dispose();
				//frame.setVisible(false);
				
			}

		});

		marcaciones.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				char cTeclaPresionada = e.getKeyChar();
				if(cTeclaPresionada == KeyEvent.VK_ENTER) {
					marcaciones.doClick();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {


			}

			@Override
			public void keyReleased(KeyEvent e) {


			}

		});
		
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		
				Thread t1 = new Thread(new Runnable() {
					@Override
					public void run() {
						Login login = new Login();
						frame.dispose();
					}
				});
				t1.start();
				/*frame.dispose();
				Login login = new Login();
				frame.dispose();*/
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
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Principal frame = new Principal();
	}
}
