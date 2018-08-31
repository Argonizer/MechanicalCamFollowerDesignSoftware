package edu.METutor.TheoryOfMachines.Cams;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window {

	public Window(MainClass main, ControlPanel panel2)
	{
		JFrame frame = new JFrame("Cam Profile Designer");
		JPanel panel1 = new JPanel();
		
		frame.setLayout(new BorderLayout(10, 10));
		frame.setPreferredSize(new Dimension(MainClass.width, MainClass.height));
		frame.setMaximumSize(new Dimension(MainClass.width, MainClass.height));
		frame.setMinimumSize(new Dimension(MainClass.width, MainClass.height));
		
		panel1.add(main);
		frame.add(panel1, BorderLayout.WEST);
		frame.add(panel2, BorderLayout.EAST);
		frame.pack();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusable(true);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		main.start();
	}
	
}
