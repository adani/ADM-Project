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

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.awt.FlowLayout;

public class SearchFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JRadioButton rdbtnTitle;
	private JRadioButton rdbtnIsbn;
	private BookDao dao;
	private JTextField textField;
	private final JLabel lblNewLabel = new JLabel("New label");

	/**
	 * Create the frame.
	 */
	public SearchFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 419);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblSearchBy = new JLabel("Search By:");
		lblSearchBy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		rdbtnTitle = new JRadioButton("Title");
		
		rdbtnIsbn = new JRadioButton("ISBN");
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(this);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		
		JPanel panel_4 = new JPanel();
		
		JPanel panel_5 = new JPanel();
		
		JPanel panel_6 = new JPanel();
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_3 = new JPanel();
		
		JLabel lblTitle = new JLabel("title1");
		
		JLabel lblPrice = new JLabel("price1");
		
		JLabel lblTitle_1 = new JLabel("title2");
		
		JLabel lblPrice_1 = new JLabel("price2");
		
		JLabel lblTitle_2 = new JLabel("title3");
		
		JLabel lblPrice_2 = new JLabel("price3");
		
		JLabel lblTitle_3 = new JLabel("title4");
		
		JLabel lblPrice_3 = new JLabel("price4");
		
		JLabel lblTitle_4 = new JLabel("title5");
		
		JLabel lblPrice_4 = new JLabel("price5");
		
		JLabel lblTitle_5 = new JLabel("title6");
		
		JLabel lblPrice_5 = new JLabel("price6");
		
		JLabel lblTheMostRecommended = new JLabel("The most recommended Books:");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(41)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblPrice)
										.addComponent(lblTitle)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(27)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(49)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(panel_5, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblSearchBy))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(137)
							.addComponent(rdbtnTitle)
							.addGap(51)
							.addComponent(rdbtnIsbn)))
					.addGap(52))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
							.addComponent(btnSearch)
							.addGap(175))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(58)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(10)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblTitle_1)
										.addComponent(lblPrice_1))
									.addPreferredGap(ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblTitle_2)
										.addComponent(lblPrice_2))
									.addContainerGap(47, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(128)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
										.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE))
									.addContainerGap())))))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(44)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTitle_3)
						.addComponent(lblPrice_3))
					.addGap(111)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPrice_4)
						.addComponent(lblTitle_4))
					.addPreferredGap(ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTitle_5)
						.addComponent(lblPrice_5))
					.addGap(44))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTheMostRecommended)
					.addContainerGap(368, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(rdbtnTitle)
								.addComponent(rdbtnIsbn)))
						.addComponent(lblSearchBy))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSearch)
							.addGap(35))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblTheMostRecommended)
							.addGap(18)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
								.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblTitle)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblPrice)
							.addGap(45))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTitle_1)
								.addComponent(lblTitle_2))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(2)
									.addComponent(lblPrice_1))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblPrice_2)))
							.addGap(35)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblTitle_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblPrice_3))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblTitle_4)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblPrice_4))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblTitle_5)
							.addGap(11)
							.addComponent(lblPrice_5)))
					.addContainerGap())
		);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 90, Short.MAX_VALUE)
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 43, Short.MAX_VALUE)
		);
		panel_1.setLayout(gl_panel_1);
		contentPane.setLayout(gl_contentPane);
		dao = new BookDao(SessionData.MONGO_DB);
	}
	
	@Override
	public  actionPerformed(ActionEvent arg0) {
		Book[] results;
		try {
			if (rdbtnIsbn.isSelected()) {
				Book result = dao.getBookByIsbn(isbnField.getText());
				results = new Book[] {result};
				DBObject dbObject = cursor.next();
				return createBookFromDBObject(dbObject);
			} else {
				results = dao.getBooksTitleContains(titleField.getText());
				
				
				
			}
		} catch (Exception e) {
			
		}
			
		SearchResultFrame f=new SearchResultFrame();
		f.setVisible(true);
		
		
		
		new SearchResultFrame(results);
	}
}

