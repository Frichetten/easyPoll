package com.test.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBQuery{

	static Connection dbc = DBConnection.getConnection();

	static Statement statement;
	
	public void setStatement() throws SQLException{
		this.statement = dbc.createStatement();
	}

}
