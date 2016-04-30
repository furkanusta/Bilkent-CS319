package com.oo.conquest;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.*;

public class MapView extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String[] colors = {"grey", "blue", "orange", "purple"};
	private Region regionArray[];
	private JLabel[] regionLabelList;
	private UserManager user_M;
	private GameManager game_M;
	private int status;
	private Region sourceRegion;
	private JLabel timeLabel;

	public MapView(Region[] regionArray, UserManager user_M, GameManager gameManager){
		super();
		timeLabel = new JLabel("30");
		status = -1;
		this.regionArray = regionArray;
		sourceRegion = null;
		this.user_M = user_M;
		this.game_M = gameManager;
		regionLabelList = new JLabel[12];
		setLayout(null);
		setBounds(0, 0, 1024, 468);
		setSize(new Dimension(1024, 468));
		
		timeLabel.setBounds(950, 50, 100, 100);
		timeLabel.setForeground(Color.white);
		add(timeLabel);
		for(int i=0;i<regionArray.length;i++){
			String userColor = colors[user_M.getUserList()[regionArray[i].getOwner()].getColor()];

			ImageIcon image = resizeImage(new ImageIcon("images/" + i +"_" + userColor +".png"), regionArray[i].getCoordinate().width/2, regionArray[i].getCoordinate().height/2);
			JLabel label = new JLabel("", image, JLabel.CENTER);
			label.setHorizontalTextPosition(JLabel.CENTER);
			label.setVerticalTextPosition(JLabel.CENTER);
			label.setForeground(Color.white);
			this.setBackground(Color.black);

			regionLabelList[i] = label;

			Region currentRegion = regionArray[i];

			String troopInformation = "<html>Infantry: " + currentRegion.getTroopManager().getInfantryCount() + "<br><html/>"
					+ "<html>Tank: " + currentRegion.getTroopManager().getTankCount() + "<br><html/>"
					+ "<html>Jet: " + currentRegion.getTroopManager().getJetCount() + "<br><html/>";
			label.setText(troopInformation);

			label.setBounds(regionArray[i].getCoordinate().x/2,regionArray[i].getCoordinate().y/2,regionArray[i].getCoordinate().width/2,regionArray[i].getCoordinate().height/2);

			add(label);

			label.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) 
				{
					int ownerId = currentRegion.getOwner();
					if(game_M.getTurn() == ownerId && status == -1){
						String[] buttons = { "Attack", "Transport", "Cancel" };
						
						game_M.stopTimer();
						status = JOptionPane.showOptionDialog(null, "Attack or Reinforce?", "Confirmation",
								JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[2]);
						if(status == 0){
							paintAttackGreen(currentRegion.getNeighbor());
							sourceRegion = currentRegion;
						}
						else if(status == 1){
							paintDefenseGreen(currentRegion.getNeighbor());
							sourceRegion = currentRegion;
						}

						else
							status = -1;
						game_M.continueTimer();
					}
					else if(status == 0){
						status = -1;
						for(int r : sourceRegion.getNeighbor()) {
							if(r == currentRegion.getId() && regionArray[r].getOwner() != sourceRegion.getOwner()){
								JPanel panel = new JPanel();	
								JLabel infantryLabel = new JLabel("Infantry");
								JLabel tankLabel = new JLabel("Tank");
								JLabel jetLabel = new JLabel("Jet");

								JComboBox<Integer> infantryBox = new JComboBox<Integer>();
								for(int i=0; i<=sourceRegion.getTroopManager().getInfantryCount();i++){
									infantryBox.addItem(i);
								}
								JComboBox<Integer> tankBox = new JComboBox<Integer>();
								for(int i=0; i<=sourceRegion.getTroopManager().getTankCount();i++){
									tankBox.addItem(i);
								}
								JComboBox<Integer> jetBox = new JComboBox<Integer>();
								for(int i=0; i<=sourceRegion.getTroopManager().getJetCount();i++){
									jetBox.addItem(i);
								}
								panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
								panel.add(infantryLabel);
								panel.add(infantryBox);
								panel.add(tankLabel);
								panel.add(tankBox);
								panel.add(jetLabel);
								panel.add(jetBox);
								
								game_M.stopTimer();
								int result = JOptionPane.showConfirmDialog(null, panel, 
										"Please Select your soldiers", JOptionPane.OK_CANCEL_OPTION);

								if(result == 2){
									game_M.continueTimer();
									break;
								}
								TroopManager attackTroopManager = sourceRegion.getTroopManager().getNewTroopManager(infantryBox.getSelectedIndex(), tankBox.getSelectedIndex(), jetBox.getSelectedIndex());
								if(attackTroopManager == null){
									JOptionPane.showMessageDialog(getTopLevelAncestor(), "You cannot attack with no troops or all troops!", "Warning",
											JOptionPane.WARNING_MESSAGE);
									game_M.continueTimer();
									break;
								}
								game_M.continueTimer();

								TroopManager winner = game_M.getWarManager().simulateWar(attackTroopManager, currentRegion.getTroopManager()
										, user_M.getUserList()[currentRegion.getOwner()]
										,user_M.getUserList()[sourceRegion.getOwner()]);
								if (winner != currentRegion.getTroopManager()) {
									user_M.getUserList()[currentRegion.getOwner()].setNumberOfRegions(user_M.getUserList()[currentRegion.getOwner()].getNumberOfRegions() - 1);
									user_M.getUserList()[sourceRegion.getOwner()].setNumberOfRegions(user_M.getUserList()[sourceRegion.getOwner()].getNumberOfRegions() + 1);
									currentRegion.setOwner(sourceRegion.getOwner());									
								}
								currentRegion.setTroopManager(winner);

								game_M.updateTurn();
							}
						}
						repaintMap();
					}
					else if(status == 1){
						status = -1;
						for(int r : sourceRegion.getNeighbor()) {
							if(r == currentRegion.getId() && regionArray[r].getOwner() == sourceRegion.getOwner()){


								JPanel panel = new JPanel();	
								JLabel infantryLabel = new JLabel("Infantry");
								JLabel tankLabel = new JLabel("Tank");
								JLabel jetLabel = new JLabel("Jet");

								JComboBox<Integer> infantryBox = new JComboBox<>();
								for(int i=0; i<=sourceRegion.getTroopManager().getInfantryCount();i++){
									infantryBox.addItem(i);
								}
								JComboBox<Integer> tankBox = new JComboBox<>();
								for(int i=0; i<=sourceRegion.getTroopManager().getTankCount();i++){
									tankBox.addItem(i);
								}
								JComboBox<Integer> jetBox = new JComboBox<>();
								for(int i=0; i<=sourceRegion.getTroopManager().getJetCount();i++){
									jetBox.addItem(i);
								}
								panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
								panel.add(infantryLabel);
								panel.add(infantryBox);
								panel.add(tankLabel);
								panel.add(tankBox);
								panel.add(jetLabel);
								panel.add(jetBox);
								
								game_M.stopTimer();
								
								int result = JOptionPane.showConfirmDialog(null, panel, 
										"Please Select your soldiers", JOptionPane.OK_CANCEL_OPTION);

								if(result == 2){
									game_M.continueTimer();
									break;
								}
								TroopManager transferTroopManager = sourceRegion.getTroopManager().getNewTroopManager(infantryBox.getSelectedIndex(), tankBox.getSelectedIndex(), jetBox.getSelectedIndex());
								if(transferTroopManager == null){
									JOptionPane.showMessageDialog(getTopLevelAncestor(), "You cannot transfer no troops or all troops!", "Warning",
											JOptionPane.WARNING_MESSAGE);
									game_M.continueTimer();
									break;
								}
								
								game_M.continueTimer();
								new ReinforcementManager().reinforce(currentRegion, transferTroopManager);

								game_M.updateTurn();
							}
						}
						repaintMap();
					}
				}
			});

			JButton save_button = new JButton("SAVE");
			save_button.setMargin(new Insets(0,0,0,0));
            save_button.setBounds(860, 400, 70, 20);
            save_button.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    JFileChooser openFile = new JFileChooser();
                    int returnVal = openFile.showOpenDialog(null);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
//                        System.out.println("BEFORE");
                        try {
                            File file = openFile.getSelectedFile();
                            PrintWriter writer = new PrintWriter(file);
                            writer.println(user_M.getUserList().length + " " + game_M.getDeadList().size() + " " + game_M.getTurn() + " " + game_M.getTurnCount());
//                            System.out.println("BEFORE2");
                            for (int i = 0; i < user_M.getUserList().length; i++) {
                                User u = user_M.getUserList()[i];
                                writer.println(u.getName() + " " + u.getNumberOfRegions() + " " + u.getColor() + " " +
                                        u.getInfLevel() + " " + u.getTankLevel() + " " + u.getJetLevel() + " " +
                                        u.getKillCount() + " " + u.getDeathCount() + " " + u.getExperiencePoints());
//                                System.out.println(i + " " + u.getName());
                                int k = 0;
                                for (int j = 0; j < u.getNumberOfRegions(); j++) {
//                                    System.out.println(j + " / " + u.getNumberOfRegions());
                                    for (; k < game_M.getMapManager().getRegionArray().length; k++) {
                                        Region r = game_M.getMapManager().getRegionArray()[k];
                                        if (r.getOwner() == i) {
                                            writer.println(r.getId() + " " + r.getTroopManager().getInfantryCount() + " " +
                                            r.getTroopManager().getTankCount() + " " + r.getTroopManager().getJetCount());
                                            k++;
                                            break;
                                        }
                                    }
                                }
                            }
//                            System.out.println("AFTER LIVE USER");
                            for (int i = 0; i < game_M.getDeadList().size(); i++) {
                                User u = game_M.getDeadList().get(i);
                                writer.println((user_M.getUserList().length + i) + " " + u.getColor() + " " + u.getKillCount() +
                                        " " + u.getDeathCount() + " " + u.getExperiencePoints());
                            }
//                            System.out.println("AFTER DEAD USER");
                            writer.close();
                        } catch(IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });
            add(save_button);

			JButton change_color = new JButton("Change Color");
            change_color.setMargin(new Insets(0,0,0,0));
			change_color.setBounds(860, 350, 110, 20);
			change_color.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					JPanel panel = new JPanel();
					panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
					JLabel color = new JLabel("Select Your Color");

					JComboBox<String> colorBox = new JComboBox<String>();
					colorBox.addItem("Grey");
					colorBox.addItem("Blue");
					colorBox.addItem("Orange");
					colorBox.addItem("Purple");

					panel.add(color);
					panel.add(colorBox);
					game_M.stopTimer();
					int result = JOptionPane.showConfirmDialog(null, panel,
							"Please Select your color", JOptionPane.OK_CANCEL_OPTION);
					System.out.println(result);
					if (result == 0) {
						if (!game_M.changeColor(colorBox.getSelectedIndex())) {
							JOptionPane.showMessageDialog(getTopLevelAncestor(), "You cannot select a color that has already been selected!", "Warning",
									JOptionPane.WARNING_MESSAGE);
						}
					}
					game_M.continueTimer();
					repaintMap();
				}
			});
			add(change_color);
			JCheckBox sound_check = new JCheckBox("Mute");
			//sound_check.setForeground(Color.WHITE);
			sound_check.setBounds(860, 420, 70, 20);
			sound_check.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent itemEvent) {
					if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
						game_M.getSoundManager().stop();
					}
					else {
						game_M.getSoundManager().start();
					}
				}
			});
			add(sound_check);
		}
	}

	public void updateTime(int timeLeft){
		timeLabel.setText("<html>"+timeLeft+"<br>"+user_M.getUserList()[game_M.getTurn()].getName()+"<html/>");
	}
	

	public void repaintMap(){
		for(int i=0;i<regionLabelList.length;i++){
			String userColor = colors[user_M.getUserList()[regionArray[i].getOwner()].getColor()];
			ImageIcon image = resizeImage(new ImageIcon("images/"+i+"_"+ userColor +".png"), regionArray[i].getCoordinate().width/2, regionArray[i].getCoordinate().height/2);
			regionLabelList[i].setIcon(image);
			String troopInformation = "<html>Infantry: " + regionArray[i].getTroopManager().getInfantryCount() + "<br><html/>"
					+ "<html>Tank: " + regionArray[i].getTroopManager().getTankCount() + "<br><html/>"
					+ "<html>Jet: " + regionArray[i].getTroopManager().getJetCount() + "<br><html/>";
			regionLabelList[i].setText(troopInformation);
		}
	}

	private void paintAttackGreen(int[] neighbors){
		for(int i=0;i<neighbors.length;i++){
			if(game_M.getTurn() != regionArray[neighbors[i]].getOwner()){
				ImageIcon image = resizeImage(new ImageIcon("images/" + neighbors[i] +"_green.png"), regionArray[neighbors[i]].getCoordinate().width/2, regionArray[neighbors[i]].getCoordinate().height/2);
				regionLabelList[neighbors[i]].setIcon(image);
			}
		}
	}

	private void paintDefenseGreen(int[] neighbors){
		for(int i=0;i<neighbors.length;i++){
			if(game_M.getTurn() == regionArray[neighbors[i]].getOwner()){
				ImageIcon image = resizeImage(new ImageIcon("images/" + neighbors[i] +"_green.png"), regionArray[neighbors[i]].getCoordinate().width/2, regionArray[neighbors[i]].getCoordinate().height/2);
				regionLabelList[neighbors[i]].setIcon(image);
			}
		}
	}

	public ImageIcon resizeImage(ImageIcon icon, int x, int y){
		Image img = icon.getImage() ;  
		Image newimg = img.getScaledInstance( x, y,  java.awt.Image.SCALE_SMOOTH ) ;  
		icon = new ImageIcon( newimg );

		return icon;
	}
}
