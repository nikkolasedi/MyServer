package myServer;
import myInterface.MyInterface;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MyServer extends UnicastRemoteObject implements MyInterface {
	
	public MyServer() throws RemoteException{
		
	}
	@Override
	public int length(String text) {
		
		try {
		return text.length();
		
		}catch(Exception e) {
		return 0;
		}
	}
public static void main(String[] args) {
		
		try {
			Registry reg = LocateRegistry.createRegistry(1022);
			MyServer c = new MyServer();
			reg.rebind("myRMI", c);
			
			System.out.println("server is ready...");
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("server is failed...");
		}
	}
}
