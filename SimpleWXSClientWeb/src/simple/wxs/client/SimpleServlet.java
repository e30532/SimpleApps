package simple.wxs.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.websphere.objectgrid.CatalogDomainInfo;
import com.ibm.websphere.objectgrid.ClientClusterContext;
import com.ibm.websphere.objectgrid.ObjectGrid;
import com.ibm.websphere.objectgrid.ObjectGridManager;
import com.ibm.websphere.objectgrid.ObjectGridManagerFactory;
import com.ibm.websphere.objectgrid.ObjectMap;
import com.ibm.websphere.objectgrid.Session;

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
		ObjectGridManager ogm = ObjectGridManagerFactory.getObjectGridManager();
		CatalogDomainInfo di = ogm.getCatalogDomainManager().getDomainInfo("CatalogServiceDomain");
		try {
			ClientClusterContext ccc = ogm.connect(di.getClientCatalogServerEndpoints(), di.getClientSecurityConfiguration(),null);
			ObjectGrid og = ogm.getObjectGrid(ccc, "Grid01");
			Session session = og.getSession();
			ObjectMap om = session.getMap("Map01");
			
			if(request.getParameter("operation").equals("add")) {
				Integer key = new Integer(Integer.parseInt((String)request.getParameter("id")));
				String value = new String((String)request.getParameter("msg"));
				om.insert(key, value);
				System.out.println("key = " + key.toString() + ", value = " + value + " was added!" );				
			}
			else if(request.getParameter("operation").equals("get")) {
				Integer key = new Integer(Integer.parseInt((String)request.getParameter("id")));
				session.begin();
				Object obj = om.get(key);
				session.commit();
				System.out.println("key = " + key.toString() + ", value = " + obj.toString() + " was retrieved!" );				
			}
			else if(request.getParameter("operation").equals("delete")) {
				Integer key = new Integer(Integer.parseInt((String)request.getParameter("id")));
				session.begin();
				om.remove(key);
				session.commit();
				System.out.println("key = " + key.toString() + " was deleted!" );				
			}
		} catch (Exception e) {
			e.printStackTrace();
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
