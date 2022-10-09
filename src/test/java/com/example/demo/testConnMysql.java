//package com.example.demo;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import org.junit.jupiter.api.Test;
//
//public class testConnMysql 	{
//	@Test
//	public void TestConnect() throws Exception {
//		Class.forName("com.mysql.jdbc.Driver");
//
//		try (Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306", "root", "1234")) {
//			System.out.println("Connection success");		//test용
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//}
