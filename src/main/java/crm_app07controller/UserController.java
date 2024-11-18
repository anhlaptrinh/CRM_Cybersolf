package crm_app07controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_app07entity.LoginEntity;
import crm_app07entity.RoleEntity;
import crm_app07entity.TaskEntity;
import crm_app07service.UserService;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@WebServlet(name = "userController", urlPatterns = { "/users", "/user-add", "/user-details", "/user-edit",
		"/user-update" })
public class UserController extends HttpServlet {

	private UserService service = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath(); // trả ra đường dẫn servlet đang gọi

		if (path.equals("/users")) {
			loadUsers(req, resp);
		} else if (path.equals("/user-add")) {
			addUser(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath(); // trả ra đường dẫn servlet đang gọi

		if (path.equals("/user-add")) {
			addUserPost(req, resp);
		} else if (path.equals("/user-details")) {
			loadUserDetails(req, resp);
		} else if (path.equals("/user-edit")) {
			editUser(req, resp);
		} else if (path.equals("/user-update")) {
			updateUser(req, resp);
		}
	}

	private void addUserPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String fullname = req.getParameter("fullname");
		int roleId = Integer.parseInt(req.getParameter("role") != null ? req.getParameter("role") : "0");

		boolean result = service.insertUser(email, password, fullname, roleId);

		List<RoleEntity> listRole = service.getAllRole();

		req.setAttribute("listRole", listRole);
		req.setAttribute("message", result ? "Add user successfully" : "Add user fail");

		req.getRequestDispatcher("user-add.jsp").forward(req, resp);
	}

	private void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<RoleEntity> listRole = service.getAllRole();

		req.setAttribute("listRole", listRole);
		
		req.getRequestDispatcher("user-add.jsp").forward(req, resp);
	}

	private void loadUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");

		if (id != null) {
			// Tinh nang xoa
			service.deleteUserById(Integer.parseInt(id));
		}

		List<LoginEntity> listUser = service.getAllUser();
		String message = req.getParameter("message");
		req.setAttribute("message", message);
		req.setAttribute("listUser", listUser);

		req.getRequestDispatcher("user-table.jsp").forward(req, resp);
	}

	private void loadUserDetails(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		String fullname = req.getParameter("fullname");
		String email = req.getParameter("email");

		List<TaskEntity> listTask = service.getTask(Integer.parseInt(id));
		req.setAttribute("fullname", fullname);
		req.setAttribute("email", email);
		req.setAttribute("listTask", listTask);
		req.getRequestDispatcher("user-details.jsp").forward(req, resp);
	}

	private void editUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<RoleEntity> listRole = service.getAllRole();
		String id = req.getParameter("id");
		String fullname = req.getParameter("fullname");
		String email = req.getParameter("email");
		String role = req.getParameter("role");
		req.setAttribute("id", id);
		req.setAttribute("fullname", fullname);
		req.setAttribute("email", email);
		req.setAttribute("role", role);

		req.setAttribute("listRole", listRole);
		req.getRequestDispatcher("user-edit.jsp").forward(req, resp);
	}

	private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String roleParam = req.getParameter("role");
	    int roleId = (roleParam != null && !roleParam.isEmpty()) ? Integer.parseInt(roleParam) : 0; // Kiểm tra null và rỗng

	    int id = Integer.parseInt(req.getParameter("id")); // Giả định "id" luôn hợp lệ
	    boolean result = service.updateRoleUserById(id, roleId);

	    List<RoleEntity> listRole = service.getAllRole();

	    req.setAttribute("listRole", listRole);
	    String message = result ? "Update user successfully" : "Update user failed";

	    resp.sendRedirect("users?message=" + URLEncoder.encode(message, "UTF-8"));
	}

}
