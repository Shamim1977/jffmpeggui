package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MainFrame extends JFrame implements ActionListener {
	private JTextArea cmdStdout = new JTextArea();
	private JScrollPane spCmdStdout = new JScrollPane(cmdStdout);
	private JButton start = new JButton("START");
	
	public MainFrame() {
		super("jffmpegGUI");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setLayout(new BorderLayout());
		//spCmdStdout.set
		cmdStdout.setRows(25);
		cmdStdout.setEditable(false);
		cmdStdout.setWrapStyleWord(true);
		cmdStdout.setLineWrap(true);
		add(spCmdStdout, BorderLayout.NORTH);
		add(start, BorderLayout.SOUTH);
		start.addActionListener(this);
		start.setActionCommand("start");
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("start")) {
			try {
				Process pr = Runtime.getRuntime().exec("ffmpeg");
				BufferedReader out = new BufferedReader(new InputStreamReader(pr.getInputStream()));
				String strOut = null;
				while ((strOut = out.readLine()) != null) {
					cmdStdout.append(strOut + "\n");
				}
			
				cmdStdout.append("\n\nExit Value: "+ pr.exitValue());
				
			} catch (IOException e1) {				
				e1.printStackTrace();
			}
		}
		
		
	}
	

}
