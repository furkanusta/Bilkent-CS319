package com.oo.conquest;

/**
 * Created by eksi on 4/29/16.
 */
public class GameInfo {

    private User[] users;
    GameInfo(int userNo) {
        setUsers(new User[userNo]);
    }
	public User[] getUsers() {
		return users;
	}
	public void setUsers(User[] users) {
		this.users = users;
	}
}
