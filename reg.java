package com.codsoft;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;

import java.awt.Color;
import javax.swing.table.DefaultTableModel;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.PreparedStatement;


import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EtchedBorder;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class reg extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtname;
	private JTextField txtmobile;
	private JTextField txtcourse;

	Connection con1;
	PreparedStatement insert;
	private JTable table;
	private JTextField textFieldID;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					reg frame = new reg();
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
	@SuppressWarnings("serial")
	public reg() {
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 1022, 609);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setBounds(23, 64, 487, 370);
		contentPane_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Student", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(contentPane_1);
		contentPane_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Student Name");
		lblNewLabel_1.setForeground(new Color(25, 25, 112));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setBounds(26, 71, 128, 14);
		contentPane_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mobile No");
		lblNewLabel_2.setForeground(new Color(25, 25, 112));
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_2.setBounds(26, 122, 116, 14);
		contentPane_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Course");
		lblNewLabel_2_1.setForeground(new Color(25, 25, 112));
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_2_1.setBounds(26, 172, 89, 14);
		contentPane_1.add(lblNewLabel_2_1);
	
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(new Color(60, 179, 113));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton.addActionListener(new ActionListener() {
			
	public void actionPerformed(ActionEvent e) {
				
				String name=txtname.getText();
				String mobile=txtmobile.getText();
				String course=txtcourse.getText();
				try {
						
					Class.forName("com.mysql.cj.jdbc.Driver"); // new driver class
					con1= DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdatabase", "root", "root");
					
					  insert = con1.prepareStatement("insert into record (Name,MobileNumber,Course)values(?,?,?)");
					insert.setString(1, name);
					insert.setString(2, mobile);
					insert.setString(3, course);
					
					insert.executeUpdate();
				
					JOptionPane.showMessageDialog(null, "Recorded Added");
					
					txtname.setText("");
					txtmobile.setText("");
					txtcourse.setText("");
				
					
					table_update();
					txtname.requestFocus();
					
					
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			
			}
		});
		btnNewButton.setBounds(129, 246, 89, 34);
		contentPane_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Edit");
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBackground(new Color(0, 128, 255));
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dfDefaultTableModel=(DefaultTableModel)table.getModel();
			    int selectedIndex = table.getSelectedRow();
			    try {   
			        
			    int id = Integer. parseInt(dfDefaultTableModel.getValueAt(selectedIndex, 0).toString());
			    String name =txtname.getText();
			    String mobile =txtmobile.getText();
			    String course =txtcourse.getText();
			  
				Class.forName("com.mysql.cj.jdbc.Driver"); // new driver class
				con1= DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdatabase", "root", "root");
			    insert = con1.prepareStatement("update record set name=?,mobileNumber=?, course=? where ID=?");
			    insert.setString(1,name);
			    insert.setString(2,mobile);
			    insert.setString(3,course);
			    insert.setInt(4,id);
			    insert.executeUpdate();
			    JOptionPane.showMessageDialog(null, "Record Updated");
			    txtname.setText("");
			    txtmobile.setText("");
			    txtcourse.setText("");
			    table_update();
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			   
			} catch (SQLException ex) {
					ex.printStackTrace();
			}
			}
		});
		btnNewButton_1.setBounds(228, 246, 89, 34);
		contentPane_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setBackground(new Color(255, 0, 0));
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_2.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				
		          DefaultTableModel model = (DefaultTableModel) table.getModel();
		          int selectedIndex = table.getSelectedRow();
		            try {   
		                
		            int id = Integer.parseInt(model.getValueAt(selectedIndex, 0).toString());
		            int dialogResult = JOptionPane.showConfirmDialog (null, "Do you want to Delete the record","Warning",JOptionPane.YES_NO_OPTION);
		            if(dialogResult == JOptionPane.YES_OPTION){

		            	Class.forName("com.mysql.cj.jdbc.Driver"); // new driver class
						con1= DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdatabase", "root", "root");
		            insert = con1.prepareStatement("delete from record where ID= ?");
		        
		            insert.setInt(1,id);
		            insert.executeUpdate();
		            JOptionPane.showMessageDialog(null, "Record Delete");
		            txtname.setText("");
		            txtmobile.setText("");
		            txtcourse.setText("");

		            table_update();
		           
		           }

		        } catch (ClassNotFoundException ex) {
		        
		        } catch (SQLException ex) {
		            
		        }
				
				
				
			}
		});
		btnNewButton_2.setBounds(327, 246, 89, 34);
		contentPane_1.add(btnNewButton_2);
		
		txtname = new JTextField();
		txtname.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtname.setForeground(new Color(25, 25, 112));
		txtname.setBounds(152, 60, 264, 34);
		contentPane_1.add(txtname);
		txtname.setColumns(10);
		
		txtmobile = new JTextField();
		txtmobile.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtmobile.setForeground(new Color(25, 25, 112));
		txtmobile.setColumns(10);
		txtmobile.setBounds(152, 108, 264, 34);
		contentPane_1.add(txtmobile);
		
		txtcourse = new JTextField();
		txtcourse.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtcourse.setForeground(new Color(25, 25, 112));
		txtcourse.setColumns(10);
		txtcourse.setBounds(152, 162, 264, 34);
		contentPane_1.add(txtcourse);
		
		JButton clearbutton = new JButton("Clear");
		clearbutton.setBorderPainted(false);
		clearbutton.setBackground(new Color(123, 104, 238));
		clearbutton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		clearbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtname.setText("");
				txtmobile.setText("");
				txtcourse.setText("");
				txtname.requestFocus();
			}
		});
		clearbutton.setBounds(26, 246, 89, 34);
		contentPane_1.add(clearbutton);
		
		JLabel lblNewLabel = new JLabel("Student Management");
		lblNewLabel.setForeground(new Color(25, 25, 112));
		lblNewLabel.setBounds(354, 10, 247, 29);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(135, 206, 250));
		scrollPane.setBounds(534, 64, 404, 370);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(20);
		table.setForeground(new Color(25, 25, 112));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int selectedIndex =table .getSelectedRow();
				      
				txtname.setText(model.getValueAt(selectedIndex, 1).toString());
				txtmobile.setText(model.getValueAt(selectedIndex, 2).toString());
				txtcourse.setText(model.getValueAt(selectedIndex, 3).toString());
			}
		});
		table.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		table.setBackground(new Color(255, 255, 255));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "MobileNumber", "Course"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Integer.class, String.class
			};
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(146);
		table.getColumnModel().getColumn(2).setPreferredWidth(113);
		table.getColumnModel().getColumn(3).setPreferredWidth(101);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search here...", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(25, 25, 112)));
		panel.setBounds(23, 445, 689, 83);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textFieldID = new JTextField();
		textFieldID.setFont(new Font("Times New Roman", Font.BOLD, 13));
		textFieldID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				 try {
                     
                     String id = textFieldID.getText();
                        insert = con1.prepareStatement("select name,mobileNumber,course from record where id = ?");
                        insert.setString(1, id);
                         ResultSet rs = insert.executeQuery();
                     if(rs.next()==true)
                     {
                       
                         String name = rs.getString(1);
                         String mobile = rs.getString(2);
                         String course = rs.getString(3);
                         
                         txtname.setText(name);
                         txtmobile.setText(mobile);
                         txtcourse.setText(course);
 
                     }   
                     else
                     {
                    	 txtname.setText("");
                    	 txtmobile.setText("");
                    	 txtcourse.setText("");
                          
                     }
                 } 
             
              catch (SQLException ex) {
            	ex.printStackTrace();
                    
                 }
         }
				
			
		});
		textFieldID.setBounds(94, 22, 451, 36);
		panel.add(textFieldID);
		textFieldID.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("ID");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_3.setBounds(26, 33, 46, 14);
		panel.add(lblNewLabel_3);
		table_update();
		
	}

	
	private void table_update() {

		int c;
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver"); // new driver class
			con1= DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdatabase", "root", "root");
			
			insert =con1.prepareStatement("select * from record");
			ResultSet rSet=insert.executeQuery();
 			ResultSetMetaData rssData=rSet.getMetaData();
			
			c=rssData.getColumnCount();
		
			DefaultTableModel dfDefaultTableModel=(DefaultTableModel)table.getModel();
			dfDefaultTableModel.setRowCount(0);
			while(rSet.next())
			{
				Vector v2=new Vector();
				for (int a = 1; a <=c; a++) {
					v2.add(rSet.getString("ID"));
					v2.add(rSet.getString("name"));
					v2.add(rSet.getString("mobileNumber"));
					v2.add(rSet.getString("course"));
			}
				dfDefaultTableModel.addRow(v2);
			}
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
	}
}
