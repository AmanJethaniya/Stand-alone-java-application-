import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FirstPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstPage frame = new FirstPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FirstPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 526, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		panel.setBounds(0, 0, 114, 407);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("Welcome Admin");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setForeground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		lblNewLabel.setBounds(220, 25, 177, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Select the action you want to perform");
		lblNewLabel_1.setBounds(124, 79, 317, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Manage Student's Data");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentData std=new StudentData();
				std.setVisible(true);
				dispose();
				
				
			}
		});
		btnNewButton.setBounds(220, 134, 177, 23);
		contentPane.add(btnNewButton);
		
		JButton CheckButton = new JButton("Check Eligibility");
		CheckButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EligibleStudents est=new EligibleStudents();
				est.setVisible(true);
				dispose();
			}
		});
		CheckButton.setBounds(220, 224, 177, 23);
		contentPane.add(CheckButton);
	}
}
