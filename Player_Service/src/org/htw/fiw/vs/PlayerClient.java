package src.org.htw.fiw.vs;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class PlayerClient implements Remote {
	
	//we need to think of the registry, because we can not create the same registry in both Heartbeat_Client and Player_Service
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
