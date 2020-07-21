package Login;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Consultas.Impresion;
import Main.Principal;
import java.awt.Color;

@SuppressWarnings("serial")
public class Login extends JFrame {

	JLabel lUsername = new JLabel("USERNAME");
	JLabel lPassword = new JLabel("PASSWORD");
	JTextField username = new JTextField(10);
	JPasswordField password = new JPasswordField();
	JButton login = new JButton("LOGIN");
	JButton reset = new JButton("RESET");
	JCheckBox showPassword = new JCheckBox("SHOW PASSWORD");
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	
	/*private static Login log;
	
	public synchronized static Login getLogin() {
		if(log == null) {
			log = new Login();
		}
		return log;
	}*/
	
	public Login() {
		frame.setTitle("LOGIN");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/Marcacion/logodatamex.jpg")));
		placeComponents(panel);
		frame.getContentPane().add(panel);
		frame.setBounds(500, 120, 370, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void placeComponents(JPanel panel) {

		panel.setLayout(null);
		lUsername.setForeground(new Color(255, 255, 255));
		lUsername.setBackground(new Color(0, 0, 0));

		lUsername.setBounds(50, 100, 100, 30);
		panel.add(lUsername);

		username.setBounds(150, 100, 150, 30);
		panel.add(username);
		lPassword.setForeground(new Color(255, 255, 255));

		lPassword.setBounds(50, 170, 100, 30);
		panel.add(lPassword);

		password.setBounds(150, 170, 150, 30);
		panel.add(password);

		showPassword.setBounds(150, 201, 150, 30);
		showPassword.setBackground(new Color(0, 0, 0));
		panel.add(showPassword);

		login.setBounds(50, 300, 100, 30);
		panel.add(login);

		reset.setBounds(200, 300, 100, 30);
		panel.add(reset);

		JLabel foto = new JLabel();
		foto.setBounds(0, 0, 354, 361);
		panel.add(foto);
		ImageIcon data = new ImageIcon(panel.getClass().getResource("/Login/mbarakaja.jpg"));
		ImageIcon icono = new ImageIcon(data.getImage().getScaledInstance(foto.getWidth(), foto.getHeight(),
				Image.SCALE_DEFAULT));
		foto.setIcon(icono);

		login.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String userText;
				String pwdText;
				userText = username.getText();
				pwdText = password.getText();
				if (userText.equalsIgnoreCase("abenitez") && pwdText.equalsIgnoreCase("aa")) {

					//@SuppressWarnings("unused")
					frame.dispose();
					Principal principal = new Principal();
					frame.dispose();
					//frame.setVisible(false);
					
					JOptionPane.showMessageDialog(null, "Login Successful");


				}
				else {
					JOptionPane.showMessageDialog(login, "Invalid Username or Password");
				}
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

		reset.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				username.setText("");
				password.setText("");
			}

		});

		reset.addKeyListener(new KeyListener() {


			@Override
			public void keyTyped(KeyEvent e) {
				char cTeclaPresionada = e.getKeyChar();
				if(cTeclaPresionada == KeyEvent.VK_ENTER) {
					reset.doClick();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {


			}

			@Override
			public void keyReleased(KeyEvent e) {


			}

		});

		showPassword.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (showPassword.isSelected()) {
					password.setEchoChar((char) 0);
				} else {
					password.setEchoChar('*');
				}

			}

		});

		showPassword.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				char cTeclaPresionada = e.getKeyChar();
				if(cTeclaPresionada == KeyEvent.VK_ENTER) {
					showPassword.doClick();
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
		Login frame = new Login();

	}

}
