import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.SystemColor;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.Button;
import java.awt.event.ActionListener;

public class Application {

	private JFrame frmLoginPage;
	private JTextField textField;
	private JTextField textField_1;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.frmLoginPage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Application() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLoginPage = new JFrame();
		frmLoginPage.setTitle("Login Page");
		frmLoginPage.getContentPane().setBackground(SystemColor.activeCaption);
		frmLoginPage.setBounds(100, 100, 791, 479);
		frmLoginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoginPage.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Placement Cell Application");
		lblNewLabel.setBounds(239, 11, 434, 42);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 29));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBackground(new Color(240, 240, 240));
		lblNewLabel.setLabelFor(frmLoginPage);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frmLoginPage.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("User Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(252, 112, 83, 14);
		frmLoginPage.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(417, 111, 197, 20);
		frmLoginPage.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(252, 158, 83, 14);
		frmLoginPage.getContentPane().add(lblNewLabel_2);
		
		textField_1 = new JPasswordField();
		textField_1.setBounds(417, 157, 197, 20);
		
		frmLoginPage.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("@AmanJethaniya");
		lblNewLabel_3.setBounds(679, 415, 86, 14);
		frmLoginPage.getContentPane().add(lblNewLabel_3);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 170, 440);
		frmLoginPage.getContentPane().add(panel);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username=textField.getText();
				String password=textField_1.getText();
				if(username.equals("Aman")&&password.equals("admin@123"))
				{
					FirstPage firstpage=new FirstPage();
					firstpage.setVisible(true);
					frmLoginPage.dispose();
		 
					
				}
				else
				{
					JOptionPane.showMessageDialog(frmLoginPage,"Invalid username or password");
				}
				
			}
		});
		btnNewButton.setBounds(417, 227, 89, 23);
		frmLoginPage.getContentPane().add(btnNewButton);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Submit");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			
		}
	}
}
