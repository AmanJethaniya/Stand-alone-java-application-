import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudentData extends JFrame {

	private JPanel contentPane;
	private JTextField StudentText;
	private JTextField NameText;
	private JTextField PercentText;
	private JTable table;
	protected AbstractButton jTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentData frame = new StudentData();
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
	public StudentData() {
	
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 736, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student Data");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(253, 31, 245, 47);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(29, 102, 320, 290);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel StudentID = new JLabel("StudentID");
		StudentID.setBounds(29, 66, 70, 14);
		panel.add(StudentID);
		
		JLabel Name = new JLabel("Name");
		Name.setBounds(29, 115, 46, 14);
		panel.add(Name);
		
		JLabel Percent = new JLabel("Percentage");
		Percent.setBounds(29, 169, 70, 14);
		panel.add(Percent);
		
		StudentText = new JTextField();
		StudentText.setBounds(124, 63, 149, 20);
		panel.add(StudentText);
		StudentText.setColumns(10);
		
		NameText = new JTextField();
		NameText.setBounds(124, 112, 149, 20);
		panel.add(NameText);
		NameText.setColumns(10);
		
		PercentText = new JTextField();
		PercentText.setBounds(124, 166, 149, 20);
		panel.add(PercentText);
		PercentText.setColumns(10);
		
		JButton AddButton = new JButton("Add");
		AddButton.addActionListener(new ActionListener() {
			
			Connection con;
			PreparedStatement insert;
			
			
			public void actionPerformed(ActionEvent e) {
				String name=NameText.getText();
				int id=Integer.parseInt(StudentText.getText());
				int percent=Integer.parseInt(PercentText.getText());
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost/project","root","root");
					insert=con.prepareStatement("insert into student(ID,Name,Percent) values(?,?,?)");
					insert.setInt(1, id);
					insert.setString(2, name);
					insert.setInt(3, percent);
					insert.executeUpdate();
					JOptionPane.showMessageDialog(null,"record added");
					NameText.setText("");
					StudentText.setText("");
					PercentText.setText("");
					NameText.requestFocus();
					
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
		AddButton.setBounds(10, 225, 89, 23);
		panel.add(AddButton);
		
		JButton RefreshButton = new JButton("Refresh");
		RefreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connection con;
				PreparedStatement insert;
				int c;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost/project","root","root");
					insert=con.prepareStatement("Select * from student");
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
		RefreshButton.setBounds(117, 225, 89, 23);
		panel.add(RefreshButton);
		
		JButton EditButton = new JButton("Edit");
		EditButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con;
				PreparedStatement insert;
				//fe
				DefaultTableModel df = (DefaultTableModel)table.getModel();
				int selectedIndex=table.getSelectedRow();
				
				try {
					int id=Integer.parseInt(df.getValueAt(selectedIndex, 0).toString());
					String name=NameText.getText();
					//int id=Integer.parseInt(StudentText.getText());
					int percent=Integer.parseInt(PercentText.getText());
					
					
					Class.forName("com.mysql.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost/project","root","root");
					insert=con.prepareStatement("update student set  Name=?, Percent=? where ID=? ");
					insert.setInt(3, id);
					insert.setString(1, name);
					insert.setInt(2, percent);
					insert.executeUpdate();
					JOptionPane.showMessageDialog(null,"record updated");
					NameText.setText("");
					StudentText.setText("");
					PercentText.setText("");
					NameText.requestFocus();
					
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
		EditButton.setBounds(221, 225, 89, 23);
		panel.add(EditButton);
		
		JButton DeleteButton = new JButton("Delete");
		DeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//delete
				Connection con;
				PreparedStatement insert;
				DefaultTableModel df = (DefaultTableModel)table.getModel();
				int selectedIndex=table.getSelectedRow();
				try {
					int id=Integer.parseInt(df.getValueAt(selectedIndex, 0).toString());
					int dialougeResult=JOptionPane.showConfirmDialog(null, "Do you want to delete the record","Warning",JOptionPane.YES_NO_OPTION);
					if(dialougeResult==JOptionPane.YES_OPTION)
					{
						Class.forName("com.mysql.jdbc.Driver");
						con=DriverManager.getConnection("jdbc:mysql://localhost/project","root","root");
						insert=con.prepareStatement("delete from student where ID=? ");
						insert.setInt(1, id);
						insert.executeUpdate();
						JOptionPane.showMessageDialog(null,"record Deleted");
						NameText.setText("");
						StudentText.setText("");
						PercentText.setText("");
						NameText.requestFocus();
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
		DeleteButton.setBounds(117, 256, 89, 23);
		panel.add(DeleteButton);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(400, 105, 291, 280);
		contentPane.add(scrollPane_1);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				DefaultTableModel df = (DefaultTableModel)table.getModel();
				int selectedIndex=table.getSelectedRow();
			StudentText.setText( df.getValueAt(selectedIndex, 0).toString());
				NameText.setText( df.getValueAt(selectedIndex, 1).toString());
				PercentText.setText( df.getValueAt(selectedIndex, 2).toString());
				
				
			}
		});
		scrollPane_1.setViewportView(table);
		table.setSurrendersFocusOnKeystroke(true);
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setToolTipText("");
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setForeground(UIManager.getColor("ColorChooser.foreground"));
		table.setBackground(UIManager.getColor("CheckBox.interiorBackground"));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Percentage"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		JButton BackButton = new JButton("Back");
		BackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				FirstPage fp=new FirstPage();
				fp.setVisible(true);
				dispose();
				
			}
		});
		BackButton.setBounds(29, 422, 89, 23);
		contentPane.add(BackButton);
		table.getColumnModel().getColumn(0).setPreferredWidth(87);
		table.getColumnModel().getColumn(0).setMinWidth(31);
	//	table_update();
	}
	
	
	
	
	
	
	
}
