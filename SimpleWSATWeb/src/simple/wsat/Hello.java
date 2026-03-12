package simple.wsat;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.sql.DataSource;

@WebService
public class Hello {

	public String say(String name) {
        DataSource ds = null;
        Connection con = null;
        Statement stmt = null;
        try {
        		InitialContext initCtx = new InitialContext();
                ds = (DataSource) initCtx.lookup("jdbc/SAMPLE");
                con = ds.getConnection();
                stmt = con.createStatement();
        		System.out.println("insert>");
                int i = stmt.executeUpdate("INSERT INTO EMP(ID, NAME) VALUES(4,'DDD')");
                System.out.println(i + " record was inserted.");
        } catch (Exception e) {
                e.printStackTrace();
        } finally {
                try {
                        if (stmt != null) stmt.close();
                        if (con != null) con.close();
                } catch (SQLException e) {
                	e.printStackTrace();
                }
        }
//        int i = 1/0;
		
		return "Hello " + name;
	}
}