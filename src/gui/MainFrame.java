package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
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
				//for win env.
				String[] cmd = { "cmd.exe", "/C", "ffmpeg"};
				
				Process pr = Runtime.getRuntime().exec(cmd);
				BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
//				BufferedReader inErr = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
//				InputStream in = pr.getInputStream();
				InputStream in2 = pr.getErrorStream();
//				StringBuffer strOut = new StringBuffer();
				StringBuffer strOut2 = new StringBuffer();
				String strOut = null;		
				while ((strOut = in.readLine()) != null && in.ready()) {					
					cmdStdout.append(strOut + "\n");
					System.out.println("tu sam");					
				}
//					int ch;
//				   while ((ch = in.read()) != -1)
//		            {
//		                strOut.append((char)ch + "");
//		            }
//				   cmdStdout.setText(strOut.toString());
//				   
//					int ch2;
//					   while ((ch2 = in2.read()) != -1)
//			            {
//			                strOut2.append((char)ch2 + "");
//			            }
//					   cmdStdout.append(strOut2.toString());
				
				
				in.close();
				in2.close();
				//pr.destroy();
				
				pr.destroy();
				cmdStdout.append("\n\nExit Value: "+ pr.waitFor());
				
				
			} catch (IOException e1) {				
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
	}
	

}
