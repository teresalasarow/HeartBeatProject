package src.org.htw.fiw.vs;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SpotifyClient {
	public SpotifyClient() {
		try
		{
		  LocateRegistry.createRegistry( Registry.REGISTRY_PORT );
		}
		catch ( RemoteException e )  {
			System.out.println(e);
		}
		
		try {
			SpotifyService ss = new SpotifyServiceImpl();
			Naming.rebind("//localhost:1099/SpotifyService", ss);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void main(String args[]) {
		new SpotifyClient();
	}
}
