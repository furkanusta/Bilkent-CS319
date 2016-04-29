package com.oo.conquest;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class UserSelectionMenu extends Menu{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserSelectionMenu(GameManager game_M){
		JTextField userNameField1 = new JTextField();
		userNameField1.setBounds(10+200, 10, 120, 25);
		add(userNameField1);

		JComboBox<String> colorBox1 = new JComboBox<String>();
		colorBox1.addItem("Grey");
		colorBox1.addItem("Blue");
		colorBox1.addItem("Orange");
		colorBox1.addItem("Purple");
		colorBox1.setBounds(90+50+200, 10, 70, 25);
		add(colorBox1);

		JCheckBox checkBox1 = new JCheckBox();
		checkBox1.setBounds(170+50+200, 10, 70, 25);
		add(checkBox1);
		checkBox1.setSelected(true);

		checkBox1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(checkBox1.isSelected()){
					userNameField1.setEnabled(true);
					colorBox1.setEnabled(true);
				}
				else{
					userNameField1.setEnabled(false);
					colorBox1.setEnabled(false);
				}
			}
		});

		JTextField userNameField2 = new JTextField();
		userNameField2.setBounds(10+200, 10+40, 120, 25);
		add(userNameField2);

		JComboBox<String> colorBox2 = new JComboBox<String>();
		colorBox2.addItem("Grey");
		colorBox2.addItem("Blue");
		colorBox2.addItem("Orange");
		colorBox2.addItem("Purple");
		colorBox2.setBounds(90+50+200, 10+40, 70, 25);
		add(colorBox2);

		JCheckBox checkBox2 = new JCheckBox();
		checkBox2.setBounds(170+50+200, 10+40, 70, 25);
		add(checkBox2);
		checkBox2.setSelected(true);

		checkBox2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(checkBox2.isSelected()){
					userNameField2.setEnabled(true);
					colorBox2.setEnabled(true);
				}
				else{
					userNameField2.setEnabled(false);
					colorBox2.setEnabled(false);
				}
			}
		});

		JTextField userNameField3 = new JTextField();
		userNameField3.setBounds(10+200, 10+40+40, 120, 25);
		add(userNameField3);

		JComboBox<String> colorBox3 = new JComboBox<String>();
		colorBox3.addItem("Grey");
		colorBox3.addItem("Blue");
		colorBox3.addItem("Orange");
		colorBox3.addItem("Purple");
		colorBox3.setBounds(90+50+200, 10+40+40, 70, 25);
		add(colorBox3);

		JCheckBox checkBox3 = new JCheckBox();
		checkBox3.setBounds(170+50+200, 10+40+40, 70, 25);
		add(checkBox3);
		checkBox3.setSelected(false);
		userNameField3.setEnabled(false);
		colorBox3.setEnabled(false);
		checkBox3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(checkBox3.isSelected()){
					userNameField3.setEnabled(true);
					colorBox3.setEnabled(true);
				}
				else{
					userNameField3.setEnabled(false);
					colorBox3.setEnabled(false);
				}
			}
		});

		JTextField userNameField4 = new JTextField();
		userNameField4.setBounds(10+200, 10+40+40+40, 120, 25);
		add(userNameField4);

		JComboBox<String> colorBox4 = new JComboBox<String>();
		colorBox4.addItem("Grey");
		colorBox4.addItem("Blue");
		colorBox4.addItem("Orange");
		colorBox4.addItem("Purple");
		colorBox4.setBounds(90+50+200, 10+40+40+40, 70, 25);
		add(colorBox4);

		JCheckBox checkBox4 = new JCheckBox();
		checkBox4.setBounds(170+50+200, 10+40+40+40, 70, 25);
		add(checkBox4);
		checkBox4.setSelected(false);
		userNameField4.setEnabled(false);
		colorBox4.setEnabled(false);

		checkBox4.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(checkBox4.isSelected()){
					userNameField4.setEnabled(true);
					colorBox4.setEnabled(true);
				}
				else{
					userNameField4.setEnabled(false);
					colorBox4.setEnabled(false);
				}
			}
		});

		JButton startGameButton = new JButton("Start Game");
		startGameButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				ArrayList<User> userList = new ArrayList<User>();
				if(checkBox1.isSelected()){
					userList.add(new User(userList.size() , colorBox1.getSelectedIndex(), userNameField1.getText(), 0, 0, 0, 0));
				}
				if(checkBox2.isSelected()){
					userList.add(new User(userList.size() , colorBox2.getSelectedIndex(), userNameField2.getText(), 0, 0, 0, 0));
				}
				if(checkBox3.isSelected()){
					userList.add(new User(userList.size() , colorBox3.getSelectedIndex(), userNameField3.getText(), 0, 0, 0, 0));
				}
				if(checkBox4.isSelected()){
					userList.add(new User(userList.size() , colorBox4.getSelectedIndex(), userNameField4.getText(), 0, 0, 0, 0));
				}
				game_M.initialize(userList, null, null, 0, 0);
			} 
		} );
		startGameButton.setBounds(700,0, 70, 20);
		add(startGameButton);
	}

	public ImageIcon resizeImage(ImageIcon icon, int x, int y){
		Image img = icon.getImage() ;  
		Image newimg = img.getScaledInstance( x, y,  java.awt.Image.SCALE_SMOOTH ) ;  
		icon = new ImageIcon( newimg );

		return icon;
	}

}
