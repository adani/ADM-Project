package org.adm.project.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import org.adm.project.SessionData;
import org.adm.project.dao.BookDao;
import org.adm.project.model.Book;

public class SearchFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField titleField;
	private JTextField isbnField;
	private JRadioButton rdbtnTitle;
	private JRadioButton rdbtnIsbn;
	private BookDao dao;

	/**
	 * Create the frame.
	 */
	public SearchFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblSearchBy = new JLabel("Search By:");
		lblSearchBy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		rdbtnTitle = new JRadioButton("Title");
		
		titleField = new JTextField();
		titleField.setColumns(10);
		
		rdbtnIsbn = new JRadioButton("ISBN");
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(this);
		
		isbnField = new JTextField();
		isbnField.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(rdbtnTitle)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(titleField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(43)
									.addComponent(rdbtnIsbn)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(isbnField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblSearchBy)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(136)
							.addComponent(btnSearch)))
					.addContainerGap(93, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(43)
					.addComponent(lblSearchBy)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnTitle)
						.addComponent(titleField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbtnIsbn)
						.addComponent(isbnField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addComponent(btnSearch)
					.addContainerGap(102, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		dao = new BookDao(SessionData.MONGO_DB);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Book[] results;
		if (rdbtnIsbn.isSelected()) {
			Book result = dao.getBookByIsbn(isbnField.getText());
			results = new Book[] {result};
		} else {
			results = dao.getBooksTitleContains(titleField.getText());
		}
		
		new SearchResultFrame(results);
	}
}
