/**
 * Author: Kevin Dalle
 * Date: 11/21/16
 * Purpose: This is the group class that will be used for group polls.
 */
package com.test.test;

import java.util.ArrayList;

public class Group {

	private int groupID;
	private String admin;
	private Poll groupPoll;
	private String groupName;
	private ArrayList<String> members = new ArrayList<String>();
	
	// Allows for the creation of a group object with an already available member list
	public Group(int groupID, String admin, Poll poll, ArrayList<String> mem){
		this.groupID = groupID;
		this.admin = admin;
		this.groupPoll = poll;
		this.members = mem;
	}
	
	// This is for if you do not have a list of people already
	public Group(int groupID, String admin, Poll poll, String groupName)
	{
		this.groupID = groupID;
		this.admin = admin;
		this.groupPoll = poll;
		this.groupName = groupName;
	}
	
	public Group(){
		
	}
	
	public static void addUserToGroup(String username, String groupNum){
		DBQuery.addUserToGroup(username, groupNum);
	}
	
	public static void deleteUserFromGroup(String username, String groupNum){
		DBQuery.deleteUserFromGroup(username, groupNum);
	}
	
	public static Group getGroup(int groupNum){
		return DBQuery.getPollGroup(groupNum);
	}
	
	public static void createGroup(String groupName, int pollNum, String username){
		DBQuery.createGroup(groupName, pollNum, username);
	}
	
	public static ArrayList<Group> getYourPollGroups(String username){
		return DBQuery.getYourPollGroups(username);
	}
	
	public ArrayList<String> getRegisteredUsers(){
		return members;
	}

	public int getGroupID() {
		return groupID;
	}
	
	public void setGroupID(int groupID){
		this.groupID = groupID;
	}

	public String getAdmin() {
		return admin;
	}
	
	public void setAdmin(String admin){
		this.admin = admin;
	}
	
	public Poll getGroupPoll() {
		return groupPoll;
	}
	
	public void setGroupPoll(Poll groupPoll){
		this.groupPoll = groupPoll;
	}
	
	// This simple method is just adding someone to the groupdd
	public void addUser(String newUser){
		members.add(newUser);
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
