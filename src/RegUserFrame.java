import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Member;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class RegUserFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldMemberNo;
	private JTextField textFieldMemberName;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Create the frame.
	 * @param frame 
	 */
	public RegUserFrame(JFrame main_frame) {
		setResizable(false);
		setTitle("사원등록");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 318, 236);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Member member = new Member();
		int newMemberNo = member.gettheLastMemberNo();
		System.out.println(String.valueOf(newMemberNo));
		
		JLabel memberNoLabel = new JLabel("사원번호:");
		memberNoLabel.setBounds(19, 20, 118, 16);
		contentPane.add(memberNoLabel);
		
		JLabel memberNameLabel = new JLabel("사 원 명:");
		memberNameLabel.setBounds(19, 56, 118, 16);
		contentPane.add(memberNameLabel);
		
		JLabel memberPasswdLabel = new JLabel("패스워드:");
		memberPasswdLabel.setBounds(19, 92, 118, 16);
		contentPane.add(memberPasswdLabel);
		
		JLabel memberPasswdReLabel = new JLabel("패스워드 재입력:");
		memberPasswdReLabel.setBounds(19, 128, 118, 16);
		contentPane.add(memberPasswdReLabel);
		
		JButton btnRegButton = new JButton("등록");
		btnRegButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldMemberName.getText().length() > 0 && passwordField.getPassword().length > 0 && passwordField_1.getPassword().length > 0) {
					if (Arrays.equals(passwordField.getPassword(), passwordField_1.getPassword())) {
						System.out.println("registration");
						JOptionPane.showMessageDialog(main_frame,
							    "사원이 등록되었습니다.",
								"Message", 
								JOptionPane.INFORMATION_MESSAGE);

					} else {
						System.out.println("alert invalid password");						

						JOptionPane.showMessageDialog(main_frame,
						    "패스워드 확인 요망",
						    "Message",
						    JOptionPane.ERROR_MESSAGE);
					}
				} else {
					System.out.println("alert empty text field");
					
					JOptionPane.showMessageDialog(main_frame,
						    "항목 누락",
						    "Message",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnRegButton.setBounds(6, 164, 117, 29);
		contentPane.add(btnRegButton);
		
		JButton btnCloseButton = new JButton("닫기");
		btnCloseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldMemberName.setText("");
				passwordField.setText("");
				passwordField_1.setText("");
				
				dispose();
				
				main_frame.setVisible(true);
			}
		});
		btnCloseButton.setBounds(169, 164, 117, 29);
		contentPane.add(btnCloseButton);
		
		textFieldMemberNo = new JTextField();
		textFieldMemberNo.setEditable(false);
		textFieldMemberNo.setBounds(169, 15, 130, 26);
		contentPane.add(textFieldMemberNo);
		textFieldMemberNo.setText(String.valueOf(++newMemberNo));
		textFieldMemberNo.setColumns(10);
		
		textFieldMemberName = new JTextField();
		textFieldMemberName.setBounds(169, 51, 130, 26);
		contentPane.add(textFieldMemberName);
		textFieldMemberName.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(169, 87, 130, 26);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(169, 123, 130, 26);
		contentPane.add(passwordField_1);
	}
}
