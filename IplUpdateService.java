package iplteam;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IplUpdateService
 */
@WebServlet("/IplUpdateService")
public class IplUpdateService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IplUpdateService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		 ;
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
				
				SummaryTable st = new SummaryTable();
				
		        st.setTeamId(request.getParameter("teamId"));
		        st.setOppId(request.getParameter("oppId"));
		        String ownScore  = request.getParameter("ownScore");
		        st.setOwnScore(Integer.parseInt(ownScore));
		        String oppScore  = request.getParameter("oppScore");
		        st.setOppScore(Integer.parseInt(oppScore));

		        if(st.getTeamId().equalsIgnoreCase(st.getOppId()))    // st = object of Summary Table
		        {
		     	   out.println("<p style= 'color:red'>Invalid Input</p>");
		     	   request.getRequestDispatcher("IplUpdateTable.jsp").include(request, response);
		        }
		        else{
		             	List<PointTable> pt = Dao.table();                    //pt = list of Point Table type having complete Point table
		        	  
		             	checkExist(st.getTeamId(), pt);
		             	checkExist(st.getOppId(), pt);
		             	
		        	if(st.getOwnScore() > st.getOppScore())
		 		   {			     
		 			   updatePoints(pt, st.getTeamId(), 3);
		 			     
		 		   }
		 		   else if(st.getOwnScore() < st.getOppScore())
		 		   {
		 			   updatePoints(pt, st.getOppId(), 3);
		 			   
		 		   }
		 		   else
		 		   {			   
		 			   updatePoints(pt, st.getTeamId(), 1);			     
		 			     
		 			   updatePoints(pt, st.getOppId(), 1);
		 		   }
		 		   
		 		   int status = Dao.addIntoSummary(st);
		 				 		
		 			String tid = st.getTeamId();
		 		   st.setTeamId(st.getOppId());
		 		   st.setOppId(tid);    
		 		   
		 		   int score = st.getOwnScore();
		 		   st.setOwnScore(st.getOppScore());
		 		   st.setOppScore(score);
		 		   
		 		   int status1 = Dao.addIntoSummary(st);
		 		   if(status > 0 && status1 > 0)
		           	   out.print("<p style= 'color:green'>Successfully Added Into Database</p>");			 
		     	   else
		     		   out.print("<p style= 'color:red'>Error Occured</p>");
		     	       
		     		   request.getRequestDispatcher("IplUserView.jsp").include(request, response);
	                 
		     }
	}	
      
	public static void updatePoints(List<PointTable>pt, String id, int p)
	{
		List <PointTable> pf = pt.stream().filter( x -> x.getTeamId().equalsIgnoreCase(id)).collect(Collectors.toList());
	      
	     int points = pf.get(0).getTotalPoints() + p;
	     
	     PointTable obj = new PointTable();
	     obj.setTeamId(id);
	     obj.setTotalPoints(points);
	     
	     Dao.updatePoints(obj);
	}
	
	/* Method to Check if New Team is Added to Not  */
	public static void checkExist(String id, List<PointTable>pt)          
	{
		List <PointTable> pf = pt.stream().filter( x -> x.getTeamId().equalsIgnoreCase(id)).collect(Collectors.toList());
		if(pf.get(0).getTeamId() == null)
		{		     
		     PointTable obj = new PointTable();
		     obj.setTeamId(id);
		     obj.setTotalPoints(0);
		     obj.setTeamName(Dao.fetchName(id));
		     
		     Dao.addIntoPointTable(obj);
		}
	}
	

}
