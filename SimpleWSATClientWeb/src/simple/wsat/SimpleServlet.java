package simple.wsat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceRef;

/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet("/SimpleServlet")
public class SimpleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @WebServiceRef(name="HelloService")
    private HelloService service;

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

		
        DataSource ds = null;
        Connection con = null;
        Statement stmt = null;
        UserTransaction tran = null;
        try {
                InitialContext initCtx = new InitialContext();
                tran =(UserTransaction)initCtx.lookup("java:comp/UserTransaction");
                tran.begin();
                ds = (DataSource) initCtx.lookup("jdbc/SAMPLE2");
                con = ds.getConnection();
                stmt = con.createStatement();
                int i = stmt.executeUpdate("INSERT INTO EMP(ID, NAME) VALUES(3,'DDD')");
                System.out.println(i + " record was inserted.");

        		Hello proxy = service.getHelloPort();
        		BindingProvider bp = (BindingProvider)proxy;
        		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://v2-2542652.dev.fyre.ibm.com:9080/SimpleWSATWeb/HelloService");
        		System.out.println(proxy.say("Chihiro"));

        		tran.commit();
        } catch (Exception e) {
                e.printStackTrace();
                try {
					tran.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
        } finally {
                try {
                        if (stmt != null) stmt.close();
                        if (con != null) con.close();
                } catch (SQLException e) {
                                e.printStackTrace();
                }
        }
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}