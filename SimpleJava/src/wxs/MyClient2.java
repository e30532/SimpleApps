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
		String catalogServiceEndpoints = "pontiffs1.fyre.ibm.com:2809,canaster1.fyre.ibm.com:2810";
		try {
			ClientClusterContext ccc = ogm.connect(catalogServiceEndpoints, (ClientSecurityConfiguration) null, (URL) null);
			ObjectGrid og = ogm.getObjectGrid(ccc, "Grid01");
			Session session = og.getSession();
			ObjectMap om = session.getMap("Map01");
			ObjectMap om2 = session.getMap("Map02");
			ObjectMap om3 = session.getMap("Map03");

			for(int j =0; j<5; j++) {
			om.insert(10+j, "1");
			om2.insert(20+j, "2");
			om3.insert(30+j, "3");

			long start_point = System.currentTimeMillis();			
		    for(int i=0; i<1000; i++) {
		    	om.update(10+j, "11");
		    	om.get(10+j);
		    	om.update(10+j, "1");
		    }
		    long end_point = System.currentTimeMillis();
		    long processing_time = end_point - start_point;
			System.out.println("processing time(optimistic+invalidation): " + processing_time);

		    start_point = System.currentTimeMillis();			
		    for(int i=0; i<1000; i++) {
		    	om2.update(20+j, "22");
		    	om2.get(20+j);
		    	om2.update(20+j, "2");
		    }
		    end_point = System.currentTimeMillis();
		    processing_time = end_point - start_point;
			System.out.println("processing time(pessmistic): " + processing_time);

		    start_point = System.currentTimeMillis();			
		    for(int i=0; i<1000; i++) {
		    	om3.update(30+j, "33");
		    	om3.get(30+j);
		    	om3.update(30+j, "3");
		    }
		    end_point = System.currentTimeMillis();
		    processing_time = end_point - start_point;
			System.out.println("processing time(optimistic): " + processing_time);

			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
