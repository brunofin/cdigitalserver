package gui.modelo;

import java.awt.EventQueue;

import javax.swing.JFrame;
// 88120329
public class FrmTeste {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public FrmTeste() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

}
