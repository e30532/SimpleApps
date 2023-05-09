package wxs;

import java.net.URL;
import java.util.ArrayList;

import com.ibm.websphere.objectgrid.ClientClusterContext;
import com.ibm.websphere.objectgrid.NoActiveTransactionException;
import com.ibm.websphere.objectgrid.ObjectGrid;
import com.ibm.websphere.objectgrid.ObjectGridManager;
import com.ibm.websphere.objectgrid.ObjectGridManagerFactory;
import com.ibm.websphere.objectgrid.ObjectMap;
import com.ibm.websphere.objectgrid.Session;
import com.ibm.websphere.objectgrid.TransactionException;
import com.ibm.websphere.objectgrid.security.config.ClientSecurityConfiguration;

public class MyClient5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ObjectGridManager ogm = ObjectGridManagerFactory.getObjectGridManager();
		String catalogServiceEndpoints = args[0];
		Session session=null;
		try {
			ClientClusterContext ccc = ogm.connect(catalogServiceEndpoints, (ClientSecurityConfiguration) null, (URL) null);
			ObjectGrid og = ogm.getObjectGrid(ccc, "Grid1");
			session = og.getSession();
			ObjectMap om = session.getMap("Map1");
//			session.setTransactionIsolation(Session.TRANSACTION_READ_COMMITTED);
/**
			session.begin();
			System.out.println(om.get(1));
//			om.getForUpdate(1);
//			om.lock(1, LockMode.EXCLUSIVE);
//			om.getForUpdate(1);
			om.update(1, "a2");
			System.out.println(om.get(1));
//			Object value = om.getForUpdate(6);
//			om.update(1, "2");
			System.out.println("sleep>" + System.currentTimeMillis());
			Thread.sleep(1000*40);
			System.out.println("sleep<" + System.currentTimeMillis());
//			om.invalidate(1, false);
			System.out.println(om.get(1));
			System.out.println("commit>" + System.currentTimeMillis());
			session.commit();
			System.out.println("commit<"+ System.currentTimeMillis());
			System.out.println(om.get(1));
**/
//			System.out.println(om.getForUpdate(1));
			for (int i=0; i<1; i++) {
				om.update(i,new byte[10*1000*1000]);
			}
//			session.commit();
//			System.out.println(om.get(7));
		} catch (Exception e) {
			try {
				System.out.println("rollback>" + System.currentTimeMillis());
//				session.rollback();
				System.out.println("rollback<" + System.currentTimeMillis());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

}
