package com.oo.conquest;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class UpgradeMenu extends Menu{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String infoString;
	private JLabel infoLabel, infantryLabel, infantryUpdateButton, tankLabel, tankUpdateButton, jetLabel, jetUpdateButton;
	private UserManager user_M;
	private int userId;

	public UpgradeMenu(UserManager userManager, int id){
		setSize(1024, 300);
		setBounds(0, 468, 1024, 300);

		this.user_M = userManager;
		this.userId = id;

		infoLabel = new JLabel();
		infoString = "";
		infoString = "User Name: " + user_M.getUserList()[userId].getName() + "Experience: ";
		infoString += user_M.getUserList()[userId].getExperiencePoints();
		infoLabel.setText(infoString);
		infoLabel.setBounds(20, 20, 1024, 30);
		add(infoLabel);

		ImageIcon infantry = resizeImage(new ImageIcon("images/asker.png"), 75, 75);
		infantryLabel = new JLabel("", infantry, JLabel.CENTER);
		infantryLabel.setHorizontalTextPosition(JLabel.CENTER);
		infantryLabel.setVerticalTextPosition(JLabel.TOP);
		infantryLabel.setBounds(20, 50, 80, 120);
		infantryLabel.setText("Level " + user_M.getUserList()[userId].getInfLevel());
		add(infantryLabel);

		ImageIcon upgrade = resizeImage(new ImageIcon("images/upgradebutton.png"), 30, 30);
		infantryUpdateButton = new JLabel("-100 Exp.", upgrade, JLabel.CENTER);
		infantryUpdateButton.setVerticalTextPosition(JLabel.BOTTOM);
		infantryUpdateButton.setHorizontalTextPosition(JLabel.CENTER);
		infantryUpdateButton.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				if(user_M.getUserList()[userId].getExperiencePoints() >= 100){
					user_M.getUserList()[userId].setInfLevel(user_M.getUserList()[userId].getInfLevel() + 1);
					user_M.getUserList()[userId].setExperiencePoints(0-100);
					infantryLabel.setText("Level " + user_M.getUserList()[userId].getInfLevel());
					infoString = "";
					infoString = "User Name: " + user_M.getUserList()[userId].getName() + "Experience: ";
					infoString += user_M.getUserList()[userId].getExperiencePoints();
					infoLabel.setText(infoString);
				}
				else{
					JOptionPane.showMessageDialog(getTopLevelAncestor(), "You dont have enough experience!", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		infantryUpdateButton.setBounds(20+20, 180, 70, 51);
		add(infantryUpdateButton);

		ImageIcon tank = resizeImage(new ImageIcon("images/tank.png"), 75, 75);
		tankLabel = new JLabel("", tank, JLabel.CENTER);
		tankLabel.setHorizontalTextPosition(JLabel.CENTER);
		tankLabel.setVerticalTextPosition(JLabel.TOP);
		tankLabel.setBounds(20+90, 50, 80, 120);
		tankLabel.setText("Level " + user_M.getUserList()[userId].getTankLevel());
		add(tankLabel);

		ImageIcon upgrade2 = resizeImage(new ImageIcon("images/upgradebutton.png"), 30, 30);
		tankUpdateButton = new JLabel("-200 Exp.", upgrade2, JLabel.CENTER);
		tankUpdateButton.setVerticalTextPosition(JLabel.BOTTOM);
		tankUpdateButton.setHorizontalTextPosition(JLabel.CENTER);
		tankUpdateButton.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				if(user_M.getUserList()[userId].getExperiencePoints() >= 200){
					user_M.getUserList()[userId].setTankLevel(user_M.getUserList()[userId].getTankLevel() + 1);
					user_M.getUserList()[userId].setExperiencePoints(0-200);
					tankLabel.setText("Level " + user_M.getUserList()[userId].getTankLevel());
					infoString = "";
					infoString = "User Name: " + user_M.getUserList()[userId].getName() + "Experience: ";
					infoString += user_M.getUserList()[userId].getExperiencePoints();
					infoLabel.setText(infoString);
				}
				else{
					JOptionPane.showMessageDialog(getTopLevelAncestor(), "You dont have enough experience!", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		tankUpdateButton.setBounds(20+90+20, 180, 70, 51);
		add(tankUpdateButton);

		ImageIcon jet = resizeImage(new ImageIcon("images/jet.png"), 75, 75);
		jetLabel = new JLabel("", jet, JLabel.CENTER);
		jetLabel.setHorizontalTextPosition(JLabel.CENTER);
		jetLabel.setVerticalTextPosition(JLabel.TOP);
		jetLabel.setBounds(20+180, 50, 80, 120);
		jetLabel.setText("Level " + user_M.getUserList()[userId].getJetLevel());
		add(jetLabel);

		ImageIcon upgrade3 = resizeImage(new ImageIcon("images/upgradebutton.png"), 30, 30);
		jetUpdateButton = new JLabel("-400 Exp.", upgrade3, JLabel.CENTER);
		jetUpdateButton.setVerticalTextPosition(JLabel.BOTTOM);
		jetUpdateButton.setHorizontalTextPosition(JLabel.CENTER);
		jetUpdateButton.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				if(user_M.getUserList()[userId].getExperiencePoints() >= 400){
					user_M.getUserList()[userId].setJetLevel(user_M.getUserList()[userId].getJetLevel() + 1);
					user_M.getUserList()[userId].setExperiencePoints(0-400);
					jetLabel.setText("Level " + user_M.getUserList()[userId].getJetLevel());
					infoString = "";
					infoString = "User Name: " + user_M.getUserList()[userId].getName() + "Experience: ";
					infoString += user_M.getUserList()[userId].getExperiencePoints();
					infoLabel.setText(infoString);
				}
				else{
					JOptionPane.showMessageDialog(getTopLevelAncestor(), "You dont have enough experience!", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		jetUpdateButton.setBounds(20+180+20, 180, 70, 51);
		add(jetUpdateButton);

	}

	public ImageIcon resizeImage(ImageIcon icon, int x, int y){
		Image img = icon.getImage() ;  
		Image newimg = img.getScaledInstance( x, y,  java.awt.Image.SCALE_SMOOTH ) ;  
		icon = new ImageIcon( newimg );

		return icon;
	}

	public void repaintMenu(UserManager userMan, int id){
		this.userId = id;
		infoString = "";
		infoString = "User Name: " + userMan.getUserList()[id].getName() +"\tExperience: ";
		infoString += user_M.getUserList()[id].getExperiencePoints();
		infoLabel.setText(infoString);

		infantryLabel.setText("Level " + user_M.getUserList()[userId].getInfLevel());
		tankLabel.setText("Level " + user_M.getUserList()[userId].getTankLevel());
		jetLabel.setText("Level " + user_M.getUserList()[userId].getJetLevel());


		validate();
		repaint();
	}

}
