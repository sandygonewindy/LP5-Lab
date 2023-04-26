import java.rmi.*;

public class AddClient {
	public static void main(String args[]) {
		try {
			String addServerUrl = "rmi://" + args[0] + "/AddServer";
			AddServerIntf addServerIntf = (AddServerIntf)Naming.lookup(addServerUrl);
			System.out.println("The 1st number is: " + args[1]);
			double d1 = Double.valueOf(args[1]).doubleValue();
			System.out.println("The 2nd number is: " + args[2]);
			double d2 = Double.valueOf(args[2]).doubleValue();
			System.out.println("The sum is : " + addServerIntf.add(d1, d2));
		}catch(Exception e) {
			System.out.println("Exception is " + e);
		}
	}
}
