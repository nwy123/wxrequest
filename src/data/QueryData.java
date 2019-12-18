package data;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.DBUtil;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class QueryData
 */
@WebServlet(description = "QueryData", urlPatterns = { "/QueryData" })
public class QueryData extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8"); 
		response.setContentType("text/html; charset=utf-8");
		Connection con = null;  
		ResultSet rsd = null;  
		Statement pstd = null; 
		
		String sqld = "select * from biao2";
		
		//收集数据
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		
		try {   
			con = DBUtil.getConnection();   
			pstd = con.prepareStatement(sqld);   
			rsd=pstd.executeQuery(sqld);
			while (rsd.next( )) {    
				Map<String, String> dataMap = new LinkedHashMap<String, String>( );    
				dataMap.put("name", rsd.getString("addr"));
				dataMap.put("xiansuo", String.valueOf(rsd.getDouble("xiansuo")));
				dataMap.put("chashi", String.valueOf(rsd.getDouble("chashi")));
				dataMap.put("chafou", String.valueOf(rsd.getDouble("chafou")));
				dataMap.put("zaicha", String.valueOf(rsd.getDouble("zaicha")));				
				dataMap.put("jindu", String.valueOf(rsd.getDouble("lon")));
				dataMap.put("weidu", String.valueOf(rsd.getDouble("lat")));
				  
				dataList.add(dataMap); 
			}
			
		} catch (SQLException e) {   
			e.printStackTrace( );  
				
		} finally {   
			DBUtil.release(con, pstd, rsd); 
			
				
		}  
		System.out.println(dataList.size());
		JSONObject toJsonObject = new JSONObject();
		toJsonObject.accumulate("data", dataList);
		PrintWriter out = response.getWriter();
		out.print(toJsonObject);

		
	}
}
