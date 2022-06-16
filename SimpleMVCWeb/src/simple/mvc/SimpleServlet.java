package simple.mvc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet("/SimpleServlet")
public class SimpleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SimpleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("simple.mvc.SimpleServlet.doGet() >");
		System.out.println("input: " + request.getParameter("message"));
        getServletContext().getRequestDispatcher("index.jsp").forward(request, response);
        System.out.println("simple.mvc.SimpleServlet.doGet() <");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("simple.mvc.SimpleServlet.doPost() > ");
        System.out.println("input: " + request.getParameter("message"));
        getServletContext().getRequestDispatcher("index.jsp").forward(request, response);
        System.out.println("simple.mvc.SimpleServlet.doPost() < ");
		doGet(request, response);
	}

}
