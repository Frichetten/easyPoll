/**
 * Author: Kevin Dalle
 * Date: 11/21/16
 * Purpose: This is the group class that will be used for group polls.
 */
package com.test.test;

import java.util.ArrayList;

/**
 * @author dalle
 *
 */
public class Group {

	private int groupID;
	private String admin;
	private Poll groupPoll;
	private ArrayList<String> members = new ArrayList<String>();
	
	// Allows for the creation of a group object with an already available member list
	public Group(int groupID, String admin, Poll poll, ArrayList<String> mem){
		this.groupID = groupID;
		this.admin = admin;
		this.groupPoll = poll;
		this.members = mem;
	}
	
	// This is for if you do not have a list of people already
	public Group(int groupID, String admin, Poll poll)
	{
		this.groupID = groupID;
		this.admin = admin;
		this.groupPoll = poll;
	}
	
	public ArrayList<String> getRegisteredUsers(){
		return members;
	}

	public int getGroupID() {
		return groupID;
	}

	public String getAdmin() {
		return admin;
	}

	public Poll getGroupPoll() {
		return groupPoll;
	}
	
	// This simple method is just adding someone to the groupdd
	public void addUser(String newUser){
		members.add(newUser);
	}
}
