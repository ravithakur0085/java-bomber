package mypack;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Login")
public class Login extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname= request.getParameter("uname");
		String pass=request.getParameter("pass");
		
		HttpSession session=request.getSession();
		RequestDispatcher dispatcher=null;
		if(uname == null || uname.equals(""))
		{
			request.setAttribute("status","invalidEmail");
			dispatcher = request.getRequestDispatcher("index.html");
			dispatcher.forward(request, response);
		}
		if(pass == null || pass.equals(""))
		{
			request.setAttribute("status","invalidUpwd");
			dispatcher = request.getRequestDispatcher("index.html");
			dispatcher.forward(request, response);
		}
		Connection con=null;
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					 con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","0085");
					PreparedStatement pst=con.prepareStatement("select * from javabomber1 where uname = ? and pass = ?");
					pst.setString(1, uname);
					pst.setString(2, pass);
					ResultSet rs=pst.executeQuery();
					if(rs.next())
					{
						session.setAttribute("name", rs.getString("uname"));
						dispatcher = request.getRequestDispatcher("index.html");
					}else{
						request.setAttribute("status", "failed");
						dispatcher = request.getRequestDispatcher("Login.java");
					}
					dispatcher.forward(request, response);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e);
				}
	
	}

}
