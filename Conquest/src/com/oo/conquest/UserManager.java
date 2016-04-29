package com.oo.conquest;

import java.util.ArrayList;
import java.util.Arrays;

public class UserManager {
	User[] userList;
	public UserManager(User[] userList){
		this.userList = userList;
	}
	
	public User[] getUserList(){
		return userList;
	}
	
	public void updateStatistics(int userId, int killCount, int deathCount){
		userList[userId].updateStatistics(killCount, deathCount);
	}
	
	public void removeUser(int userId){
		ArrayList<User> temp = new ArrayList<User> (Arrays.asList(userList));
		temp.remove(userId);
		userList = temp.toArray(new User[userList.length-1]);
	}
}
