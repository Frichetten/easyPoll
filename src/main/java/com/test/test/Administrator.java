/**
 * 
 */
package com.test.test;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author dalle
 *
 */
public class Administrator {
	
	private String username;
	private String password;
	private String email;
	private ArrayList<ReportedQuestion> reportedQuestions;
	
	public static Administrator verifyAdmin(String email, String password) throws SQLException{
		return DBQuery.adminLogin(email, password);
	}
	
	public Administrator(){
		
	}
	
	
	public Administrator(String username) throws SQLException {
		Administrator ad = DBQuery.getAdmin(username);
		this.username = ad.getUsername();
		this.email = ad.getEmail();
		this.reportedQuestions = ad.getReportedQuestions();
	}
	public Administrator(String username, String email, ArrayList<ReportedQuestion> reportedQuestions){
		this.username = username;
		this.email = email;
		this.reportedQuestions = reportedQuestions;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ArrayList<ReportedQuestion> getReportedQuestions() {
		return reportedQuestions;
	}
	public void setReportedQuestions(ArrayList<ReportedQuestion> reportedQuestions) {
		this.reportedQuestions = reportedQuestions;
	}
	
	

}
