package simple.wxs.client;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.websphere.objectgrid.CatalogDomainInfo;
import com.ibm.websphere.objectgrid.CatalogDomainManager;
import com.ibm.websphere.objectgrid.ClientClusterContext;
import com.ibm.websphere.objectgrid.ConnectException;
import com.ibm.websphere.objectgrid.ElasticCacheClientConnectionHelper;
import com.ibm.websphere.objectgrid.ObjectGrid;
import com.ibm.websphere.objectgrid.ObjectGridManager;
import com.ibm.websphere.objectgrid.ObjectGridManagerFactory;
import com.ibm.websphere.objectgrid.ObjectMap;
import com.ibm.websphere.objectgrid.Session;
import com.ibm.websphere.objectgrid.security.config.ClientSecurityConfiguration;
import com.ibm.websphere.objectgrid.security.config.ClientSecurityConfigurationFactory;
import com.ibm.websphere.objectgrid.security.plugins.builtins.UserPasswordCredentialGenerator;

/**
 * Servlet implementation class SimpleServletLiberty
 */
@WebServlet("/SimpleServletLiberty")
public class SimpleServletLiberty extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SimpleServletLiberty() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectGrid aObjGrid = null;
		try {
			
			//https://www.ibm.com/support/knowledgecenter/en/SSTVLU_8.6.1/com.ibm.websphere.extremescale.doc/txs_authecaas.html
			/**
			 <elasticCacheClient id="Grid01" elasticCacheClientSecurityRef="securitySettings">
               <elasticCacheCatalogCluster>
                 <server host="vlrh1" port="4809" />
               </elasticCacheCatalogCluster>
             </elasticCacheClient>
             <elasticCacheClientSecurity id="securitySettings"
                                enabled="true"
                                credentialAuthentication="Required"
                                credentialGeneratorClass="com.ibm.websphere.objectgrid.security.plugins.builtins.UserPasswordCredentialGenerator"
                                credentialGeneratorProps="username password"
                                transportType="TCP/IP">
             </elasticCacheClientSecurity>
			**/
			//ObjectGridManager ogManager= ObjectGridManagerFactory.getObjectGridManager();
		    //CatalogDomainManager domainManager = ogManager.getCatalogDomainManager();
		    //CatalogDomainInfo domainInfo = domainManager.getDomainInfo("testGrid1"); //This should be id of elasticCacheClient element in server.xml
		    //String endpoints = domainInfo.getClientCatalogServerEndpoints();
		    //ClientSecurityConfiguration csc = domainInfo.getClientSecurityConfiguration();
		    //ClientClusterContext ccc = ogManager.connect(endpoints, csc, null);
		    //aObjGrid = ogManager.getObjectGrid(ccc, "testGrid1");
		    

		    //If security is not enabled,  ElasticCacheClientConnectionHelper can be used.
		    //https://www.ibm.com/support/knowledgecenter/en/SSTVLU_8.6.1/com.ibm.websphere.extremescale.doc/txsjavaclientliberty.html
			aObjGrid = ElasticCacheClientConnectionHelper.connect("Grid1");
		    
		    Session session = aObjGrid.getSession();
			ObjectMap om = session.getMap("Map1");
			
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

