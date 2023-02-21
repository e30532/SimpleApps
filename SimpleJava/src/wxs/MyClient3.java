package wxs;

import java.net.URL;
import java.util.ArrayList;

import com.ibm.websphere.objectgrid.ClientClusterContext;
import com.ibm.websphere.objectgrid.LockMode;
import com.ibm.websphere.objectgrid.ObjectGrid;
import com.ibm.websphere.objectgrid.ObjectGridManager;
import com.ibm.websphere.objectgrid.ObjectGridManagerFactory;
import com.ibm.websphere.objectgrid.ObjectMap;
import com.ibm.websphere.objectgrid.Session;
import com.ibm.websphere.objectgrid.security.config.ClientSecurityConfiguration;

public class MyClient3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ObjectGridManager ogm = ObjectGridManagerFactory.getObjectGridManager();
		String catalogServiceEndpoints = "9.46.115.205:2809";
		try {
			ClientClusterContext ccc = ogm.connect(catalogServiceEndpoints, (ClientSecurityConfiguration) null, (URL) null);
			ObjectGrid og = ogm.getObjectGrid(ccc, "Grid01");
			Session session = og.getSession();
			session.setRequestRetryTimeout(30000);
			ObjectMap om = session.getMap("Map01");
//			session.setTransactionIsolation(Session.TRANSACTION_READ_COMMITTED);

			om.insert(0, "WASWIN");
//			session.begin();
//			System.out.println(om.get(1));
//				om.update(1,"a2");
/**
			session.begin();
			System.out.println(om.get(1));
//			om.getForUpdate(1);	
			om.update(1, "a11");
			System.out.println("sleep>" + System.currentTimeMillis());
			Thread.sleep(1000*30);
			System.out.println("sleep<" + System.currentTimeMillis());
			System.out.println(om.get(1));
			System.out.println("commit>" + System.currentTimeMillis());
			session.commit();
			System.out.println("commit<" + System.currentTimeMillis());
			System.out.println(om.get(1));
**/
			//			om.getForUpdate(1);
//			om.lock(1, LockMode.EXCLUSIVE);
//			System.out.println(om.get(1));
//			Object value = om.getForUpdate(6);
//			om.update(1, "2");


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
