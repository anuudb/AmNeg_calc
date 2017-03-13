package pseudoPrice;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class core {

	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ArrayList<amneg> amneg_list = new ArrayList();
		// JDBC driver name and database URL
		    String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		    String DB_URL = "jdbc:mysql://localhost/pricepattern";

		   //  Database credentials
		    String USER = "root";
		    String PASS = "";
		   
		   Connection conn = null;
		   Statement stmt = null; 
		   
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);

		      //STEP 4: Execute a query
		      System.out.println("Creating statement...");
		      stmt = (Statement) conn.createStatement();
		      String sql,sql_company;
		      
		      
		      sql_company = "SELECT PERMNO,amneg_id,dateMax_index,dateMin_index FROM amneg_2004";
		      ResultSet amnegs = stmt.executeQuery(sql_company);
		      //System.out.println("anushka");
		      
		      while (amnegs.next()) {
		    	  amneg amn = new amneg();
					amn.permno = amnegs.getInt("PERMNO");
					amn.amneg_id = amnegs.getInt("amneg_id");
					amn.dateMax_index = amnegs.getInt("dateMax_index");
					amn.dateMin_index = amnegs.getInt("dateMin_index");
					amneg_list.add(amn);
			  }
		      
		      
		      for (int i = 0; i < amneg_list.size(); i++) {
		    	  
		    	  sql =  "select a.date_id, (a.PseudoPRC-b.PseudoPRC) diff  FROM "+
		    			 "(SELECT date_id,PseudoPRC FROM stock_all_indexed WHERE PERMNO = "+amneg_list.get(i).permno+" and date_id BETWEEN "+(amneg_list.get(i).dateMax_index-1)+" and "+(amneg_list.get(i).dateMin_index)+") as b "+
		    			 " INNER JOIN "+
		    			 "(SELECT date_id, PseudoPRC FROM stock_all_indexed WHERE PERMNO = " +amneg_list.get(i).permno+ " and date_id BETWEEN "+(amneg_list.get(i).dateMax_index-1)+" and "+(amneg_list.get(i).dateMin_index)+") as a " +
		    			 "ON (b.date_id = a.date_id -1)";
		    	  //System.out.println(sql);
		    	  ResultSet rs = stmt.executeQuery(sql);
		    	  //System.out.println(sql);
		    	  ArrayList<double[]> prc_diff = new ArrayList<double[]>() ;
		    	  
		    	  	while(rs.next()){
		    	  		////System.out.println(amneg_list.get(i).amneg_id+" : "+Double.parseDouble(rs.getString("diff")));
		    	  		double[] data = new double[2];
		    	  		data[0] = Double.parseDouble(rs.getString("diff"));
		    	  		data[1] = Double.parseDouble(rs.getString("date_id"));
		    	  		prc_diff.add(data);
		    	  		////System.out.println(cnt++);
		    	  	}
		    	  	amneg_logic amn_logic = new amneg_logic();
		    	  	System.out.println("permno: "+amneg_list.get(i).permno);
		    	  	System.out.println("amneg_id: "+amneg_list.get(i).amneg_id);
		    	  	//System.out.println("date_id: "+prc_diff.get(i)[1]);
		    	  	ArrayList<double[]> amneg_reslt =  amn_logic.amneg_find(prc_diff);
		    	  	String inst_amneg_rlt = "";
		    	  	for (int j = 0; j < amneg_reslt.size(); j++) {
		    	  		 if(j!=0){
		    	  			 inst_amneg_rlt = inst_amneg_rlt+"," +"("+amneg_list.get(i).permno+","+amneg_list.get(i).amneg_id+","+amneg_reslt.get(j)[1]+","+amneg_reslt.get(j)[0]+")";
		    	  		 }else{
		    	  			inst_amneg_rlt = inst_amneg_rlt+"" +"("+amneg_list.get(i).permno+","+amneg_list.get(i).amneg_id+","+amneg_reslt.get(j)[1]+","+amneg_reslt.get(j)[0]+")"; 
		    	  		 }
		    	  	}
		    	  	String inst_amneg_rlt_hdr = "INSERT INTO amNeg_result_final (permno,amneg_id,date_id,PseudoPRC_diff) VALUES ";
				    //System.out.println(inst_amneg_rlt_hdr + inst_amneg_rlt);
		      }
		      
		      stmt.close();
		      conn.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
		  
		   
	}
	
	public static String lastCharRemove(String str) {
	    if (str != null && str.length() > 0 && str.charAt(str.length()-1)==',') {
	      str = str.substring(0, str.length()-1);
	    }
	    return str;
	}

}
