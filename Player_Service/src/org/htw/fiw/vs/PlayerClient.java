package src.org.htw.fiw.vs;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;

public class PlayerClient implements Remote {
	
	public static void main(String[] args) throws AlreadyBoundException {
		Registry registry = null;
		try {
			PlayerImpl player = new PlayerImpl();
			try {
				//creates Registry if not there
				registry = LocateRegistry.createRegistry(8080);
				registry.bind("Player", player);
				System.out.println("created 8080 Registry");
				
			} catch (ExportException e) {
				e.printStackTrace();
			}
			//gets Registry which already exists or which was just created from HeartBeatClient
			System.out.println("Binding to already existing registry....");
			registry = LocateRegistry.getRegistry(8080);
			registry.list();
			registry.rebind("Player", player);
			System.out.println("located and binded to 8080 Registry");	
			//player.startMusic();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}

}

