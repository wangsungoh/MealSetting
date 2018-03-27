package component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

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

public class Ticket extends JFrame {
	/**
	 * Create the frame.
	 */
	public Ticket(JFrame main_frame, List<Object> tableData) {		
		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(400, 400));
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);

		JPanel panel = new JPanel(new GridLayout(0, 1));

		JScrollPane contentpane = new JScrollPane(panel);
		frame.add(contentpane);
		
		tableData.forEach((item) -> {
			System.out.print(item);
			
			@SuppressWarnings("unchecked")
			List<Object> obj = (List<Object>) item;
			JPanel newpanel = new JPanel(new BorderLayout());
			newpanel.add(new JLabel((String) obj.get(0)), BorderLayout.NORTH);
			
			newpanel.add(new JLabel("식권\r\n"+String.valueOf(obj.get(4))), BorderLayout.CENTER);
			
			JPanel bottomPanel = new JPanel(new BorderLayout());
			bottomPanel.add(new JLabel((String) obj.get(2)), BorderLayout.WEST);
			bottomPanel.add(new JLabel((String) obj.get(5)), BorderLayout.EAST);
			bottomPanel.setBackground((Color) obj.get(6));
			
			newpanel.add(bottomPanel, BorderLayout.SOUTH);
			
			newpanel.setBackground((Color) obj.get(6));
			
			newpanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
			newpanel.setBorder(BorderFactory.createLineBorder(Color.black));

			panel.add(newpanel);
			panel.setPreferredSize(new Dimension(0, 200 * panel.getComponents().length));
			contentpane.validate();
		});
		
		frame.setVisible(true);
	}

}
