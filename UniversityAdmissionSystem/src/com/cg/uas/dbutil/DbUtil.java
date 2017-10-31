package com.cg.uas.dbutil;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtil {

	public static Connection getConnection() throws IOException, SQLException

	{
		Properties prop=readDbInfo();
		String url=prop.getProperty("url");
		String username=prop.getProperty("username");
		String password=prop.getProperty("password");
		
		Connection conn=DriverManager.getConnection(url,username,password);
		return conn;
	}
	
	
	private static Properties readDbInfo() throws IOException
	{
		Properties p=new Properties();
		FileReader fr=new FileReader("DBInfo.properties");
		p.load(fr);
		return p;
	}
}
