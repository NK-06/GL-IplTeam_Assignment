package iplteam;

import java.sql.*;
import java.util.*;

public class Dao {

		ResultSet rs;
		PreparedStatement ps;
		Statement st;		
		String query ;
		
		/* Method to get Database Connection */
		public static Connection getCon() 
		{
			Connection con = null;
			try {
				   Class.forName("oracle.jdbc.driver.OracleDriver");
				    con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","xm");
				  //System.out.println("Connected...");
			} catch(Exception e) {
				e.printStackTrace();
			}
		    return con;
		}
		
		/* Method to return complete Point Table Data   */
		public static List<PointTable> table()
		{
			List<PointTable> pt = new ArrayList<PointTable>();
			
			try {
				   Connection con = getCon();
				  Statement st = con.createStatement();
		    		
		    		String query = "SELECT * from point_table";
		    		ResultSet rs = st.executeQuery(query);
		    	   
		    		while(rs.next())
		    		{
		    			PointTable p = new PointTable();
		    			
		    		   p.setTeamId(rs.getString(1));
		    		    p.setTeamName(rs.getString(2));
		    		    p.setTotalPoints(rs.getInt(3));
		    		    
		    		pt.add(p);
		    		}
		    		st.close();
		    		con.close();
			  }catch(Exception e1) {
				e1.printStackTrace();
		      	}
			return pt;
		}
		
		/* Method to Fetch Data From Summary Table  */
		public static List<SummaryTable> summary()
		{
			List<SummaryTable> pt = new ArrayList<SummaryTable>();
			
			try {
				   Connection con = getCon();
				  Statement st = con.createStatement();
		    	
				  String query = "SELECT p.team_id ,p.team_name,(select c.team_name from id_name c where s.opp_id = c.team_id) as a,"
				  		+ " s.own_score, s.opp_score FROM id_name p LEFT JOIN ipl_summary s on p.team_id = s.team_id";
				  ResultSet rs = st.executeQuery(query);
		    	   
		    		while(rs.next())
		    		{
		    			SummaryTable p = new SummaryTable();
		    			
		    		    p.setTeamId(rs.getString(1));
		    		    p.setTeam(rs.getString(2));
		    		    p.setOppTeam(rs.getString(3));
		    		    p.setOwnScore(rs.getInt(4));
		    		    p.setOppScore(rs.getInt(5));
		    		    
		    		pt.add(p);
		    		}
		    		st.close();
		    		con.close();
			  }catch(Exception e1) {
				e1.printStackTrace();
		      	}
			return pt;
		}
		
		/*  Method to Fetch Team Name   */   
		public static String fetchName(String id)
		{	
			 String name  = new String();
			try {
				   Connection con = getCon();
				  PreparedStatement ps = con.prepareStatement("SELECT team_name from id_name WHERE team_id = ?");
		    		ps.setString(1, id);		    		
		    		ResultSet rs = ps.executeQuery();
		    		rs.next();
		    	      name = rs.getString(1);
		    		ps.close();
		    		con.close();
			  }catch(Exception e1) {
				e1.printStackTrace();
		      	}
			return name;
		}    
		
		/* Method to Add New Data Into Summary Table   */
		public static int addIntoSummary(SummaryTable e)
		{
			int i = 0;
			try {
				    Connection con = getCon();
				    PreparedStatement ps=con.prepareStatement("INSERT INTO ipl_summary VALUES(?, ?, ?, ?)");
				    
				    ps.setString(1, e.getTeamId());
				    ps.setString(2, e.getOppId());
				    ps.setInt(3, e.getOwnScore());
				    ps.setInt(4, e.getOppScore());
				    
				    i = ps.executeUpdate();
				    
				    ps.close();
				    con.close();
			    
			} catch(Exception ex) {ex.printStackTrace();}
			
			return i;
		}
		
		/* Method to Add New Team Into Point Table  */
		public static int addIntoPointTable(PointTable e)
		{
			int i = 0;
			try {
				    Connection con = getCon();
				    PreparedStatement ps=con.prepareStatement("INSERT INTO point_table VALUES(?, ?, ?)");
				    
				    ps.setString(1, e.getTeamId());
				    ps.setString(2, e.getTeamName());
				    ps.setInt(3, e.getTotalPoints());
				    				    
				    i = ps.executeUpdate();
				    
				    ps.close();
				    con.close();
			    
			} catch(Exception ex) {ex.printStackTrace();}
			
			return i;
		}
	    
		/*  Method to Update Points into Point Table  */
		public static int updatePoints(PointTable e)
		{
			int i = 0;
			try {
				    Connection con = getCon();
				    PreparedStatement ps=con.prepareStatement("Update point_table SET total_points = ? WHERE team_id = ?");
				    
				    ps.setInt(1, e.getTotalPoints());
				    ps.setString(2, e.getTeamId());
				    
				    i = ps.executeUpdate();
				    
				    ps.close();
				    con.close();
			    
			} catch(Exception ex) {ex.printStackTrace();}
			
			return i;
		}
		
		/*  Method to Fetch Id & Name From Id_Name Table  */
		public static List<PointTable> idName()
		{
			List<PointTable> pt = new ArrayList<PointTable>();
			
			try {
				   Connection con = getCon();
				  Statement st = con.createStatement();
		    		
		    		String query = "SELECT * from id_name";
		    		ResultSet rs = st.executeQuery(query);
		    	   
		    		while(rs.next())
		    		{
		    			PointTable p = new PointTable();
		    			
		    		   p.setTeamId(rs.getString(1));
		    		    p.setTeamName(rs.getString(2));
		    		   		    		    
		    		pt.add(p);
		    		}
		    		st.close();
		    		con.close();
			  }catch(Exception e1) {
				e1.printStackTrace();
		      	}
			return pt;
		}		
		
}
