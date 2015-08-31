
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DemoCustomer;


/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static Connection conn;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String message="";
		EntityManager em = model.DBUtil.getEmFactory().createEntityManager();
		try {

			String search = request.getParameter("lastname");
			String q="select t from DemoCustomer t where t.custFirstName LIKE \"%"+search+"%\"";
			//System.out.println(q);
			TypedQuery<DemoCustomer>bq =em.createQuery(q,DemoCustomer.class);

			List<DemoCustomer> list=bq.getResultList();
			message+="<table class=\"table table-hover\"><tr><td><b>People Found </td></tr>";
			for(DemoCustomer temp:list){
				message+="<td><a href=\"GetCustomer?name="+temp.getCustFirstName() +"\">"+temp.getCustFirstName()+"</a></td>";
				message+="<td>"+temp.getCustStreetAddress1()+"</td></tr>";
			}
			message+="</table>";
			request.setAttribute("message", message);

		 getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
