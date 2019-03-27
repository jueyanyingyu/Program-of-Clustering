package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ErrorFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6491060330888028286L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public ErrorFrame(String warning) {
		setTitle("error!");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 279, 160);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel(warning);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(14, 13, 245, 99);
		contentPane.add(label);
		this.setVisible(true);
	}

}
