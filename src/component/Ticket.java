package component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.SwingConstants;

public class Ticket extends JFrame {
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Ticket(JFrame main_frame, List<Object> tableData) {		
		setPreferredSize(new Dimension(200, 400));
		setSize(200, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);

		JPanel panel = new JPanel(new GridLayout(0, 1));

		JScrollPane contentpane = new JScrollPane(panel);
		
		getContentPane().add(contentpane);
		
		tableData.forEach((item) -> {
			System.out.print(item);
			
			@SuppressWarnings("unchecked")
			List<Object> obj = (List<Object>) item;
			JPanel newpanel = new JPanel(new BorderLayout());
			newpanel.add(new JLabel((String) obj.get(0)), BorderLayout.NORTH);
			
			JPanel centerPanel = new JPanel(new BorderLayout());

			JLabel lblNewLabel = new JLabel("식권");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("HY신명조", Font.BOLD, 38));

			JLabel lblNewLable_2 = new JLabel(String.valueOf(obj.get(4)));
			lblNewLable_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLable_2.setFont(new Font("HY신명조", Font.BOLD, 32));

			centerPanel.add(lblNewLabel, BorderLayout.NORTH);
			centerPanel.add(lblNewLable_2, BorderLayout.CENTER);
			centerPanel.setBackground((Color) obj.get(6));

			newpanel.add(centerPanel, BorderLayout.CENTER);

			JPanel bottomPanel = new JPanel(new BorderLayout());
			
			bottomPanel.add(new JLabel("메뉴 : "+(String) obj.get(2)), BorderLayout.WEST);
			
			bottomPanel.add(new JLabel((String) obj.get(5)), BorderLayout.EAST);
			bottomPanel.setBackground((Color) obj.get(6));
			
			newpanel.add(bottomPanel, BorderLayout.SOUTH);
			
			newpanel.setBackground((Color) obj.get(6));
			
			newpanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));
			newpanel.setBorder(BorderFactory.createLineBorder(Color.black));

			panel.add(newpanel);
			panel.setPreferredSize(new Dimension(0, 200 * panel.getComponents().length));
			contentpane.validate();
		});
		
		setVisible(true);
	}

}
