package org.adm.project.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollBar;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;

public class SearchResultFrame extends JFrame {

	private JPanel contentPane;
	private JLabel lblAuthor;
	private JLabel lblDescription;
	private JLabel lblPossibleRequiredBooks;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchResultFrame frame = new SearchResultFrame();
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
	public SearchResultFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollBar scrollBar = new JScrollBar();
		
		lblPossibleRequiredBooks = new JLabel("Recommended Books ");
		lblPossibleRequiredBooks.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		JPanel panel = new JPanel();
		
		JPanel panel_2 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(135)
									.addComponent(lblPossibleRequiredBooks))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(21)
									.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(130)
							.addComponent(scrollBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(13)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblPossibleRequiredBooks)
							.addGap(27)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollBar, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE))
					.addGap(122)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		
		JLabel lblBookTitle = new JLabel("Book Title");
		
		JLabel lblThisIsBook = new JLabel("This is book");
		
		JLabel lblIsbn = new JLabel("Price");
		
		JLabel lblThisIsIsbn = new JLabel("This is price");
		
		lblAuthor = new JLabel("Author");
		
		JLabel lblThisIsAuthor = new JLabel("This is author");
		
		lblDescription = new JLabel("Description");
		
		JTextArea textArea = new JTextArea();
		
		JButton btnView = new JButton("View");
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(Alignment.LEADING, gl_panel_2.createSequentialGroup()
							.addContainerGap()
							.addComponent(textArea))
						.addGroup(Alignment.LEADING, gl_panel_2.createSequentialGroup()
							.addComponent(lblBookTitle)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblThisIsBook)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblIsbn)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblThisIsIsbn)
							.addGap(18)
							.addComponent(lblAuthor)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblThisIsAuthor))
						.addComponent(lblDescription, Alignment.LEADING))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnView)
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBookTitle)
						.addComponent(lblThisIsBook)
						.addComponent(lblIsbn)
						.addComponent(lblAuthor)
						.addComponent(lblThisIsAuthor)
						.addComponent(lblThisIsIsbn))
					.addGap(18)
					.addComponent(lblDescription)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(34, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addContainerGap(73, Short.MAX_VALUE)
					.addComponent(btnView)
					.addGap(61))
		);
		panel_2.setLayout(gl_panel_2);
		contentPane.setLayout(gl_contentPane);
	}
}
