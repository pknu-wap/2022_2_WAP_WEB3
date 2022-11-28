// <<<<<<< HEAD
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
// =======
package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import org.junit.jupiter.api.Test;

public class testConnMysql 	{
	@Test
	public void TestConnect() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver"); // com.mysql.jdbc.Driver가 deprecated 됐다고 뜸.

		try (Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306", "root", "1234")) {
			System.out.println("Connection success");		//test용
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
>>>>>>> refs/remotes/origin/leesh
