package com.oo.conquest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;

public class EndGameMenu extends Menu{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private final Color[] colors = {new Color(105,105,105), Color.BLUE, Color.ORANGE, new Color(128,0,128)};

	public EndGameMenu(ArrayList<User> users){
		super();
        users.sort(new Comparator<User>() {
            @Override
            public int compare(User user, User t1) {
                if (user.getExperiencePoints() < t1.getExperiencePoints())
                    return -1;
                else if (user.getExperiencePoints() == t1.getExperiencePoints())
                    return 0;
                return 1;
            }
        });

        for (int i = 0; i < users.size(); i++) {
            drawUser(20, 200*i, users.get(i));
            System.out.println(i);
        }
        JButton new_Game = new JButton("Go to Main Menu");
        new_Game.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String[] arg = {""};
                Main.main(arg);
            }
        });
        System.out.println("zaa");
        new_Game.setBounds(300, 300, 150, 150);
        add(new_Game);
	}

    public void drawUser(int x, int y, User u) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        System.out.println("" + u.getKillCount() + " " + u.getDeathCount() + " " + u.getExperiencePoints());
//        JButton user_name = new JButton(u.getName() + ": ");
//        JButton kill_count = new JButton("" + u.getKillCount());
//        JButton death_count = new JButton("" + u.getDeathCount());
        JLabel user_name = new JLabel(u.getName() + ": ");
        JLabel kill_count = new JLabel("Kill Count: " + u.getKillCount());
        JLabel death_count = new JLabel("Death Count: " + u.getDeathCount());
        user_name.setForeground(colors[u.getColor()]);

        panel.add(user_name);
        panel.add(kill_count);
        panel.add(death_count);
        panel.setBounds(x, y, 50, 150);
        this.add(panel);
    }
}
