package myServer;
import myInterface.MyInterface;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import org.neo4j.driver.v1.*;

import static org.neo4j.driver.v1.Values.parameters;


public class MyServer extends UnicastRemoteObject implements MyInterface {
	
	public MyServer() throws RemoteException{
		
	}
	public static String[] dataNames = new String[5];
	@Override
	public String[] dataNames() {
		return dataNames;
	}
	public int length(String text) {
		
		try {
		return text.length();
		
		}catch(Exception e) {
		return 0;
		}
	}
public static void main(String[] args) {
		try {
		Example example = new Example("bolt://localhost:11002","test","123");
		example.addPerson("nikko123");
		example.printPeople("n");
		dataNames = example.getPeople("n");
		example.close();
		}catch(Exception e) {
			
		
			e.printStackTrace();
		}
		
		
		try {
			Registry reg = LocateRegistry.createRegistry(1023);
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
