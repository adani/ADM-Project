package org.adm.project.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import org.adm.project.model.Book;

public class SearchResultFrame extends JFrame {
	
	private Border lineBorder;
	private Border panelEdge;
	/**
	 * Create the frame.
	 */
	public SearchResultFrame(Book[] books) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = getContentPane();
		JPanel scrolledPanel = new JPanel();
		scrolledPanel.setLayout(new BoxLayout(scrolledPanel, BoxLayout.Y_AXIS));
		JScrollPane scrollPane = new JScrollPane(scrolledPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(800, 600));
		//scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		for (Book book : books) {
			scrolledPanel.add(createEntryPanel(book));
		}
		setVisible(true);
		panelEdge = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		lineBorder = BorderFactory.createLineBorder(Color.BLACK);
		pack();
	}
	
	public JPanel createEntryPanel(final Book book) {
		JPanel container = new JPanel();
		container.setPreferredSize(new Dimension(800, 100));
		JPanel entryPanel = new JPanel();
		Border titleBorder = BorderFactory.createTitledBorder(lineBorder, book.getTitle(), TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION);
		entryPanel.setBorder(titleBorder);
		container.setBorder(panelEdge);
		entryPanel.setLayout(new GridLayout(3, 1));
		container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
		

		final JButton viewButton = new JButton();
		container.add(viewButton);
		Thread loadImage = new Thread() {
			public void run() {

				if (book.getCoverUrl() != null) {
					try {
						BufferedImage image = ImageIO.read(new URL(book.getCoverUrl()));
						ImageIcon icon = new ImageIcon(resize(image, 60, 90));
						viewButton.setIcon(icon);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			};
		};
		loadImage.start();
		
		viewButton.addActionListener(new ViewBookActionListener(book));
		
		container.add(entryPanel);
		container.add(Box.createRigidArea(new Dimension(0, 10)));
		
		JPanel authorPanel = new JPanel();
		authorPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JPanel additionalPanel = new JPanel();
		additionalPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JPanel descPanel = new JPanel();
		descPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		entryPanel.add(authorPanel);
		entryPanel.add(additionalPanel);
		entryPanel.add(descPanel);
		
		JLabel lblAuthor = new JLabel("");
		authorPanel.add(lblAuthor);
		
		JLabel lblPrice = new JLabel("USD " + book.getPrice(), SwingConstants.LEFT);
		lblPrice.setBackground(Color.RED);
		lblPrice.setOpaque(true);
		additionalPanel.setBackground(Color.BLUE);
		additionalPanel.setOpaque(true);
		additionalPanel.add(lblPrice);
		additionalPanel.setAlignmentX(0);
		
		String[] authors = book.getAuthors();
		if (authors != null && authors.length > 1) {
			lblAuthor.setText(authors[0] + " et al.");
		} else if (authors != null && authors.length == 1) {
			lblAuthor.setText(authors[0]);
		}
		
		int subsLen = book.getDescription().length();
		JLabel lblDescription = new JLabel("", SwingConstants.LEFT);
		String description = new String(book.getDescription());
		if (description.length() > 140) {
			subsLen = 140;
			lblDescription.setText(description.substring(0, subsLen) + "...");
		} else {
			lblDescription.setText(description.substring(0, subsLen));
		}
		descPanel.add(lblDescription);
		
		return container;
	}
	
	public static BufferedImage resize(BufferedImage image, int width, int height) {
	    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
	    Graphics2D g2d = (Graphics2D) bi.createGraphics();
	    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
	    g2d.drawImage(image, 0, 0, width, height, null);
	    g2d.dispose();
	    return bi;
	}
	
	private class ViewBookActionListener implements ActionListener {
		Book book;
		
		public ViewBookActionListener(Book book) {
			this.book = book;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			new ViewBookFrame(book);
		}
	}
}
