package org.adm.project.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
<<<<<<< HEAD
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewBookFrame extends JFrame implements ActionListener  {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblPrice;
	private JTextField textField_1;
	private JButton btnCancel;
	JButton btnBuyNow = new JButton("Buy Now");
=======
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.adm.project.SessionData;
import org.adm.project.dao.BookDao;
import org.adm.project.model.Book;
>>>>>>> 0e949a3ab01059fcd61b58f7021e5a6c12fae91c

public class ViewBookFrame extends JFrame implements ActionListener  {

	private int stock;
	
	/**
	 * Create the frame.
	 */
	public ViewBookFrame(final Book book) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout(10, 10));
		JPanel attrPanel = new JPanel();
		attrPanel.setLayout(new BoxLayout(attrPanel, BoxLayout.Y_AXIS));
		JPanel valuePanel = new JPanel();
		valuePanel.setLayout(new BoxLayout(valuePanel, BoxLayout.Y_AXIS));
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		contentPane.add(attrPanel, BorderLayout.WEST);
		contentPane.add(valuePanel, BorderLayout.CENTER);
		contentPane.add(rightPanel, BorderLayout.EAST);
		
		JLabel titleLbl = new JLabel("Title");
		JLabel isbnLbl = new JLabel("ISBN");
		JLabel priceLbl = new JLabel("Price");
		JLabel authorsLbl = new JLabel("Authors");
		JLabel descLbl = new JLabel("Description");
		
		attrPanel.add(titleLbl);
		attrPanel.add(isbnLbl);
		attrPanel.add(priceLbl);
		attrPanel.add(authorsLbl);
		attrPanel.add(descLbl);
		
<<<<<<< HEAD
		 btnBuyNow = new JButton("Buy Now");
		 btnBuyNow.addActionListener(this);
		btnBuyNow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
=======
		JLabel titleLbl2 = new JLabel(": " + book.getTitle());
		titleLbl2.setAlignmentX(Component.LEFT_ALIGNMENT);
		JLabel isbnLbl2 = new JLabel(": " + book.getIsbn());
		isbnLbl2.setAlignmentX(Component.LEFT_ALIGNMENT);
		JLabel priceLbl2 = new JLabel(": " + "USD " + book.getPrice());
		priceLbl2.setAlignmentX(Component.LEFT_ALIGNMENT);
		StringBuilder authors = new StringBuilder();
		boolean first = true;
		if (book.getAuthors() != null) {
			for (String author : book.getAuthors()) {
				if (!first) {
					authors.append(", ");
				}
				authors.append(author);
>>>>>>> 0e949a3ab01059fcd61b58f7021e5a6c12fae91c
			}
		}
		JLabel authorsLbl2 = new JLabel(": " + authors.toString());
		authorsLbl2.setAlignmentX(Component.LEFT_ALIGNMENT);
		JTextArea descArea = new JTextArea(book.getDescription());
		descArea.setAlignmentX(Component.LEFT_ALIGNMENT);
		descArea.setLineWrap(true);
		
		valuePanel.add(titleLbl2);
		valuePanel.add(isbnLbl2);
		valuePanel.add(priceLbl2);
		valuePanel.add(authorsLbl2);
		valuePanel.add(descArea);
		
		JLabel coverImage = null;
		if (book.getCoverUrl() != null) {
			try {
				coverImage = new JLabel(new ImageIcon(new URL(book.getCoverUrl())));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		final BookDao bookDao = new BookDao(SessionData.ACTIVE_DB);
		stock = bookDao.getBookStock(book.getIsbn());
		final JLabel stockLabel = new JLabel(stock + " in stock");
		JButton buyButton = new JButton("Buy");
		buyButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(ViewBookFrame.this, "Are you sure you want to buy this item?");
				if (choice == JOptionPane.OK_OPTION) {
					bookDao.setBookStock(book.getIsbn(), --stock);
					stockLabel.setText(stock - 1 + " in stock");	
				}
			}
		});
		
		rightPanel.add(coverImage);
		rightPanel.add(stockLabel);
		rightPanel.add(buyButton);
		
		setSize(new Dimension(800, 600));
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(btnBuyNow)){
			int n = 1;
			JOptionPane.showConfirmDialog(this, "The number of books available is " + n, "Thank you", JOptionPane.YES_NO_OPTION);
			
			//MessageFrame frame = new MessageFrame();
			//frame.setVisible(true);
		}
		
	}
		}
	
