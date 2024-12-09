package crm_app07controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_app07entity.RoleEntity;
import crm_app07service.RolesService;

/**
 * Servlet implementation class RoleController
 */
@WebServlet(name = "roleController", urlPatterns = {"/roles","/role-add"})
public class RoleController extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RolesService rolesService = new RolesService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getServletPath();
		if(path.equals("/roles")) {
			request.setAttribute("rolelist", rolesService.loadRoles());
			request.getRequestDispatcher("role-table.jsp").forward(request, response);
			
		}else if(path.equals("/role-add")){
			request.getRequestDispatcher("role-add.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getServletPath();
		if(path.equals("/role-add")) {
			String tenQuyen = request.getParameter("tenquyen");
			String moTa = request.getParameter("mota");
			boolean result = rolesService.addRole(tenQuyen, moTa);
			request.setAttribute("message", result?"add success":"add failed");
			request.getRequestDispatcher("role-add.jsp").forward(request, response);
		}
		
	}
	
	

}
