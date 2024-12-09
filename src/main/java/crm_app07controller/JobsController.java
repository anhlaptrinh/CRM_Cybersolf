package crm_app07controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_app07service.JobsService;

/**
 * Servlet implementation class JobsController
 */
@WebServlet(name="JobsController", urlPatterns = {"/groupwork"})
public class JobsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private JobsService jobsService =  new JobsService();
   
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path =  request.getServletPath();
		if(path.equals("/groupwork")) {
			request.setAttribute("listJob", jobsService.getListJob());
			request.getRequestDispatcher("groupwork.jsp").forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
