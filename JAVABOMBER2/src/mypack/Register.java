package mypack;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@SuppressWarnings("serial")
@WebServlet("/Register")
public class Register extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname=request.getParameter("uname");
		String email=request.getParameter("email");
		String pass=request.getParameter("pass");
		RequestDispatcher dispatcher=null;
		Connection con=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","0085");
			PreparedStatement pst = con.prepareStatement("insert into javabomber1 values(?,?,?)");
			pst.setString(1, uname);
			pst.setString(2, email);
			pst.setString(3, pass);
			@SuppressWarnings("unused")
			int row=pst.executeUpdate();
			dispatcher=request.getRequestDispatcher("account.html");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	}


