
public class MyMain {

	public static void main(String[] args) {
		byte b[] = new byte[100];
		java.util.Arrays.fill(b, (byte)1);
		System.out.println(new String(b));
	}

}
