import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class EligibleStudents extends JFrame {

	private JPanel contentPane;
	private JTextField CutOffText;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EligibleStudents frame = new EligibleStudents();
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
	public EligibleStudents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 704, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Insert the cutoff percentage");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(125, 5, 389, 34);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("ComboBox.buttonShadow"));
		panel.setBounds(41, 98, 225, 133);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Type aggregate (avoid decimals)");
		lblNewLabel_1.setBackground(UIManager.getColor("FormattedTextField.selectionBackground"));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 11, 205, 24);
		panel.add(lblNewLabel_1);
		
		CutOffText = new JTextField();
		CutOffText.setBounds(43, 61, 86, 20);
		panel.add(CutOffText);
		CutOffText.setColumns(10);
		
		JButton btnNewButton = new JButton("List of Eligible Students");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cutoff=Integer.parseInt(CutOffText.getText());
				
				Connection con;
				PreparedStatement insert;
				int c;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost/project","root","root");
					insert=con.prepareStatement("Select * from student where Percent>=?");
					insert.setInt(1, cutoff);
					ResultSet rs=insert.executeQuery();
					ResultSetMetaData Rss=rs.getMetaData();
					c=Rss.getColumnCount();
					
					 DefaultTableModel df;
					
						 df = (DefaultTableModel) table.getModel();
						df.setRowCount(0);
					
					
					while(rs.next())
					{
						Vector v2=new Vector();
						
							v2.add(rs.getInt("ID"));
							v2.add(rs.getString("Name"));
							v2.add(rs.getInt("Percent"));
							
							df.addRow(v2);
							
							
							
						
					}
					
					
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setBounds(25, 99, 149, 23);
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(373, 98, 275, 217);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Percent"
			}
		));
		table.setToolTipText("");
		scrollPane.setViewportView(table);
		
		JButton BackButton = new JButton("Back");
		BackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FirstPage fp=new FirstPage();
				fp.setVisible(true);
				dispose();
				
			}
		});
		BackButton.setBounds(10, 291, 89, 23);
		contentPane.add(BackButton);
		
		
	}

}
