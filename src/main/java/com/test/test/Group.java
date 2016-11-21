/**
 * 
 */
package com.test.test;

import java.util.ArrayList;

/**
 * @author dalle
 *
 */
public class Group {

	private int groupID;
	private User admin;
	private Poll groupPoll;
	private ArrayList<String> members = new ArrayList<String>();
	
	public ArrayList<String> getRegisteredUsers(){
		return members;
	}

	public int getGroupID() {
		return groupID;
	}

	public User getAdmin() {
		return admin;
	}

	public Poll getGroupPoll() {
		return groupPoll;
	}
}
