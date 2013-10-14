package org.adm.project.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import org.adm.project.SessionData;
import org.adm.project.dao.UserDao;

public class LoginFrame extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	JButton btnLogin = new JButton("Login");
	private UserDao userDao;

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setVisible(true);
		
		JLabel lblUserName = new JLabel("User Name");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		 btnLogin = new JButton("Login");
		 btnLogin.addActionListener(this);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("images/books.jpg"));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(116)
					.addComponent(lblUserName)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(40, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(27, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(23))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(226, Short.MAX_VALUE)
					.addComponent(btnLogin)
					.addGap(141))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addGap(55)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUserName))
					.addGap(18)
					.addComponent(btnLogin)
					.addContainerGap(65, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		userDao = new UserDao();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnLogin)){
			String userName = textField.getText();
			if (doLogin(userName)) {
				SearchFrame frame = new SearchFrame();
				frame.setVisible(true);	
			}
		}
		
	}
	
	private boolean doLogin(String userName) {
		boolean success = true;
		SessionData.CURRENT_USER = userName;
		userDao.addNewUser(userName);
		return success;
	}
}
