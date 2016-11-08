package com.test.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBQuery {

	Connection conn = DBConnection.getConnection();
	static Statement stmt;

	public static Statement getStatement(Connection con) {

		if (stmt == null) {

			try {
				stmt = con.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return stmt;
	}
	
	public DBQuery(){
		
		stmt = null;
		
	}
	
	public ResultSet dbQuery(String query) throws SQLException{
		return  stmt.executeQuery(query);
	}
}
