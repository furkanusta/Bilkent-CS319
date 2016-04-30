package com.oo.conquest;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

public class MainMenu extends Menu{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MainMenu(ScreenManager screen_M, GameManager game_M){
		JButton startButton = new JButton("Start Game");
		startButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				UserSelectionMenu selectionMenu = new UserSelectionMenu(game_M);
				screen_M.clearContents();
				screen_M.draw(selectionMenu, 0, 0);
			} 
		} );
		
		
		startButton.setBounds(300,10,100,100);
        startButton.setMargin(new Insets(0,0,0,0));
		add(startButton);

		JButton loadGameButton = new JButton("Load Game");
        loadGameButton.setMargin(new Insets(0,0,0,0));
		loadGameButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
                JFileChooser openFile = new JFileChooser();
                int returnVal = openFile.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = openFile.getSelectedFile();
                    BufferedReader br = null;

                    int userCount, deadCount = 0, turn = 0, turnCount = 0;
                    ArrayList<User> userList = new ArrayList<>();
                    ArrayList<User> deadList = new ArrayList<>();
                    MapManager load_map = new MapManager();
                    try {
                        br = new BufferedReader(new FileReader(file.getAbsolutePath()));
                        Scanner scan = new Scanner(br.readLine());
                        userCount = scan.nextInt();
                        deadCount = scan.nextInt();
                        turn = scan.nextInt();
                        turnCount = scan.nextInt();

//                        userName numberOfRegions InfLevel TankLevel JetLevel KillCount DeathCount
//                        RegionID InfCount TankCount JetCount

                        for (int i = 0; i < userCount; i++) {
                            scan = new Scanner(br.readLine());
                            String name = scan.next();
                            int regionCount = scan.nextInt();
                            int color = scan.nextInt();
                            int InfLevel = scan.nextInt();
                            int tankLevel = scan.nextInt();
                            int jetLevel = scan.nextInt();
                            int killCount = scan.nextInt();
                            int deathCount = scan.nextInt();
                            System.out.println(name + " " + deathCount);
                            int exp = scan.nextInt();
                            User temp = new User(i, color, name, killCount, deathCount, regionCount, exp);
                            temp.setInfLevel(InfLevel);
                            temp.setTankLevel(tankLevel);
                            temp.setJetLevel(jetLevel);
                            userList.add(temp);

                            for (int j = 0; j < regionCount; j++) {
                                scan = new Scanner(br.readLine());
                                int id = scan.nextInt();
                                int infCount = scan.nextInt();
                                int tankCount = scan.nextInt();
                                int jetCount = scan.nextInt();
                                load_map.getRegionArray()[id].setOwner(i);
                                load_map.getRegionArray()[id].getTroopManager().clear();
                                for (int k = 0; k < infCount; k++)
                                    load_map.getRegionArray()[id].getTroopManager().addTroop(new Infantry(InfLevel));
                                for (int k = 0; k < tankCount; k++)
                                    load_map.getRegionArray()[id].getTroopManager().addTroop(new Tank(tankLevel));
                                for (int k = 0; k < jetCount; k++)
                                    load_map.getRegionArray()[id].getTroopManager().addTroop(new Jet(jetLevel));
                            }
                        }

                        for (int i = 0; i < deadCount; i++) {
                            scan = new Scanner(br.readLine());
                            String name = scan.next();
                            int color = scan.nextInt();
                            int kill = scan.nextInt();
                            int death = scan.nextInt();
                            int exp = scan.nextInt();
                            User temp = new User(userCount + i, color, name, kill, death, 0, exp);
                            deadList.add(temp);
                        }
                        scan.close();
                        br.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    game_M.initialize(userList, deadList, load_map, turn, turnCount);
                }
			} 
		} );
		loadGameButton.setBounds(300,120,100,100);
		add(loadGameButton);

		JButton optionsButton = new JButton("Options");
		optionsButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				System.exit(0);
			} 
		} );
		optionsButton.setBounds(300,230,100,100);
        optionsButton.setMargin(new Insets(0,0,0,0));
		add(optionsButton);

		JButton exitGameButton = new JButton("Exit Game");
        exitGameButton.setMargin(new Insets(0,0,0,0));
		exitGameButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				System.exit(0);
			} 
		} );
		exitGameButton.setBounds(300,340,100,100);
		add(exitGameButton);
	}
}
