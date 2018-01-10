package src.org.htw.fiw.vs;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.htw.fiw.vs.IBinder;

public class PlayerClient implements Remote {
	
	public static void main(String[] args) throws AlreadyBoundException, NotBoundException {
		try {			
			PlayerImpl player = new PlayerImpl();
			//Registry registry = LocateRegistry.getRegistry("141.45.152.61", 1099);
			Registry registry = LocateRegistry.getRegistry(args[0], Integer.parseInt(args[1]));
			IBinder binder = (IBinder) registry.lookup("binder");
					
			binder.bind("team3/PlayerService", player);	
			System.out.println("located and binded to Registry");				
		} catch (RemoteException e) {
			e.printStackTrace();
		}		
	}
}