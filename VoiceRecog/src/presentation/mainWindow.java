package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.plaf.SliderUI;

import domain.CounterSeconds;
import domain.Microphone;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class mainWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainWindow window = new mainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Microphone mic = new Microphone();
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnMic = new JButton("MIC");
		btnMic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mic.startMic(5);
			}
		});
		btnMic.setBounds(103, 73, 99, 25);
		frame.getContentPane().add(btnMic);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 183, 420, 69);
		frame.getContentPane().add(panel);
		
		JLabel lbloutputMic = new JLabel("Press MIC to speak");
		panel.add(lbloutputMic);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mic.playMic();
			}
		});
		btnPlay.setBounds(241, 73, 99, 25);
		frame.getContentPane().add(btnPlay);
	}
}
