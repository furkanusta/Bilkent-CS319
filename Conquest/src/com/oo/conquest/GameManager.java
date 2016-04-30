package com.oo.conquest;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameManager {
	private ScreenManager screen_M;
	private SoundManager sound_M;
	private MainMenu main_Menu;
	private MapManager map_M; 
	private MapView map_V;
	private UserManager user_M;
	private ArrayList<User> dead_users;
	private WarManager war_M;
	private int turn;
	private Timer timer;
	private int timeLeft;
	private int turnCount;
	private UpgradeMenu upgrade_M;

	public GameManager(){
		GameManager parent = this;
		screen_M = new ScreenManager();
		sound_M = new SoundManager();
		war_M = new WarManager();
		ImageIcon image = resizeImage(new ImageIcon("images/startImage.jpg"), 1024, 768);
		JLabel label = new JLabel("", image, JLabel.CENTER);
		JPanel startPanel = new JPanel(new BorderLayout());
		startPanel.add( label, BorderLayout.CENTER );
		startPanel.setSize(new Dimension(1024, 768));
		label.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{

				main_Menu = new MainMenu(screen_M, parent);
				screen_M.clearContents();
				screen_M.draw(main_Menu, 0, 0);

			}
		});
		screen_M.draw(startPanel, 0, 0);
		turn = 0;
		timeLeft = 30;
		turnCount = 0;
	}

	private boolean isFinished(){
		for(int i=0;i<user_M.getUserList().length;i++){
			if(user_M.getUserList()[i].getNumberOfRegions() == 12){
                dead_users.add(user_M.getUserList()[i]);
				this.getScreenManager().clearContents();
				this.getScreenManager().draw(new EndGameMenu(dead_users), 0, 0);
                return true;
			}
		}
        return false;
	}

	private void isDead(){
		for(int i=0;i<user_M.getUserList().length;i++){
			if(user_M.getUserList()[i].getNumberOfRegions() == 0){
                dead_users.add(user_M.getUserList()[i]);
                System.out.println(user_M.getUserList()[i].getName() + "listeden silindi!");
                user_M.removeUser(i);
			}
		}
	}

	public int getTurn(){
		return turn;
	}

	public void updateTurn(){
		if (isFinished()) {
            stopTimer();
            return;
        }
		isDead();

		new BonusManager().checkBonusCondition(map_M, user_M.getUserList()[turn], turnCount);

		turn = (turn+1) % user_M.getUserList().length;
		if(turn == 0){
			turnCount++;
		}
		timeLeft = 30;
		map_V.repaintMap();
		upgrade_M.repaintMenu(user_M, turn);
	}

	public WarManager getWarManager() {
		return war_M;
	}

	public ScreenManager getScreenManager() {
		return screen_M;
	}

    public ArrayList<User> getDeadList() {
        return dead_users;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public MapManager getMapManager() { return map_M; }

	public SoundManager getSoundManager() {return sound_M;}

	public void initialize(ArrayList<User> userList, ArrayList<User> deadList, MapManager map, int turn, int turnCount){
		User[] userArray = new User[userList.size()];
		userArray = userList.toArray(userArray);
		user_M = new UserManager(userArray);
        dead_users = new ArrayList<>();

        if (map == null) {
            map_M = new MapManager();
            map_M.initialize(userList.size());
            for (int i = 0; i < userList.size(); i++) {
                userList.get(i).setNumberOfRegions(12 / userList.size());
            }
        }
        else {
            map_M = map;
            for (int i = 0; i < userList.size(); i++) {
                int count = 0;
                for (int j = 0; j < map.getRegionArray().length; j++)
                    if (map.getRegionArray()[j].getOwner() == i)
                        count++;
                userList.get(i).setNumberOfRegions(count);
            }
            if (deadList != null)
                dead_users = deadList;
            this.turn = turn;
            this.turnCount = turnCount;
        }
		sound_M.start();
		map_V = new MapView(map_M.getRegionArray(), user_M, this);
		screen_M.clearContents();
		screen_M.draw(map_V, 0, 0);
		upgrade_M = new UpgradeMenu(user_M, user_M.getUserList()[turn].getId());
		screen_M.draw(upgrade_M, 0, 468);
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				timeLeft--;
				map_V.updateTime(timeLeft);
				if(timeLeft == 0){
					updateTurn();
				}
			}
		}, 1000, 1000);
	}

	public void stopTimer(){
		timer.cancel();
	}

	public void continueTimer(){
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				timeLeft--;
				map_V.updateTime(timeLeft);
				if(timeLeft == 0){
					updateTurn();
				}
			}
		}, 1000, 1000);
	}

	public boolean changeColor (int newColor) {
		return user_M.changeColor(turn, newColor);
	}

	public ImageIcon resizeImage(ImageIcon icon, int x, int y){
		Image img = icon.getImage() ;  
		Image newimg = img.getScaledInstance( x, y,  java.awt.Image.SCALE_SMOOTH ) ;  
		icon = new ImageIcon( newimg );

		return icon;
	}
}
