package all;

public class ObjectExample {

	public static void main(String args) {
		AfricanLion africanLion = new AfricanLion(true, 5);
		// Implicit casting
		@SuppressWarnings("unused")
		Object object = africanLion;
	}

}
