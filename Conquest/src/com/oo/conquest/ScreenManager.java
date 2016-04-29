package com.oo.conquest;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ScreenManager extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ScreenManager(){
		super();
		setLayout(null);
		setSize(1024, 768);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void clearContents(){
		this.getContentPane().removeAll();
		this.validate();
		this.repaint();
	}
	
	public void draw(JPanel panel, int x, int y){
		panel.setBounds(x, y, (int)panel.getSize().getWidth(), (int)panel.getSize().getHeight());
		add(panel);
		getContentPane();
		validate();
		repaint();
//		getContentPane().validate();
//		getContentPane().repaint();
//		panel.repaint();
	}
}
