package src.org.htw.fiw.vs;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class PlayerClient implements Remote {
	
	public static void main(String[] args) {
		try {
			PlayerImpl player = new PlayerImpl();
			Registry registry = LocateRegistry.createRegistry(8080);
			registry.bind("Player", player);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
