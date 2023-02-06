package wxs;

import java.net.URL;
import java.util.ArrayList;

import com.ibm.websphere.objectgrid.ClientClusterContext;
import com.ibm.websphere.objectgrid.ObjectGrid;
import com.ibm.websphere.objectgrid.ObjectGridManager;
import com.ibm.websphere.objectgrid.ObjectGridManagerFactory;
import com.ibm.websphere.objectgrid.ObjectMap;
import com.ibm.websphere.objectgrid.Session;
import com.ibm.websphere.objectgrid.security.config.ClientSecurityConfiguration;

public class MyClient2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ObjectGridManager ogm = ObjectGridManagerFactory.getObjectGridManager();
		String catalogServiceEndpoints = "armless1.fyre.ibm.com:2809,vesture1.fyre.ibm.com:2810";
		try {
			ClientClusterContext ccc = ogm.connect(catalogServiceEndpoints, (ClientSecurityConfiguration) null, (URL) null);
			ObjectGrid og = ogm.getObjectGrid(ccc, "Grid1");
			ObjectGrid og2 = ogm.getObjectGrid(ccc, "Grid2");
			Session session = og.getSession();
			Session session2 = og2.getSession();
			ObjectMap om = session.getMap("Map1");
			ObjectMap om1 = session.getMap("Map11");
			ObjectMap om2 = session2.getMap("Map2");
			
			for(int i=1; i<5; i++) {
				System.out.println(i);
				om.insert(i, new byte[1024*1024*5] );
				om1.insert(i, new byte[1024*1024*5] );
				om2.insert(i, new byte[1024*1024*10] );
				Thread.sleep(1000*2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
