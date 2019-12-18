package data;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import com.alibaba.druid.pool.DruidDataSource;
public class DBUtil {
	 private static DruidDataSource dsMySql = null;      
	 static{          
		 try {              
			 //1、初始化名称查找上下文            
			 Context ctx = new InitialContext( );              
			 //2、通过JNDI名称找到DataSource              
			 dsMySql = (DruidDataSource) ctx.lookup("java:comp/env/jdbc/MysqlDataSource"); 
		 } catch (NamingException e) {              
			 e.printStackTrace( );          
		 }      
	 }      
	 public static Connection getConnection( ) throws SQLException {          
		 return dsMySql.getConnection( );      
	 }      
	 public static void release(Connection conn, Statement st, ResultSet rs) {
			if (rs != null) {
				try {// 关闭存储查询结果的ResultSet对象
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				rs = null;
			}

			if (st != null) {
				try {// 关闭负责执行SQL命令的Statement对象
					st.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {// 关闭Connection数据库连接对象
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
}