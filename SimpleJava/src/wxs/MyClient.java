package wxs;

import java.net.URL;

import com.ibm.websphere.objectgrid.ClientClusterContext;
import com.ibm.websphere.objectgrid.ObjectGrid;
import com.ibm.websphere.objectgrid.ObjectGridManager;
import com.ibm.websphere.objectgrid.ObjectGridManagerFactory;
import com.ibm.websphere.objectgrid.ObjectMap;
import com.ibm.websphere.objectgrid.Session;
import com.ibm.websphere.objectgrid.security.config.ClientSecurityConfiguration;

public class MyClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String operation = "get";
		operation = args[0];

		ObjectGridManager ogm = ObjectGridManagerFactory.getObjectGridManager();
		String catalogServiceEndpoints = "10.21.34.38:4809,10.21.34.38:4810";
		try {
			ClientClusterContext ccc = ogm.connect(catalogServiceEndpoints, (ClientSecurityConfiguration) null, (URL) null);
			ObjectGrid og = ogm.getObjectGrid(ccc, "Grid01");
			Session session = og.getSession();
			ObjectMap om = session.getMap("Map01");
			
			if(operation.equals("add")) {
				Integer key = new Integer(Integer.parseInt(args[1]));
				String value = args[2];
				om.insert(key, value);
				System.out.println("key = " + key.toString() + ", value = " + value + " was added!" );				
			}
			else if(operation.equals("get")) {
				Integer key = new Integer(Integer.parseInt(args[1]));
				session.begin();
				Object obj = om.get(key);
				session.commit();
				System.out.println("key = " + key.toString() + ", value = " + obj.toString() + " was retrieved!" );				
			}
			else if(operation.equals("delete")) {
				Integer key = new Integer(Integer.parseInt(args[1]));
				session.begin();
				om.remove(key);
				session.commit();
				System.out.println("key = " + key.toString() + " was deleted!" );				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
