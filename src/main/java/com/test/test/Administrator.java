/**
 * 
 */
package com.test.test;

import java.sql.SQLException;

/**
 * @author dalle
 *
 */
public class Administrator {
	
	private String username;
	private String password;
	private String email;
	private ReportedQuestion[] reportedQuestions;
	
	public static Administrator verifyAdmin(String email, String password) throws SQLException{
		return DBQuery.adminLogin(email, password);
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
	public ReportedQuestion[] getReportedQuestions() {
		return reportedQuestions;
	}
	public void setReportedQuestions(ReportedQuestion[] reportedQuestions) {
		this.reportedQuestions = reportedQuestions;
	}
	
	

}
